/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.beans.InformeForense;
import examen.Almudi.Arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InformeForenseDAOImpl extends AbstractDAO<InformeForense> {

    private static final String SQL_FIND_ALL =
            "SELECT * FROM informes_forenses ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * FROM informes_forenses WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO informes_forenses (adn_positivo, nivel_riesgo, conclusion, fk_muestra_id, autor_examen) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE informes_forenses " +
            "SET adn_positivo = ?, nivel_riesgo = ?, conclusion = ?, autor_examen = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM informes_forenses WHERE id = ?";

    public InformeForenseDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(InformeForense informe) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setBoolean(1, informe.isAdnPositivo());
            motorSQL.getPs().setInt(2, informe.getNivelRiesgo());
            motorSQL.getPs().setString(3, informe.getConclusion());
            motorSQL.getPs().setLong(4, informe.getIdMuestra());
            motorSQL.getPs().setString(5, informe.getAutorExamen());
            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, InformeForense informe) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setBoolean(1, informe.isAdnPositivo());
            motorSQL.getPs().setInt(2, informe.getNivelRiesgo());
            motorSQL.getPs().setString(3, informe.getConclusion());
            motorSQL.getPs().setString(4, informe.getAutorExamen());
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
    public InformeForense find(int id) {
        InformeForense informe = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                informe = mapRow(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return informe;
    }

    @Override
    public ArrayList<InformeForense> findAll() {
        ArrayList<InformeForense> lista = new ArrayList<>();
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

    private InformeForense mapRow(ResultSet rs) throws Exception {
        InformeForense informe = new InformeForense();
        informe.setId(rs.getLong("id"));
        informe.setAdnPositivo(rs.getBoolean("adn_positivo"));
        informe.setNivelRiesgo(rs.getInt("nivel_riesgo"));
        informe.setConclusion(rs.getString("conclusion"));
        informe.setAutorExamen(rs.getString("autor_examen"));
        return informe;
    }

}
