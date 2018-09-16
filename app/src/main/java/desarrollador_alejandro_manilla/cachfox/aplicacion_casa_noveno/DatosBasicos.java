package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno;

public class DatosBasicos {
     String uid,dato;
    DatosBasicos(){

    }

    public DatosBasicos(String uid, String dato) {
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

    public void setDatos(String dato) {
        this.dato = dato;
    }
}
