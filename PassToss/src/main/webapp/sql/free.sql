drop table board_free cascade constraints purge;

create table board_free(
BOARD_NUM		NUMBER(5) 		primary key,
BOARD_NAME		VARCHAR2(30),
BOARD_SUBJECT	VARCHAR2(300),
BOARD_CONTENT	VARCHAR2(4000),
BOARD_FILE		VARCHAR2(50),
BOARD_RE_REF	NUMBER(5),
BOARD_RE_LEV	NUMBER(5) check(BOARD_RE_LEV in(0,1,2)),
BOARD_RE_SEQ	NUMBER(5),
BOARD_READCOUNT	NUMBER(5),
BOARD_DATE		DATE default sysdate,
BOARD_NOTICE	NUMBER(5) check(BOARD_NOTICE in(0,1))-- 0이면 공지 1이면 일반게시물 
);

select * from board_free;

create table comment_free(
	num					number			primary key,
	id					varchar2(30) 	references member(id),
	content				varchar2(200),
	comment_date		DATE default sysdate,
	comment_board_num	number		references board_free(board_num)on delete cascade,	--comm 테이블이 참조하는 보드 글 번호
	comment_re_lev		number(1)	check(comment_re_lev in (0,1,2)), --원문이면 0 답글이면 1 답글 2개까지
	comment_re_seq		number, -- 원문이면 0, 1레벨이면 시퀀스 +1
	comment_re_ref 		number 	--원문은 자신 글번호, 답글이면 원문 글번호
	);
	
select * from comment_free;
delete comment_free;
	
create sequence fcom_seq; -- 댓글
drop sequence fcom_seq;

create sequence fboard_seq; -- 게시판
drop sequence fboard_seq;

SELECT fboard_seq.nextval FROM DUAL;
SELECT fboard_seq.CURRVAL FROM DUAL;

insert into board_free
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT,
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(1, 'admin', '안녕1', '안녕하세요','fox.png', 1, 0, 0, 0,sysdate, 0);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(2, 'admin', '안녕2', '안녕하세요','apple.png', 2, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(3, 'admin', '안녕3', '안녕하세요','lemon.png', 3, 0, 0, 0,sysdate, 0);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(4, 'admin', '안녕4', '안녕하세요','grape.png', 4, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(5, 'admin', '안녕3', '안녕하세요','lemon.png', 5, 0, 0, 0,sysdate, 0);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(6, 'admin', '안녕4', '안녕하세요','grape.png', 6, 0, 0, 0,sysdate, 0);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(7, 'a1234', '안녕4', '안녕하세요','grape.png', 7, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(8, 'b1234', '안녕4', '안녕하세요','grape.png', 8, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(9, 'c1234', '안녕4', '안녕하세요','grape.png', 9, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(10, 'd1234', '안녕4', '안녕하세요','grape.png', 10, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(11, 'a1234', '안녕4', '안녕하세요','grape.png', 11, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(12, 'admin', '안녕4', '안녕하세요','grape.png', 12, 0, 0, 0,sysdate, 1);

insert into board_free 
(BOARD_NUM, BOARD_NAME, BOARD_SUBJECT, 
BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, 
BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT,
BOARD_DATE, BOARD_NOTICE)
values(13, 'c1234', '13번째', '안녕하세요','grape.png', 13, 0, 0, 0,sysdate, 1);




insert into comment_free (num, id, content, comment_board_num) 
values(1, 'admin', '안녕', 1);
insert into comment_free (num, id, content, comment_board_num) 
values(2, 'a1234', '안녕', 2);
insert into comment_free (num, id, content, comment_board_num) 
values(3, 'b1234', '안녕', 1);
insert into comment_free (num, id, content, comment_board_num) 
values(4, 'c1234', '안녕', 1);

-- 공지사항 글목록 
select * 
from(select rownum rnum, j.* 
     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT 
     	  FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT 
				 	           		       FROM COMMENT_FREE 
				 	          		       GROUP BY COMMENT_BOARD_NUM) 
	      ON BOARD_NUM = COMMENT_BOARD_NUM 
 	      ORDER BY BOARD_RE_REF DESC,  --최신순 
	      BOARD_RE_SEQ ASC) j 
	 where board_notice = 0
     and rownum <= 10) 
 where rnum >= 1 and rnum <= 10;
 
-- 일반게시판 글목록 
select * 
from(select rownum rnum, j.* 
     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT 
     	  FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT 
				 	           		       FROM COMMENT_FREE 
				 	          		       GROUP BY COMMENT_BOARD_NUM) 
	      ON BOARD_NUM = COMMENT_BOARD_NUM 
 	      ORDER BY BOARD_RE_REF DESC,  --최신순 
	      BOARD_RE_SEQ ASC) j 
	 where board_notice = 1
     and rownum <= 10) 
 where rnum >= 1 and rnum <= 10;

-- 전체 검색 
select * 
from(select rownum rnum, j.* 
     from(SELECT BOARD_FREE.*, NVL(CNT,0)CNT 
     	  FROM BOARD_FREE LEFT OUTER JOIN (SELECT COMMENT_BOARD_NUM,COUNT(*)CNT 
				 	           			   FROM COMMENT_FREE 
				 	            		   GROUP BY COMMENT_BOARD_NUM) 
	      ON BOARD_NUM = COMMENT_BOARD_NUM 
 	      ORDER BY BOARD_RE_REF DESC,
	      BOARD_RE_SEQ ASC) j 
	 where board_notice = 1
	 and(board_subject like '%안녕2%' or board_name like '%안녕2%')
     and rownum <= 10) 
 where rnum >= 1 and rnum <= 10;
 
 select count(*)
 from board_free 
 where board_name like '%admin%';
 
 select count(*)
 from board_free
 where(board_subject like '%안녕2%' or board_name like '%안녕2%');
 
update board_free
set board_subject= '안녕', board_content='수정됨', board_file='', board_notice =1
where board_num = 10;
 
select count(*) from comment_free 
where comment_board_num = 15