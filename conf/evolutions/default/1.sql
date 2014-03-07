# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table TIPSCOUPON (
  tips_id                   number(19) not null,
  product_type              number(19),
  product_id                number(19),
  start_date                timestamp,
  deadline_date             timestamp,
  week                      number(19),
  constraint pk_TIPSCOUPON primary key (tips_id))
;

create sequence tipscoupon_seq;




# --- !Downs

drop table TIPSCOUPON cascade constraints purge;

drop sequence tipscoupon_seq;

