-- =========================================
-- AUTOR: ARTURO ALMUDI
-- =========================================

INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES
('Algo', 'ESPAÑA',   'ALTO',  'Arturo_Almudi_DAM2'),
('Algo2', 'ESPAÑA',   'MEDIO', 'Arturo_Almudi_DAM2'),
('Algo3',  'ESPAÑA',   'ALTO',  'Arturo_Almudi_DAM2'),
('Algo4',   'FRANCIA',  'MEDIO', 'Arturo_Almudi_DAM2'),
('Algo5',    'ALEMANIA', 'BAJO',  'Arturo_Almudi_DAM2');

INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES
('INC-2026-001', 'HACKEO', '15/01/2026', 'EN_PROCESO', 1, 'Arturo_Almudi_DAM2'),
('INC-2026-002', 'HACKEO', '20/02/2026', 'CERRADO',    2, 'Arturo_Almudi_DAM2'),
('INC-2026-003', 'APAGON', '05/03/2026', 'EN_PROCESO', 3, 'Arturo_Almudi_DAM2'),
('INC-2026-004', 'HACKEO', '10/04/2026', 'PENDIENTE',  4, 'Arturo_Almudi_DAM2'),
('INC-2026-005', 'HACKEO', '28/05/2026', 'EN_PROCESO', 5, 'Arturo_Almudi_DAM2');

INSERT INTO informes_incidentes (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES
(TRUE,  95, 'NO FUNCIONA NADA.', 1, 'Arturo_Almudi_DAM2'),
(FALSE, 45, 'NO CONTROLAMOS EL SISTEMA.',2, 'Arturo_Almudi_DAM2'),
(TRUE,  92, 'NO HAY LUZ.', 3, 'Arturo_Almudi_DAM2'),
(TRUE,  75, 'NO FUNCIONA NADA.', 4, 'Arturo_Almudi_DAM2'),
(FALSE, 30, 'NO CONTROLAMOS EL SISTEMA.', 5, 'Arturo_Almudi_DAM2');
