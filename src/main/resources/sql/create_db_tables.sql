CREATE DATABASE event_ticket_db;

CREATE TABLE event (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255),
                       location VARCHAR(255),
                       capacity INTEGER,
                       reserved_count INTEGER,
                       ticket_price DECIMAL,
                       status VARCHAR(50)
);

CREATE TABLE reservation (
                             id BIGSERIAL PRIMARY KEY,
                             customer_name VARCHAR(255),
                             customer_phone VARCHAR(255),
                             event_id BIGINT,
                             ticket_count INTEGER,
                             reservation_date DATE,
                             status VARCHAR(50)
);


DROP DATABASE event_ticket_db;

DROP TABLE event;
DROP TABLE reservation;