package examen.almudi.arturo.dao;

import examen.almudi.arturo.beans.Soc;
import examen.almudi.arturo.beans.InformeIncidente;
import examen.almudi.arturo.beans.Incidente;
import examen.almudi.arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class IncidenteDAOImpl extends AbstractDAO<Incidente> {

    private static final String SQL_FIND_ALL =
            "SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente, " +
                    "incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor, " +
                    "socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id = socs.id " +
                    "ORDER BY incidentes.id";

    private static final String SQL_FIND =
            "SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente, " +
                    "incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor, " +
                    "socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id = socs.id " +
                    "WHERE incidentes.id = ?";

    private static final String SQL_FIND_BY_SOC =
            "SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente, " +
                    "incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor, " +
                    "socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id = socs.id " +
                    "WHERE incidentes.fk_soc_id = ?";

    private static final String SQL_FIND_WITH_INFORME =
            "SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente, " +
                    "incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor, " +
                    "socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor, " +
                    "informes_incidente.id AS informe_id, informes_incidente.malware_detectado, " +
                    "informes_incidente.nivel_severidad, informes_incidente.conclusion, informes_incidente.autor_examen AS informe_autor " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id = socs.id " +
                    "INNER JOIN informes_incidente ON incidentes.id = informes_incidente.fk_incidente_id " +
                    "WHERE incidentes.id = ?";

    private static final String SQL_INCIDENTES_CRITICOS =
            "SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente, " +
                    "incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor, " +
                    "socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor, " +
                    "informes_incidente.id AS informe_id, informes_incidente.malware_detectado, " +
                    "informes_incidente.nivel_severidad, informes_incidente.conclusion, informes_incidente.autor_examen AS informe_autor " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id = socs.id " +
                    "INNER JOIN informes_incidente ON incidentes.id = informes_incidente.fk_incidente_id " +
                    "WHERE informes_incidente.malware_detectado = TRUE " +
                    "AND informes_incidente.nivel_severidad > 90 " +
                    "AND socs.pais = 'ESPAÑA'";

    private static final String SQL_INCIDENTES_SEVERIDAD =
            "SELECT incidentes.id AS incidente_id, incidentes.codigo_incidente, incidentes.tipo_incidente, " +
                    "incidentes.fecha_deteccion, incidentes.estado, incidentes.autor_examen AS incidente_autor, " +
                    "socs.id AS soc_id, socs.nombre AS soc_nombre, socs.pais, socs.nivel_seguridad, socs.autor_examen AS soc_autor, " +
                    "informes_incidente.id AS informe_id, informes_incidente.malware_detectado, " +
                    "informes_incidente.nivel_severidad, informes_incidente.conclusion, informes_incidente.autor_examen AS informe_autor " +
                    "FROM incidentes " +
                    "INNER JOIN socs ON incidentes.fk_soc_id = socs.id " +
                    "INNER JOIN informes_incidente ON incidentes.id = informes_incidente.fk_incidente_id " +
                    "WHERE informes_incidente.nivel_severidad > ?";

    private static final String SQL_INSERT =
            "INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) " +
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

    public ArrayList<Incidente> findBySoc(int socId) {
        ArrayList<Incidente> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_SOC);
            motorSQL.getPs().setInt(1, socId);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                Soc soc = new Soc();
                soc.setId(rs.getLong("soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getString("nivel_seguridad"));
                soc.setAutorExamen(rs.getString("soc_autor"));
                Incidente incidente = new Incidente();
                incidente.setId(rs.getLong("incidente_id"));
                incidente.setCodigoIncidente(rs.getString("codigo_incidente"));
                incidente.setTipoIncidente(rs.getString("tipo_incidente"));
                incidente.setFechaDeteccion(rs.getString("fecha_deteccion"));
                incidente.setEstado(rs.getString("estado"));
                incidente.setAutorExamen(rs.getString("incidente_autor"));
                incidente.setSoc(soc);
                lst.add(incidente);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }

    public Incidente findWithInforme(int id) {
        Incidente incidente = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_WITH_INFORME);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                Soc soc = new Soc();
                soc.setId(rs.getLong("soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getString("nivel_seguridad"));
                soc.setAutorExamen(rs.getString("soc_autor"));
                incidente = new Incidente();
                incidente.setId(rs.getLong("incidente_id"));
                incidente.setCodigoIncidente(rs.getString("codigo_incidente"));
                incidente.setTipoIncidente(rs.getString("tipo_incidente"));
                incidente.setFechaDeteccion(rs.getString("fecha_deteccion"));
                incidente.setEstado(rs.getString("estado"));
                incidente.setAutorExamen(rs.getString("incidente_autor"));
                incidente.setSoc(soc);
                InformeIncidente informe = new InformeIncidente();
                informe.setId(rs.getLong("informe_id"));
                informe.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                informe.setNivelSeveridad(rs.getInt("nivel_severidad"));
                informe.setConclusion(rs.getString("conclusion"));
                informe.setAutorExamen(rs.getString("informe_autor"));
                incidente.setInforme(informe);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return incidente;
    }

    public ArrayList<Incidente> findIncidentesCriticos() {
        ArrayList<Incidente> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INCIDENTES_CRITICOS);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                Soc soc = new Soc();
                soc.setId(rs.getLong("soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getString("nivel_seguridad"));
                soc.setAutorExamen(rs.getString("soc_autor"));
                Incidente incidente = new Incidente();
                incidente.setId(rs.getLong("incidente_id"));
                incidente.setCodigoIncidente(rs.getString("codigo_incidente"));
                incidente.setTipoIncidente(rs.getString("tipo_incidente"));
                incidente.setFechaDeteccion(rs.getString("fecha_deteccion"));
                incidente.setEstado(rs.getString("estado"));
                incidente.setAutorExamen(rs.getString("incidente_autor"));
                incidente.setSoc(soc);
                InformeIncidente informe = new InformeIncidente();
                informe.setId(rs.getLong("informe_id"));
                informe.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                informe.setNivelSeveridad(rs.getInt("nivel_severidad"));
                informe.setConclusion(rs.getString("conclusion"));
                informe.setAutorExamen(rs.getString("informe_autor"));
                incidente.setInforme(informe);
                lst.add(incidente);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }

    public ArrayList<Incidente> findBySeveridad(int minSeveridad) {
        ArrayList<Incidente> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INCIDENTES_SEVERIDAD);
            motorSQL.getPs().setInt(1, minSeveridad);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                Soc soc = new Soc();
                soc.setId(rs.getLong("soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getString("nivel_seguridad"));
                soc.setAutorExamen(rs.getString("soc_autor"));
                Incidente incidente = new Incidente();
                incidente.setId(rs.getLong("incidente_id"));
                incidente.setCodigoIncidente(rs.getString("codigo_incidente"));
                incidente.setTipoIncidente(rs.getString("tipo_incidente"));
                incidente.setFechaDeteccion(rs.getString("fecha_deteccion"));
                incidente.setEstado(rs.getString("estado"));
                incidente.setAutorExamen(rs.getString("incidente_autor"));
                incidente.setSoc(soc);
                InformeIncidente informe = new InformeIncidente();
                informe.setId(rs.getLong("informe_id"));
                informe.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                informe.setNivelSeveridad(rs.getInt("nivel_severidad"));
                informe.setConclusion(rs.getString("conclusion"));
                informe.setAutorExamen(rs.getString("informe_autor"));
                incidente.setInforme(informe);
                lst.add(incidente);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }

    @Override
    public void add(Incidente object) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, object.getCodigoIncidente());
            motorSQL.getPs().setString(2, object.getTipoIncidente());
            motorSQL.getPs().setString(3, object.getFechaDeteccion());
            motorSQL.getPs().setString(4, object.getEstado());
            motorSQL.getPs().setLong(5, object.getSoc().getId());
            motorSQL.getPs().setString(6, object.getAutorExamen());
            motorSQL.executeUpdate();
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, Incidente object) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, object.getCodigoIncidente());
            motorSQL.getPs().setString(2, object.getTipoIncidente());
            motorSQL.getPs().setString(3, object.getFechaDeteccion());
            motorSQL.getPs().setString(4, object.getEstado());
            motorSQL.getPs().setLong(5, object.getSoc().getId());
            motorSQL.getPs().setString(6, object.getAutorExamen());
            motorSQL.getPs().setInt(7, id);
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
    public Incidente find(int id) {
        Incidente incidente = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                Soc soc = new Soc();
                soc.setId(rs.getLong("soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getString("nivel_seguridad"));
                soc.setAutorExamen(rs.getString("soc_autor"));
                incidente = new Incidente();
                incidente.setId(rs.getLong("incidente_id"));
                incidente.setCodigoIncidente(rs.getString("codigo_incidente"));
                incidente.setTipoIncidente(rs.getString("tipo_incidente"));
                incidente.setFechaDeteccion(rs.getString("fecha_deteccion"));
                incidente.setEstado(rs.getString("estado"));
                incidente.setAutorExamen(rs.getString("incidente_autor"));
                incidente.setSoc(soc);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return incidente;
    }

    @Override
    public ArrayList<Incidente> findAll() {
        ArrayList<Incidente> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                Soc soc = new Soc();
                soc.setId(rs.getLong("soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getString("nivel_seguridad"));
                soc.setAutorExamen(rs.getString("soc_autor"));
                Incidente incidente = new Incidente();
                incidente.setId(rs.getLong("incidente_id"));
                incidente.setCodigoIncidente(rs.getString("codigo_incidente"));
                incidente.setTipoIncidente(rs.getString("tipo_incidente"));
                incidente.setFechaDeteccion(rs.getString("fecha_deteccion"));
                incidente.setEstado(rs.getString("estado"));
                incidente.setAutorExamen(rs.getString("incidente_autor"));
                incidente.setSoc(soc);
                lst.add(incidente);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lst;
    }
}
