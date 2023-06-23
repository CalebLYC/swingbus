/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implementations;

import dao.interfaces.LigneDao;
import entities.Ligne;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import utils.Connexion;

/**
 *
 * @author Caleb Lyc
 */
public class LigneDaoImpl implements LigneDao {
    private EntityManager em = Connexion.getManager();
    private EntityTransaction et = Connexion.getTransaction();

    @Override
    public List<Ligne> lister() {
        Query query = em.createQuery("SELECT l FROM Ligne l", Ligne.class);
        return query.getResultList();
    }

    @Override
    public void ajouter(Ligne ligne) {
        try {
            et.begin();
            em.persist(ligne);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw e;
        }
    }

    @Override
    public Ligne trouver(Integer numero) {
        return em.find(Ligne.class, numero);
    }

    @Override
    public Ligne trouver(Ligne ligne) {
        return trouver(ligne.getNumero());
    }
    
    @Override
    public Ligne trouver(String nom) {
        Query query = em.createQuery("SELECT l FROM Ligne l WHERE l.nom = :nom", Ligne.class);
        query.setParameter("nom", nom);
        return (Ligne)query.getSingleResult();
    }

    @Override
public Ligne modifier(Ligne ligne) {
    try {
        et.begin();

        Query query = em.createQuery("UPDATE Ligne l SET l.numero = :numero, l.nom = :nom, l.distance = :distance, l.depart = :depart, l.destination = :destination WHERE l.numero = :numero")
                .setParameter("nom", ligne.getNom())
                .setParameter("distance", ligne.getDistance())
                .setParameter("depart", ligne.getDepart())
                .setParameter("destination", ligne.getDestination())
                .setParameter("numero", ligne.getNumero());

        int updatedRows = query.executeUpdate();
        if (updatedRows == 0) {
            // Aucune ligne mise à jour, peut-être que la ligne avec le numéro spécifié n'existe pas
            throw new IllegalArgumentException("Ligne avec le numéro spécifié n'existe pas.");
        }

        Ligne updatedLigne = em.find(Ligne.class, ligne.getNumero()); // Récupère la ligne mise à jour

        et.commit(); // Valide les modifications dans la base de données
        return updatedLigne;
    } catch (Exception e) {
        et.rollback(); // Effectue un rollback en cas d'erreur
        throw e;
    }
}




    @Override
    public Ligne supprimer(Integer numero) {
        Ligne ligne = trouver(numero);
        if (ligne != null) {
            try {
                et.begin();
                em.remove(ligne);
                et.commit();
                return ligne;
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
        return null;
    }

    @Override
    public Ligne supprimer(Ligne ligne) {
        Ligne ligneASupprimer = trouver(ligne.getNumero());
        if (ligneASupprimer != null) {
            try {
                et.begin();
                em.remove(ligneASupprimer);
                et.commit();
                return ligneASupprimer;
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
        return null;
    }
}
