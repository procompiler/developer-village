package com.devil.domain;

public class BadgeStan {
  private int bno;
  private int bsno; // 활성상태 0 안보임 1 보임
  private int beno; // 활성상태 0 안보임 1 보임
  private int count; // 활성상태 0 안보임 1 보임
  
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
