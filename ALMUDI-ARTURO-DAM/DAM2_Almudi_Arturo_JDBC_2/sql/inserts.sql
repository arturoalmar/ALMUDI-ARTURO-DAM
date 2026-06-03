-- =========================================
-- AUTOR: ARTURO ALMUDI
-- GRUPO: DAM2
-- EXAMEN JDBC AWS RDS
-- FECHA: 02/06/2026
-- =========================================

INSERT INTO centros_forenses (nombre, pais, nivel_seguridad, autor_examen) VALUES
('Instituto Nacional de Toxicologia', 'ESPAÑA',   'ALTO',  'Arturo_Almudi_DAM2'),
('Centro Forense de Madrid',          'ESPAÑA',   'MEDIO', 'Arturo_Almudi_DAM2'),
('Laboratorio Forense de Barcelona',  'ESPAÑA',   'ALTO',  'Arturo_Almudi_DAM2'),
('Centre des Sciences Forensiques',   'FRANCIA',  'MEDIO', 'Arturo_Almudi_DAM2'),
('Europäisches Forensik Institut',    'ALEMANIA', 'BAJO',  'Arturo_Almudi_DAM2');

INSERT INTO muestras_forenses (codigo_caso, tipo_muestra, fecha_recogida, estado_custodia, fk_centro_id, autor_examen) VALUES
('CASO-2026-001', 'ADN',     '15/01/2026', 'EN_PROCESO', 1, 'Arturo_Almudi_DAM2'),
('CASO-2026-002', 'HUELLA',  '20/02/2026', 'CERRADO',    2, 'Arturo_Almudi_DAM2'),
('CASO-2026-003', 'ADN',     '05/03/2026', 'EN_PROCESO', 3, 'Arturo_Almudi_DAM2'),
('CASO-2026-004', 'FIBRA',   '10/04/2026', 'PENDIENTE',  4, 'Arturo_Almudi_DAM2'),
('CASO-2026-005', 'ADN',     '28/05/2026', 'EN_PROCESO', 5, 'Arturo_Almudi_DAM2');

-- NOTA: fk_muestra_id UNIQUE garantiza relación 1:1 con muestras_forenses
-- Registros 1 y 3 cumplen MUESTRAS_PELIGROSAS (adn_positivo=TRUE, nivel_riesgo>90, pais=ESPAÑA)
INSERT INTO informes_forenses (adn_positivo, nivel_riesgo, conclusion, fk_muestra_id, autor_examen) VALUES
(TRUE,  95, 'ADN coincide con sospechoso principal. Riesgo extremo.',    1, 'Arturo_Almudi_DAM2'),
(FALSE, 45, 'Huella no identificada. Sin coincidencias en base de datos.',2, 'Arturo_Almudi_DAM2'),
(TRUE,  92, 'Perfil ADN positivo. Coincidencia con caso anterior.',       3, 'Arturo_Almudi_DAM2'),
(TRUE,  75, 'Fibra compatible con vehiculo del sospechoso.',              4, 'Arturo_Almudi_DAM2'),
(FALSE, 30, 'Muestra degradada. Resultado no concluyente.',               5, 'Arturo_Almudi_DAM2');
