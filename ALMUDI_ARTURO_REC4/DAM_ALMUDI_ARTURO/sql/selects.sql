SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente,
       incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor,
       socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id = socs.id
ORDER BY incidentes.id;

SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente,
       incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor,
       socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id = socs.id
WHERE incidentes.id = 1;

SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente,
       incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor,
       socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id = socs.id
WHERE incidentes.fk_soc_id = 1;

SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente,
       incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor,
       socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor,
       informes_incidente.id AS informe_id, informes_incidente.malware_detectado,
       informes_incidente.nivel_severidad, informes_incidente.conclusion, informes_incidente.autor_examen AS informe_autor
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id = socs.id
INNER JOIN informes_incidente ON incidentes.id = informes_incidente.fk_incidente_id
WHERE incidentes.id = 1;

INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen)
VALUES ('INC-2026-006', 'HACKEO', '15/01/2026', 'EN_PROCESO', 1, 'Arturo_Almudi_Marco_DAM3');

UPDATE incidentes
SET codigo_incidente = 'INC-2026-006', tipo_incidente = 'HACKEO', fecha_deteccion = '15/01/2026',
    estado = 'CERRADO', fk_soc_id = 1, autor_examen = 'Arturo_Almudi_Marco_DAM3'
WHERE id = 1;

DELETE FROM incidentes WHERE id = 1;

SELECT * FROM socs ORDER BY id;

SELECT * FROM socs WHERE id = 1;

INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen)
VALUES ('SOC Nuevo', 'ESPAÑA', 'ALTO', 'Arturo_Almudi_Marco_DAM3');

UPDATE socs
SET nombre = 'SOC Madrid Actualizado', pais = 'ESPAÑA', nivel_seguridad = 'MEDIO', autor_examen = 'Arturo_Almudi_Marco_DAM3'
WHERE id = 1;

DELETE FROM socs WHERE id = 1;

SELECT * FROM informes_incidente ORDER BY id;

SELECT * FROM informes_incidente WHERE id = 1;

INSERT INTO informes_incidente (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen)
VALUES (TRUE, 95, 'Sistema comprometido.', 1, 'Arturo_Almudi_Marco_DAM3');

UPDATE informes_incidente
SET malware_detectado = TRUE, nivel_severidad = 95, conclusion = 'Sistema comprometido.', autor_examen = 'Arturo_Almudi_Marco_DAM3'
WHERE id = 1;

DELETE FROM informes_incidente WHERE id = 1;

SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente,
       incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor,
       socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor,
       informes_incidente.id AS informe_id, informes_incidente.malware_detectado,
       informes_incidente.nivel_severidad, informes_incidente.conclusion, informes_incidente.autor_examen AS informe_autor
FROM incidentes
INNER JOIN socs ON incidentes.fk_soc_id = socs.id
INNER JOIN informes_incidente ON incidentes.id = informes_incidente.fk_incidente_id
WHERE informes_incidente.malware_detectado = TRUE
  AND informes_incidente.nivel_severidad > 90
  AND socs.pais = 'ESPAÑA';

UPDATE informes_incidente
SET nivel_severidad = 99, conclusion = 'ACTUALIZADO_DINAMICAMENTE'
WHERE id = 1;

UPDATE informes_incidente
SET malware_detectado = TRUE
WHERE id = 2;

UPDATE informes_incidente
SET conclusion = 'REVISION COMPLETADA'
WHERE id = 3;
