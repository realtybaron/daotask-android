package com.socotech.daotask.lib;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 3/16/14
 * Time: 12:06 PM
 */
@Deprecated
public class ActivityCallback<T> extends AdaptiveCallback<T> {
    private WeakReference<Activity> activity;

    public ActivityCallback(Activity activity, TaskCallback<T> adapted) {
        super(adapted);
        // init a weak reference to activity
        this.activity = new WeakReference<>(activity);
    }

    @Override
    public void kill() {
        activity.clear();
    }

    @Override
    public void after() {
        if (isReadyState()) {
            super.after();
        } else {
            Log.d(LogTag.DATA, "Activity was not ready");
        }
    }

    @Override
    public void before() {
        if (isReadyState()) {
            super.before();
        } else {
            Log.d(LogTag.DATA, "Activity was not ready");
        }
    }

    @Override
    public void success(T t) {
        if (isReadyState()) {
            super.success(t);
        } else {
            Log.d(LogTag.DATA, "Activity was not ready");
        }
    }

    @Override
    public void failure(String... errors) {
        if (isReadyState()) {
            super.failure(errors);
        } else {
            Log.d(LogTag.DATA, "Activity was not ready");
        }
    }

    private boolean isReadyState() {
        Activity a = this.activity.get();
        return a != null && !a.isFinishing();
    }
}
