drop table company cascade constraints purge;

create table company(
	company_id	 	number(10) primary key,						-- 회사id
	company_name	 varchar2(30),								-- 회사이름
	ceo_id		 	varchar2(50),								-- 대표자id references member(id)
	url		 		varchar2(100),								-- 회사 url주소
	type		 	varchar2(100),								-- 회사 업종
	logo		 	varchar2(50),								-- 회사 로고
	access_option 	number(1) check (access_option in (0,1)),	-- 관리자 승인후 가입 옵션(0-승인후가입,1-url주소로 바로가입)
	create_date	 	date										-- 생성 일자
);