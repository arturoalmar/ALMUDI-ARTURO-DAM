/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo.beans;

public class InformeForense {

    private long id;
    private boolean adnPositivo;
    private int nivelRiesgo;
    private String conclusion;
    private long idMuestra;
    private String autorExamen;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAdnPositivo() {
        return adnPositivo;
    }

    public void setAdnPositivo(boolean adnPositivo) {
        this.adnPositivo = adnPositivo;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public long getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(long idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getAutorExamen() {
        return autorExamen;
    }

    public void setAutorExamen(String autorExamen) {
        this.autorExamen = autorExamen;
    }

    @Override
    public String toString() {
        return "InformeForense{" +
                "id=" + id +
                ", adnPositivo=" + adnPositivo +
                ", nivelRiesgo=" + nivelRiesgo +
                ", conclusion='" + conclusion + '\'' +
                ", idMuestra=" + idMuestra +
                ", autorExamen='" + autorExamen + '\'' +
                '}';
    }
}
