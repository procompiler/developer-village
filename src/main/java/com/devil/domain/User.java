package com.devil.domain;

import java.sql.Date;
import java.util.Map;

public class User {
  private int no;
  private String email;
  private String nickname;
  private String name;
  private String password;
  private Date createdDate;
  private Map<String, String> url;
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
  public Map<String, String> getUrl() {
    return url;
  }
  public void setUrl(Map<String, String> url) {
    this.url = url;
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
}
