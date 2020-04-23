create table if not exists USR
(
    id         bigint auto_increment,
    login      varchar(256) not null,
    password   varchar(256) not null,
    name       varchar(256) not null,
    role       varchar(256) not null,
    UNIQUE (login),
    primary key (id)
);

INSERT INTO USR (id, login, password, name, role)
VALUES (1,
        'admin',
        '123',
        'admin',
        'admin'
);

INSERT INTO USR (id, login, password, name, role)
VALUES (2,
        'Vasyan',
        '123',
        'Ivan',
        'user'
);


