package com.devil.domain;

import java.util.Date;

public class Comment {
  private int no;
  private User writer;
  private String content;
  private Date createdDate;
  private int ord;
  private int step;
  private String updatedDate;
  private String deletedDate;
  private boolean status;
  private boolean selected;

  public String getUpdatedDate() {
    return updatedDate;
  }
  public void setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
  }
  public String getDeletedDate() {
    return deletedDate;
  }
  public void setDeletedDate(String deletedDate) {
    this.deletedDate = deletedDate;
  }
  public boolean isStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }
  public boolean isSelected() {
    return selected;
  }
  public void setSelected(boolean selected) {
    this.selected = selected;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}


}
