/* =========================================
   AUTOR: ARTURO ALMUDI
   GRUPO: DAM2
   EXAMEN JDBC AWS RDS
   FECHA: 02/06/2026
   =========================================
*/
package examen.Almudi.Arturo.beans;

public class MuestraForense {

    private long id;
    private String codigoCaso;
    private String tipoMuestra;
    private String fechaRecogida;
    private String estadoCustodia;
    private CentroForense centro;
    private InformeForense informe;
    private String autorExamen;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(String fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public String getEstadoCustodia() {
        return estadoCustodia;
    }

    public void setEstadoCustodia(String estadoCustodia) {
        this.estadoCustodia = estadoCustodia;
    }

    public CentroForense getCentro() {
        return centro;
    }

    public void setCentro(CentroForense centro) {
        this.centro = centro;
    }

    public InformeForense getInforme() {
        return informe;
    }

    public void setInforme(InformeForense informe) {
        this.informe = informe;
    }

    public String getAutorExamen() {
        return autorExamen;
    }

    public void setAutorExamen(String autorExamen) {
        this.autorExamen = autorExamen;
    }

    @Override
    public String toString() {
        return "MuestraForense{" +
                "id=" + id +
                ", codigoCaso='" + codigoCaso + '\'' +
                ", tipoMuestra='" + tipoMuestra + '\'' +
                ", fechaRecogida='" + fechaRecogida + '\'' +
                ", estadoCustodia='" + estadoCustodia + '\'' +
                ", centro=" + centro +
                ", informe=" + informe +
                ", autorExamen='" + autorExamen + '\'' +
                '}';
    }
}
