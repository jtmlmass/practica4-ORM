package entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Entity
@NamedQueries({@NamedQuery(name = "Articulo.selectDescDate", query = "select a from Articulo as a order by a.fecha desc")})
public class Articulo implements Serializable {
    /*    private long id;
    private String titulo;
    private String cuerpo;
    private String cuerpoHome;
    private String autor;
    private Date fecha;
    private ArrayList<Comentario> listaComentarios;
    private ArrayList<Etiqueta> listaEtiquetas;
    private int etiquetaId;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;

    @Column(columnDefinition="LONGTEXT")
    private String cuerpo;

    private Date fecha;

    @ManyToOne
    private Usuario autor;

    @ManyToMany(mappedBy = "listaArticulos", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Etiqueta> listaEtiquetas;

    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
    private Set<Comentario> listaComentarios;

    @OneToMany(mappedBy = "articuloLiked", fetch = FetchType.EAGER)
    private Set<Like> listaLiked;

    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
    private Set<Dislike> listaDislike;

    public Articulo() {}
    public Articulo(String titulo, String cuerpo, Date fecha, Usuario usuario) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.autor = usuario;
        this.listaEtiquetas = Collections.<Etiqueta>emptySet();
        this.listaComentarios = Collections.<Comentario>emptySet();
        this.listaLiked = Collections.<Like>emptySet();
        this.listaDislike = Collections.<Dislike>emptySet();
    }
    public Articulo(String titulo, String cuerpo, Date fecha, Usuario autor, Set<Comentario> listaComentarios){}
    public Articulo(String titulo, String cuerpo, Date fecha, Usuario autor, Set<Like> listaLiked, Set<Dislike> listaDisiked) {}
    public Articulo(String titulo, String cuerpo, Date fecha, Usuario autor, Set<Comentario> listaComentarios, Set<Like> listaLiked, Set<Dislike> listaDisiked) {}



    public Set<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(Set<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Set<Etiqueta> getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(Set<Etiqueta> listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }

    public Set<Like> getListaLike() {
        return listaLiked;
    }

    public void setListaLike(Set<Like> listaLike) {
        this.listaLiked = listaLike;
    }

    public Set<Dislike> getListaDislike() {
        return listaDislike;
    }

    public void setListaDislike(Set<Dislike> listaDislike) {
        this.listaDislike = listaDislike;
    }
}
