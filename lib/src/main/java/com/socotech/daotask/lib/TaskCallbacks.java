package com.socotech.daotask.lib;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 7/4/15
 * Time: 9:32 AM
 */
public class TaskCallbacks {
    /**
     * Kill, kill, kill!
     *
     * @param callbacks task callbacks
     */
    @Deprecated
    public static void kill(TaskCallback... callbacks) {
        for (TaskCallback callback : callbacks) {
            callback.kill();
        }
    }
}
