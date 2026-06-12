package examen.almudi.arturo.beans;

public class InformeIncidente {

    private long id;
    private boolean malwareDetectado;
    private int nivelSeveridad;
    private String conclusion;
    private long idIncidente;
    private String autorExamen;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public boolean isMalwareDetectado() { return malwareDetectado; }
    public void setMalwareDetectado(boolean malwareDetectado) { this.malwareDetectado = malwareDetectado; }

    public int getNivelSeveridad() { return nivelSeveridad; }
    public void setNivelSeveridad(int nivelSeveridad) { this.nivelSeveridad = nivelSeveridad; }

    public String getConclusion() { return conclusion; }
    public void setConclusion(String conclusion) { this.conclusion = conclusion; }

    public long getIdIncidente() { return idIncidente; }
    public void setIdIncidente(long idIncidente) { this.idIncidente = idIncidente; }

    public String getAutorExamen() { return autorExamen; }
    public void setAutorExamen(String autorExamen) { this.autorExamen = autorExamen; }

    @Override
    public String toString() {
        return "InformeIncidente{" +
                "id=" + id +
                ", malwareDetectado=" + malwareDetectado +
                ", nivelSeveridad=" + nivelSeveridad +
                ", conclusion='" + conclusion + '\'' +
                ", idIncidente=" + idIncidente +
                ", autorExamen='" + autorExamen + '\'' +
                '}';
    }
}
