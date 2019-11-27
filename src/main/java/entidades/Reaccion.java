package entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reacciones")

public class Reaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    private Articulo articuloLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comentario comentario;

    @Enumerated(EnumType.STRING)
    private TipoReaccion tipoReaccion;


    private String icono;

    public Reaccion() {
    }

    public Reaccion(Usuario usuarioLiked, Articulo articuloLiked, Comentario comentario, TipoReaccion tipoReaccion, String icono) {
        this.usuarioLiked = usuarioLiked;
        this.articuloLiked = articuloLiked;
        this.comentario = comentario;
        this.tipoReaccion = tipoReaccion;
        this.icono = icono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioLiked() {
        return usuarioLiked;
    }

    public void setUsuarioLiked(Usuario usuarioLiked) {
        this.usuarioLiked = usuarioLiked;
    }

    public Articulo getArticuloLiked() {
        return articuloLiked;
    }

    public void setArticuloLiked(Articulo articuloLiked) {
        this.articuloLiked = articuloLiked;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public TipoReaccion getTipoReaccion() {
        return tipoReaccion;
    }

    public void setTipoReaccion(TipoReaccion tipoReaccion) {
        this.tipoReaccion = tipoReaccion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "Reaccion{" +
                "id=" + id +
                ", usuarioLiked=" + usuarioLiked +
                ", articuloLiked=" + articuloLiked +
                ", comentario=" + comentario +
                '}';
    }
}