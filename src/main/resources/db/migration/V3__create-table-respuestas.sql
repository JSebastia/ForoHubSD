create table respuestas(
    id bigint not null auto_increment,
    mensaje varchar(255) not null,
    fecha datetime not null,
    solucion tinyint,
    autor bigint not null,
    topico bigint not null,

    primary key(id)
);