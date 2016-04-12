package com.socotech.daotask.lib;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/3/13
 * Time: 2:23 PM
 */
public interface TaskCallback<T> {
    /**
     * Kill the task
     */
    @Deprecated
    void kill();

    /**
     * Called on UI thread after execution *regardless of outcome*
     */
    void after();

    /**
     * Called on UI thread before retrying task
     *
     * @param delay amount of milliseconds before retry is attempted
     */
    void retry(long delay);

    /**
     * Called on UI thread before execution
     */
    void before();

    /**
     * Called on UI thread after a execution success
     */
    void success(T t);

    /**
     * Called on UI thread after a execution failure
     */
    void failure(String... errors);

    /**
     * Called on UI thread after an execution exception
     */
    void exception(Exception e);
}
