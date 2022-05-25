CREATE TABLE uf (
    id text,
    PRIMARY KEY (id)
);

CREATE TABLE city (
    id text,
    PRIMARY KEY (id)
);

CREATE TABLE district (
    id text,
    PRIMARY KEY (id)
);

CREATE TABLE address (
    id bigserial NOT NULL,
    postal_code text,
    description text,
    district_id text,
    uf_id text,
    city_id text,
    UNIQUE(postal_code, description, district_id, city_id, uf_id),
    foreign key (district_id) references district(id),
    foreign key (city_id) references city(id),
    foreign key (uf_id) references uf(id),
    PRIMARY KEY (id)
);
