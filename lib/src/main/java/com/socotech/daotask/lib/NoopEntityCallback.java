package com.socotech.daotask.lib;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/4/13
 * Time: 7:12 AM
 */
public class NoopEntityCallback<T> implements TaskCallback<T> {
    @Override
    @Deprecated
    public void kill() {
        // noop
    }

    @Override
    public void after() {
        // noop
    }

    @Override
    public void before() {
        // noop
    }

    @Override
    public void success(T t) {
        // noop
    }

    @Override
    public void failure(String... errors) {
        // noop
    }

    @Override
    public void retry(long delay) {
        // noop
    }

    @Override
    public void exception(Exception e) {
        // noop
    }
}
