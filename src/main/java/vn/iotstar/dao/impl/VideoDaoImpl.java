package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import vn.iotstar.controller.JPAConfigs;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.entity.Video;

public class VideoDaoImpl implements VideoDao {

    @Override
    public Video findById(Integer id) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> findAll() {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            TypedQuery<Video> q = em.createQuery(
                "SELECT v FROM Video v ORDER BY v.id DESC", Video.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> search(String kw) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            TypedQuery<Video> q = em.createQuery(
                "SELECT v FROM Video v " +
                "WHERE (:kw IS NULL OR :kw = '' " +
                "   OR LOWER(v.title) LIKE LOWER(CONCAT('%', :kw, '%')) " +
                "   OR LOWER(v.href)  LIKE LOWER(CONCAT('%', :kw, '%')) ) " +
                "ORDER BY v.id DESC", Video.class);
            q.setParameter("kw", kw);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Video create(Video e) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Video update(Video e) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            em.getTransaction().begin();
            e = em.merge(e);
            em.getTransaction().commit();
            return e;
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            em.getTransaction().begin();
            Video v = em.find(Video.class, id);
            if (v != null) em.remove(v);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
