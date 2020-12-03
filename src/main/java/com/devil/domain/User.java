package com.devil.domain;

import java.util.Date;
import java.util.List;

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
  private String homepageURL;
  private String githubURL;
  private String instarURL;
  private String twitterURL;
  private int blocked;
  private String tech;
  private int auth;
  private int noti;
  private String bio;
  private int state; // 활성상태 0 안보임 1 보임
  private List<Tag> tags;
  private List<User> users;

  public int getNo() {
    return no;
  }
  public User setNo(int no) {
    this.no = no;
    return this;
  }
  public String getEmail() {
    return email;
  }
  public User setEmail(String email) {
    this.email = email;
    return this;
  }
  public String getNickname() {
    return nickname;
  }
  public User setNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }
  public String getName() {
    return name;
  }
  public User setName(String name) {
    this.name = name;
    return this;
  }
  public String getPassword() {
    return password;
  }
  public User setPassword(String password) {
    this.password = password;
    return this;
  }
  public Date getCdt() {
    return createdDate;
  }
  public User setCdt(Date cdt) {
    this.createdDate = cdt;
    return this;
  }
  public String getHomepageURL() {
    return homepageURL;
  }
  public User setHomepageURL(String homepageURL) {
    this.homepageURL = homepageURL;
    return this;
  }
  public String getGithubURL() {
    return githubURL;
  }
  public User setGithubURL(String githubURL) {
    this.githubURL = githubURL;
    return this;
  }
  public String getInstarURL() {
    return instarURL;
  }
  public User setInstarURL(String instarURL) {
    this.instarURL = instarURL;
    return this;
  }
  public String getTwitterURL() {
    return twitterURL;
  }
  public User setTwitterURL(String twitterURL) {
    this.twitterURL = twitterURL;
    return this;
  }

  public String getTech() {
    return tech;
  }

  public User setTech(String tech) {
    this.tech = tech;
    return this;
  }

  public Date getCreatedDate() {
    return createdDate;
  }
  public User setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }
  public Date getRecentVisitedDate() {
    return recentVisitedDate;
  }
  public User setRecentVisitedDate(Date recentVisitedDate) {
    this.recentVisitedDate = recentVisitedDate;
    return this;
  }
  public String getLoginType() {
    return loginType;
  }
  public User setLoginType(String loginType) {
    this.loginType = loginType;
    return this;
  }
  public String getPhoto() {
    return photo;
  }
  public User setPhoto(String photo) {
    this.photo = photo;
    return this;
  }
  public String getBio() {
    return bio;
  }
  public User setBio(String bio) {
    this.bio = bio;
    return this;
  }
  public int getBlocked() {
    return blocked;
  }
  public User setBlocked(int blocked) {
    this.blocked = blocked;
    return this;
  }
  public int getAuth() {
    return auth;
  }
  public User setAuth(int auth) {
    this.auth = auth;
    return this;
  }
  public User setNoti(int noti) {
    this.noti = noti;
    return this;
  }
  public int getNoti() {
    return noti;
  }
  public int getState() {
    return state;
  }
  public User setState(int state) {
    this.state = state;
    return this;
  }

  public List<Tag> getTags() {
    return tags;
  }
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }
  public List<User> getUsers() {
    return users;
  }
  public void setUsers(List<User> users) {
    this.users = users;
  }
}
