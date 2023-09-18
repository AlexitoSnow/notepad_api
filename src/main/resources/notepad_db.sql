CREATE DATABASE IF NOT EXISTS note_pad;
USE note_pad;

CREATE TABLE IF NOT EXISTS files(
title varchar(32) primary key,
content text
);

-- Test Files
/*
DROP PROCEDURE IF EXISTS initial;

DELIMITER &
CREATE PROCEDURE initial()
BEGIN
SET @bienvenida =
"¡Hola! Se podría decir que esta es mi primera aplicación full stack, ya que implementa las siguientes tecnologías:
- Una Web app en Flutter
- Una ApiREST en Spring Boot
- Una base de datos en MySQL
La parte más complicada fue hacer que se carguen los datos en el bloc de notas :C
¡Como sea, espero que la disfrutes!
Saludos, Alexito Snow.";

SET @notas =
'- Si tratas de nombrar un archivo como el nombre de la aplicación (Notepad), no surtirá efecto
- Llamadas a API Rest en Spring Boot
- API Rest conectada a una base de datos MySQL
- Permite todas las operaciones CRUD
	- SELECT:
		- Nombre de todos los archivos
		- Contenido de un archivo
	- INSERT:
		- Nuevo archivo vacío
	- UPDATE:
		- Renombrar archivo
		- Modificar contenido de un archivo
	- DELETE:
		- Elimina un archivo y su contenido';
IF (SELECT title FROM files WHERE title = "¡Bienvenida!") IS NULL THEN
    INSERT INTO files VALUES ("¡Bienvenida!", @bienvenida);
END IF;

IF (SELECT title FROM files WHERE title = "Notas") IS NULL THEN
    INSERT INTO files VALUES ("Notas de Versión", @notas);
END IF;
END &
DELIMITER ;

CALL initial;

DROP PROCEDURE IF EXISTS initial;
*/
-- SELECT * FROM files;
-- DELETE FROM files;