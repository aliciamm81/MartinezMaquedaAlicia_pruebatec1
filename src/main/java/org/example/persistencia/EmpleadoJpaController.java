package org.example.persistencia;

import org.example.logica.Empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmpleadoJpaController {

    private EntityManagerFactory emf = null;

    /**
     * Obtener unidad de persistencia
     */
    public EmpleadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("empresaPU");
    }

    /**
     * @return
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Método que permite crear en la base de datos
     *
     * @param empleado
     */
    public void create(Empleado empleado) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
    }

    /**
     * Método que permite buscar un empleado por su id.
     *
     * @param id
     * @return
     */
    public Empleado read(Integer id) {
        EntityManager em = null;
        em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Empleado> root = cq.from(Empleado.class);
        cq.select(root).where(root.get("id").in(id));
        Query q = em.createQuery(cq);
        return (Empleado) q.getSingleResult();
    }

    /**
     * Método que permite editar
     *
     * @param empleado
     */
    public void update(Empleado empleado) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(empleado);
        em.getTransaction().commit();
    }

    /**
     * Método que permite borrar
     *
     * @param id
     */
    public void delete(Integer id) {
        EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        Empleado empleado = em.find(Empleado.class, id);
        em.remove(empleado);
        em.getTransaction().commit();
    }

    /**
     * Método que permite listar el contenido de la base de datos.
     *
     * @return
     */
    public List<Empleado> findEmpleadoEntities() {
        EntityManager em = null;
        em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Empleado.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    /**
     * @param cargo
     * @return
     */
    public List<Empleado> findEmpleadosByCargo(String cargo) {
        EntityManager em = null;
        em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Empleado> root = cq.from(Empleado.class);
        cq.select(root).where(root.get("cargo").in(cargo));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

}
