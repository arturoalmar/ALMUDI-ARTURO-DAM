CREATE TABLE AGENCIAS(
  id_Agencia SERIAL,
  nombre VARCHAR(40) NOT NULL,
  pais VARCHAR(40) NOT NULL,
  fecha_Fundacion VARCHAR(40) NOT NULL,
  autor_Examen VARCHAR(150) NOT NULL,
  CONSTRAINT PK_Agencias PRIMARY KEY (id_Agencia)
);

CREATE TABLE SATELITES(
  id_Satelite SERIAL,
  nombre VARCHAR(40) NOT NULL,
  orbita VARCHAR(40) NOT NULL,
  peso INTEGER NOT NULL,
  coste NUMERIC(6,2) NOT NULL,
  activo BOOLEAN DEFAULT TRUE,
  fecha_Lanzamiento VARCHAR(40) NOT NULL,
  id_Agencia INTEGER NOT NULL,
  autor_Examen VARCHAR(150) NOT NULL,
  CONSTRAINT PK_Satelites PRIMARY KEY (id_Satelite),
  CONSTRAINT FK_Id_Agencia FOREIGN KEY (id_Agencia) REFERENCES AGENCIAS(id_Agencia)
);

CREATE TABLE DETALLE_SATELITE(
  id_Detalle SERIAL,
  velocidad_Maxima INTEGER NOT NULL,
  combustible INTEGER NOT NULL,
  vida_Util INTEGER NOT NULL,
  temperatura_Maxima INTEGER NOT NULL,
  id_Satelite INTEGER NOT NULL,
  autor_Examen VARCHAR(150) NOT NULL,
  CONSTRAINT PK_Detalle PRIMARY KEY (id_Detalle),
  CONSTRAINT FK_Id_Satelite FOREIGN KEY (id_Satelite) REFERENCES SATELITES(id_Satelite),
  CONSTRAINT UQ_Detalle_Satelite UNIQUE (id_Satelite)
);
