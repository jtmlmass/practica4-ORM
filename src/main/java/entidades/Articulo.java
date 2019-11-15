//package entidades;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.Set;
//
//@Entity
//@NamedQueries({@NamedQuery(name = "Articulo.findAllDescByFecha", query = "select a from Articulo as a order by a.fecha_publicacion desc")})
//public class Articulo implements Serializable {
//    /*    private long id;
//    private String titulo;
//    private String cuerpo;
//    private String cuerpoHome;
//    private String autor;
//    private Date fecha;
//    private ArrayList<Comentario> listaComentarios;
//    private ArrayList<Etiqueta> listaEtiquetas;
//    private int etiquetaId;*/
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long codigo;
//    private String titulo;
//
//    @Column(columnDefinition="LONGTEXT")
//    private String cuerpo;
//
//    private Date fecha_publicacion;
//
//    @ManyToOne
//    private Usuario usuario;
//
//    @ManyToMany(mappedBy = "listaArticulos", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private Set<Etiqueta> listaEtiquetas;
//
//    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
//    private Set<Comentario> listaComentarios;
//
//    @OneToMany(mappedBy = "articuloLike", fetch = FetchType.EAGER)
//    private Set<Like> listaLike;
//
//    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
//    private Set<Dislike> listaDislike;
//
//    public Articulo() {}
//
//    public Set<Comentario> getListaComentarios() {
//        return listaComentarios;
//    }
//
//    public void setListaComentarios(Set<Comentario> listaComentarios) {
//        this.listaComentarios = listaComentarios;
//    }
//
//    public Long getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(Long codigo) {
//        this.codigo = codigo;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }
//
//    public String getCuerpo() {
//        return cuerpo;
//    }
//
//    public void setCuerpo(String cuerpo) {
//        this.cuerpo = cuerpo;
//    }
//
//    public Date getFecha_publicacion() {
//        return fecha_publicacion;
//    }
//
//    public void setFecha_publicacion(Date fecha_publicacion) {
//        this.fecha_publicacion = fecha_publicacion;
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
//    public Set<Etiqueta> getListaEtiquetas() {
//        return listaEtiquetas;
//    }
//
//    public void setListaEtiquetas(Set<Etiqueta> listaEtiquetas) {
//        this.listaEtiquetas = listaEtiquetas;
//    }
//
//    public Set<Like> getListaLike() {
//        return listaLike;
//    }
//
//    public void setListaLike(Set<Like> listaLike) {
//        this.listaLike = listaLike;
//    }
//
//    public Set<Dislike> getListaDislike() {
//        return listaDislike;
//    }
//
//    public void setListaDislike(Set<Dislike> listaDislike) {
//        this.listaDislike = listaDislike;
//    }
//}
