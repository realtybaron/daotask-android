package com.socotech.daotask.lib;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 9/17/14
 * Time: 8:14 AM
 */
@Deprecated
public class V4FragmentCallback<T> implements TaskCallback<T> {
    TaskCallback<T> wrapped;
    WeakReference<Fragment> fragment;

    public V4FragmentCallback(Fragment fragment, TaskCallback<T> wrapped) {
        this.wrapped = wrapped;
        this.fragment = new WeakReference<>(fragment);
    }

    @Override
    @Deprecated
    public void kill() {
        fragment.clear();
    }

    @Override
    public void after() {
        if (isReadyState()) {
            wrapped.after();
        } else {
            Log.d(LogTag.DATA, "Fragment was not ready");
        }
    }

    @Override
    public void retry(long delay) {
        if (isReadyState()) {
            wrapped.retry(delay);
        } else {
            Log.d(LogTag.DATA, "Fragment was not ready");
        }
    }

    @Override
    public void before() {
        if (isReadyState()) {
            wrapped.before();
        } else {
            Log.d(LogTag.DATA, "Fragment was not ready");
        }
    }

    @Override
    public void success(T t) {
        if (isReadyState()) {
            wrapped.success(t);
        } else {
            Log.d(LogTag.DATA, "Fragment was not ready");
        }
    }

    @Override
    public void failure(String... s) {
        if (isReadyState()) {
            wrapped.failure(s);
        } else {
            Log.d(LogTag.DATA, "Fragment was not ready");
        }
    }

    @Override
    public void exception(Exception e) {
        if (isReadyState()) {
            wrapped.exception(e);
        } else {
            Log.d(LogTag.DATA, "Fragment was not ready");
        }
    }

    private boolean isReadyState() {
        Fragment f = fragment.get();
        return f != null && f.getActivity() != null && !f.getActivity().isFinishing() && f.isResumed();
    }
}
