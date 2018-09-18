package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.habitaciones;

public class AdaptadorHabitacion {
    String uid,dato,nombre;

    public AdaptadorHabitacion() {
    }

    public AdaptadorHabitacion(String uid, String dato, String nombre) {
        this.uid = uid;
        this.dato = dato;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
