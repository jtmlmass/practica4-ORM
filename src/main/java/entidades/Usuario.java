package entidades;


import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/*Pasos:
* 1. Crear POJO
* 2. Anotar la clase como entidad (luego de importar javax.persistence)
* 3. Anotar los atributos que poseen características relacionales. Ej: Articulos:
*   "Un usuario tiene varios Artículos" y el ORM no va a adivinarlo.
* 4. Indicar la cardinalidad de la relación: "El usuario es quien tiene varios articulos", así que este atributo debe
* de ser "mappedBy = usuario".
* 5. Indicar el tipo de carga.*/
@Entity
public class Usuario implements Serializable {
    @Id
    private String username;
    private String nombre;
    private String password;
    private boolean administrator;
    private boolean author;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Comentario> misComentarios;
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private Set<Articulo> misArticulos;
    @OneToMany(mappedBy = "usuarioLiked", fetch = FetchType.LAZY)
    private Set<Like> misLikes;
    @OneToMany(mappedBy = "usuario")
    private Set <Dislike> misDislikes;

    public Usuario() {}

    public Usuario(String username, String nombre, String password,
                   boolean administrator, boolean author) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.administrator = administrator;
        this.author = author;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public boolean isAuthor() {
        return author;
    }

    public void setAuthor(boolean author) {
        this.author = author;
    }

    public Set<Articulo> getMisArticulos() {
        return misArticulos;
    }

    public void setMisArticulos(Set<Articulo> misArticulos) {
        this.misArticulos = misArticulos;
    }

    public Set<Like> getMisLikes() {
        return misLikes;
    }

    public void setMisLikes(Set<Like> misLikes) {
        this.misLikes = misLikes;
    }

    public Set<Dislike> getMisDislikes() {
        return misDislikes;
    }

    public void setMisDislikes(Set<Dislike> misDislikes) {
        this.misDislikes = misDislikes;
    }
}
