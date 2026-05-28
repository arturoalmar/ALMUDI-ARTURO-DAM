package examen.Almudi.Arturo;

import java.util.ArrayList;

import examen.Almudi.Arturo.DAO.SateliteDAOImpl;
import examen.Almudi.Arturo.beans.DetalleSatelite;
import examen.Almudi.Arturo.beans.Satelite;
import examen.Almudi.Arturo.motores.MotorSQL;
import examen.Almudi.Arturo.motores.MotorFactory;

public class Main {

    public static void main(String[] args) {

        SateliteDAOImpl SateliteDAO =
                new SateliteDAOImpl(MotorFactory.
                        create(
                                MotorFactory.POSTGRE));
        SateliteDAO.check();
        // Prueba Unitaria: ADD Película
        Satelite satelite = new Satelite();
        satelite.setNombre("300");
        satelite.setOrbita("Tierra");
        satelite.setPeso(2007);
        satelite.setCoste(2000);
        satelite.setActivo(true);
        satelite.setFecha_Lanzamiento("30/05/2025");
        satelite.setAutor_Examen("Arturo_Almudi");
        SateliteDAO.add(satelite);
        ArrayList<DetalleSatelite> lstSatelite = SateliteDAO.findAll();
        for (DetalleSatelite satelite1:lstSatelite
        ) {
            System.out.println(satelite1.toString());
        }

    }
}
