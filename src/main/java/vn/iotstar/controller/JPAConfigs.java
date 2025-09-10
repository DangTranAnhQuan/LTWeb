package vn.iotstar.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import vn.iotstar.entity.Category; // dùng đúng entity bạn đã định nghĩa

public class JPAConfigs {

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("dataSource"); 

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void main(String[] args) {
        EntityManager enma = getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        Category cate = new Category();
        cate.setName("Iphone");
        cate.setIcon("abc.jpg");

        try {
            trans.begin();
            enma.persist(cate);
            trans.commit();
            System.out.println("Insert thành công: " + cate);
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) trans.rollback();
        } finally {
            enma.close();
            FACTORY.close();
        }
    }
}
