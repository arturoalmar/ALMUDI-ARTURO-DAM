package examen.Almudi.Arturo.beans;

public class Satelite {

    private long id_Satelite;
    private String nombre;
    private String orbita;
    private int peso;
    private int coste;
    private Boolean activo;
    private String fecha_Lanzamiento;
    private Agencia id_Agencia;
    private String autor_Examen;

    public long getId_Satelite() {
        return id_Satelite;
    }

    public void setId_Satelite(long id_Satelite) {
        this.id_Satelite = id_Satelite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrbita() {
        return orbita;
    }

    public void setOrbita(String orbita) {
        this.orbita = orbita;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getFecha_Lanzamiento() {
        return fecha_Lanzamiento;
    }

    public void setFecha_Lanzamiento(String fecha_Lanzamiento) {
        this.fecha_Lanzamiento = fecha_Lanzamiento;
    }

    public Agencia getId_Agencia() {
        return id_Agencia;
    }

    public void setId_Agencia(Agencia id_Agencia) {
        this.id_Agencia = id_Agencia;
    }

    public String getAutor_Examen() {
        return autor_Examen;
    }

    public void setAutor_Examen(String autor_Examen) {
        this.autor_Examen = autor_Examen;
    }

    @Override
    public String toString() {
        return "Satelite{" +
                "id_Satelite=" + id_Satelite +
                ", nombre='" + nombre + '\'' +
                ", orbita='" + orbita + '\'' +
                ", peso=" + peso +
                ", coste=" + coste +
                ", activo=" + activo +
                ", fecha_Lanzamiento='" + fecha_Lanzamiento + '\'' +
                ", id_Agencia=" + id_Agencia +
                ", autor_Examen='" + autor_Examen + '\'' +
                '}';
    }
}
