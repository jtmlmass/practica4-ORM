package encapsulacion;

public class Comentario {
    private long id;
    private String comentario;
    private Articulo articulo;
    private String autor;

    public Comentario() {}
    
    public Comentario(long id, String comentario, Articulo articulo, String autor) {
        this.id = id;
        this.comentario = comentario;
        this.articulo = articulo;
        this.autor = autor;
    }
    public Comentario(String comentario, Articulo articulo, String autor) {
        this.comentario = comentario;
        this.articulo = articulo;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                ", articulo=" + articulo +
                ", autor=" + autor +
                '}';
    }
}
