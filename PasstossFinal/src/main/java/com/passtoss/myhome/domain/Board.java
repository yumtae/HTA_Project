package com.passtoss.myhome.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Board {
	
	private int BOARD_NUM;
	private int  PROJECT_ID;		 
	private int  COMPANY_ID;		 
	private int  BOARD_DIVISION	;
	private String  WRITER;			 	
	private String  USERNAME;			 	
	private String SUBJECT;			 
	private String CONTENT;			 	
	private String STATUS;			 
	private String PRIORITY;		 	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date START_DATE;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")		 
	private Date END_DATE;		 
	private Date  WRITE_DATE;		 	
	private Date MODIFY_DATE;		 	
	private int PROGRESS;			 	
	private String FILE_NAME;		 	
	private String ORIGINAL_FILE_NAME; 	
	private int READCOUNT; 	
	private int BOARD_RE_REF; 	
	private int BOARD_RE_LEV;	 	
	private int BOARD_RE_SEQ;
	

	private MultipartFile UPLOADFILE;
	
	private List<ProjectManagers> projectManagersList;
	
	private List<Board> subBoardList;
	
	private List<Member> getMemberInfo;
	
	private List<Comment> getCommentList;
	
	 
	
	SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
	 
	
	
	
	
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public int getCOMPANY_ID() {
		return COMPANY_ID;
	}
	public void setCOMPANY_ID(int cOMPANY_ID) {
		COMPANY_ID = cOMPANY_ID;
	}
	public List<Comment> getGetCommentList() {
		return getCommentList;
	}
	public void setGetCommentList(List<Comment> getCommentList) {
		this.getCommentList = getCommentList;
	}
	public List<Member> getGetMemberInfo() {
		return getMemberInfo;
	}
	public void setGetMemberInfo(List<Member> getMemberInfo) {
		this.getMemberInfo = getMemberInfo;
	}
	
	
	
	public List<Board> getSubBoardList() {
		return subBoardList;
	}
	public void setSubBoardList(List<Board> subBoardList) {
		this.subBoardList = subBoardList;
	}
	public List<ProjectManagers> getProjectManagersList() {
		return projectManagersList;
	}
	public void setProjectManagersList(List<ProjectManagers> projectManagersList) {
		this.projectManagersList = projectManagersList;
	}
	public MultipartFile getUPLOADFILE() {
		return UPLOADFILE;
	}
	public void setUPLOADFILE(MultipartFile uPLOADFILE) {
		UPLOADFILE = uPLOADFILE;
	}
	
	public int getPROGRESS() {
		return PROGRESS;
	}
	public void setPROGRESS(int pROGRESS) {
		PROGRESS = pROGRESS;
	}

	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public int getPROJECT_ID() {
		return PROJECT_ID;
	}
	public void setPROJECT_ID(int pROJECT_ID) {
		PROJECT_ID = pROJECT_ID;
	}
	public int getBOARD_DIVISION() {
		return BOARD_DIVISION;
	}
	public void setBOARD_DIVISION(int bOARD_DIVISION) {
		BOARD_DIVISION = bOARD_DIVISION;
	}
	public String getWRITER() {
		return WRITER;
	}
	public void setWRITER(String wRITER) {
		WRITER = wRITER;
	}
	public String getSUBJECT() {
		return SUBJECT;
	}
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	
	
	public String getPRIORITY() {
		return PRIORITY;
	}
	public void setPRIORITY(String pRIORITY) {
		PRIORITY = pRIORITY;
	}
	public Date getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(Date sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public Date getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(Date eND_DATE) {
		END_DATE = eND_DATE;
	}
	public String getWRITE_DATE() {
		return simpleFormat.format(WRITE_DATE);
	}
	public void setWRITE_DATE(Date wRITE_DATE) {
		WRITE_DATE = wRITE_DATE;
	}
	public String getMODIFY_DATE() {
		if (MODIFY_DATE != null) {
	        return simpleFormat.format(MODIFY_DATE);
	    } else {
	        return "";
	    }
	}
	public void setMODIFY_DATE(Date mODIFY_DATE) {
		MODIFY_DATE = mODIFY_DATE;
	}
	
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public String getORIGINAL_FILE_NAME() {
		return ORIGINAL_FILE_NAME;
	}
	public void setORIGINAL_FILE_NAME(String oRIGINAL_FILE_NAME) {
		ORIGINAL_FILE_NAME = oRIGINAL_FILE_NAME;
	}
	public int getREADCOUNT() {
		return READCOUNT;
	}
	public void setREADCOUNT(int rEADCOUNT) {
		READCOUNT = rEADCOUNT;
	}
	public int getBOARD_RE_REF() {
		return BOARD_RE_REF;
	}
	public void setBOARD_RE_REF(int bOARD_RE_REF) {
		BOARD_RE_REF = bOARD_RE_REF;
	}
	public int getBOARD_RE_LEV() {
		return BOARD_RE_LEV;
	}
	public void setBOARD_RE_LEV(int bOARD_RE_LEV) {
		BOARD_RE_LEV = bOARD_RE_LEV;
	}
	public int getBOARD_RE_SEQ() {
		return BOARD_RE_SEQ;
	}
	public void setBOARD_RE_SEQ(int bOARD_RE_SEQ) {
		BOARD_RE_SEQ = bOARD_RE_SEQ;
	}	 	
	
	@Override
	public String toString() {
		return "Board [BOARD_NUM=" + BOARD_NUM + ", PROJECT_ID=" + PROJECT_ID + ", BOARD_DIVISION=" + BOARD_DIVISION
				+ ", WRITER=" + WRITER + ", SUBJECT=" + SUBJECT + ", CONTENT=" + CONTENT + ", STATUS=" + STATUS
				+ ", PRIORITY=" + PRIORITY + ", START_DATE=" + START_DATE + ", END_DATE=" + END_DATE + ", WRITE_DATE="
				+ WRITE_DATE + ", MODIFY_DATE=" + MODIFY_DATE + ", PROGRESS=" + PROGRESS + ", FILE_NAME=" + FILE_NAME
				+ ", ORIGINAL_FILE_NAME=" + ORIGINAL_FILE_NAME + ", READCOUNT=" + READCOUNT + ", BOARD_RE_REF="
				+ BOARD_RE_REF + ", BOARD_RE_LEV=" + BOARD_RE_LEV + ", BOARD_RE_SEQ=" + BOARD_RE_SEQ + ", UPLOADFILE="
				+ UPLOADFILE + ", simpleFormat=" + simpleFormat + "]";
	}	 	
	
	
	
}
