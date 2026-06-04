/*
   =========================================
   AUTOR: ARTURO ALMUDI
   =========================================
*/

package examen.almudi.arturo.dao;

import examen.almudi.arturo.beans.Soc;
import examen.almudi.arturo.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SocDAOImpl extends AbstractDAO<Soc> {

    private static final String SQL_FIND_ALL =
            "SELECT * FROM socs ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * FROM socs WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE socs " +
                    "SET nombre = ?, pais = ?, nivel_seguridad = ?, autor_examen = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM socs WHERE id = ?";

    public SocDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    private Soc mapRow(ResultSet rs) throws Exception {
        Soc c = new Soc();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));
        c.setPais(rs.getString("pais"));
        c.setNivelSeguridad(rs.getString("nivel_seguridad"));
        c.setAutorExamen(rs.getString("autor_examen"));
        return c;
    }

    @Override
    public void add(Soc object) {

    }

    @Override
    public void update(int id, Soc object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Soc find(int id) {
        return null;
    }

    @Override
    public ArrayList<Soc> findAll() {
        return null;
    }
}
