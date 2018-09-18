package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.adaptadores;

import java.util.List;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.objetos;

public class AdaptadorDeObjetos {
    String uid,nombre;

    public AdaptadorDeObjetos() {
    }

    public AdaptadorDeObjetos(String uid, String nombre) {
        this.uid = uid;
        this.nombre = nombre;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
