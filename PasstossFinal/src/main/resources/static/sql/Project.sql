drop table project cascade constraints purge;

create table project(
	project_id		number(10) primary key,	-- 프로젝트 아이디
	project_name	varchar2(50),	-- 프로젝트 이름
	description		varchar2(1500),	-- 프로젝트 설명
	admin_auth		number(1) check (admin_auth in (0,1)), -- 관리자 승인 후 가입 설정
	write_auth		number(1) check (write_auth in (0,1)), -- 게시물 작성 권한
	comment_auth	number(1) check (comment_auth in (0,1)), -- 댓글 작성 권한
	file_auth		number(1) check (file_auth in (0,1)),	-- 파일 다운로드 권한
	company_id		number(10) references company(company_id), -- 참조 회사
	create_date		date									-- 생성날짜
);

drop table project_member cascade constraints;

create table project_member(
	project_id	 number(10) 		 references project(project_id), -- 참조 프로젝트
	id	 		varchar2(30)	 references member(id) -- 참조 회원
);

insert into project
values(1,'첫번째프로젝트','첫번째입니다.',0,0,0,0,1,sysdate);
insert into project_member
values(1,'duawodud66@naver.com');

insert into project
values(2,'두번째프로젝트','두번째입니다.',0,0,0,0,1,sysdate);
insert into project_member
values(2,'duawodud66@naver.com');
insert into project_member
values(2,'duawodud66@hanmail.net');

insert into project
values(3,'세번째프로젝트','세번째입니다.',0,0,0,0,1,sysdate);
insert into project_member
values(3,'duawodud66@naver.com');
