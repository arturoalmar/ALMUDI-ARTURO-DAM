package examen.Almudi.Arturo.beans;

public class Agencia {

    private long id_Agencia;
    private String nombre;
    private String pais;
    private String fecha_Fundacion;
    private String autor_Examen;

    public long getId_Agencia() {
        return id_Agencia;
    }

    public void setId_Agencia(long id_Agencia) {
        this.id_Agencia = id_Agencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFecha_Fundacion() {
        return fecha_Fundacion;
    }

    public void setFecha_Fundacion(String fecha_Fundacion) {
        this.fecha_Fundacion = fecha_Fundacion;
    }

    public String getAutor_Examen() {
        return autor_Examen;
    }

    public void setAutor_Examen(String autor_Examen) {
        this.autor_Examen = autor_Examen;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "id_Agencia=" + id_Agencia +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", fecha_Fundacion='" + fecha_Fundacion + '\'' +
                ", autor_Examen='" + autor_Examen + '\'' +
                '}';
    }
}
