/*
   =========================================
   AUTOR: ARTURO ALMUDI
   =========================================
*/

package examen.almudi.arturo.dao;

import examen.almudi.arturo.beans.Soc;
import examen.almudi.arturo.beans.InformeIncidente;
import examen.almudi.arturo.beans.Incidente;
import examen.almudi.arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class IncidenteDAOImpl extends AbstractDAO<Incidente> {

    // INNER JOIN con soc para poblar el objeto soc
    private static final String SQL_FIND_ALL =
            "SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion, " +
                    "incidentes.estado, incidentes.autor_examen, " +
                    "socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id " +
                    "ORDER BY incidentes.id";

    private static final String SQL_FIND =
            "SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion, " +
                    "incidentes.estado, incidentes.autor_examen, " +
                    "socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id " +
                    "WHERE incidentes.id = ?";

    private static final String SQL_FIND_BY_soc =
            "SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion, " +
                    "incidentes.estado, incidentes.autor_examen, " +
                    "socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id " +
                    "WHERE incidentes.fk_soc_id = ?";

    // INNER JOIN triple: socS + INFORMES para poblar soc e informe
    private static final String SQL_FIND_WITH_INFORME =
            "SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion, " +
                    "incidentes.estado, incidentes.autor_examen, " +
                    "socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen " +
                    "informes_incidentes.id, informes_incidentes.malware_detectado, informes_incidentes.nivel_severidad, informes_incidentes.conclusion, informes_incidentes.autor_examen" +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id " +
                    "INNER JOIN informes_incidentes ON incidentes.id = informes_incidentes.fk_incidente_id " +
                    "WHERE incidentes.id = ?";

    // BONUS_QUERY_ADVANCED: INCIDENCIAS_PELIGROSAS
    private static final String SQL_Incidencias_PELIGROSAS =
            "SELECT incidentes.id, incidentes.codigo_incidente, incidentes.tipo_incidente, incidentes.fecha_deteccion, " +
                    "incidentes.estado, incidentes.autor_examen, " +
                    "socs.id, socs.nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen " +
                    "informes_incidentes.id, informes_incidentes.malware_detectado, informes_incidentes.nivel_severidad, informes_incidentes.conclusion, informes_incidentes.autor_examen" +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id " +
                    "INNER JOIN informes_incidentes ON incidentes.id = informes_incidentes.fk_incidente_id " +
                    "WHERE informes_incidentes.malware_detectado = TRUE AND informes_incidentes.nivel_severidad > 90 AND socs.pais = 'ESPAÑA'";

    private static final String SQL_INSERT =
            "INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE incidentes " +
                    "SET codigo_incidente = ?, tipo_incidente = ?, fecha_deteccion = ?, estado = ?, " +
                    "fk_soc_id = ?, autor_examen = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM incidentes WHERE id = ?";

    public IncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    public ArrayList<Incidente> findBysoc(int socId) {
        ArrayList<Incidente> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_soc);
            motorSQL.getPs().setInt(1, socId);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lst.add(mapRowBase(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }

    // devuelve la muestra con su informe relacionado (join triple)
    public Incidente findWithInforme(int id) {
        Incidente muestra = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_WITH_INFORME);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                muestra = mapRowWithInforme(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return muestra;
    }

    public ArrayList<Incidente> findIncidenciasPeligrosas() {
        ArrayList<Incidente> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_Incidencias_PELIGROSAS);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lst.add(mapRowWithInforme(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }

    private Incidente mapRowBase(ResultSet rs) throws Exception {
        Soc soc = new Soc();
        soc.setId(rs.getLong("soc_id"));
        soc.setNombre(rs.getString("soc_nombre"));
        soc.setPais(rs.getString("pais"));
        soc.setNivelSeguridad(rs.getString("nivel_seguridad"));
        soc.setAutorExamen(rs.getString("soc_autor"));

        Incidente incidente = new Incidente();
        incidente.setId(rs.getLong("muestra_id"));
        incidente.setCodigo_incidente(rs.getString("codigo_incidente"));
        incidente.setTipo_incidente(rs.getString("tipo_incidente"));
        incidente.setFecha_deteccion(rs.getString("fecha_deteccion"));
        incidente.setEstado(rs.getString("estado"));
        incidente.setAutorExamen(rs.getString("muestra_autor"));
        incidente.setSoc(soc);
        return incidente;
    }

    private Incidente mapRowWithInforme(ResultSet rs) throws Exception {
        Incidente incidente = mapRowBase(rs);

        InformeIncidente informe = new InformeIncidente();
        informe.setId(rs.getLong("informe_id"));
        informe.setMalware_detectado(rs.getBoolean("malware_detectado"));
        informe.setNivel_severidad(rs.getInt("nivel_severidad"));
        informe.setConclusion(rs.getString("conclusion"));
        informe.setAutorExamen(rs.getString("informe_autor"));

        Incidente.setInforme(informe);
        return incidente;
    }

    @Override
    public void add(Incidente object) {

    }

    @Override
    public void update(int id, Incidente object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Incidente find(int id) {
        return null;
    }

    @Override
    public ArrayList<Incidente> findAll() {
        return null;
    }
}
