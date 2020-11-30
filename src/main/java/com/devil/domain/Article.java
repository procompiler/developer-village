package com.devil.domain;

import java.util.Date;
import java.util.List;

public class Article {
  private int no;
  private User writer;
  private int categoryNo;
  private String title;
  private String content;
  private Date createdDate;
  private int viewCount;
  private Date updatedDate;
  private Date deletedDate;
  private int status;
  private int studyStatus;
  private Date endDate;
  private List<Comment> comments;
  private List<Tag> tags;



  public int getStudyStatus() {
    return studyStatus;
  }
  public void setStudyStatus(int studyStatus) {
    this.studyStatus = studyStatus;
  }
  public Date getDeletedDate() {
    return deletedDate;
  }
  public void setDeletedDate(Date deletedDate) {
    this.deletedDate = deletedDate;
  }
  public Date getUpdatedDate() {
    return updatedDate;
  }
  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public User getWriter() {
    return writer;
  }
  public void setWriter(User writer) {
    this.writer = writer;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
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
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public List<Comment> getComments() {
    return comments;
  }
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public List<Tag> getTags() {
    return tags;
  }
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }
}



