//package entidades;
//
//import javafx.beans.DefaultProperty;
//import org.h2.value.Value;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.cfg.annotations.reflection.XMLContext;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Set;
//
//@Entity
//public class Comentario implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long codigo;
//
//    @Column(columnDefinition="LONGTEXT")
//    private String comentario;
//
//    @ManyToOne
//    private Articulo articulo;
//
//    @ManyToOne
//    private Usuario usuario;
//
//    @Column(columnDefinition = "default '0'")
//    private Long meGusta;
//
//    @Column(columnDefinition = "default '0'")
//    private Long noMegusta;
//
//    public Long getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(Long codigo) {
//        this.codigo = codigo;
//    }
//
//    public String getComentario() {
//        return comentario;
//    }
//
//    public void setComentario(String comentario) {
//        this.comentario = comentario;
//    }
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
//    public Long getMeGusta() {
//        return meGusta;
//    }
//
//    public void setMeGusta(Long meGusta) {
//        this.meGusta = meGusta;
//    }
//
//    public Long getNoMegusta() {
//        return noMegusta;
//    }
//
//    public void setNoMegusta(Long noMegusta) {
//        this.noMegusta = noMegusta;
//    }
//}
//
