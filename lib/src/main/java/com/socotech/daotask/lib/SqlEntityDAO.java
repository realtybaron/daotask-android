package com.socotech.daotask.lib;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/29/14
 * Time: 11:27 AM
 */
public interface SqlEntityDAO<F, T> extends EntityDAO<F, T> {
    public DatabaseHelper getDatabaseHelper();
}
