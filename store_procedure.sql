/*
    Some handful procedures

    mysql -uroot < store_procesure.sql

*/
USE agenda;
DROP PROCEDURE IF EXISTS generar_contactos;
DROP PROCEDURE IF EXISTS generar_grupos;
DROP PROCEDURE IF EXISTS generar_contactos_grupos;

DELIMITER //
CREATE PROCEDURE generar_contactos()
BEGIN
    SET @i=1;
    WHILE @i <= 20 DO
        INSERT INTO contactos (nombre,apellido,telefono,email) VALUES (
            CONCAT("Nombre ",@i),
            CONCAT("Apellido ",@i),
            FLOOR(RAND()*1000000),
            CONCAT(@i,"@example.com"));
        SET @i=@i+1;
    END WHILE;
END//

DELIMITER //
CREATE PROCEDURE generar_grupos()
BEGIN
    SET @i=1;
    WHILE @i <= 20 DO
        INSERT INTO grupos (nombre,descr) VALUES (
            CONCAT("Nombre ",@i),
            CONCAT("Descripcion ",@i));
        SET @i=@i+1;
    END WHILE;
END//

DELIMITER //
CREATE PROCEDURE generar_contactos_grupos()
BEGIN
    SET @i=1;
    WHILE @i <= 20 DO
        INSERT INTO contactos_grupos (contacto,grupo) VALUES (@i,@i);
        SET @i=@i+1;
    END WHILE;
END//

DELIMITER ; 
