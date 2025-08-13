create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(255) not null,
    fecha datetime not null,
    status tinyint,
    autor bigint not null,
    curso bigint not null,
    respuesta bigint not null,

    primary key (id),
    constraint fk_topicos_autor foreign key (autor) references usuarios(id),
    constraint fk_topicos_curso foreign key (curso) references cursos(id),
    constraint fk_topicos_respuesta foreign key (respuesta) references respuestas(id)
);