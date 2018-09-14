package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.adaptadores;

public class vista_sala_focos {
    private String uid,dato;
    vista_sala_focos(){

    }

    public vista_sala_focos(String uid, String dato) {
        this.uid = uid;
        this.dato = dato;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
