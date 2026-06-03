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
                    "pais, " +
                    "fecha_Fundacion, " +
                    "autor_Examen" +
                    ") " +
                    "VALUES " +
                    "(" +
                    "?, ?, ?, ?" +
                    ")";

    public void add(Agencia agencia) {
        MotorSQL motorSQL = null;
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, agencia.getNombre());
            motorSQL.getPs().setString(2, agencia.getPais());
            motorSQL.getPs().setString(3, agencia.getFecha_Fundacion());
            motorSQL.getPs().setString(4, agencia.getAutor_Examen());

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

    protected void printError(Exception e){
        System.out.println(
                "[ERROR] " +
                        e.getMessage());
    }

}
