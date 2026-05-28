package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.beans.DetalleSatelite;
import examen.Almudi.Arturo.beans.Satelite;
import examen.Almudi.Arturo.motores.MotorSQL;

import java.sql.Array;
import java.util.ArrayList;

public class SateliteDAOImpl extends AbstractDAO<DetalleSatelite>{
    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM SATELITES " +
                    "ORDER BY id_Satelite";

    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM SATELITES " +
                    "WHERE id_Satelite = ?";

    private static final String SQL_SATELITES =
            "SELECT * " +
                    "FROM SATELITES " +
                    "WHERE activo == TRUE";

    private static final String SQL_INSERT =
            "INSERT INTO SATELITES " +
                    "(" +
                    "nombre, " +
                    "orbita, " +
                    "peso, " +
                    "coste, " +
                    "activo, " +
                    "fecha_Lanzamiento, " +
                    "id_Agencia, " +
                    "autor_Examen" +
                    ") " +
                    "VALUES " +
                    "(" +
                    "?, ?, ?, ?, ?, ?, ?, ?" +
                    ")";

    private static final String SQL_UPDATE =
            "UPDATE SATELITES " +
                    "SET " +
                    "nombre = ?, " +
                    "orbita = ?, " +
                    "peso = ?, " +
                    "coste = ?, " +
                    "activo = ?, " +
                    "fecha_Lanzamiento = ?, " +
                    "id_Agencia = ?, " +
                    "autor_Examen = ? " +
                    "WHERE id = ?";

    public SateliteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    public void add(Satelite satelite) {
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, satelite.getNombre());
            motorSQL.getPs().setString(2, satelite.getOrbita());
            motorSQL.getPs().setInt(3, satelite.getPeso());
            motorSQL.getPs().setInt(4, satelite.getCoste());
            motorSQL.getPs().setBoolean(5, satelite.getActivo());
            motorSQL.getPs().setString(6, satelite.getFecha_Lanzamiento());
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

    public void check() {
        try {
            motorSQL.connect();
            if (motorSQL.conn != null &&
                    !motorSQL.conn.isClosed()) {
                System.out.println("CONEXION OK");
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void add(DetalleSatelite object) {

    }

    @Override
    public void update(int id, DetalleSatelite object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public DetalleSatelite find(int id) {
        return null;
    }

    @Override
    public ArrayList<DetalleSatelite> findAll() {
        return null;
    }
}
