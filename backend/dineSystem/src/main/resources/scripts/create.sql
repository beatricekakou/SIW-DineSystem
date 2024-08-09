CREATE TABLE User
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    credential_id INTEGER REFERENCES Credential
    cellphone  VARCHAR(15)
);

CREATE TABLE Category
(
    id          SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE Dish
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100)   NOT NULL,
    description  TEXT,
    price        DECIMAL(10, 2) NOT NULL,
    category_id  INTEGER REFERENCES Category (id),
    is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE Order
(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES User (id),
    date    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount  DECIMAL(10, 2)
);

CREATE TABLE Order_detail
(
    id       SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES Order (id),
    dish_id  INTEGER REFERENCES Dish (id),
    quantity INTEGER        NOT NULL
);

CREATE TABLE Table
(
    id       SERIAL PRIMARY KEY,
    number   INTEGER NOT NULL UNIQUE,
    capacity INTEGER NOT NULL
);

CREATE TABLE Reservation
(
    id            SERIAL PRIMARY KEY,
    user_id       INTEGER REFERENCES User (id),
    table_id      INTEGER REFERENCES Table (id),
    date          DATE    NOT NULL,
    slot_id     INTEGER,
    number_people INTEGER NOT NULL,
    CONSTRAINT fk_seating FOREIGN KEY (seating) REFERENCES seating (id)
);

CREATE TABLE Slot
(
    id          SERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    time        TIME        NOT NULL
);

CREATE TABLE Credential
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    );
