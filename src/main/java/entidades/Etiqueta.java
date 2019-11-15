//package entidades;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Set;
//
//@Entity
//public class Etiqueta implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int codigo;
//    private String nombre;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private Set<Articulo> listaArticulos;
//
//    public int getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(int codigo) {
//        this.codigo = codigo;
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
//    public Set<Articulo> getArticulo() {
//        return listaArticulos;
//    }
//
//    public void setArticulo(Set<Articulo> articulo) {
//        this.listaArticulos = articulo;
//    }
//}
