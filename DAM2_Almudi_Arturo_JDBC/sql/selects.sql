SATELITE
private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM SATELITES " +
                    "ORDER BY id_Satelite";

private static final String SQL_SATELITES =
            "SELECT * " +
                    "FROM SATELITES " +
                    "WHERE activo = TRUE";

==================================================
AGENCIA
private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM AGENCIAS " +
                    "ORDER BY id_Agencia";