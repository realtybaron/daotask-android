package com.socotech.daotask.lib;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 9/2/13
 * Time: 7:09 AM
 */
public class AdaptiveCallback<T> implements TaskCallback<T> {
    TaskCallback<T> adapted;

    public AdaptiveCallback(TaskCallback<T> adapted) {
        this.adapted = adapted;
    }

    @Override
    @Deprecated
    public void kill() {
        adapted.kill();
    }

    @Override
    public void after() {
        adapted.after();
    }

    @Override
    public void retry(long delay) {
        adapted.retry(delay);
    }

    @Override
    public void before() {
        adapted.before();
    }

    @Override
    public void success(T t) {
        adapted.success(t);
    }

    @Override
    public void failure(String... errors) {
        adapted.failure(errors);
    }

    @Override
    public void exception(Exception e) {
        adapted.exception(e);
    }
}
