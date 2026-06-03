package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.beans.Agencia;
import examen.Almudi.Arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AgenciaDAOImpl extends AbstractDAO<Agencia> {

    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM AGENCIAS " +
                    "ORDER BY id_Agencia";

    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM AGENCIAS " +
                    "WHERE id_Agencia = ?";

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

    private static final String SQL_UPDATE =
            "UPDATE AGENCIAS " +
                    "SET " +
                    "nombre = ?, " +
                    "pais = ?, " +
                    "fecha_Fundacion = ?, " +
                    "autor_Examen = ? " +
                    "WHERE id_Agencia = ?";

    public AgenciaDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(Agencia agencia) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, agencia.getNombre());
            motorSQL.getPs().setString(2, agencia.getPais());
            motorSQL.getPs().setString(3, agencia.getFecha_Fundacion());
            motorSQL.getPs().setString(4, agencia.getAutor_Examen());

            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);

        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, Agencia agencia) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Agencia find(int id) {
        Agencia agencia = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                agencia = mapRow(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return agencia;
    }

    @Override
    public ArrayList<Agencia> findAll() {
        ArrayList<Agencia> lista = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lista;
    }

    private Agencia mapRow(ResultSet rs) throws Exception {
        Agencia agencia = new Agencia();
        agencia.setId_Agencia(rs.getLong("id_Agencia"));
        agencia.setNombre(rs.getString("nombre"));
        agencia.setPais(rs.getString("pais"));
        agencia.setFecha_Fundacion(rs.getString("fecha_Fundacion"));
        agencia.setAutor_Examen(rs.getString("autor_Examen"));
        return agencia;
    }

    @Override
    public void check() {
        try {
            motorSQL.connect();
            if (motorSQL.conn != null && !motorSQL.conn.isClosed()) {
                System.out.println("CONEXION OK");
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }
}
