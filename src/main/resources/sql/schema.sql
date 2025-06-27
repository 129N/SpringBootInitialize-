create table country
(
    id      bigint       not null,
    version integer,
    created timestamp(6),
    updated timestamp(6),
    name    varchar(100) not null,
    sign    varchar(10)  not null,
    primary key (id)
);

create sequence country_seq start with 1 increment by 1;

create table client
(
    id      bigint       not null,
    version integer,
    created timestamp(6),
    updated timestamp(6),
    name    varchar(100) not null,
    address varchar(30)  not null,
    amount  integer      not null,
    country bigint       not null,
    primary key (id)
);

create sequence client_seq start with 1 increment by 1;

create table person
(
    id      bigint       not null,
    client_id     bigint       not null,
    version integer,
    created timestamp(6),
    updated timestamp(6),
    personal_id varchar(12)  not null unique,
    birth_date  date  not null,
    primary key (id)
);

create sequence person_seq start with 1 increment by 1;

create table company
(
    id      bigint       not null,
    client_id     bigint       not null,
    version integer,
    created timestamp(6),
    updated timestamp(6),
    established bigint not null,
    tax_id      varchar(9)  not null unique,
    capitalisation bigint,
    primary key (id)
);

create sequence company_seq start with 1 increment by 1;

create table jobs
(
    id      bigint       not null,
    version integer,
    created timestamp(6),
    updated timestamp(6),
    starting timestamp(6),
    finished timestamp(6),
    client_id     bigint       not null,
    name    varchar(255) not null,
    val    integer     not null,
    job_type varchar(255) not null check (job_type in ('SELL','BUY')),
    primary key (id)
);

create sequence jobs_seq start with 1 increment by 1;

alter table if exists client
    add constraint client_country_fk
    foreign key (country)
    references country;

alter table if exists company
    add constraint company_client_fk
    foreign key (client_id)
    references client;

alter table if exists jobs
    add constraint job_client_fk
    foreign key (client_id)
    references client;

alter table if exists person
    add constraint person_client_fk
    foreign key (client_id)
    references client;
