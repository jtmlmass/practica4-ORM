package services;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;
public class BaseService<T>{
    /*Interface used to interact with the entity manager factory for the persistence unit.*/
    private static EntityManagerFactory emFactory;
    private Class<T> claseEntidad;

    public BaseService(Class<T> claseEntidad){
        if (emFactory == null){
            /*Bootstrap class that is used to obtain an EntityManagerFactory in Java SE environments. It may also be
            used to cause schema generation to occur.*/
            emFactory = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        }
        this.claseEntidad = claseEntidad;
    }
    public EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }

    /**
     * Metodo para obtener el valor del campo anotado como @ID.
     * @param entidad
     * @return
     */
    private Object getValorCampo(T entidad){
        if(entidad == null){
            return null;
        }
        //aplicando la clase de reflexión.
        for(Field f : entidad.getClass().getDeclaredFields()) {  //tomando todos los campos privados.
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    Object valorCampo = f.get(entidad);

//                    System.out.println("nombre del campo: "+f.getName());
//                    System.out.println("Tipo del campo: "+f.getType().getName());
//                    System.out.println("Valor del campo: "+valorCampo );

                    return valorCampo;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     *
     * @param entidad
     */
    public void crear(T entidad){
        EntityManager entityManager = getEntityManager();

        try {
            if (entityManager.find(claseEntidad, getValorCampo(entidad)) != null) {
                System.out.println("La entidad a guardar existe, no creada.");
                return;
            }
        }catch (IllegalArgumentException ie){
            //
            System.out.println("Parametro ilegal.");
        }

        entityManager.getTransaction().begin();
        try {
            entityManager.persist(entidad);
            entityManager.getTransaction().commit();

        }catch (Exception ex){
            entityManager.getTransaction().rollback();
            throw  ex;
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param entidad
     */
    public void editar(T entidad){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(entidad);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            entityManager.getTransaction().rollback();
            throw  ex;
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param entidadId
     */
    public void eliminar(Object  entidadId){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try {
            T entidad = entityManager.find(claseEntidad, entidadId);
            entityManager.remove(entidad);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            entityManager.getTransaction().rollback();
            throw  ex;
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        EntityManager entityManager = getEntityManager();
        try{
            return entityManager.find(claseEntidad, id);
        } catch (Exception ex){
            throw  ex;
        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * @return
     */
    public List<T> findAll(){
        EntityManager entityManager = getEntityManager();
        try{
            CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(claseEntidad);
            criteriaQuery.select(criteriaQuery.from(claseEntidad));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex){
            throw  ex;
        }finally {
            entityManager.close();
        }
    }
}
