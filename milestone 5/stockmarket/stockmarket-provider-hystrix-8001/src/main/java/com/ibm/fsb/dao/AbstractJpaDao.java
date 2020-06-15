package com.ibm.fsb.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Base class for JPA Data Access Objects
 * @param <E> the entity type
 * @param <K> the primary key type
 */
public abstract class AbstractJpaDao<E, K> {

    /** the entity class  */
    Class<E> entityClass;

    /** the entity manager */
    protected final EntityManager em;

    /**
     * Constructor
     * @param em the entity manager
     */
    @SuppressWarnings("unchecked")
    public AbstractJpaDao(EntityManager em) {
        this.em = em;
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * Makes an entity instance managed and persistent.
     * @param entity the entity
     */
    public void persist(E entity) {
        em.persist(entity);
    }

    /**
     * Merges the state of the given entity into the current persistence context. 
     * @param entity the entity
     * @return the merged entity
     */
    public E merge(E entity) {
        return em.merge(entity);
    }
    
    /**
     * Flushes pending queries to the database 
     */
    public void flush() {
        em.flush();
    }

    /**
     * Removes the entity instance. 
     * @param entity the entity
     */
    public void remove(E entity) {
        em.remove(entity);    
    }
    
    /**
     * Removes the entity instance by primary key
     * @param id the primary key
     */
    public void removeById(K id) {
        E entity = em.getReference(entityClass, id);
        em.remove(entity);
    }

    /**
     * Finds by primary key.
     * @param id the primary key
     * @return the found entity instance or null if the entity does not exist 
     */
    public E findById(K id) {
        return em.find(entityClass, id);
    }

    /**
     * Finds by primary key.
     * @param id the primary key
     * @return the found entity instance
     * @throws EntityNotFoundException if the entity does not exist
     */
    public E getById(K id) {
        E entity = em.find(entityClass, id);
        if (entity == null) {
            throw new EntityNotFoundException("Entity " + entityClass.getName() + " with id " + id + " not found");
        }
        return entity;
    }

    /**
     * Finds all entities.
     * @return list of found entities
     */
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        return em.createQuery("from " + entityClass.getName()).getResultList();
    }
    
    /**
     * Executes a SELECT query that returns a single result.
     * @param query the query
     * @return the found entity, or null
     */
    protected Object findSingle(Query query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
