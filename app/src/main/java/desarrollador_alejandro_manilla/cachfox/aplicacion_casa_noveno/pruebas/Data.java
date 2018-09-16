package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas;

public class Data {
    int imagenId;
    String descripcion;

    public Data() {
    }

    public Data(int imagenId, String descripcion) {
        this.imagenId = imagenId;
        this.descripcion = descripcion;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
