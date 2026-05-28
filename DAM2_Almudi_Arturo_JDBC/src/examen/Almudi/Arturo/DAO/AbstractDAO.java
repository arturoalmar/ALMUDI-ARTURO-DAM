package examen.Almudi.Arturo.DAO;

import java.util.ArrayList;
import examen.Almudi.Arturo.motores.MotorSQL;

public abstract class AbstractDAO<T>
        implements DAO<T> {
    protected MotorSQL motorSQL;
    public AbstractDAO(MotorSQL motorSQL) {
        this.motorSQL = motorSQL;
    }
    protected void printError(Exception e){
        System.out.println(
                "[ERROR] " +
                        e.getMessage());
    }

    public abstract void check();
    /*
     * CONSULTAS AVANZADAS
     */
    //public abstract ArrayList<T> findByGenero(String genero);
    //public abstract ArrayList<T> findByDirector(String director);
    //public abstract Pelicula findDetallePeliculaByPelicula(int idPelicula);
}

