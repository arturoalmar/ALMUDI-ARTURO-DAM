-- =========================================
-- AUTOR: ARTURO ALMUDI
-- GRUPO: DAM2
-- EXAMEN JDBC AWS RDS
-- FECHA: 02/06/2026
-- BASE DE DATOS: almudi_arturo_dam_rec_1
-- =========================================

CREATE TABLE centros_forenses (
    id               SERIAL,
    nombre           VARCHAR(100) NOT NULL,
    pais             VARCHAR(60)  NOT NULL,
    nivel_seguridad  VARCHAR(20)  NOT NULL,
    autor_examen     VARCHAR(150) NOT NULL,
    CONSTRAINT PK_centros_forenses PRIMARY KEY (id)
);

-- Relación 1:N → un centro tiene muchas muestras
CREATE TABLE muestras_forenses (
    id               SERIAL,
    codigo_caso      VARCHAR(50)  NOT NULL,
    tipo_muestra     VARCHAR(50)  NOT NULL,
    fecha_recogida   VARCHAR(40)  NOT NULL,
    estado_custodia  VARCHAR(40)  NOT NULL,
    fk_centro_id     INTEGER      NOT NULL,
    autor_examen     VARCHAR(150) NOT NULL,
    CONSTRAINT PK_muestras_forenses  PRIMARY KEY (id),
    CONSTRAINT FK_muestra_centro     FOREIGN KEY (fk_centro_id) REFERENCES centros_forenses(id)
);

-- Relación 1:1 → una muestra tiene un único informe (UNIQUE garantiza 1:1)
CREATE TABLE informes_forenses (
    id               SERIAL,
    adn_positivo     BOOLEAN      NOT NULL DEFAULT FALSE,
    nivel_riesgo     INTEGER      NOT NULL,
    conclusion       VARCHAR(255) NOT NULL,
    fk_muestra_id    INTEGER      NOT NULL,
    autor_examen     VARCHAR(150) NOT NULL,
    CONSTRAINT PK_informes_forenses  PRIMARY KEY (id),
    CONSTRAINT FK_informe_muestra    FOREIGN KEY (fk_muestra_id) REFERENCES muestras_forenses(id),
    CONSTRAINT UQ_informe_muestra    UNIQUE (fk_muestra_id)
);
