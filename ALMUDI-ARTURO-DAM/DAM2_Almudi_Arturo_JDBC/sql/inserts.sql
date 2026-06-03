INSERT INTO AGENCIAS (nombre, pais, fecha_Fundacion, autor_Examen) VALUES
('NASA', 'Estados Unidos', '01/10/1958', 'Arturo_Almudi_DAM2'),
('ESA', 'Europa', '30/05/1975', 'Arturo_Almudi_DAM2'),
('SpaceX', 'Estados Unidos', '14/03/2002', 'Arturo_Almudi_DAM2'),
('JAXA', 'Japon', '01/10/2003', 'Arturo_Almudi_DAM2'),
('Roscosmos', 'Rusia', '25/02/1992', 'Arturo_Almudi_DAM2');

INSERT INTO SATELITES (nombre, orbita, peso, coste, activo, fecha_Lanzamiento, id_Agencia, autor_Examen) VALUES
('Hubble', 'Baja Terrestre', 11110, 4700, TRUE, '24/04/1990', 1, 'Arturo_Almudi_DAM2'),
('Sentinel-1A', 'Polar Heliosincrona', 2300, 320, TRUE, '03/04/2014', 2, 'Arturo_Almudi_DAM2'),
('Starlink-1', 'Baja Terrestre', 260, 50, TRUE, '24/05/2019', 3, 'Arturo_Almudi_DAM2'),
('Himawari-8', 'Geoestacionaria', 3500, 530, FALSE, '07/10/2014', 4, 'Arturo_Almudi_DAM2'),
('Meteor-M2', 'Polar', 2900, 210, FALSE, '08/07/2014', 5, 'Arturo_Almudi_DAM2');

INSERT INTO DETALLE_SATELITE (velocidad_Maxima, combustible, vida_Util, temperatura_Maxima, id_Satelite, autor_Examen) VALUES
(28000, 500, 20, 120, 1, 'Arturo_Almudi_DAM2'),
(27000, 300, 7, 100, 2, 'Arturo_Almudi_DAM2'),
(27600, 200, 5, 90, 3, 'Arturo_Almudi_DAM2'),
(11000, 400, 15, 150, 4, 'Arturo_Almudi_DAM2'),
(26500, 350, 10, 110, 5, 'Arturo_Almudi_DAM2');
