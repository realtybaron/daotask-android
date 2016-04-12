package com.socotech.daotask.lib;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/3/13
 * Time: 2:23 PM
 */
public interface EntityManager<F, T> {
    public void add(F input, TaskCallback<T> callback);

    public void get(Object id, TaskCallback<T> callback);

    public void update(F arg, TaskCallback<T> callback);

    public void delete(T arg, TaskCallback<T> callback);

    public void find(Integer startIndex, Integer maxResults, TaskCallback<List<T>> callback);
}
