package com.devil;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Table {
  public static void main(String[] args) throws Exception {
	Date date = new Date();
	String from = "2018-09-20 15:27:27";
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date to = transFormat.parse(from);
	Time time = Time.valueOf("15:27:27");
	
	System.out.println(time);
    System.out.println(to.toString());
  }
}
