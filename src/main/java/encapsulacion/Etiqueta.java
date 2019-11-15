package encapsulacion;

public class Etiqueta {
    private long id;

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    private int articulo;
    private String etiqueta;


    public Etiqueta() {}
    public Etiqueta ( String etiqueta) {
        this.etiqueta = etiqueta;
    }
    public Etiqueta(long id, String etiqueta) {
        this.id = id;

        this.etiqueta = etiqueta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
