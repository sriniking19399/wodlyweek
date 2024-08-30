create table if not exists writer(
    id int primary key AUTO_INCREMENT,
    name text,
    bio text
);
create table if not exists magazine(
    id int primary key AUTO_INCREMENT,
    title text,
    publicationDate text
);

create table if not exists writer_magazine(
    writerId int,
    magazineId int,
    primary key (writerId,magazineId),
    foreign key(writerId)references writer(id),
    foreign key (magazineId)references magazine(id)
);