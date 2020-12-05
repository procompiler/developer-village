package com.devil.domain;

import java.util.Date;
import java.util.List;

public class Article {
  private int no;
  private User writer;
  private int categoryNo; // 1 커뮤니티 2 QnA 3 채용공고 4 스터디
  private String title;
  private String content;
  private Date createdDate;
  private int viewCount;
  private Date updatedDate;
  private Date deletedDate;
  private int state;
  private int studyStatus;
  private Date endDate;
  private List<Comment> comments;
  private List<Tag> tags;

  public int getStudyStatus() {
    return studyStatus;
  }
  public Article setStudyStatus(int studyStatus) {
    this.studyStatus = studyStatus;
    return this;
  }
  public Date getDeletedDate() {
    return deletedDate;
  }
  public Article setDeletedDate(Date deletedDate) {
    this.deletedDate = deletedDate;
    return this;
  }
  public Date getUpdatedDate() {
    return updatedDate;
  }
  public Article setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
    return this;
  }
  public int getNo() {
    return no;
  }
  public Article setNo(int no) {
    this.no = no;
    return this;
  }
  public User getWriter() {
    return writer;
  }
  public Article setWriter(User writer) {
    this.writer = writer;
    return this;
  }
  public String getTitle() {
    return title;
  }
  public Article setTitle(String title) {
    this.title = title;
    return this;
  }
  public String getContent() {
    return content;
  }
  public Article setContent(String content) {
    this.content = content;
    return this;
  }
  public int getCategoryNo() {
    return categoryNo;
  }
  public void setCategoryNo(int categoryNo) {
    this.categoryNo = categoryNo;
  }
  public Date getEndDate() {
    return endDate;
  }
  public Article setEndDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }
  public int getState() {
    return state;
  }
  public Article setState(int state) {
    this.state = state;
    return this;
  }
  public List<Comment> getComments() {
    return comments;
  }
  public Article setComments(List<Comment> comments) {
    this.comments = comments;
    return this;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public Article setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }
  public int getViewCount() {
    return viewCount;
  }
  public Article setViewCount(int viewCount) {
    this.viewCount = viewCount;
    return this;
  }
  public List<Tag> getTags() {
    return tags;
  }
  public Article setTags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }
}


