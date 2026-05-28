CREATE TABLE AGENCIAS(
  id_Agencia NUMBER(4),
  nombre VARCHAR2(40) NOT NULL,
  pais VARCHAR2(40) NOT NULL,
  fecha_Fundacion VARCHAR2(40) NOT NULL,
  autor_Examen VARCHAR(150) NOT NULL,
  CONSTRAINT PK_Agencias PRIMARY KEY (id_Agencia),
);

CREATE TABLE SATELITES(
  id_Satelite NUMBER(4),
  nombre VARCHAR2(40) NOT NULL,
  orbita VARCHAR2(40) NOT NULL,
  peso VARCHAR2(40) NOT NULL,
  coste NUMBER(6,2) NOT NULL,
  activo BOOLEAN DEFAULT TRUE,
  fecha_Lanzamiento VARCHAR2(40) NOT NULL,
  id_Agencia NUMBER(4),
  autor_Examen VARCHAR(150) NOT NULL,
  CONSTRAINT PK_Agencias PRIMARY KEY (id_Satelite),
  CONSTRAINT FK_Id_Agencia FOREIGN KEY (id_Agencia) REFERENCES AGENCIAS,
);


CREATE TABLE DETALLE_SATELITE(
  id_Detalle NUMBER(4) NOT NULL,
  velocidad_Maxima NUMBER(6) NOT NULL,
  vida_Util NUMBER(3) NOT NULL,
  temperatura_Maxima NUMBER(4) NOT NULL,
  id_Satelite NUMBER(4) NOT NULL,
  autor_Examen VARCHAR(150) NOT NULL,
  CONSTRAINT PK_Detalle PRIMARY KEY (id_Detalle),
  CONSTRAINT FK_Id_Satelite FOREIGN KEY (id_Satelite) REFERENCES SATELITES,
);