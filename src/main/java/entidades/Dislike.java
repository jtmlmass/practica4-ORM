//package entidades;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "NOMEGUSTA")
//public class Dislike {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long codigo;
//
//    @ManyToOne
//    private Articulo articulo;
//
//    @ManyToOne
//    private Usuario usuario;
//
//    public Articulo getArticulo() {
//        return articulo;
//    }
//
//    public void setArticulo(Articulo articulo) {
//        this.articulo = articulo;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    public Long getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(Long codigo) {
//        this.codigo = codigo;
//    }
//}
