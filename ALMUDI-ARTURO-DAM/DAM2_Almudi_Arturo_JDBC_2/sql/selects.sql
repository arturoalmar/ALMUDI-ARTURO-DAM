-- =========================================
-- AUTOR: ARTURO ALMUDI
-- GRUPO: DAM2
-- EXAMEN JDBC AWS RDS
-- FECHA: 02/06/2026
-- =========================================


-- =============================================
-- CONSULTAS DAO — MuestraForenseDAOImpl
-- =============================================

-- SQL_FIND_ALL: todas las muestras con su centro (INNER JOIN)
SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida,
       m.estado_custodia, m.autor_examen AS muestra_autor,
       c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor
FROM muestras_forenses m
INNER JOIN centros_forenses c ON m.fk_centro_id = c.id
ORDER BY m.id;

-- SQL_FIND: muestra por id con su centro (INNER JOIN)
SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida,
       m.estado_custodia, m.autor_examen AS muestra_autor,
       c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor
FROM muestras_forenses m
INNER JOIN centros_forenses c ON m.fk_centro_id = c.id
WHERE m.id = 1;

-- SQL_FIND_BY_CENTRO: muestras filtradas por centro (INNER JOIN)
SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida,
       m.estado_custodia, m.autor_examen AS muestra_autor,
       c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor
FROM muestras_forenses m
INNER JOIN centros_forenses c ON m.fk_centro_id = c.id
WHERE m.fk_centro_id = 1;

-- SQL_FIND_WITH_INFORME: muestra con centro e informe (INNER JOIN triple)
SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida,
       m.estado_custodia, m.autor_examen AS muestra_autor,
       c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor,
       i.id AS informe_id, i.adn_positivo, i.nivel_riesgo, i.conclusion, i.autor_examen AS informe_autor
FROM muestras_forenses m
INNER JOIN centros_forenses c ON m.fk_centro_id = c.id
INNER JOIN informes_forenses i ON m.id = i.fk_muestra_id
WHERE m.id = 1;

-- SQL_INSERT
INSERT INTO muestras_forenses (codigo_caso, tipo_muestra, fecha_recogida, estado_custodia, fk_centro_id, autor_examen)
VALUES ('CASO-2026-006', 'ADN', '02/06/2026', 'EN_PROCESO', 1, 'Arturo_Almudi_DAM2');

-- SQL_UPDATE
UPDATE muestras_forenses
SET codigo_caso = 'CASO-2026-006', tipo_muestra = 'ADN', fecha_recogida = '02/06/2026',
    estado_custodia = 'CERRADO', fk_centro_id = 1, autor_examen = 'Arturo_Almudi_DAM2'
WHERE id = 1;

-- SQL_DELETE
DELETE FROM muestras_forenses WHERE id = 1;


-- =============================================
-- CONSULTAS DAO — CentroForenseDAOImpl
-- =============================================

-- SQL_FIND_ALL
SELECT * FROM centros_forenses ORDER BY id;

-- SQL_FIND
SELECT * FROM centros_forenses WHERE id = 1;

-- SQL_INSERT
INSERT INTO centros_forenses (nombre, pais, nivel_seguridad, autor_examen)
VALUES ('Nuevo Centro', 'ESPAÑA', 'ALTO', 'Arturo_Almudi_DAM2');

-- SQL_UPDATE
UPDATE centros_forenses
SET nombre = 'Centro Actualizado', pais = 'ESPAÑA', nivel_seguridad = 'MEDIO', autor_examen = 'Arturo_Almudi_DAM2'
WHERE id = 1;

-- SQL_DELETE
DELETE FROM centros_forenses WHERE id = 1;


-- =============================================
-- CONSULTAS DAO — InformeForenseDAOImpl
-- =============================================

-- SQL_FIND_ALL
SELECT * FROM informes_forenses ORDER BY id;

-- SQL_FIND
SELECT * FROM informes_forenses WHERE id = 1;

-- SQL_INSERT
INSERT INTO informes_forenses (adn_positivo, nivel_riesgo, conclusion, fk_muestra_id, autor_examen)
VALUES (TRUE, 88, 'Coincidencia parcial detectada.', 1, 'Arturo_Almudi_DAM2');

-- SQL_UPDATE
UPDATE informes_forenses
SET adn_positivo = TRUE, nivel_riesgo = 95, conclusion = 'Confirmado.', autor_examen = 'Arturo_Almudi_DAM2'
WHERE id = 1;

-- SQL_DELETE
DELETE FROM informes_forenses WHERE id = 1;


-- =============================================
-- BLOQUE 6 — BONUS_QUERY_ADVANCED: MUESTRAS_PELIGROSAS
-- =============================================
-- Devuelve muestras con ADN positivo, nivel de riesgo > 90 y centro en España
-- Utiliza INNER JOIN triple: muestras + centros + informes
SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida,
       m.estado_custodia, m.autor_examen AS muestra_autor,
       c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor,
       i.id AS informe_id, i.adn_positivo, i.nivel_riesgo, i.conclusion, i.autor_examen AS informe_autor
FROM muestras_forenses m
INNER JOIN centros_forenses c ON m.fk_centro_id = c.id
INNER JOIN informes_forenses i ON m.id = i.fk_muestra_id
WHERE i.adn_positivo = TRUE AND i.nivel_riesgo > 90 AND c.pais = 'ESPAÑA';


-- =============================================
-- BLOQUE 7 — DYNAMIC_UPDATE_ENGINE (ejemplo)
-- =============================================
-- El SET se construye dinamicamente en AbstractDAO segun los campos recibidos.
-- Ejemplo con 2 campos actualizados:
UPDATE muestras_forenses
SET estado_custodia = 'CERRADO', nivel_riesgo = 95
WHERE id = 1;

-- Ejemplo con 1 campo actualizado:
UPDATE muestras_forenses
SET estado_custodia = 'PENDIENTE'
WHERE id = 2;
