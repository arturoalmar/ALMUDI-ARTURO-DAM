-- FIND ALL SATELITES con Agencia (INNER JOIN)
SELECT s.id_Satelite, s.nombre AS satelite_nombre, s.orbita, s.peso, s.coste, s.activo,
       s.fecha_Lanzamiento, s.autor_Examen AS satelite_autor,
       a.id_Agencia, a.nombre AS agencia_nombre, a.pais, a.fecha_Fundacion, a.autor_Examen AS agencia_autor
FROM SATELITES s
INNER JOIN AGENCIAS a ON s.id_Agencia = a.id_Agencia
ORDER BY s.id_Satelite;

-- FIND SATELITE POR ID con Agencia (INNER JOIN)
SELECT s.id_Satelite, s.nombre AS satelite_nombre, s.orbita, s.peso, s.coste, s.activo,
       s.fecha_Lanzamiento, s.autor_Examen AS satelite_autor,
       a.id_Agencia, a.nombre AS agencia_nombre, a.pais, a.fecha_Fundacion, a.autor_Examen AS agencia_autor
FROM SATELITES s
INNER JOIN AGENCIAS a ON s.id_Agencia = a.id_Agencia
WHERE s.id_Satelite = 1;

-- FIND ALL AGENCIAS
SELECT * FROM AGENCIAS ORDER BY id_Agencia;

-- FIND AGENCIA POR ID
SELECT * FROM AGENCIAS WHERE id_Agencia = 1;

-- SATELITES ACTIVOS
SELECT * FROM SATELITES WHERE activo = TRUE;

-- BONUS_QUERY_ADVANCED: satelites activos con Agencia y DetalleSatelite (INNER JOIN triple)
SELECT s.id_Satelite, s.nombre AS satelite_nombre, s.orbita, s.peso, s.coste,
       s.fecha_Lanzamiento, s.autor_Examen AS satelite_autor,
       a.id_Agencia, a.nombre AS agencia_nombre, a.pais,
       d.id_Detalle, d.velocidad_Maxima, d.combustible, d.vida_Util, d.temperatura_Maxima
FROM SATELITES s
INNER JOIN AGENCIAS a ON s.id_Agencia = a.id_Agencia
INNER JOIN DETALLE_SATELITE d ON s.id_Satelite = d.id_Satelite
WHERE s.activo = TRUE;
