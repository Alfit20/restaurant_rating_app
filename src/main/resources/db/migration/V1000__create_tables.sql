CREATE TABLE customers
(
    id       bigserial,
    name     varchar     NOT NULL,
    email    varchar     NOT NULL,
    password varchar     NOT NULL,
    enabled  boolean     NOT NULL default true,
    role     varchar(16) NOT NULL default 'USER',
    PRIMARY KEY (id)
);

CREATE TABLE places
(
    id          bigserial,
    name        varchar NOT NULL,
    photo       varchar NOT NULL,
    description varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE gallery
(
    id       bigserial,
    photo    varchar NOT NULL,
    place_id BIGINT  NOT NULL,
    FOREIGN KEY (place_id)
        REFERENCES places (id),
    PRIMARY KEY (id)
);

CREATE TABLE reviews
(
    id         bigserial,
    text       varchar   NOT NULL,
    date_added timestamp NOT NULL,
    author_id  BIGINT    NOT NULL,
    place_id   BIGINT    NOT NULL,
    votes     INT       NOT NULL,
        FOREIGN KEY (author_id)
        REFERENCES customers (id),
    FOREIGN KEY (place_id)
        REFERENCES places (id),
    PRIMARY KEY (id)
);

