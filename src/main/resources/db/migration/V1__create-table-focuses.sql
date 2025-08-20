create table focuses(

    id bigint not null auto_increment,
    title varchar(100) not null,
    timer integer not null,
    short_break  integer not null,
    long_break integer not null,

    primary key(id)

);