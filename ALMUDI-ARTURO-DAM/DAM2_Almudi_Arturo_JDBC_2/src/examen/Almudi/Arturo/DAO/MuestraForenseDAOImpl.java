/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.beans.CentroForense;
import examen.Almudi.Arturo.beans.InformeForense;
import examen.Almudi.Arturo.beans.MuestraForense;
import examen.Almudi.Arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MuestraForenseDAOImpl extends AbstractDAO<MuestraForense> {

    // INNER JOIN con CENTROS_FORENSES para poblar el objeto centro
    private static final String SQL_FIND_ALL =
            "SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida, " +
            "m.estado_custodia, m.autor_examen AS muestra_autor, " +
            "c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor " +
            "FROM muestras_forenses m " +
            "INNER JOIN centros_forenses c ON m.fk_centro_id = c.id " +
            "ORDER BY m.id";

    private static final String SQL_FIND =
            "SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida, " +
            "m.estado_custodia, m.autor_examen AS muestra_autor, " +
            "c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor " +
            "FROM muestras_forenses m " +
            "INNER JOIN centros_forenses c ON m.fk_centro_id = c.id " +
            "WHERE m.id = ?";

    private static final String SQL_FIND_BY_CENTRO =
            "SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida, " +
            "m.estado_custodia, m.autor_examen AS muestra_autor, " +
            "c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor " +
            "FROM muestras_forenses m " +
            "INNER JOIN centros_forenses c ON m.fk_centro_id = c.id " +
            "WHERE m.fk_centro_id = ?";

    // INNER JOIN triple: CENTROS + INFORMES para poblar centro e informe
    private static final String SQL_FIND_WITH_INFORME =
            "SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida, " +
            "m.estado_custodia, m.autor_examen AS muestra_autor, " +
            "c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor, " +
            "i.id AS informe_id, i.adn_positivo, i.nivel_riesgo, i.conclusion, i.autor_examen AS informe_autor " +
            "FROM muestras_forenses m " +
            "INNER JOIN centros_forenses c ON m.fk_centro_id = c.id " +
            "INNER JOIN informes_forenses i ON m.id = i.fk_muestra_id " +
            "WHERE m.id = ?";

    // BONUS_QUERY_ADVANCED: MUESTRAS_PELIGROSAS
    private static final String SQL_MUESTRAS_PELIGROSAS =
            "SELECT m.id AS muestra_id, m.codigo_caso, m.tipo_muestra, m.fecha_recogida, " +
            "m.estado_custodia, m.autor_examen AS muestra_autor, " +
            "c.id AS centro_id, c.nombre AS centro_nombre, c.pais, c.nivel_seguridad, c.autor_examen AS centro_autor, " +
            "i.id AS informe_id, i.adn_positivo, i.nivel_riesgo, i.conclusion, i.autor_examen AS informe_autor " +
            "FROM muestras_forenses m " +
            "INNER JOIN centros_forenses c ON m.fk_centro_id = c.id " +
            "INNER JOIN informes_forenses i ON m.id = i.fk_muestra_id " +
            "WHERE i.adn_positivo = TRUE AND i.nivel_riesgo > 90 AND c.pais = 'ESPAÑA'";

    private static final String SQL_INSERT =
            "INSERT INTO muestras_forenses " +
            "(codigo_caso, tipo_muestra, fecha_recogida, estado_custodia, fk_centro_id, autor_examen) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE muestras_forenses " +
            "SET codigo_caso = ?, tipo_muestra = ?, fecha_recogida = ?, estado_custodia = ?, " +
            "fk_centro_id = ?, autor_examen = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM muestras_forenses WHERE id = ?";

    public MuestraForenseDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(MuestraForense muestra) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, muestra.getCodigoCaso());
            motorSQL.getPs().setString(2, muestra.getTipoMuestra());
            motorSQL.getPs().setString(3, muestra.getFechaRecogida());
            motorSQL.getPs().setString(4, muestra.getEstadoCustodia());
            motorSQL.getPs().setLong(5, muestra.getCentro().getId());
            motorSQL.getPs().setString(6, muestra.getAutorExamen());
            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, MuestraForense muestra) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, muestra.getCodigoCaso());
            motorSQL.getPs().setString(2, muestra.getTipoMuestra());
            motorSQL.getPs().setString(3, muestra.getFechaRecogida());
            motorSQL.getPs().setString(4, muestra.getEstadoCustodia());
            motorSQL.getPs().setLong(5, muestra.getCentro().getId());
            motorSQL.getPs().setString(6, muestra.getAutorExamen());
            motorSQL.getPs().setInt(7, id);
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
    public MuestraForense find(int id) {
        MuestraForense muestra = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                muestra = mapRowBase(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return muestra;
    }

    @Override
    public ArrayList<MuestraForense> findAll() {
        ArrayList<MuestraForense> lista = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lista.add(mapRowBase(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lista;
    }

    // busca todas las muestras de un centro concreto
    public ArrayList<MuestraForense> findByCentro(int centroId) {
        ArrayList<MuestraForense> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_CENTRO);
            motorSQL.getPs().setInt(1, centroId);
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
    public MuestraForense findWithInforme(int id) {
        MuestraForense muestra = null;
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

    // BONUS: muestras con ADN positivo, riesgo > 90 y pais España
    public ArrayList<MuestraForense> findMuestrasPeligrosas() {
        ArrayList<MuestraForense> lst = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_MUESTRAS_PELIGROSAS);
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

    private MuestraForense mapRowBase(ResultSet rs) throws Exception {
        CentroForense centro = new CentroForense();
        centro.setId(rs.getLong("centro_id"));
        centro.setNombre(rs.getString("centro_nombre"));
        centro.setPais(rs.getString("pais"));
        centro.setNivelSeguridad(rs.getString("nivel_seguridad"));
        centro.setAutorExamen(rs.getString("centro_autor"));

        MuestraForense muestra = new MuestraForense();
        muestra.setId(rs.getLong("muestra_id"));
        muestra.setCodigoCaso(rs.getString("codigo_caso"));
        muestra.setTipoMuestra(rs.getString("tipo_muestra"));
        muestra.setFechaRecogida(rs.getString("fecha_recogida"));
        muestra.setEstadoCustodia(rs.getString("estado_custodia"));
        muestra.setAutorExamen(rs.getString("muestra_autor"));
        muestra.setCentro(centro);
        return muestra;
    }

    private MuestraForense mapRowWithInforme(ResultSet rs) throws Exception {
        MuestraForense muestra = mapRowBase(rs);

        InformeForense informe = new InformeForense();
        informe.setId(rs.getLong("informe_id"));
        informe.setAdnPositivo(rs.getBoolean("adn_positivo"));
        informe.setNivelRiesgo(rs.getInt("nivel_riesgo"));
        informe.setConclusion(rs.getString("conclusion"));
        informe.setAutorExamen(rs.getString("informe_autor"));

        muestra.setInforme(informe);
        return muestra;
    }

}
