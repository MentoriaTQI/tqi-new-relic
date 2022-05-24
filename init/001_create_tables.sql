CREATE TABLE state (
    id bigserial NOT NULL,
    description text,
    PRIMARY KEY (id)
);

CREATE TABLE city (
    id bigserial NOT NULL,
    description text,
    state_id int8,
    foreign key (state_id) references state(id),
    PRIMARY KEY (id)
);

CREATE TABLE district (
    id bigserial NOT NULL,
    description text,
    PRIMARY KEY (id)
);

CREATE TABLE address (
    id bigserial NOT NULL,
    postalCode text,
    description text,
    district_id int8,
    city_id int8,
    foreign key (district_id) references district(id),
    foreign key (city_id) references city(id),
    PRIMARY KEY (id)
);
