/*
   =========================================
   AUTOR: ARTURO ALMUDI
   =========================================
*/

package examen.almudi.arturo.dao;

import examen.almudi.arturo.beans.InformeIncidente;
import examen.almudi.arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InformeIncidenteDAOImpl extends AbstractDAO<InformeIncidente> {

    private static final String SQL_FIND_ALL =
            "SELECT * FROM informes_incidentes ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * FROM informes_incidentes WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO informes_incidentes (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE informes_incidentes " +
                    "SET malware_detectado = ?, nivel_severidad = ?, conclusion = ?, autor_examen = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM informes_incidentes WHERE id = ?";

    public InformeIncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    private InformeIncidente mapRow(ResultSet rs) throws Exception {
        InformeIncidente informe = new InformeIncidente();
        informe.setId(rs.getLong("id"));
        informe.setMalware_detectado(rs.getBoolean("positivo"));
        informe.setNivel_severidad(rs.getInt("nivel_severidad"));
        informe.setConclusion(rs.getString("conclusion"));
        informe.setAutorExamen(rs.getString("autor_examen"));
        return informe;
    }

    @Override
    public void add(InformeIncidente object) {

    }

    @Override
    public void update(int id, InformeIncidente object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public InformeIncidente find(int id) {
        return null;
    }

    @Override
    public ArrayList<InformeIncidente> findAll() {
        return null;
    }
}
