/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implementations;

import dao.interfaces.PosteDao;
import entities.Poste;
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
public class PosteDaoImpl implements PosteDao {

    private EntityManager em = Connexion.getManager();
    private EntityTransaction et = Connexion.getTransaction();

    @Override
    public List<Poste> lister() {
        try {
            Query query = em.createQuery("SELECT p FROM Poste p", Poste.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la liste des postes." + e.getMessage(), e);
        }
    }

    @Override
    public void ajouter(Poste poste) {
        try {
            et.begin();
            em.persist(poste);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de l'ajout du poste." + e.getMessage(), e);
        }
    }

    @Override
    public Poste trouver(Integer id) {
        try {
            return em.find(Poste.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du poste par ID." + e.getMessage(), e);
        }
    }
    
    @Override
public Poste trouver(String libelle) {
    try {
        Query query = em.createQuery("SELECT p FROM Poste p WHERE p.libelle = :libelle")
                .setParameter("libelle", libelle);

        return (Poste) query.getSingleResult();
    } catch (NoResultException e) {
        // Aucun poste trouvé avec le libellé spécifié
        return null;
    } catch (Exception e) {
        throw new RuntimeException("Erreur lors de la recherche du poste par libellé." + e.getMessage(), e);
    }
}



    @Override
    public Poste trouver(Poste poste) {
        try {
            return trouver(poste.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du poste." + e.getMessage(), e);
        }
    }

    @Override
    public Poste modifier(Poste poste) {
        try {
            et.begin();
            Poste updatedPoste = em.merge(poste);
            et.commit();
            return updatedPoste;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur lors de la modification du poste : " + e.getMessage(), e);
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la modification du poste." + e.getMessage(), e);
        }
    }

    @Override
    public Poste supprimer(Integer id) {
        try {
            Poste poste = trouver(id);
            if (poste != null) {
                et.begin();
                em.remove(poste);
                et.commit();
                return poste;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du poste." + e.getMessage(), e);
        }
    }

    @Override
    public Poste supprimer(Poste poste) {
        try {
            Poste posteASupprimer = trouver(poste.getId());
            if (posteASupprimer != null) {
                et.begin();
                em.remove(posteASupprimer);
                et.commit();
                return posteASupprimer;
            }
            return null;
        } catch (Exception e) {
            et.rollback();
            throw new RuntimeException("Erreur lors de la suppression du poste." + e.getMessage(), e);
        }
    }
}