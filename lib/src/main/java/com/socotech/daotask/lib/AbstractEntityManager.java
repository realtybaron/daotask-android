package com.socotech.daotask.lib;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/11/14
 * Time: 7:59 AM
 */
public class AbstractEntityManager<F, T> implements EntityManager<F, T> {
    @Override
    public void add(F input, TaskCallback<T> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void get(Object id, TaskCallback<T> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(F input, TaskCallback<T> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(T arg, TaskCallback<T> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void find(Integer startIndex, Integer maxResults, TaskCallback<List<T>> callback) {
        throw new UnsupportedOperationException();
    }
}
