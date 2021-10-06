-- -----------------------------------------------------
-- Schema ProyectoRevistas
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS ProyectoRevistas;

-- -----------------------------------------------------
-- Schema ProyectoRevistas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS ProyectoRevistas;
USE ProyectoRevistas;

-- -----------------------------------------------------
-- Table Persona
-- -----------------------------------------------------
DROP TABLE IF EXISTS Persona ;

CREATE TABLE IF NOT EXISTS Persona (
  id INT NOT NULL AUTO_INCREMENT,
  tipo_usuario TINYINT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  password VARCHAR(25) NOT NULL,
  foto LONGBLOB NULL,
  hobbies VARCHAR(75) NULL,
  descripcion VARCHAR(200) NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table Revista
-- -----------------------------------------------------
DROP TABLE IF EXISTS Revista ;

CREATE TABLE IF NOT EXISTS Revista (
  nombre VARCHAR(50) NOT NULL,
  categoria VARCHAR(45) NOT NULL,
  descripcion VARCHAR(200) NOT NULL,
  pago TINYINT NOT NULL,
  costo_suscripcion DOUBLE NULL,
  costo_dia DOUBLE NULL,
  id_editor INT NOT NULL,
  PRIMARY KEY (nombre),
  INDEX fk_Revista_Persona1_idx (id_editor ASC) VISIBLE,
  CONSTRAINT fk_Revista_Persona1
    FOREIGN KEY (id_editor)
    REFERENCES Persona (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table Anunciante
-- -----------------------------------------------------
DROP TABLE IF EXISTS Anunciante ;

CREATE TABLE IF NOT EXISTS Anunciante (
  nombre VARCHAR(45) NOT NULL,
  nombre_empresa VARCHAR(45) NOT NULL,
  edad INT NOT NULL,
  PRIMARY KEY (nombre));



-- -----------------------------------------------------
-- Table Anuncio
-- -----------------------------------------------------
DROP TABLE IF EXISTS Anuncio ;

CREATE TABLE IF NOT EXISTS Anuncio (
  id INT NOT NULL,
  tipo_anuncio INT NOT NULL,
  texto_anuncio VARCHAR(200) NULL,
  foto_anuncio LONGBLOB NULL,
  link_video VARCHAR(60) NULL,
  costo DOUBLE NOT NULL,
  fecha_creacion DATE NOT NULL,
  fecha_expiracion DATE NOT NULL,
  nombre_anunciante VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Anuncio_Anunciante1_idx (nombre_anunciante ASC) VISIBLE,
  CONSTRAINT fk_Anuncio_Anunciante1
    FOREIGN KEY (nombre_anunciante)
    REFERENCES Anunciante (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Publicacion
-- -----------------------------------------------------
DROP TABLE IF EXISTS Publicacion ;

CREATE TABLE IF NOT EXISTS Publicacion (
  id INT NOT NULL AUTO_INCREMENT,
  contenido LONGBLOB NOT NULL,
  fecha_publicacion DATE NOT NULL,
  nombre_revista VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Publicacion_Revista_idx (nombre_revista ASC) VISIBLE,
  CONSTRAINT fk_Publicacion_Revista
    FOREIGN KEY (nombre_revista)
    REFERENCES Revista (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Etiqueta
-- -----------------------------------------------------
DROP TABLE IF EXISTS Etiqueta ;

CREATE TABLE IF NOT EXISTS Etiqueta (
  nombre VARCHAR(30) NOT NULL,
  PRIMARY KEY (nombre));



-- -----------------------------------------------------
-- Table Preferencias_Usuario
-- -----------------------------------------------------
DROP TABLE IF EXISTS Preferencias_Usuario ;

CREATE TABLE IF NOT EXISTS Preferencias_Usuario (
  nombre_etiqueta VARCHAR(30) NOT NULL,
  id_usuario INT NOT NULL,
  PRIMARY KEY (nombre_etiqueta, id_usuario),
  INDEX fk_Preferencias_Usuario_Etiqueta1_idx (nombre_etiqueta ASC) VISIBLE,
  INDEX fk_Preferencias_Usuario_Usuario1_idx (id_usuario ASC) VISIBLE,
  CONSTRAINT fk_Preferencias_Usuario_Etiqueta1
    FOREIGN KEY (nombre_etiqueta)
    REFERENCES Etiqueta (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Preferencias_Usuario_Usuario1
    FOREIGN KEY (id_usuario)
    REFERENCES Persona (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Etiquetas_Revista
-- -----------------------------------------------------
DROP TABLE IF EXISTS Etiquetas_Revista ;

CREATE TABLE IF NOT EXISTS Etiquetas_Revista (
  nombre_revista VARCHAR(50) NOT NULL,
  nombre_etiqueta VARCHAR(30) NOT NULL,
  PRIMARY KEY (nombre_revista, nombre_etiqueta),
  INDEX fk_Etiquetas_Revista_Revista1_idx (nombre_revista ASC) VISIBLE,
  INDEX fk_Etiquetas_Revista_Etiqueta1_idx (nombre_etiqueta ASC) VISIBLE,
  CONSTRAINT fk_Etiquetas_Revista_Revista1
    FOREIGN KEY (nombre_revista)
    REFERENCES Revista (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Etiquetas_Revista_Etiqueta1
    FOREIGN KEY (nombre_etiqueta)
    REFERENCES Etiqueta (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Comentario
-- -----------------------------------------------------
DROP TABLE IF EXISTS Comentario ;

CREATE TABLE IF NOT EXISTS Comentario (
  id INT NOT NULL,
  contenido VARCHAR(200) NOT NULL,
  fecha_comentario DATE NOT NULL,
  nombre_revista VARCHAR(50) NOT NULL,
  id_usuario INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Comentario_Revista1_idx (nombre_revista ASC) VISIBLE,
  INDEX fk_Comentario_Usuario1_idx (id_usuario ASC) VISIBLE,
  CONSTRAINT fk_Comentario_Revista1
    FOREIGN KEY (nombre_revista)
    REFERENCES Revista (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Comentario_Usuario1
    FOREIGN KEY (id_usuario)
    REFERENCES Persona (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Suscripcion
-- -----------------------------------------------------
DROP TABLE IF EXISTS Suscripcion ;

CREATE TABLE IF NOT EXISTS Suscripcion (
  id INT NOT NULL AUTO_INCREMENT,
  fecha_suscripcion DATE NOT NULL,
  fecha_caducidad DATE NULL,
  nombre_revista VARCHAR(50) NOT NULL,
  id_usuario INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Suscripcion_Revista1_idx (nombre_revista ASC) VISIBLE,
  INDEX fk_Suscripcion_Usuario1_idx (id_usuario ASC) VISIBLE,
  CONSTRAINT fk_Suscripcion_Revista1
    FOREIGN KEY (nombre_revista)
    REFERENCES Revista (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Suscripcion_Usuario1
    FOREIGN KEY (id_usuario)
    REFERENCES Persona (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Me_Gusta
-- -----------------------------------------------------
DROP TABLE IF EXISTS Me_Gusta ;

CREATE TABLE IF NOT EXISTS Me_Gusta (
  nombre_revista VARCHAR(50) NOT NULL,
  id_usuario INT NOT NULL,
  PRIMARY KEY (nombre_revista, id_usuario),
  INDEX fk_Me_Gusta_Usuario1_idx (id_usuario ASC) VISIBLE,
  CONSTRAINT fk_Me_Gusta_Revista1
    FOREIGN KEY (nombre_revista)
    REFERENCES Revista (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Me_Gusta_Usuario1
    FOREIGN KEY (id_usuario)
    REFERENCES Persona (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Etiquetas_Anuncio
-- -----------------------------------------------------
DROP TABLE IF EXISTS Etiquetas_Anuncio ;

CREATE TABLE IF NOT EXISTS Etiquetas_Anuncio (
  Etiqueta_nombre VARCHAR(30) NOT NULL,
  Anuncio_id INT NOT NULL,
  PRIMARY KEY (Etiqueta_nombre, Anuncio_id),
  INDEX fk_Etiquetas_Anuncio_Anuncio1_idx (Anuncio_id ASC) VISIBLE,
  CONSTRAINT fk_Etiquetas_Anuncio_Etiqueta1
    FOREIGN KEY (Etiqueta_nombre)
    REFERENCES Etiqueta (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Etiquetas_Anuncio_Anuncio1
    FOREIGN KEY (Anuncio_id)
    REFERENCES Anuncio (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Cambio_Costo_Diario
-- -----------------------------------------------------
DROP TABLE IF EXISTS Cambio_Costo_Diario ;

CREATE TABLE IF NOT EXISTS Cambio_Costo_Diario (
  id INT NOT NULL AUTO_INCREMENT,
  monto DOUBLE NOT NULL,
  fecha_cambio DATE NOT NULL,
  nombre_revista VARCHAR(50) NOT NULL,
  INDEX fk_Costo_Diario_Revista1_idx (nombre_revista ASC) VISIBLE,
  PRIMARY KEY (id),
  CONSTRAINT fk_Costo_Diario_Revista1
    FOREIGN KEY (nombre_revista)
    REFERENCES Revista (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Valores_Global
-- -----------------------------------------------------
DROP TABLE IF EXISTS Valores_Global ;

CREATE TABLE IF NOT EXISTS Valores_Global (
  porcentaje_ganancia INT NOT NULL,
  costo_anuncio_texto DOUBLE NOT NULL,
  costo_anuncio_imagen DOUBLE NOT NULL,
  costo_anuncio_video DOUBLE NOT NULL,
  PRIMARY KEY (porcentaje_ganancia, costo_anuncio_texto, costo_anuncio_imagen, costo_anuncio_video));



-- -----------------------------------------------------
-- Table Pago
-- -----------------------------------------------------
DROP TABLE IF EXISTS Pago ;

CREATE TABLE IF NOT EXISTS Pago (
  id INT NOT NULL AUTO_INCREMENT,
  monto DOUBLE NOT NULL,
  fecha_pago DATE NOT NULL,
  id_suscripcion INT NOT NULL,
  INDEX fk_Pago_Suscripcion1_idx (id_suscripcion ASC) VISIBLE,
  PRIMARY KEY (id),
  CONSTRAINT fk_Pago_Suscripcion1
    FOREIGN KEY (id_suscripcion)
    REFERENCES Suscripcion (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Contador_Anuncio
-- -----------------------------------------------------
DROP TABLE IF EXISTS Contador_Anuncio ;

CREATE TABLE IF NOT EXISTS Contador_Anuncio (
  id INT NOT NULL AUTO_INCREMENT,
  URL_pagina VARCHAR(60) NOT NULL,
  id_anuncio INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Contador_Anuncio_Anuncio1_idx (id_anuncio ASC) VISIBLE,
  CONSTRAINT fk_Contador_Anuncio_Anuncio1
    FOREIGN KEY (id_anuncio)
    REFERENCES Anuncio (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- ---------------------------------------------
-- CREACION DEL USUARIO ADMIN
-- --------------------------------------------
DROP USER IF EXISTS 'admin_revistas'@'localhost';
CREATE USER 'admin_revistas'@'localhost' identified by 'Proyecto_Revistas_2021';
GRANT ALL PRIVILEGES ON ProyectoRevistas.* TO admin_revistas@localhost;
FLUSH PRIVILEGES;


