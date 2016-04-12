package com.socotech.daotask.lib;

import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 3/16/14
 * Time: 12:06 PM
 */
public class ContextCallback<T> extends AdaptiveCallback<T> {
    private WeakReference<Context> context;

    public ContextCallback(Context context, TaskCallback<T> adapted) {
        super(adapted);
        // init a weak reference to context
        this.context = new WeakReference<>(context);
    }

    @Override
    public void kill() {
        context.clear();
    }

    @Override
    public void after() {
        if (isReadyState()) {
            super.after();
        } else {
            Log.d(LogTag.DATA, "Context is not available");
        }
    }

    @Override
    public void before() {
        if (isReadyState()) {
            super.before();
        } else {
            Log.d(LogTag.DATA, "Context is not available");
        }
    }

    @Override
    public void success(T t) {
        if (isReadyState()) {
            super.success(t);
        } else {
            Log.d(LogTag.DATA, "Context is not available");
        }
    }

    @Override
    public void failure(String... errors) {
        if (isReadyState()) {
            super.failure(errors);
        } else {
            Log.d(LogTag.DATA, "Context is not available");
        }
    }

    private boolean isReadyState() {
        return this.context.get() != null;
    }
}
