drop table member cascade constraints purge

create table dept(
	deptno	number(5) primary key,
	dname	varchar2(20)
)

create table member(
	id			varchar2(12) primary key,			-- 아이디
	password	varchar2(10),						-- 비밀번호
	name		varchar2(15),						-- 이름
	jumin		varchar2(14),						-- 주민번호
	deptno		number(2) references dept(deptno),	-- 부서번호
	email		varchar2(30),						-- 이메일
	phone		varchar2(15),						-- 연락처
	address		varchar2(150),						-- 주소
	post		number,								-- 우편번호
	profileImg	varchar2(50)						-- 프로필이미지
	joindate	sysdate								-- 가입날짜
	authority	number(1) check(authority in (0,1,2)) -- 권한	
)

alter table member add profileImg varchar2(50);		-- 프로필사진 경로

alter table  member modify authority default 0; -- 회원가입 시 기본값 0(준회원)

alter table member add joindate date default sysdate; -- 가입날짜

alter table member add POST number; -- 우편번호

select * from member;

update member
set authority = 2
where id='admin'

delete member
where id='P1234'

insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('admin','1234','Y','560555-1234567',0,'admin@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',2,'',sysdate);

insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('A1234','1234','a','560555-1234567',10,'a1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('B1234','1234','b','560555-2234567',20,'b1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('C1234','1234','c','560555-1234567',30,'c1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('D1234','1234','d','560555-2234567',40,'d1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('E1234','1234','e','560555-1234567',10,'e1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('F1234','1234','f','560555-2234567',20,'f1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('G1234','1234','g','560555-1234567',30,'g1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('H1234','1234','h','560555-2234567',40,'h1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('I1234','1234','i','560555-1234567',10,'i1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('J1234','1234','j','560555-2234567',20,'j1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('K1234','1234','k','560555-1234567',30,'k1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('L1234','1234','l','560555-2234567',40,'l1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('M1234','1234','m','560555-1234567',10,'m1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('N1234','1234','n','560555-2234567',20,'n1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('O1234','1234','o','560555-1234567',30,'o1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);
insert into member (id,password,name,jumin,deptno,email,phone,address,post,authority,profileImg,joindate) 
values('P1234','1234','p','560555-1234567',40,'p1234@admin.com','010-0000-0000','경기도 고양시 일산서구','123456',0,'',sysdate);


delete dept

insert into dept
values(0,'관리자');

insert into dept
values(10, '1팀');

insert into dept
values(20, '2팀');

insert into dept
values(30, '3팀');

insert into dept
values(40, '4팀');

select * from dept
