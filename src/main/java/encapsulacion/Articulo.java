package encapsulacion;

import java.util.ArrayList;
import java.sql.Date;

public class Articulo {


    private long id;
    private String titulo;
    private String cuerpo;
    private String cuerpoHome;
    private String autor;
    private Date fecha;
    private ArrayList<Comentario> listaComentarios;
    private ArrayList<Etiqueta> listaEtiquetas;
    private int etiquetaId;


    public int getEtiquetaId() {
        return etiquetaId;
    }

    public void setEtiquetaId(int etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    public Articulo(String titulo, String cuerpo, String autor, Date fecha, ArrayList<Comentario> listaComentarios, ArrayList<Etiqueta> listaEtiquetas) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
        this.listaComentarios = listaComentarios;
        this.listaEtiquetas = listaEtiquetas;
    }

    public Articulo() {
    }
    public Articulo(String titulo, String cuerpo, String autor, Date fecha) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
    }
    public Articulo(long id, String titulo, String cuerpo, String autor, Date fecha, int etiquetaId) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
        this.etiquetaId = etiquetaId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public ArrayList<Etiqueta> getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(ArrayList<Etiqueta> listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }

    public String getCuerpoHome() {
        return cuerpoHome;
    }

    public void setCuerpoHome(String cuerpoHome) {
        this.cuerpoHome = cuerpoHome;
    }


    public Date getFehca() {
        return fecha;
    }

    public void setFehca(Date fecha) {
        this.fecha = fecha;
    }

    public String selectCuerpoHome(){
        if (this.cuerpo.length() > 70){
            this.cuerpoHome = this.cuerpo.substring(0, 70);
            return this.cuerpoHome;
        }else if (this.cuerpo.length() <= 70 && this.cuerpo.length() > 0){
            this.cuerpoHome = this.cuerpo;
            return this.cuerpoHome;
        }else{
            return "";
        }
    }

    @Override
    public String toString() {
        return Long.toString(id);
//        return "Articulo{" +
//                "id=" + id +
//                ", titulo='" + titulo + '\'' +
//                ", cuerpo='" + cuerpo + '\'' +
//                ", autor=" + autor +
//                ", fehca=" + fecha +
////                ", listaComentarios=" + listaComentarios +
////                ", listaEtiquetas=" + listaEtiquetas +
//
//                '}';
    }
}
