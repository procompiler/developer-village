package com.devil.domain;

import java.util.Date;
import java.util.List;

public class Badge {
  private int no;
  private String name;
  private String photo;
  private Tag tag;
  private int state; // 활성상태 0 안보임 1 보임
  private String content;
  private Date collectDate;
<<<<<<< HEAD
  private List<BadgeStan> badgeStans;
=======
  private List<BadgeStandard> standards;
>>>>>>> e7cead434b9603a28ad04997a214b048a546bb74

  public int getState() {
    return state;
  }

  public Badge setState(int state) {
    this.state = state;
    return this;
  }

  public int getNo() {
    return no;
  }

  public Badge setNo(int no) {
    this.no = no;
    return this;
  }

  public String getName() {
    return name;
  }

  public Badge setName(String name) {
    this.name = name;
    return this;
  }

  public String getPhoto() {
    return photo;
  }

  public Badge setPhoto(String photo) {
    this.photo = photo;
    return this;
  }


  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public String getContent() {
    return content;
  }

  public Badge setContent(String content) {
    this.content = content;
    return this;
  }

  public Date getCollectDate() {
    return collectDate;
  }

  public Badge setCollectDate(Date collectDate) {
    this.collectDate = collectDate;
    return this;
  }

<<<<<<< HEAD
public List<BadgeStan> getBadgeStans() {
	return badgeStans;
}

public Badge setBadgeStans(List<BadgeStan> badgeStans) {
	this.badgeStans = badgeStans;
	return this;
}
 
=======
  public List<BadgeStandard> getStandards() {
    return standards;
  }

  public void setStandards(List<BadgeStandard> standards) {
    this.standards = standards;
  }
>>>>>>> e7cead434b9603a28ad04997a214b048a546bb74
}
