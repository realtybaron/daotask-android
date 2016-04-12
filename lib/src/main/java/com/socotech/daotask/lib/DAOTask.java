package com.socotech.daotask.lib;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 5/5/14
 * Time: 2:32 PM
 */
public class DAOTask<F, T> extends AsyncTask<F, Void, Optional<T>> {
    private F[] params;
    private long retryDelay = 500;
    private Exception exception;
    private Function<F, T> function;
    private TaskCallback<T> callback;
    private AtomicInteger retryCount = new AtomicInteger();

    /**
     * Set function
     *
     * @param function function
     * @return this task
     */
    public DAOTask<F, T> function(Function<F, T> function) {
        this.function = function;
        return this;
    }

    /**
     * Set callback
     *
     * @param callback callback
     * @return this task
     */
    public DAOTask<F, T> callback(TaskCallback<T> callback) {
        this.callback = callback;
        return this;
    }

    /**
     * Set both retry count and delay
     *
     * @param count retry count
     * @param delay retry delay
     * @return this task
     */
    public DAOTask<F, T> retry(int count, long delay) {
        this.retryDelay = delay;
        this.retryCount.set(count);
        return this;
    }

    /**
     * Set retry count
     *
     * @param i count
     */
    public DAOTask<F, T> retryCount(int i) {
        this.retryCount.set(i);
        return this;
    }

    /**
     * Set retry delay
     *
     * @param l delay
     */
    public DAOTask<F, T> retryDelay(long l) {
        this.retryDelay = l;
        return this;
    }

    /**
     * Perform task
     *
     * @param params parameters
     */
    public void perform(F... params) {
        // validate params
        Preconditions.checkNotNull(params, "Parameters cannot be null");
        Preconditions.checkArgument(params.length <= 1, "Only one parameter is allowed");
        // retain params
        this.params = params;
        // execute function
        if (getStatus() != Status.PENDING) {
            Log.w(LogTag.DATA, "Task was cancelled");
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            execute(params);
        } else {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        }
    }

    @Override
    protected Optional<T> doInBackground(F... params) {
        // validate
        Preconditions.checkNotNull(function, "Function cannot be null");
        Preconditions.checkNotNull(callback, "Callback cannot be null");
        // execute
        if (!isCancelled()) {
            try {
                if (params.length == 0) {
                    return Optional.fromNullable(function.apply(null));
                } else {
                    return Optional.fromNullable(function.apply(params[0]));
                }
            } catch (Exception e) {
                exception = e;
                Log.e(LogTag.DATA, e.getMessage(), e);
            }
        }
        // give up the ghost
        return Optional.absent();
    }

    @Override
    protected void onPreExecute() {
        if (isCancelled()) {
            Log.w(LogTag.DATA, "Task was cancelled");
        } else {
            callback.before();
        }
    }

    @Override
    protected void onPostExecute(Optional<T> result) {
        if (result.isPresent()) {
            callback.success(result.get());
        } else if (retryCount.decrementAndGet() > 0) {
            // indicate retry will be attempted
            callback.retry(retryDelay);
            // schedule another attempt
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    DAOTask<F, T> task = DAOTask.newTask();
                    task.function(function).callback(callback);
                    task.retryCount(retryCount.get()).retryDelay(retryDelay);
                    task.perform(params);
                }
            }, retryDelay);
            // exit now to avoid calling callback.after()
            return;
        } else if (exception != null) {
            callback.exception(exception);
        } else {
            callback.failure();
        }
        callback.after();
    }

    /**
     * Factory method
     *
     * @return new task
     */
    public static <F, T> DAOTask<F, T> newTask() {
        return new DAOTask<>();
    }
}
