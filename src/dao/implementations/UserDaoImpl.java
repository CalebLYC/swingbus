/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implementations;

import dao.interfaces.UserDao;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import utils.Connexion;

/**
 *
 * @author Caleb Lyc
 */

public class UserDaoImpl implements UserDao {

    private EntityManager em = Connexion.getManager();
    private EntityTransaction et = Connexion.getTransaction();

    @Override
    public List<User> lister() {
        try {
            Query query = em.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la liste des utilisateurs." + e.getMessage(), e);
        }
    }

    @Override
    public void ajouter(User user) {
        try {
            et.begin();
            em.persist(user);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de l'ajout de l'utilisateur." + e.getMessage(), e);
        }
    }

    @Override
    public void ajouter(String nom, String prenom, Date dateNaissance) {
        try {
            User user = new User(nom, prenom, dateNaissance) {};
            ajouter(user);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'utilisateur." + e.getMessage(), e);
        }
    }

    @Override
    public User trouver(Integer id) {
        try {
            return em.find(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'utilisateur par ID." + e.getMessage(), e);
        }
    }

    @Override
    public User trouver(User user) {
        try {
            return trouver(user.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'utilisateur." + e.getMessage(), e);
        }
    }

    @Override
    public User modifier(Integer id, String nom, String prenom, Date dateNaissance) {
        try {
            User user = trouver(id);
            if (user != null) {
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setDateNaissance(dateNaissance);
                et.begin();
                User updatedUser = em.merge(user);
                et.commit();
                return updatedUser;
            }
            return null;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la modification de l'utilisateur : " + e.getMessage(), e);
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la modification de l'utilisateur." + e.getMessage(), e);
        }
    }

    @Override
    public User modifier(User user) {
        try {
            return modifier(user.getId(), user.getNom(), user.getPrenom(), user.getDateNaissance());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de l'utilisateur." + e.getMessage(), e);
        }
    }

    @Override
    public User supprimer(Integer id) {
        try {
            User user = trouver(id);
            if (user != null) {
                et.begin();
                em.remove(user);
                et.commit();
                return user;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur." + e.getMessage(), e);
        }
    }

    @Override
    public User supprimer(User user) {
        try {
            User userASupprimer = trouver(user.getId());
            if (userASupprimer != null) {
                et.begin();
                em.remove(userASupprimer);
                et.commit();
                return userASupprimer;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur." + e.getMessage(), e);
        }
    }
}
