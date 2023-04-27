drop table BOARD CASCADE CONSTRAINTS purge;


//PROJECT 테이블의  (PROJECT_ID) 속성 필요
//BOARD_DIVISION - 게시판 구분 시퀀스 ( 1.일반글 / 2.업무 / 3. 캘린더 )
CREATE TABLE BOARD(
	 	BOARD_NUM		 	NUMBER PRIMARY KEY,
		COMPANY_ID		 	NUMBER references COMPANY(COMPANY_ID),
		BOARD_DIVISION	 	            NUMBER ,
		WRITER			  	VARCHAR2(30),
		SUBJECT			  	VARCHAR2(100),
		CONTENT		 	 VARCHAR2(4000),
		STATUS			  	VARCHAR2(10) NULL,
		PRIORITY		 	 	VARCHAR2(10) NULL,
		START_DATE		 	DATE NULL,
		END_DATE		 	DATE NULL,
		WRITE_DATE		 	TIMESTAMP default CURRENT_DATE,
		MODIFY_DATE		 	DATE NULL,
		PROGRESS			 NUMBER NULL,
		FILE_NAME		 	VARCHAR2(60) NULL,
		ORIGINAL_FILE_NAME 	VARCHAR2(60) NULL,
		READCOUNT		 	NUMBER NULL,
		BOARD_RE_REF	 	NUMBER NULL,
		BOARD_RE_LEV	 	NUMBER NULL,
		BOARD_RE_SEQ	 	NUMBER NULL
);


//테이블수정
alter table  board  modify STATUS varchar2(30);
alter table  board  modify PRIORITY varchar2(30);
alter table  board  RENAME column PROGRSS to PROGRESS ;
alter table board modify subject varchar2(100) ;
alter table board RENAME column PROJECT_ID to COMPANY_ID;
alter table board modify COMPANY_ID references COMPANY(COMPANY_ID) ;

//삭제
delete  from projectmanagers;
delete from board;
commit;

