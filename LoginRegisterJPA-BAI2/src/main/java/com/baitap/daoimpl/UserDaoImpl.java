package com.baitap.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import com.baitap.dao.UserDao;
import com.baitap.model.User;

public class UserDaoImpl implements UserDao {

    // Kết nối JPA thông qua persistence.xml
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("LoginRegisterJPA_API");

    // =====================================================
    // 1. GET USER BY USERNAME
    // =====================================================
    @Override
    public User get(String username) {

        EntityManager em = emf.createEntityManager();

        try {

            String jpql = "SELECT u FROM User u WHERE u.userName = :username";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("username", username);

            return query.getSingleResult();

        } catch (Exception e) {

            // Không tìm thấy user
            return null;

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 2. LOGIN
    // =====================================================
    @Override
    public User login(String username, String password) {

        EntityManager em = emf.createEntityManager();

        try {

            String jpql =
                    "SELECT u FROM User u " +
                    "WHERE u.userName = :username " +
                    "AND u.passWord = :password";

            TypedQuery<User> query =
                    em.createQuery(jpql, User.class);

            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.getSingleResult();

        } catch (Exception e) {

            // Sai username/password hoặc không tìm thấy user
            return null;

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 3. INSERT USER
    // =====================================================
    @Override
    public void insert(User user) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            // JPA tự tạo câu INSERT
            em.persist(user);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 4. KIỂM TRA EMAIL
    // =====================================================
    @Override
    public boolean checkExistEmail(String email) {

        EntityManager em = emf.createEntityManager();

        try {

            String jpql =
                    "SELECT COUNT(u) FROM User u " +
                    "WHERE u.email = :email";

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return count > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 5. KIỂM TRA USERNAME
    // =====================================================
    @Override
    public boolean checkExistUsername(String username) {

        EntityManager em = emf.createEntityManager();

        try {

            String jpql =
                    "SELECT COUNT(u) FROM User u " +
                    "WHERE u.userName = :username";

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("username", username)
                    .getSingleResult();

            return count > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 6. KIỂM TRA PHONE
    // =====================================================
    @Override
    public boolean checkExistPhone(String phone) {

        EntityManager em = emf.createEntityManager();

        try {

            String jpql =
                    "SELECT COUNT(u) FROM User u " +
                    "WHERE u.phone = :phone";

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("phone", phone)
                    .getSingleResult();

            return count > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        } finally {

            em.close();
        }
    }
}