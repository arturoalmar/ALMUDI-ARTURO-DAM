/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo.DAO;

import examen.Almudi.Arturo.motores.MotorSQL;

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
