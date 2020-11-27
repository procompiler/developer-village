package com.devil.domain;

import java.util.Date;

public class User {
  private int no;
  private String email;
  private String nickname;
  private String name;
  private String password;
  private Date createdDate;
  private Date recentVisitedDate;
  private String loginType;
  private String photo;
  private String hompageURL;
  private String githubURL;
  private String instarURL;
  private String twitterURL;
  private boolean blocked;
  private String tech;
  private boolean auth;
  private boolean noti;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getCdt() {
    return createdDate;
  }
  public void setCdt(Date cdt) {
    this.createdDate = cdt;
  }
  public String getHompageURL() {
    return hompageURL;
  }
  public void setHompageURL(String hompageURL) {
    this.hompageURL = hompageURL;
  }
  public String getGithubURL() {
    return githubURL;
  }
  public void setGithubURL(String githubURL) {
    this.githubURL = githubURL;
  }
  public String getInstarURL() {
    return instarURL;
  }
  public void setInstarURL(String instarURL) {
    this.instarURL = instarURL;
  }
  public String getTwitterURL() {
    return twitterURL;
  }
  public void setTwitterURL(String twitterURL) {
    this.twitterURL = twitterURL;
  }
  public boolean isBlocked() {
    return blocked;
  }
  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }
  public String getTech() {
    return tech;
  }
  public void setTech(String tech) {
    this.tech = tech;
  }
  public boolean isAuth() {
    return auth;
  }
  public void setAuth(boolean auth) {
    this.auth = auth;
  }
  public boolean isNoti() {
    return noti;
  }
  public void setNoti(boolean noti) {
    this.noti = noti;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public Date getRecentVisitedDate() {
    return recentVisitedDate;
  }
  public void setRecentVisitedDate(Date recentVisitedDate) {
    this.recentVisitedDate = recentVisitedDate;
  }
  public String getLoginType() {
    return loginType;
  }
  public void setLoginType(String loginType) {
    this.loginType = loginType;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }

}
