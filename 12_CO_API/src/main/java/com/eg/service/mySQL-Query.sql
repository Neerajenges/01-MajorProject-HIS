show databases;
use jrtp;
show tables;

select * from citizens_app;
select * from dc_cases;
select * from dc_children;
select * from dc_education;
select * from dc_income;
select * from plan_master;
select * from plane_category;

select * from eligibility_dtls;
select * from co_triggers;

update dc_cases set plan_id=1 where cse_num=1;
insert into dc_cases values(1,1,1);



call insertRowsToDcCases;
call insertRowsToCoTriggers();
call insertRowsToCitizenApp();
call insertRowsToEligDtls();

select count(*) from co_triggers where trg_status='pending';

update co_triggers set trg_status='pending' where trg_id>=1;

delete from eligibility_dtls where ed_trace_id=1;