PREGUNTA 1

Explica cómo funciona la relación 1:N entre Agencia y Satelite tanto en SQL como en Java.

Esta planteado de esta forma por motivos logicos, un satelite no pertenecera a varias agencias pero una agencia si que podra tener varios satelites.

PREGUNTA 2

Explica por qué en Java utilizamos:

private Agencia agencia;

y no:

private int agenciaId;

Usamos esto porque necesitamos todos los datos de Agencia y no solo el id.

PREGUNTA 3

Explica qué ventaja aporta PreparedStatement frente a concatenar SQL manualmente.

Es mas rapido porque la BD optimiza la consulta una sola vez aunque se ejecute muchas veces.
