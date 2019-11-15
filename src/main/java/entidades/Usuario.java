//package entidades;
//
//
//import java.io.Serializable;
//import java.util.Set;
//import javax.persistence.*;
//import javax.persistence.criteria.Fetch;
//
///*Pasos:
//* 1. Crear POJO
//* 2. Anotar la clase como entidad (luego de importar javax.persistence.*/
//@Entity
//public class Usuario implements Serializable {
//    @Id
//    private String username;
//    private String nombre;
//    private String password;
//    private boolean administrator;
//    private boolean author;
//
//    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
//    private Set<Comentario> misComentarios;
//    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
//    private Set<Articulo> misArticulos;
//    @OneToMany(mappedBy = "usuarioLike", fetch = FetchType.EAGER)
//    private Set<Like> misLikes;
//    @OneToMany(mappedBy = "usuario")
//    private Set <Dislike> misDislikes;
//
//    public Usuario() {}
//
//    public Usuario(String username, String nombre, String password,
//                   boolean administrator, boolean author) {
//        this.username = username;
//        this.nombre = nombre;
//        this.password = password;
//        this.administrator = administrator;
//        this.author = author;
//    }
//
//
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isAdministrator() {
//        return administrator;
//    }
//
//    public void setAdministrator(boolean administrator) {
//        this.administrator = administrator;
//    }
//
//    public boolean isAuthor() {
//        return author;
//    }
//
//    public void setAuthor(boolean author) {
//        this.author = author;
//    }
//
//    public Set<Articulo> getMisArticulos() {
//        return misArticulos;
//    }
//
//    public void setMisArticulos(Set<Articulo> misArticulos) {
//        this.misArticulos = misArticulos;
//    }
//
//    public Set<Like> getMisLikes() {
//        return misLikes;
//    }
//
//    public void setMisLikes(Set<Like> misLikes) {
//        this.misLikes = misLikes;
//    }
//
//    public Set<Dislike> getMisDislikes() {
//        return misDislikes;
//    }
//
//    public void setMisDislikes(Set<Dislike> misDislikes) {
//        this.misDislikes = misDislikes;
//    }
//}
