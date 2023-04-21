--캘린더는 calendar 테이블과 cal_seq 시퀀스 생성 필요합니다.

drop table calendar;
 
create table calendar(

title			varchar2(50),
start1 			varchar2(50),
end1 			varchar2(50),
num				number(2)

);

drop sequence cal_seq;


create sequence cal_seq;














insert into CALENDAR values('미팅', '2023-02-07', '2023-02-08', '1');

select * from CALENDAR


           delete from calendar1 
           where start1 ='2023-02-02' and title='meeting';


 drop table calendar1;

create table calendar1(

title			varchar2(50),
start1 			varchar2(50),
end1 			varchar2(50)


);

delete from calendar
where title='Conference' and start1='2023-02-07' and end1='2023-02-08'

select * from calendar
where title='Marketing' and start1='2023-02-16' and end1='2023-02-17'

select * from CALENDAR

insert into CALENDAR1 values('미팅', '2023-02-07', '2023-02-08', cal_seq.nextval);

create sequence cal_seq1;