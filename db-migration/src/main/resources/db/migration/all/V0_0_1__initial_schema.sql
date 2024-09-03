CREATE TABLE car
(
    id SERIAL PRIMARY KEY NOT NULL,
    model VARCHAR(100),
    brand VARCHAR(100),
    created_at TIMESTAMPTZ
);