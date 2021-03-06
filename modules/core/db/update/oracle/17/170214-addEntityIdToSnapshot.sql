alter table SYS_ENTITY_SNAPSHOT add STRING_ENTITY_ID varchar2(255)^
alter table SYS_ENTITY_SNAPSHOT add INT_ENTITY_ID integer^
alter table SYS_ENTITY_SNAPSHOT add LONG_ENTITY_ID number^

alter table SYS_ENTITY_SNAPSHOT modify ENTITY_ID null^

create index IDX_SYS_ENTITY_SSNAPSHOT_EN_ID on SYS_ENTITY_SNAPSHOT(STRING_ENTITY_ID)^
create index IDX_SYS_ENTITY_ISNAPSHOT_EN_ID on SYS_ENTITY_SNAPSHOT(INT_ENTITY_ID)^
create index IDX_SYS_ENTITY_LSNAPSHOT_EN_ID on SYS_ENTITY_SNAPSHOT(LONG_ENTITY_ID)^