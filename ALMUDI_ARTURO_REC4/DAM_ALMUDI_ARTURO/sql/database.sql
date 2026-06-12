CREATE TABLE socs (
    id               SERIAL,
    nombre           VARCHAR(100) NOT NULL,
    pais             VARCHAR(60)  NOT NULL,
    nivel_seguridad  VARCHAR(20)  NOT NULL,
    autor_examen     VARCHAR(150) NOT NULL,
    CONSTRAINT PK_socs PRIMARY KEY (id)
);

CREATE TABLE incidentes (
    id                  SERIAL,
    codigo_incidente    VARCHAR(50)  NOT NULL,
    tipo_incidente      VARCHAR(50)  NOT NULL,
    fecha_deteccion     VARCHAR(40)  NOT NULL,
    estado              VARCHAR(40)  NOT NULL,
    fk_soc_id           INTEGER      NOT NULL,
    autor_examen        VARCHAR(150) NOT NULL,
    CONSTRAINT PK_incidentes   PRIMARY KEY (id),
    CONSTRAINT FK_fk_soc_id    FOREIGN KEY (fk_soc_id) REFERENCES socs(id)
);

CREATE TABLE informes_incidente (
    id                  SERIAL,
    malware_detectado   BOOLEAN      NOT NULL DEFAULT FALSE,
    nivel_severidad     INTEGER      NOT NULL,
    conclusion          VARCHAR(255) NOT NULL,
    fk_incidente_id     INTEGER      NOT NULL,
    autor_examen        VARCHAR(150) NOT NULL,
    CONSTRAINT PK_informes_incidente PRIMARY KEY (id),
    CONSTRAINT FK_informe_incidente   FOREIGN KEY (fk_incidente_id) REFERENCES incidentes(id),
    CONSTRAINT UQ_fk_incidente_id     UNIQUE (fk_incidente_id)
);
