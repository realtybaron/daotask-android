package com.socotech.daotask.lib;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/29/14
 * Time: 11:32 AM
 */
public abstract class AbstractWebEntityDAO<F, T> implements EntityDAO<F, T> {
    @Override
    public void addAll(F... arg) {
        for (F f : arg) {
            add(f);
        }
    }

    @Override
    public void updateAll(F... arg) {
        for (F f : arg) {
            update(f);
        }
    }
}
