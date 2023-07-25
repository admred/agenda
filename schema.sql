/*
  Esta es el base de datos que debe ser importada primero

   mysql -uroot < schema.sql  
 */
DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda;
USE agenda;

CREATE TABLE grupos(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(31) NOT NULL UNIQUE,
    descr       VARCHAR(2040)
);

CREATE TABLE contactos(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(31) NOT NULL,
    apellido    VARCHAR(31) NOT NULL,
    telefono    VARCHAR(31),
    email       VARCHAR(31)
);

/* many-to-many relation */
CREATE TABLE contactos_grupos(
    contacto    INT,
    grupo       INT,
    FOREIGN KEY (contacto) REFERENCES contactos(id) ON DELETE CASCADE,
    FOREIGN KEY (grupo)    REFERENCES    grupos(id) ON DELETE CASCADE,
    PRIMARY KEY (contacto,grupo)
);

