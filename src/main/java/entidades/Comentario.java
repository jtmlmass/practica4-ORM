package entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition="LONGTEXT")
    private String comentario;

    @ManyToOne
    private Articulo articulo;

    @ManyToOne
    private Usuario usuario;

    @Column(columnDefinition = "long default '0'")
    private Long cantLikes;

    @Column(columnDefinition = "long default '0'")
    private Long cantDislikes;

    public Long getId() {
        return id;
    }

    public void setId(Long codigo) {
        this.id = codigo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Long getCantLikes() {
        return cantLikes;
    }

    public void setCantLikes(Long cantLikes) {
        this.cantLikes = cantLikes;
    }

    public Long getCantDislikes() {
        return cantDislikes;
    }

    public void setCantDislikes(Long cantDislikes) {
        this.cantDislikes = cantDislikes;
    }
}

