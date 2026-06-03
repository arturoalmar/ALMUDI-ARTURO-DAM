package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.beans.Agencia;
import examen.Almudi.Arturo.beans.Satelite;
import examen.Almudi.Arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SateliteDAOImpl extends AbstractDAO<Satelite> {

    private static final String SQL_FIND_ALL =
            "SELECT s.id_Satelite, s.nombre AS satelite_nombre, s.orbita, s.peso, s.coste, s.activo, " +
            "s.fecha_Lanzamiento, s.autor_Examen AS satelite_autor, " +
            "a.id_Agencia, a.nombre AS agencia_nombre, a.pais, a.fecha_Fundacion, a.autor_Examen AS agencia_autor " +
            "FROM SATELITES s " +
            "INNER JOIN AGENCIAS a ON s.id_Agencia = a.id_Agencia " +
            "ORDER BY s.id_Satelite";

    private static final String SQL_FIND =
            "SELECT s.id_Satelite, s.nombre AS satelite_nombre, s.orbita, s.peso, s.coste, s.activo, " +
            "s.fecha_Lanzamiento, s.autor_Examen AS satelite_autor, " +
            "a.id_Agencia, a.nombre AS agencia_nombre, a.pais, a.fecha_Fundacion, a.autor_Examen AS agencia_autor " +
            "FROM SATELITES s " +
            "INNER JOIN AGENCIAS a ON s.id_Agencia = a.id_Agencia " +
            "WHERE s.id_Satelite = ?";

    private static final String SQL_SATELITES_ACTIVOS =
            "SELECT * FROM SATELITES WHERE activo = TRUE";

    private static final String SQL_INSERT =
            "INSERT INTO SATELITES " +
            "(nombre, orbita, peso, coste, activo, fecha_Lanzamiento, id_Agencia, autor_Examen) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE SATELITES " +
            "SET nombre = ?, orbita = ?, peso = ?, coste = ?, activo = ?, " +
            "fecha_Lanzamiento = ?, id_Agencia = ?, autor_Examen = ? " +
            "WHERE id_Satelite = ?";

    private static final String SQL_DELETE =
            "DELETE FROM SATELITES WHERE id_Satelite = ?";

    public SateliteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(Satelite satelite) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, satelite.getNombre());
            motorSQL.getPs().setString(2, satelite.getOrbita());
            motorSQL.getPs().setInt(3, satelite.getPeso());
            motorSQL.getPs().setInt(4, satelite.getCoste());
            motorSQL.getPs().setBoolean(5, satelite.getActivo());
            motorSQL.getPs().setString(6, satelite.getFecha_Lanzamiento());
            motorSQL.getPs().setLong(7, satelite.getId_Agencia().getId_Agencia());
            motorSQL.getPs().setString(8, satelite.getAutor_Examen());
            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, Satelite satelite) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, satelite.getNombre());
            motorSQL.getPs().setString(2, satelite.getOrbita());
            motorSQL.getPs().setInt(3, satelite.getPeso());
            motorSQL.getPs().setInt(4, satelite.getCoste());
            motorSQL.getPs().setBoolean(5, satelite.getActivo());
            motorSQL.getPs().setString(6, satelite.getFecha_Lanzamiento());
            motorSQL.getPs().setLong(7, satelite.getId_Agencia().getId_Agencia());
            motorSQL.getPs().setString(8, satelite.getAutor_Examen());
            motorSQL.getPs().setInt(9, id);
            int rows = motorSQL.executeUpdate();
            System.out.println("ACTUALIZADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void delete(int id) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);
            int rows = motorSQL.executeUpdate();
            System.out.println("ELIMINADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public Satelite find(int id) {
        Satelite satelite = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                satelite = mapRow(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return satelite;
    }

    @Override
    public ArrayList<Satelite> findAll() {
        ArrayList<Satelite> lista = new ArrayList<>();
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

    private Satelite mapRow(ResultSet rs) throws Exception {
        Agencia agencia = new Agencia();
        agencia.setId_Agencia(rs.getLong("id_Agencia"));
        agencia.setNombre(rs.getString("agencia_nombre"));
        agencia.setPais(rs.getString("pais"));
        agencia.setFecha_Fundacion(rs.getString("fecha_Fundacion"));
        agencia.setAutor_Examen(rs.getString("agencia_autor"));

        Satelite satelite = new Satelite();
        satelite.setId_Satelite(rs.getLong("id_Satelite"));
        satelite.setNombre(rs.getString("satelite_nombre"));
        satelite.setOrbita(rs.getString("orbita"));
        satelite.setPeso(rs.getInt("peso"));
        satelite.setCoste(rs.getInt("coste"));
        satelite.setActivo(rs.getBoolean("activo"));
        satelite.setFecha_Lanzamiento(rs.getString("fecha_Lanzamiento"));
        satelite.setAutor_Examen(rs.getString("satelite_autor"));
        satelite.setId_Agencia(agencia);

        return satelite;
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
