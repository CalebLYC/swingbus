/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implementations;

import dao.interfaces.VoyageDao;
import entities.Voyage;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import utils.Connexion;

/**
 *
 * @author Caleb Lyc
 */

public class VoyageDaoImpl implements VoyageDao {

    private EntityManager em = Connexion.getManager();
    private EntityTransaction et = Connexion.getTransaction();

    @Override
    public List<Voyage> lister() {
        try {
            Query query = em.createQuery("SELECT v FROM Voyage v", Voyage.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la liste des voyages.", e);
        }
    }

    @Override
    public void ajouter(Voyage voyage) {
        try {
            et.begin();
            em.persist(voyage);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de l'ajout du voyage." + e.getMessage(), e);
        }
    }

    @Override
    public Voyage trouver(Integer id) {
        try {
            return em.find(Voyage.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du voyage par ID." + e.getMessage(), e);
        }
    }

    @Override
    public Voyage trouver(Voyage voyage) {
        try {
            return trouver(voyage.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du voyage." + e.getMessage(), e);
        }
    }

    @Override
    public Voyage modifier(Voyage voyage) {
        try {
            et.begin();
            Voyage updatedVoyage = em.merge(voyage);
            et.commit();
            return updatedVoyage;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la modification du voyage : " + e.getMessage(), e);
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la modification du voyage." + e.getMessage(), e);
        }
    }

    @Override
    public Voyage supprimer(Integer id) {
        try {
            Voyage voyage = trouver(id);
            voyage.setLigne(null);
            voyage.setBus(null);
            voyage.setPersonnel(null);
            if (voyage != null) {
                et.begin();
                em.remove(voyage);
                et.commit();
                return voyage;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du voyage." + e.getMessage(), e);
        }
    }

    @Override
    public Voyage supprimer(Voyage voyage) {
        try {
            Voyage voyageASupprimer = trouver(voyage.getId());
            voyageASupprimer.setLigne(null);
            voyageASupprimer.setBus(null);
            voyageASupprimer.setPersonnel(null);
            if (voyageASupprimer != null) {
                et.begin();
                em.remove(voyageASupprimer);
                et.commit();
                return voyageASupprimer;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du voyage." + e.getMessage(), e);
        }
    }

}