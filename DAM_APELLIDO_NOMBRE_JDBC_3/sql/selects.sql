-- =========================================
-- AUTOR: ARTURO ALMUDI
-- =========================================

-- =============================================
-- CONSULTAS DAO — IncidenteDAOImpl
-- =============================================

-- SQL_FIND_ALL: todas las incidencias con su socs (INNER JOIN)
SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion,
       incidentes.estado, incidentes.autor_examen,
       socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id
ORDER BY incidentes.id;

-- SQL_FIND: incidente por id con su socs (INNER JOIN)
SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion,
       incidentes.estado, incidentes.autor_examen,
       socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id
WHERE incidentes.id = 1;

-- SQL_FIND_BY_soc: incidencias filtradas por socs (INNER JOIN)
SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion,
              incidentes.estado, incidentes.autor_examen,
              socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id
WHERE incidentes.fk_soc_id = 1;

-- SQL_FIND_WITH_INFORME: incidencia con socs e informe (INNER JOIN triple)
SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion,
              incidentes.estado, incidentes.autor_examen,
              socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen,
       informes_incidentes.id, informes_incidentes.malware_detectado, informes_incidentes.nivel_severidad, informes_incidentes.conclusion, informes_incidentes.autor_examen
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id
INNER JOIN informes_incidentes ON incidentes.id = informes_incidentes.fk_incidente_id
WHERE incidentes.id = 1;

-- SQL_INSERT
INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen)
VALUES ('INC-2026-006', 'HACKEO', '15/01/2026', 'EN_PROCESO', 1, 'Arturo_Almudi_DAM2'),;

-- SQL_UPDATE
UPDATE incidentes
SET codigo_incidente = 'INC-2026-006', tipo_incidente = 'HACKEO', fecha_deteccion = '15/01/2026',
    estado = 'EN_PROCESO', fk_soc_id = 1, autor_examen = 'Arturo_Almudi_DAM2'
WHERE id = 1;

-- SQL_DELETE
DELETE FROM incidentes WHERE id = 1;


-- =============================================
-- CONSULTAS dao — SocDAOImpl
-- =============================================

-- SQL_FIND_ALL
SELECT * FROM socs ORDER BY id;

-- SQL_FIND
SELECT * FROM socs WHERE id = 1;

-- SQL_INSERT
INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen)
VALUES ('Nuevo Algo', 'ESPAÑA', 'ALTO', 'Arturo_Almudi_DAM2');

-- SQL_UPDATE
UPDATE socs
SET nombre = 'Algooo1', pais = 'ESPAÑA', nivel_seguridad = 'MEDIO', autor_examen = 'Arturo_Almudi_DAM2'
WHERE id = 1;

-- SQL_DELETE
DELETE FROM socs WHERE id = 1;


-- =============================================
-- CONSULTAS DAO — InformeIncidenciadaoImpl
-- =============================================

-- SQL_FIND_ALL
SELECT * FROM informes_incidentes ORDER BY id;

-- SQL_FIND
SELECT * FROM informes_incidentes WHERE id = 1;

-- SQL_INSERT
INSERT INTO informes_incidentes (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen)
VALUES (TRUE,  95, 'NO FUNCIONA NADA.', 1, 'Arturo_Almudi_DAM2');

-- SQL_UPDATE
UPDATE informes_incidentes
SET malware_detectado = TRUE, nivel_severidad = 95, conclusion = 'NO FUNCIONA NADA.', autor_examen = 'Arturo_Almudi_DAM2'
WHERE id = 1;

-- SQL_DELETE
DELETE FROM informes_incidentes WHERE id = 1;


-- =============================================
-- BLOQUE 6 — BONUS_QUERY_ADVANCED: INCIDENCIAS_PELIGROSAS
-- =============================================
-- Devuelve incidencias con malware detectado, nivel de seguridad > 90 y socs en España
SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion,
              incidentes.estado, incidentes.autor_examen,
              socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen,
       informes_incidentes.id, informes_incidentes.malware_detectado, informes_incidentes.nivel_severidad, informes_incidentes.conclusion, informes_incidentes.autor_examen
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id
INNER JOIN informes_incidentes ON incidentes.id = informes_incidentes.fk_incidente_id
WHERE informes_incidentes.malware_detectado = TRUE AND informes_incidentes.nivel_severidad > 90 AND socs.pais = 'ESPAÑA';


-- =============================================
-- BLOQUE 7 — DYNAMIC_UPDATE_ENGINE (ejemplo)
-- =============================================

-- Ejemplo con 2 campos actualizados:
UPDATE incidentes
SET estado = 'CERRADO', nivel_riesgo = 95
WHERE id = 1;

-- Ejemplo con 1 campo actualizado:
UPDATE incidentes
SET estado = 'PENDIENTE'
WHERE id = 2;
