/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implementations;

import dao.interfaces.PersonnelDao;
import entities.Personnel;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import utils.Connexion;

/**
 *
 * @author Caleb Lyc
 */


public class PersonnelDaoImpl implements PersonnelDao {

    private EntityManager em = Connexion.getManager();
    private EntityTransaction et = Connexion.getTransaction();

    @Override
    public List<Personnel> lister() {
        try {
            Query query = em.createQuery("SELECT p FROM Personnel p", Personnel.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la liste du personnel." + e.getMessage(), e);
        }
    }

    @Override
    public void ajouter(Personnel personnel) {
        try {
            et.begin();
            em.persist(personnel);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de l'ajout du personnel." + e.getMessage(), e);
        }
    }

    @Override
    public Personnel trouver(Integer id) {
        try {
            return em.find(Personnel.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du personnel par ID." + e.getMessage(), e);
        }
    }

    @Override
    public Personnel trouver(Personnel personnel) {
        try {
            return trouver(personnel.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du personnel." + e.getMessage(), e);
        }
    }

    @Override
    public Personnel modifier(Personnel personnel) {
        try {
            et.begin();
            Personnel updatedPersonnel = em.merge(personnel);
            et.commit();
            return updatedPersonnel;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la modification du personnel : " + e.getMessage(), e);
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la modification du personnel." + e.getMessage(), e);
        }
    }

    @Override
    public Personnel supprimer(Integer id) {
        try {
            Personnel personnel = trouver(id);
            personnel.setPoste(null);
            if (personnel != null) {
                et.begin();
                em.remove(personnel);
                et.commit();
                return personnel;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du personnel." + e.getMessage(), e);
        }
    }

    @Override
    public Personnel supprimer(Personnel personnel) {
        try {
            Personnel personnelASupprimer = trouver(personnel.getId());
            personnelASupprimer.setPoste(null);
            if (personnelASupprimer != null) {
                et.begin();
                em.remove(personnelASupprimer);
                et.commit();
                return personnelASupprimer;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du personnel." + e.getMessage(), e);
        }
    }
}