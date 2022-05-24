create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );
create table result (
    id integer not null,
    date varchar(255),
    res double precision not null,
    user_id bigint,
    primary key (id)
);
create table user_role (
    user_id bigint not null,
    roles varchar(255)
);
create table usr (id bigint not null,
        activation_code varchar(255),
        active bit not null,
        email varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
                 );
alter table result
    add constraint FKc001svrmfl0aggh1q7ao32xhs
        foreign key (user_id) references usr (id);
alter table user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5
        foreign key (user_id) references usr (id);
