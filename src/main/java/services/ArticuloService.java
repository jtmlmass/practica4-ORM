package services;

import entidades.Articulo;
import entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticuloService extends BaseService<Articulo> {
    private static ArticuloService articuloServiceInstance;

    public ArticuloService() {
        super(Articulo.class);
    }

    public static ArticuloService getInstance() {
        if (articuloServiceInstance == null) {
            articuloServiceInstance = new ArticuloService();
        }
        return articuloServiceInstance;
    }

    public Set<Articulo> selectDescDate() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("Articulo.selectDescDate");
        Set<Articulo> listaArticulosAux = setUpCuerpoHome(query.getResultList());

        return listaArticulosAux;
    }

    public Set<Articulo> setUpCuerpoHome(List<Articulo> articulos) {
        Set<Articulo> listaArticulosAux = new HashSet<>();
        for (Articulo art : articulos) {
            Articulo articuloAux = art;
            /* Añadir parseo de JSoup*/
            if (articuloAux.getCuerpo().length() >= 70 ) {
                articuloAux.setCuerpo(art.getCuerpo().substring(0, 70));
            }
            listaArticulosAux.add(articuloAux);
        }
        return listaArticulosAux;
    }

    public Set<Articulo> selectPaginated(int cantElementos, int numPagina) {
        EntityManager entityManager = getEntityManager();
        String sql = "FROM Articulo a order by a.fecha DESC";
        Query query = entityManager.createQuery(sql);
        query.setFirstResult((numPagina-1) * cantElementos);
        query.setMaxResults(cantElementos);
        List<Articulo> listaArticulo = query.getResultList();
        Set<Articulo> listaArticulosAux = setUpCuerpoHome(query.getResultList());

        return listaArticulosAux;
    }

    public List<Articulo> selectByUsuario(Usuario usuario){
        EntityManager entityManager = getEntityManager();
        String sql = "SELECT a FROM Articulo a, Usuario u WHERE a.autor.id = u.id";
        Query query = entityManager.createQuery(sql);
        System.out.println(query.getResultList());
        List<Articulo> listaArticulos = new ArrayList<>(query.getResultList());
        for (Articulo aux: listaArticulos){
            System.out.println((aux.getTitulo()));
        }
        return listaArticulos;
    }

    public Set<Articulo> findAllbyPagination(int pagina, int paginaNumero) {
        EntityManager em = getEntityManager();
        String hql = "FROM Articulo a order by a.fecha_publicacion DESC";
        Query query = em.createQuery(hql);
        query.setFirstResult((paginaNumero-1) * pagina);
        query.setMaxResults(pagina);
        List<Articulo> listaArticulo = query.getResultList();
        Set<Articulo> listaArticulosAux = setUpCuerpoHome(query.getResultList());

        return listaArticulosAux;
    }
}
