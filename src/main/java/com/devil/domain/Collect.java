package com.devil.domain;

import java.util.Date;

public class Collect {
  User user;
  Badge badge;
  Date collectedDate;
  int order;
  
  public User getUser() {
    return user;
  }
  public Collect setUser(User user) {
    this.user = user;
    return this;
  }
  public Badge getBadge() {
    return badge;
  }
  public Collect setBadge(Badge badge) {
    this.badge = badge;
    return this;
  }
  public Date getCollectedDate() {
    return collectedDate;
  }
  public Collect setCollectedDate(Date collectedDate) {
    this.collectedDate = collectedDate;
    return this;
  }
  public int getOrder() {
    return order;
  }
  public void setOrder(int order) {
    this.order = order;
  }
}
