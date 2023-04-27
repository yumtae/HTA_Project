drop table member cascade constraints purge;

create table member(
	id			varchar2(50) primary key,					-- 아이디
	password	varchar2(60),								-- 비밀번호 암호화 60자
	username	varchar2(30),								-- 이름	
	join_date	date,		 								-- 가입 일자
	join_type	number(1) check (join_type in (0,1,2)),		-- 0일반, 1네이버, 2카카오
	belong		number(1) check (belong in (0,1,2)),		-- 0승인대기, 1소속, 2거절
	company_id	number(10) references company(company_id) on delete cascade,	-- 참조 회사 
	dept		varchar2(30),								-- 부서이름
	position	varchar2(20),								-- 직책
	phone		varchar2(20),								-- 핸드폰 번호
	profile_img	varchar2(50),								-- 프로필 이미지
	auth		varchar2(50) not null, -- 권한 지정 관리자는 ADMIN, 회사 대표 회원은 MANAGER, 일반회원 MEMBER
	auth_status		number(1)	-- 이메일 인증 여부
	status	varchar2(30) DEFAULT 'online'  -- 채팅 접속 상태 
);

insert into member values('testid1@naver.com','1234','username1','2000-01-01',1,1,1,1,1,1,UTL_RAW.CAST_TO_RAW('orange.png'),0,1);
insert into member values('testid2','1234','username2','2000-01-01',1,1,1,1,1,1,UTL_RAW.CAST_TO_RAW('lemon.png'),0,1);
insert into member values('testid3','1234','username3','2000-01-01',1,1,1,1,1,1,UTL_RAW.CAST_TO_RAW('graph.png'),0,1);
insert into member values('testid4','1234','username4','2000-01-01',1,1,1,1,1,1,UTL_RAW.CAST_TO_RAW('melon.png'),0,1);


drop table emailAuth cascade constraints purge;

create table mailAuth(
	id 			varchar2(50) primary key,
	authNum	 	varchar2(10),
	sendTime	 date
);