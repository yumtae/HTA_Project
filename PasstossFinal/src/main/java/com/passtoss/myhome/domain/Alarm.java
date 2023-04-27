package com.passtoss.myhome.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;

public class Alarm {
	private int SEQ;
	private int BOARD_NUM;
	private String ID;
	private String SUBJECT;
	private int READ_YN;
	  
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date WRITE_DATE;

	public void setWRITE_DATE(Date WRITE_DATE) {
	    this.WRITE_DATE = WRITE_DATE;
	}

	public String getFormattedDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return dateFormat.format(WRITE_DATE);
	}
	
	public String getSUBJECT() {
		return SUBJECT;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public int getSEQ() {
		return SEQ;
	}
	public void setSEQ(int sEQ) {
		SEQ = sEQ;
	}
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getREAD_YN() {
		return READ_YN;
	}
	public void setREAD_YN(int rEAD_YN) {
		READ_YN = rEAD_YN;
	}

	
	
	
	
	
	
}
