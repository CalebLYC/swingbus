/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implementations;

import dao.interfaces.BusDao;
import entities.Bus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import utils.Connexion;

/**
 *
 * @author Caleb Lyc
 */
public class BusDaoImpl implements BusDao {
    private EntityManager em = Connexion.getManager();
    private EntityTransaction et = Connexion.getTransaction();

    @Override
    public List<Bus> lister() {
        try {
            Query query = em.createQuery("SELECT b FROM Bus b", Bus.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la liste des bus." + e.getMessage(), e);
        }
    }

    @Override
    public void ajouter(Bus bus) {
        try {
            et.begin();
            em.persist(bus);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de l'ajout du bus." + e.getMessage(), e);
        }
    }

    @Override
    public Bus trouver(Integer id) {
        try {
            return em.find(Bus.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du bus par ID." + e.getMessage(), e);
        }
    }

    @Override
    public Bus trouver(Bus bus) {
        try {
            return trouver(bus.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du bus." + e.getMessage(), e);
        }
    }

    @Override
    public Bus modifier(Bus bus) {
        try {
            et.begin();
            Bus updatedBus = em.merge(bus);
            et.commit();
            return updatedBus;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la modification du bus : " + e.getMessage(), e);
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la modification du bus: " + e.getMessage(), e);
        }
    }

    @Override
    public Bus supprimer(Integer id) {
        try {
            Bus bus = trouver(id);
            if (bus != null) {
                et.begin();
                em.remove(bus);
                et.commit();
                return bus;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du bus." + e.getMessage(), e);
        }
    }

    @Override
    public Bus supprimer(Bus bus) {
        try {
            Bus busASupprimer = trouver(bus.getId());
            if (busASupprimer != null) {
                et.begin();
                em.remove(busASupprimer);
                et.commit();
                return busASupprimer;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du bus." + e.getMessage(), e);
        }
    }

}
