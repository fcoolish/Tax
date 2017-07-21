/**
* Author:fcoolish	
* Date:2017-7-5
*Desc:创建人员组织架构的数据表 
*
**/
 
*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/5 15:39:07                           */
/*==============================================================*/


drop table if exists emp_role;

drop table if exists role_pri;

drop table if exists t_dept;

drop table if exists t_employee;

drop table if exists t_leader;

drop table if exists t_org;

drop table if exists t_privilege;

drop table if exists t_role;

/*==============================================================*/
/* Table: emp_role                                              */
/*==============================================================*/
create table emp_role
(
   emp_id               varchar(32) not null,
   role_id              varchar(32) not null,
   state                int,
   primary key (emp_id, role_id)
);

/*==============================================================*/
/* Table: role_pri                                              */
/*==============================================================*/
create table role_pri
(
   role_id              varchar(32) not null,
   pri_id               varchar(32) not null,
   primary key (role_id, pri_id)
);

/*==============================================================*/
/* Table: t_dept                                                */
/*==============================================================*/
create table t_dept
(
   detp_id              varchar(32) not null,
   org_id               varchar(32) not null,
   name                 varchar(50),
   primary key (detp_id)
);

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
create table t_employee
(
   emp_id               varchar(32) not null,
   detp_id              varchar(32) not null,
   name                 varchar(50) not null,
   primary key (emp_id)
);

/*==============================================================*/
/* Table: t_leader                                              */
/*==============================================================*/
create table t_leader
(
   emp_id               varchar(32) not null,
   detp_id              varchar(32),
   name                 varchar(50) not null,
   position             int,
   primary key (emp_id)
);

/*==============================================================*/
/* Table: t_org                                                 */
/*==============================================================*/
create table t_org
(
   org_id               varchar(32) not null,
   name                 varchar(50),
   primary key (org_id)
);

/*==============================================================*/
/* Table: t_privilege                                           */
/*==============================================================*/
create table t_privilege
(
   pri_id               varchar(32) not null,
   name                 varchar(50),
   primary key (pri_id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   role_id              varchar(32) not null,
   name                 varchar(50),
   primary key (role_id)
);

alter table emp_role add constraint FK_emp_role foreign key (emp_id)
      references t_employee (emp_id) on delete restrict on update restrict;

alter table emp_role add constraint FK_emp_role2 foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

alter table role_pri add constraint FK_belong foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

alter table role_pri add constraint FK_own foreign key (pri_id)
      references t_privilege (pri_id) on delete restrict on update restrict;

alter table t_dept add constraint FK_org_dept foreign key (org_id)
      references t_org (org_id) on delete restrict on update restrict;

alter table t_employee add constraint FK_dept_emp foreign key (detp_id)
      references t_dept (detp_id) on delete restrict on update restrict;

alter table t_leader add constraint FK_Inheritance_1 foreign key (emp_id)
      references t_employee (emp_id) on delete restrict on update restrict;


/**
* Author: fcoolish
* Date: 2017-07-10
* Desc: 创建投诉受理数据库表
*/

drop table if exists complain;

/*==============================================================*/
/* Table: complain                                              */
/*==============================================================*/
create table complain
(
   comp_id              varchar(32) not null,
   comp_company         varchar(100),
   comp_name            varchar(20),
   comp_mobile          varchar(20),
   is_NM                bool,
   comp_time            datetime,
   comp_title           varchar(200) not null,
   to_comp_name         varchar(20),
   to_comp_dept         varchar(100),
   comp_content         text,
   state                varchar(1),
   primary key (comp_id)
);

drop table if exists complain_reply;

/*==============================================================*/
/* Table: complain_reply                                        */
/*==============================================================*/
create table complain_reply
(
   reply_id             varchar(32) not null,
   comp_id              varchar(32) not null,
   replyer              varchar(20),
   reply_dept           varchar(100),
   reply_time           datetime,
   reply_content        varchar(300),
   primary key (reply_id)
);

alter table complain_reply add constraint FK_comp_reply foreign key (comp_id)
      references complain (comp_id) on delete restrict on update restrict;


