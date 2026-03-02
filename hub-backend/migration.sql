create table form_AUD (id integer not null, REV integer not null, REVTYPE smallint, REVEND integer, components jsonb, components_MOD boolean, created_at timestamp(6) with time zone, createdAt_MOD boolean, modified_at timestamp(6) with time zone, modifiedAt_MOD boolean, name varchar(255), name_MOD boolean, version integer, version_MOD boolean, form_status_id integer, formStatus_MOD boolean, primary key (REV, id));
create table form_status_AUD (id integer not null, REV integer not null, REVTYPE smallint, REVEND integer, created_At timestamp(6) with time zone, createdAt_MOD boolean, modified_at timestamp(6) with time zone, modifiedAt_MOD boolean, name varchar(255), name_MOD boolean, primary key (REV, id));
create table user_rev_info (id integer not null, timestamp bigint not null, ppId integer, username varchar(255), primary key (id));
alter table if exists form_AUD add constraint FK69xnet14m3ypnhsmgwcvk99if foreign key (REV) references user_rev_info;
alter table if exists form_AUD add constraint FK6051nn7v3ghfy2s83hk0yl6q foreign key (REVEND) references user_rev_info;
alter table if exists form_status_AUD add constraint FKmm4l6hqsivf7bbwpibeeestip foreign key (REV) references user_rev_info;
alter table if exists form_status_AUD add constraint FKaanonwqbu3jo4f4hmlldxypgu foreign key (REVEND) references user_rev_info;
