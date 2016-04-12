package com.socotech.daotask.lib;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/3/13
 * Time: 2:19 PM
 */
public interface EntityDAO<F, T> {
    public T add(F arg);

    public T get(Integer id);

    public T update(F arg);

    public void delete(T arg);

    public void addAll(F... arg);

    public void updateAll(F... arg);

    public List<T> find(String query, String... args);

    public List<T> find(Integer startIndex, Integer maxResults);
}
