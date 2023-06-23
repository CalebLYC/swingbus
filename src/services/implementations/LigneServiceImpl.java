/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.implementations;

import dao.implementations.LigneDaoImpl;
import dao.interfaces.LigneDao;
import entities.Ligne;
import java.util.List;
import services.interfaces.LigneService;

/**
 *
 * @author Caleb Lyc
 */
public class LigneServiceImpl implements LigneService {

    private LigneDao dao = new LigneDaoImpl();

    @Override
    public List<Ligne> lister() {
        try {
            return this.dao.lister();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la liste des lignes." + e.getMessage(), e);
        }
    }

    @Override
    public void ajouter(Ligne ligne) {
        try {
            this.dao.ajouter(ligne);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout de la ligne." + e.getMessage(), e);
        }
    }

    @Override
    public Ligne trouver(Integer numero) {
        try {
            return this.dao.trouver(numero);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de la ligne." + e.getMessage(), e);
        }
    }

    @Override
    public Ligne trouver(Ligne ligne) {
        try {
            return this.dao.trouver(ligne);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de la ligne." + e.getMessage(), e);
        }
    }

    @Override
    public Ligne modifier(Ligne ligne) {
        try {
            return this.dao.modifier(ligne);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de la ligne." + e.getMessage(), e);
        }
    }

    @Override
    public Ligne supprimer(Integer numero) {
        try {
            return this.dao.supprimer(numero);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la ligne." + e.getMessage(), e);
        }
    }

    @Override
    public Ligne supprimer(Ligne ligne) {
        try {
            return this.dao.supprimer(ligne);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la ligne." + e.getMessage(), e);
        }
    }

}
