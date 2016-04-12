package com.socotech.daotask.lib;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 8/3/13
 * Time: 8:29 PM
 */
public class EntityPager {
  public Integer maxResults;
  public Integer startIndex;

  public EntityPager() {
    // noop
  }

  public EntityPager(int maxResults, int startIndex) {
    this.maxResults = maxResults;
    this.startIndex = startIndex;
  }
}
