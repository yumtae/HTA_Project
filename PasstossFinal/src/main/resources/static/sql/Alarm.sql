drop table Alarm_log CASCADE CONSTRAINTS purge;


CREATE TABLE ALARM_LOG(
	BOARD_NUM 		number, 
	WRITER  varchar2(200),
	ID 	varchar2(200),
	SUBJECT    varchar2(30),
	READ_YN 	number,
	WRITE_DATE	 		date
);



