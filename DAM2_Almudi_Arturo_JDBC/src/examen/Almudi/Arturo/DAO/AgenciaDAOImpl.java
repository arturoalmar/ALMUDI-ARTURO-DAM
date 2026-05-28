package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.beans.Agencia;
import examen.Almudi.Arturo.motores.MotorSQL;

import java.sql.Array;
import java.util.ArrayList;

public class AgenciaDAOImpl {
    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM SATELITES " +
                    "ORDER BY id_Satelite";

    private static final String SQL_INSERT =
            "INSERT INTO AGENCIAS " +
                    "(" +
                    "nombre, " +
                    "orbita, " +
                    "peso, " +
                    "coste, " +
                    "activo," +
                    "fecha_Lanzamiento" +
                    "id_Agencia, " +
                    "autor_Examen" +
                    ") " +
                    "VALUES " +
                    "(" +
                    "?, ?, ?, ?, ?, ?, ?, ?" +
                    ")";

    public void add(Agencia agencia) {
        MotorSQL motorSQL;
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, agencia.getNombre());
            motorSQL.getPs().setString(2, agencia.getOrbita());
            motorSQL.getPs().setInt(3, agencia.getPeso());
            motorSQL.getPs().setInt(4, agencia.getCoste());
            motorSQL.getPs().setBoolean(5, agencia.getActivo());
            motorSQL.getPs().setString(6, agencia.getFecha_Lanzamiento());
            motorSQL.getPs().setArray(7, (Array) satelite.getId_Agencia());
            motorSQL.getPs().setString(8, satelite.getAutor_Examen());

            int rows = motorSQL.executeUpdate();
            System.out.println(
                    "INSERTADOS: " +
                            rows);

        }catch (Exception e){

            printError(e);

        }finally {

            motorSQL.close();
        }
    }
}
