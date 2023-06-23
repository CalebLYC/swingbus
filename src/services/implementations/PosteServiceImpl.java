/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.implementations;

import dao.implementations.PosteDaoImpl;
import dao.interfaces.PosteDao;
import entities.Poste;
import java.util.List;
import services.interfaces.PosteService;

/**
 *
 * @author Caleb Lyc
 */
public class PosteServiceImpl implements PosteService{
    private PosteDao dao = new PosteDaoImpl();

    @Override
    public List<Poste> lister() {
        try{
            return this.dao.lister();
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void ajouter(Poste poste) {
        try{
            this.dao.ajouter(poste);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Poste trouver(Integer id) {
        try{
            return this.dao.trouver(id);
        } catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public Poste trouver(String libelle){
         try{
             return this.dao.trouver(libelle);
         } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Poste trouver(Poste poste) {
        try{
            return this.dao.trouver(poste);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Poste modifier(Poste poste) {
        try{
            return this.dao.modifier(poste);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Poste supprimer(Integer id) {
        try{
            return this.dao.supprimer(id);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Poste supprimer(Poste poste) {
        try{
            return this.dao.supprimer(poste);
        } catch(Exception e){
            throw e;
        }
    }
    
}
