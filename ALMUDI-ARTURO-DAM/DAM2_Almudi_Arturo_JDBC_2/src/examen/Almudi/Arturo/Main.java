/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo;

import examen.Almudi.Arturo.DAO.CentroForenseDAOImpl;
import examen.Almudi.Arturo.DAO.MuestraForenseDAOImpl;
import examen.Almudi.Arturo.beans.CentroForense;
import examen.Almudi.Arturo.beans.MuestraForense;
import examen.Almudi.Arturo.motores.MotorFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        MuestraForenseDAOImpl muestraDAO = new MuestraForenseDAOImpl(
                MotorFactory.create(MotorFactory.POSTGRE));

        muestraDAO.check();

        // cogemos el primer centro que exista en la bd
        CentroForenseDAOImpl centroDAO = new CentroForenseDAOImpl(
                MotorFactory.create(MotorFactory.POSTGRE));
        CentroForense centro = centroDAO.findAll().get(0);

        // TEST 1: ADD
        MuestraForense muestra = new MuestraForense();
        muestra.setCodigoCaso("CASO-2026-TEST");
        muestra.setTipoMuestra("ADN");
        muestra.setFechaRecogida("02/06/2026");
        muestra.setEstadoCustodia("EN_PROCESO");
        muestra.setCentro(centro);
        muestra.setAutorExamen("Arturo_Almudi_DAM2");
        muestraDAO.add(muestra);

        // TEST 2: UPDATE
        muestra.setEstadoCustodia("CERRADO");
        muestraDAO.update(1, muestra);

        // TEST 3: FIND
        MuestraForense encontrada = muestraDAO.find(1);
        System.out.println("FIND -> " + encontrada);

        // TEST 4: FIND ALL
        ArrayList<MuestraForense> todas = muestraDAO.findAll();
        System.out.println("FIND ALL -> " + todas.size() + " registros");
        for (MuestraForense m : todas) {
            System.out.println(m);
        }

        // TEST 5: FIND BY CENTRO
        ArrayList<MuestraForense> porCentro = muestraDAO.findByCentro(1);
        System.out.println("BY CENTRO -> " + porCentro.size() + " registros");
        for (MuestraForense m : porCentro) {
            System.out.println(m);
        }

        // TEST 6: FIND WITH INFORME
        MuestraForense conInforme = muestraDAO.findWithInforme(1);
        System.out.println("WITH INFORME -> " + conInforme);

        // BONUS: MUESTRAS PELIGROSAS
        ArrayList<MuestraForense> peligrosas = muestraDAO.findMuestrasPeligrosas();
        System.out.println("PELIGROSAS -> " + peligrosas.size() + " registros");
        for (MuestraForense m : peligrosas) {
            System.out.println(m);
        }
    }
}
