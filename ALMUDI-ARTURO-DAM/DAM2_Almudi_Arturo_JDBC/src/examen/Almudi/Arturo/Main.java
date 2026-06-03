package examen.Almudi.Arturo;

import java.util.ArrayList;

import examen.Almudi.Arturo.DAO.SateliteDAOImpl;
import examen.Almudi.Arturo.beans.Agencia;
import examen.Almudi.Arturo.beans.Satelite;
import examen.Almudi.Arturo.motores.MotorFactory;

public class Main {

    public static void main(String[] args) {

        SateliteDAOImpl sateliteDAO =
                new SateliteDAOImpl(MotorFactory.create(MotorFactory.POSTGRE));

        sateliteDAO.check();

        // TEST 1: ADD SATELLITE
        Agencia agencia = new Agencia();
        agencia.setId_Agencia(1);

        Satelite satelite = new Satelite();
        satelite.setNombre("300");
        satelite.setOrbita("Tierra");
        satelite.setPeso(2007);
        satelite.setCoste(2000);
        satelite.setActivo(true);
        satelite.setFecha_Lanzamiento("30/05/2025");
        satelite.setId_Agencia(agencia);
        satelite.setAutor_Examen("Arturo_Almudi_DAM2");
        sateliteDAO.add(satelite);

        // TEST 4: FIND ALL SATELLITES
        ArrayList<Satelite> lstSatelite = sateliteDAO.findAll();
        for (Satelite s : lstSatelite) {
            System.out.println(s.toString());
        }

    }
}
