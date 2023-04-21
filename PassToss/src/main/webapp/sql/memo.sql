drop table memo cascade constraints purge;

create table memo(
	memo_num	number(5) 		primary key,
	id			varchar2(12) 	references member(id),
	memo		varchar2(200),
	reg_date	varchar2(12)
);

select * from memo;

create sequence memo_seq; 

drop sequence memo_seq;

drop table memo;

insert into memo values(1, 'admin', '영업팀 프로젝트 일정 수정했습니다', sysdate);
insert into memo values(2, 'admin', '마켓팅팀 프로젝트 조기 완료되어 일정 수정', sysdate);
insert into memo values(3, 'admin', '영업팀 상반기 회의 일정 일정 추가', sysdate);

select count(*) from memo