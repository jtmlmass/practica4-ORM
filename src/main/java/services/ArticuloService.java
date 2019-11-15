package services;

import entidades.Articulo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticuloService extends  GestionDB<Articulo>{
    private static ArticuloService articuloServiceInstance;

    public ArticuloService() {
        super(Articulo.class);
    }

    public static ArticuloService getInstancia() {
        if (articuloServiceInstance == null) {
            articuloServiceInstance = new ArticuloService();
        }
        return articuloServiceInstance;
    }

    public Set<Articulo> findAllDescByFecha() {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Articulo.findAllDescByFecha");
        Set<Articulo> listaArticulosAux = limitCuerpoArticulo(query.getResultList());

        return listaArticulosAux;
    }

    public Set<Articulo> limitCuerpoArticulo(List<Articulo> articulos) {
        Set<Articulo> listaArticulosAux = new HashSet<>();
        for (Articulo art : articulos) {
            Articulo articuloAux = art;
            articuloAux.setCuerpo(art.getCuerpo().substring(0, 70));
            listaArticulosAux.add(articuloAux);
        }
        return listaArticulosAux;
    }

    public Set<Articulo> findAllbyPagination(int pagina, int paginaNumero) {
        EntityManager em = getEntityManager();
        String hql = "FROM Articulo a order by a.fecha_publicacion DESC";
        Query query = em.createQuery(hql);
        query.setFirstResult((paginaNumero-1) * pagina);
        query.setMaxResults(pagina);
        List<Articulo> listaArticulo = query.getResultList();
        Set<Articulo> listaArticulosAux = limitCuerpoArticulo(query.getResultList());

        return listaArticulosAux;
    }
}
