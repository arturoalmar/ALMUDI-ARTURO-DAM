/*
   =========================================
   AUTOR: ARTURO ALMUDI
   =========================================
*/

package examen.almudi.arturo.dao;

import examen.almudi.arturo.motores.MotorSQL;

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
}