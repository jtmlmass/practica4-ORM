//package entidades;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "MEGUSTA")
//public class Like implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long codigo;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Usuario usuarioLike;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Articulo articuloLike;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Comentario comentario;
//
//    public Long getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(Long codigo) {
//        this.codigo = codigo;
//    }
//
//    public Usuario getUsuarioLike() {
//        return usuarioLike;
//    }
//
//    public void setUsuarioLike(Usuario usuarioLike) {
//        this.usuarioLike = usuarioLike;
//    }
//
//    public Articulo getArticuloLike() {
//        return articuloLike;
//    }
//
//    public void setArticuloLike(Articulo articuloLike) {
//        this.articuloLike = articuloLike;
//    }
//    public Comentario getComentario() {
//        return comentario;
//    }
//
//    public void setComentario(Comentario comentario) {
//        this.comentario = comentario;
//    }
//}
//
