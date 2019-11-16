package entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "likes")
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioLiked;

    @ManyToOne(fetch = FetchType.EAGER)
    private Articulo articuloLiked;

    @ManyToOne(fetch = FetchType.EAGER)
    private Comentario comentario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioLiked() {
        return usuarioLiked;
    }

    public void setUsuarioLiked(Usuario usuarioLike) {
        this.usuarioLiked = usuarioLike;
    }

    public Articulo getArticuloLike() {
        return articuloLiked;
    }

    public void setArticuloLiked(Articulo articuloLike) {
        this.articuloLiked = articuloLike;
    }
    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}

