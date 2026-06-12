package examen.almudi.arturo.dao;

import examen.almudi.arturo.beans.InformeIncidente;
import examen.almudi.arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InformeIncidenteDAOImpl extends AbstractDAO<InformeIncidente> {

    private static final String SQL_FIND_ALL =
            "SELECT * FROM informes_incidente ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * FROM informes_incidente WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO informes_incidente (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE informes_incidente " +
                    "SET malware_detectado = ?, nivel_severidad = ?, conclusion = ?, autor_examen = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM informes_incidente WHERE id = ?";

    public InformeIncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(InformeIncidente object) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setBoolean(1, object.isMalwareDetectado());
            motorSQL.getPs().setInt(2, object.getNivelSeveridad());
            motorSQL.getPs().setString(3, object.getConclusion());
            motorSQL.getPs().setLong(4, object.getIdIncidente());
            motorSQL.getPs().setString(5, object.getAutorExamen());
            motorSQL.executeUpdate();
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, InformeIncidente object) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setBoolean(1, object.isMalwareDetectado());
            motorSQL.getPs().setInt(2, object.getNivelSeveridad());
            motorSQL.getPs().setString(3, object.getConclusion());
            motorSQL.getPs().setString(4, object.getAutorExamen());
            motorSQL.getPs().setInt(5, id);
            motorSQL.executeUpdate();
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
            motorSQL.executeUpdate();
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public InformeIncidente find(int id) {
        InformeIncidente informe = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                informe = new InformeIncidente();
                informe.setId(rs.getLong("id"));
                informe.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                informe.setNivelSeveridad(rs.getInt("nivel_severidad"));
                informe.setConclusion(rs.getString("conclusion"));
                informe.setIdIncidente(rs.getLong("fk_incidente_id"));
                informe.setAutorExamen(rs.getString("autor_examen"));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return informe;
    }

    @Override
    public ArrayList<InformeIncidente> findAll() {
        ArrayList<InformeIncidente> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                InformeIncidente informe = new InformeIncidente();
                informe.setId(rs.getLong("id"));
                informe.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                informe.setNivelSeveridad(rs.getInt("nivel_severidad"));
                informe.setConclusion(rs.getString("conclusion"));
                informe.setIdIncidente(rs.getLong("fk_incidente_id"));
                informe.setAutorExamen(rs.getString("autor_examen"));
                lst.add(informe);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }

    public void updateDynamicInforme(int id, Boolean malwareDetectado, Integer nivelSeveridad, String conclusion) {
        LinkedHashMap<String, Object> campos = new LinkedHashMap<>();
        if (malwareDetectado != null) campos.put("malware_detectado", malwareDetectado);
        if (nivelSeveridad != null) campos.put("nivel_severidad", nivelSeveridad);
        if (conclusion != null) campos.put("conclusion", conclusion);
        updateDynamic("informes_incidente", id, campos);
    }
}
