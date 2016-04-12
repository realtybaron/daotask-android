package com.socotech.daotask.lib;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 9/17/14
 * Time: 8:14 AM
 */
public class VolatileCallback<T> implements TaskCallback<T> {
    TaskCallback<T> wrapped;
    WeakReference<Volatile> component;

    public VolatileCallback(Volatile component, TaskCallback<T> wrapped) {
        this.wrapped = wrapped;
        this.component = new WeakReference<>(component);
    }

    @Override
    @Deprecated
    public void kill() {
        component.clear();
    }

    @Override
    public void after() {
        Volatile f = component.get();
        if (f != null && f.isStable()) {
            wrapped.after();
        } else {
            Log.d(LogTag.DATA, "Volatile was either null or unstable");
        }
    }

    @Override
    public void retry(long delay) {
        Volatile f = component.get();
        if (f != null && f.isStable()) {
            wrapped.retry(delay);
        } else {
            Log.d(LogTag.DATA, "Volatile was either null or unstable");
        }
    }

    @Override
    public void before() {
        Volatile f = component.get();
        if (f != null && f.isStable()) {
            wrapped.before();
        } else {
            Log.d(LogTag.DATA, "Volatile was either null or unstable");
        }
    }

    @Override
    public void success(T t) {
        Volatile f = component.get();
        if (f != null && f.isStable()) {
            wrapped.success(t);
        } else {
            Log.d(LogTag.DATA, "Volatile was either null or unstable");
        }
    }

    @Override
    public void failure(String... s) {
        Volatile f = component.get();
        if (f != null && f.isStable()) {
            wrapped.failure(s);
        } else {
            Log.d(LogTag.DATA, "Volatile was either null or unstable");
        }
    }

    @Override
    public void exception(Exception e) {
        Volatile f = component.get();
        if (f != null && f.isStable()) {
            wrapped.exception(e);
        } else {
            Log.d(LogTag.DATA, "Volatile was either null or unstable");
        }
    }
}
