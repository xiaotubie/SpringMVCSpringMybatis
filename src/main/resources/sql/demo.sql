prompt PL/SQL Developer import file
prompt Created on 2015年9月18日 by Administrator
set feedback off
set define off
prompt Dropping SS_TASK...
drop table SS_TASK cascade constraints;
prompt Dropping SS_USER...
drop table SS_USER cascade constraints;
prompt Creating SS_TASK...
create table SS_TASK
(
  id          NUMBER(19) not null,
  title       VARCHAR2(128) not null,
  description VARCHAR2(255),
  user_id     NUMBER(19) not null
)
tablespace XBWL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table SS_TASK
  add primary key (ID)
  using index 
  tablespace XBWL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SS_USER...
create table SS_USER
(
  id         NUMBER(19) not null,
  login_name VARCHAR2(255) not null,
  name       VARCHAR2(64),
  password   VARCHAR2(255),
  salt       VARCHAR2(64),
  email      VARCHAR2(128),
  status     VARCHAR2(32),
  team_id    NUMBER(19)
)
tablespace XBWL
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SS_USER
  add primary key (ID)
  using index 
  tablespace XBWL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SS_USER
  add unique (LOGIN_NAME)
  using index 
  tablespace XBWL
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for SS_TASK...
alter table SS_TASK disable all triggers;
prompt Disabling triggers for SS_USER...
alter table SS_USER disable all triggers;
prompt Loading SS_TASK...
insert into SS_TASK (id, title, description, user_id)
values (1, 'Study PlayFramework 2.0', 'http://www.playframework.org/', 2);
insert into SS_TASK (id, title, description, user_id)
values (2, 'Study Grails 2.0', 'http://www.grails.org/', 2);
insert into SS_TASK (id, title, description, user_id)
values (3, 'Try SpringFuse', 'http://www.springfuse.com/', 2);
insert into SS_TASK (id, title, description, user_id)
values (4, 'Try Spring Roo', 'http://www.springsource.org/spring-roo', 2);
insert into SS_TASK (id, title, description, user_id)
values (5, 'Release SpringSide 4.0', 'As soon as posibble.', 2);
insert into SS_TASK (id, title, description, user_id)
values (1000, '测试数据3', '测试数据3', 2);
insert into SS_TASK (id, title, description, user_id)
values (1040, '测试数据4', '测试数据4', 2);
insert into SS_TASK (id, title, description, user_id)
values (1060, '测试数据6', '测试数据6', 1);
insert into SS_TASK (id, title, description, user_id)
values (10000, '测试数据', '测试数据', 1);
insert into SS_TASK (id, title, description, user_id)
values (10001, '222', '2', 1);
insert into SS_TASK (id, title, description, user_id)
values (10002, '333', '333', 1);
insert into SS_TASK (id, title, description, user_id)
values (960, '测试数据1', '测试数据1', 2);
insert into SS_TASK (id, title, description, user_id)
values (980, '测试数据2', '测试数据2', 2);
commit;
prompt 13 records loaded
prompt Loading SS_USER...
insert into SS_USER (id, login_name, name, password, salt, email, status, team_id)
values (1, 'admin', '管理员', '691b14d79bf0fa2215f155235df5e670b64394cc', '7efbd59d9741d34f', 'admin@springside.org.cn', 'enabled', 1);
insert into SS_USER (id, login_name, name, password, salt, email, status, team_id)
values (2, 'user', 'Calvin', '75caf2cc6dea47c03cbca6409c804db6a506d43c', 'f73a2e9cab0328c5', 'user@springside.org.cn', 'enabled', 1);
insert into SS_USER (id, login_name, name, password, salt, email, status, team_id)
values (3, 'user2', 'Jack', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'jack@springside.org.cn', 'enabled', 1);
insert into SS_USER (id, login_name, name, password, salt, email, status, team_id)
values (4, 'user3', 'Kate', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'kate@springside.org.cn', 'enabled', 1);
insert into SS_USER (id, login_name, name, password, salt, email, status, team_id)
values (5, 'user4', 'Sawyer', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'sawyer@springside.org.cn', 'enabled', 1);
insert into SS_USER (id, login_name, name, password, salt, email, status, team_id)
values (6, 'user5', 'Ben', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'ben@springside.org.cn', 'enabled', 1);
commit;
prompt 6 records loaded
prompt Enabling triggers for SS_TASK...
alter table SS_TASK enable all triggers;
prompt Enabling triggers for SS_USER...
alter table SS_USER enable all triggers;
set feedback on
set define on
prompt Done.


--/*******权限管理*******/
-- 角色
-- Create table
create table SS_ROLE
(
  id          NUMBER not null,
  role_code   VARCHAR2(20) not null,
  role_name   VARCHAR2(20) not null,
  create_date DATE,
  update_date DATE,
  create_id   NUMBER,
  update_id   NUMBER,
  state       NUMBER(1)
)
tablespace XBWL
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column SS_ROLE.id
  is '主键';
comment on column SS_ROLE.role_code
  is '角色编码';
comment on column SS_ROLE.role_name
  is '角色名称';
comment on column SS_ROLE.create_date
  is '创建时间';
comment on column SS_ROLE.update_date
  is '更新时间';
comment on column SS_ROLE.create_id
  is '创建人';
comment on column SS_ROLE.update_id
  is '更新人';
comment on column SS_ROLE.state
  is '状态（启用1/停用0)';
