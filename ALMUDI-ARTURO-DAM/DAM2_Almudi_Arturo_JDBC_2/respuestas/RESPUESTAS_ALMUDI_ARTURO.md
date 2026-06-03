# RESPUESTAS - ARTURO ALMUDI
DAM2 · Examen JDBC AWS RDS · 02/06/2026

---

## PREGUNTA 1
Explica cómo funciona la relación 1:N entre CentroForense y MuestraForense tanto en SQL como en Java.

En SQL la relación 1:N se implementa añadiendo una clave foránea en la tabla del lado "muchos", que en este caso es MUESTRAS_FORENSES. La columna fk_centro_id guarda el id del centro al que pertenece cada muestra. Así un centro puede tener muchas muestras pero cada muestra solo pertenece a un centro. Si intentas insertar una muestra con un fk_centro_id que no existe en CENTROS_FORENSES la base de datos lanza un error de integridad referencial.

Para recuperar los datos relacionados en una sola consulta se usa INNER JOIN:

```sql
SELECT m.*, c.nombre, c.pais
FROM muestras_forenses m
INNER JOIN centros_forenses c ON m.fk_centro_id = c.id
```

En Java la relación se representa con un atributo de tipo objeto dentro de MuestraForense:

```java
private CentroForense centro;
```

El DAO hace el INNER JOIN en la query y en el mapRow construye el objeto CentroForense y lo asigna a la muestra, de forma que cuando recibes una MuestraForense ya tienes el centro con todos sus datos dentro.

---

## PREGUNTA 2
Explica por qué en Java utilizamos `private CentroForense centro` y no `private int centroId`.

Porque en orientacion a objetos trabajamos con objetos, no con numeros sueltos. Si guardáramos solo el id del centro tendríamos que hacer una segunda consulta a la base de datos cada vez que necesitáramos saber el nombre o el país del centro. Con el objeto directamente podemos acceder a cualquier dato sin consultas extra:

```java
muestra.getCentro().getNombre();  // directo
muestra.getCentro().getPais();    // directo
```

Además el criterio del examen dice que el DAO no devuelve filas SQL sino objetos Java completamente relacionados. Si solo guardáramos el id estaríamos devolviendo un dato de base de datos, no un objeto real. El INNER JOIN en el DAO es lo que nos permite construir ese objeto CentroForense dentro de MuestraForense en una sola consulta.

---

## PREGUNTA 3
Explica qué ventaja aporta PreparedStatement frente a concatenar SQL manualmente.

La principal ventaja es la seguridad. Con concatenación manual un usuario malicioso podría inyectar código SQL y modificar la consulta:

```java
// peligroso
String sql = "SELECT * FROM muestras_forenses WHERE codigo_caso = '" + input + "'";
// si input es  ' OR '1'='1  devuelve todas las filas
```

Con PreparedStatement los parámetros se tratan siempre como datos y nunca pueden modificar la estructura de la consulta:

```java
motorSQL.prepare("SELECT * FROM muestras_forenses WHERE codigo_caso = ?");
motorSQL.getPs().setString(1, input);
```

La otra ventaja es el rendimiento. La base de datos compila y optimiza el plan de la consulta una sola vez cuando se prepara, y en ejecuciones repetidas solo se envían los valores, lo que es más rápido que recompilar la consulta entera cada vez.
