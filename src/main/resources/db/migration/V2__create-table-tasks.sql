create table tasks(

    id bigint not null auto_increment,
    title varchar(100) not null,
    completed bit not null,
    due_date DATE not null,

    primary key(id)

);