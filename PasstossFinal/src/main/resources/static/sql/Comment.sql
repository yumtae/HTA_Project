drop table comments CASCADE CONSTRAINTS purge;

CREATE TABLE comments(
	num		 		number primary key,  
	id		 		varchar2(30) references member(ID),  
	content		 	varchar2(200),
	reg_date	 	date,
	board_num 		number references board(BOARD_NUM) 
					on delete cascade  
);

