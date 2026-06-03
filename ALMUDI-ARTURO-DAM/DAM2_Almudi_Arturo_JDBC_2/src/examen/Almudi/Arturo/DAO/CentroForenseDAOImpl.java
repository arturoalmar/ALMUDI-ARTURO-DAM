/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.beans.CentroForense;
import examen.Almudi.Arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CentroForenseDAOImpl extends AbstractDAO<CentroForense> {

    private static final String SQL_FIND_ALL =
            "SELECT * FROM centros_forenses ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * FROM centros_forenses WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO centros_forenses (nombre, pais, nivel_seguridad, autor_examen) " +
            "VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE centros_forenses " +
            "SET nombre = ?, pais = ?, nivel_seguridad = ?, autor_examen = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM centros_forenses WHERE id = ?";

    public CentroForenseDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(CentroForense centro) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, centro.getNombre());
            motorSQL.getPs().setString(2, centro.getPais());
            motorSQL.getPs().setString(3, centro.getNivelSeguridad());
            motorSQL.getPs().setString(4, centro.getAutorExamen());
            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, CentroForense centro) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, centro.getNombre());
            motorSQL.getPs().setString(2, centro.getPais());
            motorSQL.getPs().setString(3, centro.getNivelSeguridad());
            motorSQL.getPs().setString(4, centro.getAutorExamen());
            motorSQL.getPs().setInt(5, id);
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
    public CentroForense find(int id) {
        CentroForense resultado = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                resultado = mapRow(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return resultado;
    }

    @Override
    public ArrayList<CentroForense> findAll() {
        ArrayList<CentroForense> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lst.add(mapRow(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }

    private CentroForense mapRow(ResultSet rs) throws Exception {
        CentroForense c = new CentroForense();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));
        c.setPais(rs.getString("pais"));
        c.setNivelSeguridad(rs.getString("nivel_seguridad"));
        c.setAutorExamen(rs.getString("autor_examen"));
        return c;
    }

}
