INSERT INTO AGENCIAS (id_Agencia, nombre, pais, fecha_Fundacion, autor_Examen) VALUES
('HOPE', 'España', '28/05/2020', 'Arturo'),
('Amazon', 'España', '26/05/2020', 'Arturo'),
('RuralVia', 'España', '24/05/2020', 'Arturo'),
('Google', 'España', '20/05/2020', 'Arturo'),
('San Valero', 'España', '28/05/2020', 'Arturo');

INSERT INTO SATELITES (id_Satelite, nombre, orbita, peso, coste, activo, fecha_Lanzamiento, id_Agencia, autor_Examen) VALUES
('HOPE', 'No se que poner', 300, 2000, TRUE, '28/05/2026', 1, 'Arturo'),
('Amazon', 'No se que poner', 400, 3000, FALSE, '28/05/2026', 2, 'Arturo'),
('RuralVia', 'No se que poner', 500, 4000, TRUE, '28/05/2026', 3, 'Arturo'),
('Google', 'No se que poner', 200, 2000, TRUE, '28/05/2026', 4, 'Arturo'),
('San Valero', 'No se que poner', 350, 2000, FALSE, '28/05/2026', 5, 'Arturo');

INSERT INTO DETALLE_SATELITE (id_Detalle, velocidad_Maxima, vida_Util, temperatura_Maxima, id_Satelite, autor_Examen) VALUES
(1000, 20, 200, 1, 'Arturo'),
(500, 30, 200, 2, 'Arturo'),
(5000, 10, 300, 3, 'Arturo'),
2500, 20, 200, 4, 'Arturo'),
(1000, 20, 200, 5, 'Arturo');