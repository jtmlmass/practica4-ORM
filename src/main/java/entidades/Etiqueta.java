package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

@Entity
public class Etiqueta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Articulo> listaArticulos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Articulo> getArticulo() {
        return listaArticulos;
    }

    public void setArticulo(Set<Articulo> articulo) {
        this.listaArticulos = articulo;
    }

    public void etiquetarArticulo(Articulo articulo){
        if(this.listaArticulos == null){
            this.listaArticulos = Collections.<Articulo>emptySet();
        }
        this.listaArticulos.add(articulo);
    }

    public Articulo obtenerArticulo(Articulo articulo){
        for(Articulo a : this.listaArticulos){
            if(a.equals(articulo)){
                return a;
            }
        }
        return null;
    }
}
