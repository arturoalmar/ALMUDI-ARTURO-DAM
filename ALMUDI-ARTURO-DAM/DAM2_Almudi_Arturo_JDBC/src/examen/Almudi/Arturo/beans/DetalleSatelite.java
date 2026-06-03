package examen.Almudi.Arturo.beans;

public class DetalleSatelite {

    private long id_Detalle;
    private int velocidad_Maxima;
    private int combustible;
    private int vida_Util;
    private int temperatura_Maxima;
    private Satelite id_Satelite;
    private String autor_Examen;

    public long getId_Detalle() {
        return id_Detalle;
    }

    public void setId_Detalle(long id_Detalle) {
        this.id_Detalle = id_Detalle;
    }

    public int getVelocidad_Maxima() {
        return velocidad_Maxima;
    }

    public void setVelocidad_Maxima(int velocidad_Maxima) {
        this.velocidad_Maxima = velocidad_Maxima;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public int getVida_Util() {
        return vida_Util;
    }

    public void setVida_Util(int vida_Util) {
        this.vida_Util = vida_Util;
    }

    public int getTemperatura_Maxima() {
        return temperatura_Maxima;
    }

    public void setTemperatura_Maxima(int temperatura_Maxima) {
        this.temperatura_Maxima = temperatura_Maxima;
    }

    public Satelite getId_Satelite() {
        return id_Satelite;
    }

    public void setId_Satelite(Satelite id_Satelite) {
        this.id_Satelite = id_Satelite;
    }

    public String getAutor_Examen() {
        return autor_Examen;
    }

    public void setAutor_Examen(String autor_Examen) {
        this.autor_Examen = autor_Examen;
    }

    @Override
    public String toString() {
        return "DetalleSatelite{" +
                "id_Detalle=" + id_Detalle +
                ", velocidad_Maxima=" + velocidad_Maxima +
                ", combustible=" + combustible +
                ", vida_Util=" + vida_Util +
                ", temperatura_Maxima=" + temperatura_Maxima +
                ", id_Satelite=" + id_Satelite +
                ", autor_Examen='" + autor_Examen + '\'' +
                '}';
    }
}
