package examen.almudi.arturo.dao;

import examen.almudi.arturo.motores.MotorSQL;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected MotorSQL motorSQL;

    public AbstractDAO(MotorSQL motorSQL) {
        this.motorSQL = motorSQL;
    }

    protected void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

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

    protected void updateDynamic(String tabla, int id, LinkedHashMap<String, Object> campos) {
        if (campos == null || campos.isEmpty()) {
            System.out.println("[WARN] nada que actualizar, el mapa esta vacio");
            return;
        }

        StringBuilder sql = new StringBuilder("UPDATE " + tabla + " SET ");
        List<String> keys = new ArrayList<>(campos.keySet());
        for (int i = 0; i < keys.size(); i++) {
            sql.append(keys.get(i)).append(" = ?");
            if (i < keys.size() - 1) sql.append(", ");
        }
        sql.append(" WHERE id = ?");

        try {
            motorSQL.connect();
            motorSQL.prepare(sql.toString());
            int idx = 1;
            for (Object val : campos.values()) {
                motorSQL.getPs().setObject(idx++, val);
            }
            motorSQL.getPs().setInt(idx, id);
            motorSQL.executeUpdate();
            System.out.println("[OK] UPDATE dinamico ejecutado: " + sql);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }
}
