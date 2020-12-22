package com.devil.domain;

public class BadgeStan {
  private int bsno; // 평가기준번호
  private int bno; // 뱃지번호
  private int beno; //  뱃지평가번호 1가입일 2방문횟수 3 댓글수 4게시글수
  private int count; //횟수

  public int getBno() {
    return bno;
  }
  public BadgeStan setBno(int bno) {
    this.bno = bno;
    return this;
  }
  public int getBsno() {
    return bsno;
  }
  public BadgeStan setBsno(int bsno) {
    this.bsno = bsno;
    return this;
  }
  public int getBeno() {
    return beno;
  }
  public BadgeStan setBeno(int beno) {
    this.beno = beno;
    return this;
  }
  public int getCount() {
    return count;
  }
  public BadgeStan setCount(int count) {
    this.count = count;
    return this;
  }
}
