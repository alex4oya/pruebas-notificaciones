package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.adaptadores;

public class vista_adaptador_general {
    private String datos,sala,comedor,temperatura,bomba,seguridad,exterior,rutinas;

    vista_adaptador_general(){

    }
    public vista_adaptador_general(String datos, String sala, String comedor, String temperatura, String bomba, String seguridad, String exterior, String rutinas) {
        this.datos = datos;
        this.sala = sala;
        this.comedor = comedor;
        this.temperatura = temperatura;
        this.bomba = bomba;
        this.seguridad = seguridad;
        this.exterior = exterior;
        this.rutinas = rutinas;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getComedor() {
        return comedor;
    }

    public void setComedor(String comedor) {
        this.comedor = comedor;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getBomba() {
        return bomba;
    }

    public void setBomba(String bomba) {
        this.bomba = bomba;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getRutinas() {
        return rutinas;
    }

    public void setRutinas(String rutinas) {
        this.rutinas = rutinas;
    }
}
