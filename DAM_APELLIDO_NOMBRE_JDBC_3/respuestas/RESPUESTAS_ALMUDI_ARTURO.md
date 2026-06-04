PREGUNTA 1
Explica cómo funciona la relación 1:N entre Soc e Incidente tanto en SQL como en Java.

En SQL la relación 1:N se implementa añadiendo una clave foránea en la tabla del lado "muchos",
que en este caso es INCIDENTES. La columna fk_socs_id guarda el id del socs al que pertenece cada muestra.
Así un socs puede tener muchas incidencias pero cada incidencia solo pertenece a un soc.
Si intentas insertar una incidencia con un fk_socs_id que no existe en SOCS la base de datos lanza un error de integridad referencial.

Para recuperar los datos relacionados en una sola consulta se usa INNER JOIN:

PREGUNTA 2
Explica por qué en Java utilizamos:
private Soc soc;
y no:
private int socId;

Porque en orientacion a objetos trabajamos con objetos, no con numeros sueltos.
Si guardáramos solo el id del soc tendríamos que hacer una segunda consulta a la base de datos
cada vez que necesitáramos saber el nombre por ejemplo.

PREGUNTA 3
Explica qué ventaja aporta PreparedStatement frente a concatenar SQL manualmente.

La principal ventaja es la seguridad. Con concatenación manual un usuario podría inyectar código SQL y modificar la consulta:
