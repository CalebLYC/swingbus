/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implementations;

import dao.interfaces.AdminDao;
import dao.interfaces.Authenticatable;
import entities.Administrateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import utils.Connexion;

/**
 *
 * @author Caleb Lyc
 */


public class AdminDaoImpl implements AdminDao{

    private EntityManager em = Connexion.getManager();
    private EntityTransaction et = Connexion.getTransaction();

    @Override
    public List<Administrateur> lister() {
        try {
            Query query = em.createQuery("SELECT a FROM Administrateur a", Administrateur.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la liste des administrateurs." + e.getMessage(), e);
        }
    }

    @Override
    public void ajouter(Administrateur admin) {
        try {
            et.begin();
            em.persist(admin);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de l'ajout de l'administrateur." + e.getMessage(), e);
        }
    }

    @Override
    public Administrateur trouver(Integer id) {
        try {
            return em.find(Administrateur.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'administrateur par ID." + e.getMessage(), e);
        }
    }

    @Override
    public Administrateur trouver(Administrateur admin) {
        try {
            return trouver(admin.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'administrateur." + e.getMessage(), e);
        }
    }

    @Override
    public Administrateur modifier(Administrateur admin) {
        try {
            et.begin();
            Administrateur updatedAdmin = em.merge(admin);
            et.commit();
            return updatedAdmin;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la modification de l'administrateur : " + e.getMessage(), e);
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la modification de l'administrateur." + e.getMessage(), e);
        }
    }

    @Override
    public Administrateur supprimer(Integer id) {
        try {
            Administrateur admin = trouver(id);
            if (admin != null) {
                et.begin();
                em.remove(admin);
                et.commit();
                return admin;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression de l'administrateur." + e.getMessage(), e);
        }
    }

    @Override
    public Administrateur supprimer(Administrateur admin) {
        try {
            Administrateur adminASupprimer = trouver(admin.getId());
            if (adminASupprimer != null) {
                et.begin();
                em.remove(adminASupprimer);
                et.commit();
                return adminASupprimer;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression de l'administrateur." + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean login(Authenticatable user) {
        if (user instanceof Administrateur) {
            Administrateur admin = (Administrateur) user;
            try {
                Administrateur adminFromDB = em.find(Administrateur.class, admin.getId());
                if (adminFromDB != null && adminFromDB.getPassword().equals(admin.getPassword())) {
                    return true;  // Authentification réussie
                }
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de l'authentification de l'administrateur." + e.getMessage(), e);
            }
        }
        return false;  // Authentification échouée
    }

    @Override
    public Authenticatable login(String username, String password) {
        try {
            Query query = em.createQuery("SELECT a FROM Administrateur a WHERE a.username = :username", Administrateur.class);
            query.setParameter("username", username);
            Administrateur admin = (Administrateur) query.getSingleResult();
            if (admin != null && admin.getPassword().equals(password)) {
                return (Authenticatable)admin;  // Authentification réussie
            }
        } catch (NoResultException e) {
            return null;  // Utilisateur non trouvé
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'authentification de l'administrateur." + e.getMessage(), e);
        }
        return null;  // Authentification échouée
    }

    @Override
    public void register(Authenticatable user) {
        if (user instanceof Administrateur) {
            Administrateur admin = (Administrateur) user;
            try {
                et.begin();
                em.persist(admin);
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw new RuntimeException("Erreur lors de l'inscription de l'administrateur." + e.getMessage(), e);
            }
        }
    }

    @Override
    public void logout(Authenticatable user) {
        // Logique de déconnexion de l'administrateur
    }
}