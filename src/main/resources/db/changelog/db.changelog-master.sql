-- liquibase formatted sql

-- changeset author:sergey

CREATE TABLE users
(
    id              UUID                PRIMARY KEY,
    username        varchar(255)        UNIQUE NOT NULL,
    password        varchar(255)        NOT NULL,
    email           varchar(255)        UNIQUE NOT NULL,
    created_at      TIMESTAMP default current_timestamp
);

CREATE TABLE quotes
(
    id              UUID        PRIMARY KEY,
    content         text        NOT NULL,
    rank            integer     DEFAULT 0,
    user_id         UUID        REFERENCES users (id),
    created_at      TIMESTAMP default current_timestamp,
    updated_at      TIMESTAMP default current_timestamp
);


INSERT INTO users (id, username, password, email) values
('ec2aa7f4-42d5-442c-8baf-6d4cc6d15db3', 'admin', 'admin', 'admin@admin.com'),
('fa6f0504-54bd-42b1-8363-7970492e724d', 'some_user','123', 'some_one@mail.com');

INSERT INTO quotes (id, content, rank, user_id) values
('f20fb1a6-e4b2-48ba-840c-d083e0143fc0', 'bla-bla', 1, 'ec2aa7f4-42d5-442c-8baf-6d4cc6d15db3'),
('01739ed0-a9bb-43a0-ac86-a28bbc3d143f', 'bla-bla-bla', 2, 'ec2aa7f4-42d5-442c-8baf-6d4cc6d15db3'),
('36073866-d8e6-4454-beda-22c6c010b529', 'Hello world', 100500, 'ec2aa7f4-42d5-442c-8baf-6d4cc6d15db3'),
('047939d5-e0b4-481f-9559-2d9548b5edd5', 'some quote', 231, 'ec2aa7f4-42d5-442c-8baf-6d4cc6d15db3'),
('85a36399-0164-4b13-ae72-d1f07a866e17', 'another quote', 231, 'ec2aa7f4-42d5-442c-8baf-6d4cc6d15db3'),
('46687e00-9ccc-4725-b361-142aeb00feec', 'and another quote', 451, 'ec2aa7f4-42d5-442c-8baf-6d4cc6d15db3'),
('90c90ebe-8b53-4ef5-985d-f275c3082e43', 'For the emperor', 123123, 'fa6f0504-54bd-42b1-8363-7970492e724d'),
('0cfc3e32-49f4-4dc3-9fac-6e776a0e0f8e', 'Gork and Mork', 12312, 'fa6f0504-54bd-42b1-8363-7970492e724d'),
('4f45ac3d-dc08-44cc-b5d2-8bc00f327b82', 'dsfsdfsfsd', 112, 'fa6f0504-54bd-42b1-8363-7970492e724d'),
('7aedc6d6-0769-460b-9d94-5dd8d63de7d4', 'qdfwfwf', 12, 'fa6f0504-54bd-42b1-8363-7970492e724d'),
('9a628425-73ae-4fe1-a493-2ce172d72c20', 'ccnvdnjvbsdf', 120, 'fa6f0504-54bd-42b1-8363-7970492e724d'),
('9f576768-0f26-4840-b206-2b2f2540a172', 'bsdkfhds', 11, 'fa6f0504-54bd-42b1-8363-7970492e724d');
