create table USER_TBL(
                         id number primary key,
                         name nvarchar2(30),
                         family nvarchar2(30),
                         username nvarchar2(30) unique,
                         password nvarchar2(30),
                         creationDate timestamp
);

create sequence user_seq start with 1 increment by 1;

create table ACCOUNT_TBL(
                            id number primary key,
                            name  nvarchar2(30),
                            balance number(15,2),
                            user_id references USER_TBL
);

create sequence account_seq start with 1 increment by 1;

create table TITLES_TBL(
                           id number primary key,
                           name  nvarchar2(30),
                           type  nvarchar2(30)
);

create sequence TITLES_SEQ start with 1 increment by 1;

create table TRANSACTION_TBL(
                                id number primary key,
                                user_id references USER_TBL,
                                account_id references ACCOUNT_TBL,
                                amount number(15,2),
                                type  nvarchar2(30),
                                titles_id references TITLES_TBL,
                                description nvarchar2(100),
                                transactionDate timestamp
);

create sequence TRANSACTION_SEQ start with 1 increment by 1;


create table LOG_TBL(
                        id number ,
                        class_name  nvarchar2(50),
                        log_type  nvarchar2(50),
                        data nvarchar2(50)
);
create sequence log_seq start with 1 increment by 1;


create view ACCOUNT_REPORT as
select U.ID       as user_id,
       U.NAME     as user_name,
       U.FAMILY   as user_family,
       U.USERNAME as user_username,
       U.password as user_password,
       U.creationDate as user_creationdate,
       A.ID       as account_id,
       A.NAME     as account_name,
       A.BALANCE  as account_balance,
       A.user_id  as account_userId
from ACCOUNT_TBL A,
     USER_TBL U
where A.USER_ID = U.ID;



