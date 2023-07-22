/*
 *  Esta es el base de datos que debe ser importada primero
 */
DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda;
USE agenda;

CREATE TABLE contactos(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(30) NOT NULL,
    apellido    VARCHAR(30) NOT NULL,
    telefono    VARCHAR(30),
    email       VARCHAR(30)
);

CREATE TABLE grupos(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(20) NOT NULL UNIQUE,
    descr       VARCHAR(1000)
);

/* many-to-many relation */
CREATE TABLE contactos_grupos(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    contacto    INT,
    grupo       INT,
    FOREIGN KEY (contacto) REFERENCES contactos(id) ON DELETE SET NULL,
    FOREIGN KEY (grupo) REFERENCES grupos(id) ON DELETE SET NULL
);
