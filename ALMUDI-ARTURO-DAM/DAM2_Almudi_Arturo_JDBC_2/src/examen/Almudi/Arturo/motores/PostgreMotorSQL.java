/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo.motores;

import java.sql.DriverManager;

public class PostgreMotorSQL extends MotorSQL {

    private static final String URL =
            "jdbc:postgresql://almudi-arturo-dam.cfz5l9za6byi.us-east-1.rds.amazonaws.com:5432/almudi_arturo_dam_rec_1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";
    private static final String DRIVER = "org.postgresql.Driver";

    public PostgreMotorSQL() {
        super(URL, USER, PASSWORD, DRIVER);
    }

    @Override
    public void connect() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("CONEXIÓN OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
