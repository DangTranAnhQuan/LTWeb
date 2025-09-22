package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import vn.iotstar.controller.JPAConfigs;
import vn.iotstar.dao.UserDao;
import vn.iotstar.entity.User;

public class UserDaoImpl implements UserDao {

    @Override
    public User findByUsername(String username) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
            q.setParameter("username", username);
            q.setMaxResults(1);
            List<User> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByEmail(String email) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
            q.setParameter("email", email);
            q.setMaxResults(1);
            List<User> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u " +
                "WHERE u.username = :username AND u.password = :password",
                User.class);
            q.setParameter("username", username);
            q.setParameter("password", password);
            q.setMaxResults(1);
            List<User> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User findById(Integer id) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            return em.createQuery(
                "SELECT u FROM User u ORDER BY u.id DESC", User.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> search(String kw) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u " +
                "WHERE (:kw IS NULL OR :kw = '' " +
                "   OR LOWER(COALESCE(u.username, '')) LIKE LOWER(CONCAT('%', :kw, '%')) " +
                "   OR LOWER(COALESCE(u.fullName, '')) LIKE LOWER(CONCAT('%', :kw, '%')) " +
                "   OR LOWER(COALESCE(u.email, ''))    LIKE LOWER(CONCAT('%', :kw, '%')) " +
                "   OR LOWER(COALESCE(u.phone, ''))    LIKE LOWER(CONCAT('%', :kw, '%')) ) " +
                "ORDER BY u.id DESC",
                User.class);
            q.setParameter("kw", kw);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public User create(User e) {
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
    public void update(User user) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
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
            User u = em.find(User.class, id);
            if (u != null) em.remove(u);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPass) {
        EntityManager em = JPAConfigs.getEntityManager();
        try {
            em.getTransaction().begin();
            int updated = em.createQuery(
                "UPDATE User u SET u.password = :pwd WHERE u.email = :email")
                .setParameter("pwd", newPass)
                .setParameter("email", email)
                .executeUpdate();
            em.getTransaction().commit();
            return updated > 0;
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
