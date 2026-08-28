package com.baitap.daoimpl;

import java.util.List;

import com.baitap.config.JPAConfig;
import com.baitap.dao.CategoryDao;
import com.baitap.model.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void insert(Category category) {

        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();

            em.persist(category);

            trans.commit();

        } catch (Exception e) {

            if (trans.isActive()) {
                trans.rollback();
            }

            e.printStackTrace();
            throw e;

        } finally {

            em.close();
        }
    }

    @Override
    public void edit(Category category) {

        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();

            em.merge(category);

            trans.commit();

        } catch (Exception e) {

            if (trans.isActive()) {
                trans.rollback();
            }

            e.printStackTrace();
            throw e;

        } finally {

            em.close();
        }
    }

    @Override
    public void delete(int id) {

        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();

            Category category = em.find(Category.class, id);

            if (category != null) {
                em.remove(category);
            }

            trans.commit();

        } catch (Exception e) {

            if (trans.isActive()) {
                trans.rollback();
            }

            e.printStackTrace();
            throw e;

        } finally {

            em.close();
        }
    }

    @Override
    public Category get(int id) {

        EntityManager em = JPAConfig.getEntityManager();

        try {

            return em.find(Category.class, id);

        } finally {

            em.close();
        }
    }

    @Override
    public List<Category> getAll() {

        EntityManager em = JPAConfig.getEntityManager();

        try {

            String jpql =
                    "SELECT c FROM Category c";

            TypedQuery<Category> query =
                    em.createQuery(jpql, Category.class);

            return query.getResultList();

        } finally {

            em.close();
        }
    }
}