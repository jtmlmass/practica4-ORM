package entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "like")
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioLike;

    @ManyToOne(fetch = FetchType.EAGER)
    private Articulo articuloLike;

    @ManyToOne(fetch = FetchType.EAGER)
    private Comentario comentario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioLike() {
        return usuarioLike;
    }

    public void setUsuarioLike(Usuario usuarioLike) {
        this.usuarioLike = usuarioLike;
    }

    public Articulo getArticuloLike() {
        return articuloLike;
    }

    public void setArticuloLike(Articulo articuloLike) {
        this.articuloLike = articuloLike;
    }
    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}

