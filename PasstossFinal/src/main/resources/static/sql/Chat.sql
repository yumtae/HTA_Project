drop  table  chat CASCADE CONSTRAINTS purge;

create table chat_member(
 roomNumber 	NUMBER,
 ID		     VARCHAR2(50)
);

create table chat_log(
 roomNumber		 NUMBER, -- 나중에 PRIMARY KEY 
 CHAT_DATE		 date,
 CHAT_SENDER	 VARCHAR2(50),
 CHAT_ID		 VARCHAR2(50), -- 본인확인용 
 CHAT_CONTENT	 VARCHAR2(2000),
 CHAT_FILE		 VARCHAR2(100),
 CHAT_ORIGINAL	 VARCHAR2(100),
 CHAT_PROFILE	 VARCHAR2(50)
);


