/*
   =========================================
   AUTOR: ARTURO ALMUDI
   =========================================
*/

package examen.almudi.arturo;

import examen.almudi.arturo.dao.SocDAOImpl;
import examen.almudi.arturo.dao.IncidenteDAOImpl;
import examen.almudi.arturo.beans.Soc;
import examen.almudi.arturo.beans.Incidente;
import examen.almudi.arturo.motores.MotorFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        IncidenteDAOImpl incidenteDAO = new IncidenteDAOImpl(
                MotorFactory.create(MotorFactory.POSTGRE));

        incidenteDAO.check();

        // cogemos el primer centro que exista en la bd
        SocDAOImpl socDAO = new SocDAOImpl(
                MotorFactory.create(MotorFactory.POSTGRE));
        Soc centro = socDAO.findAll().get(0);

        // TEST 1: ADD
        Incidente test = new Incidente();
        test.setCodigo_incidente("INC-2026-TEST");
        test.setTipo_incidente("APAGON");
        test.setFecha_deteccion("02/06/2026");
        test.setEstado("EN_PROCESO");
        test.setSoc(centro);
        test.setAutorExamen("Arturo_Almudi_DAM2");
        incidenteDAO.add(test);

        // TEST 2: UPDATE
        test.setEstado("CERRADO");
        incidenteDAO.update(1, test);

        // TEST 3: FIND
        Incidente encontrada = incidenteDAO.find(1);
        System.out.println("FIND -> " + encontrada);

        // TEST 4: FIND ALL
        ArrayList<Incidente> todas = incidenteDAO.findAll();
        System.out.println("FIND ALL -> " + todas.size() + " registros");
        for (Incidente m : todas) {
            System.out.println(m);
        }

        // TEST 5: FIND BY CENTRO
        ArrayList<Incidente> porCentro = incidenteDAO.findBysoc(1);
        System.out.println("BY CENTRO -> " + porCentro.size() + " registros");
        for (Incidente m : porCentro) {
            System.out.println(m);
        }

        // TEST 6: FIND WITH INFORME
        Incidente conInforme = incidenteDAO.findWithInforme(1);
        System.out.println("WITH INFORME -> " + conInforme);

        // BONUS: MUESTRAS PELIGROSAS
        ArrayList<Incidente> peligrosas = incidenteDAO.findIncidenciasPeligrosas();
        System.out.println("PELIGROSAS -> " + peligrosas.size() + " registros");
        for (Incidente m : peligrosas) {
            System.out.println(m);
        }
    }
}
