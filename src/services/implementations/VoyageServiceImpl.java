/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.implementations;

import dao.implementations.VoyageDaoImpl;
import dao.interfaces.VoyageDao;
import entities.Voyage;
import java.util.List;
import services.interfaces.VoyageService;

/**
 *
 * @author Caleb Lyc
 */
public class VoyageServiceImpl implements VoyageService{
    private VoyageDao dao = new VoyageDaoImpl();

    @Override
    public List<Voyage> lister() {
        try{
            return this.dao.lister();
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public void ajouter(Voyage voyage) {
        try{
            this.dao.ajouter(voyage);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Voyage trouver(Integer id) {
        try{
            return this.dao.trouver(id);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Voyage trouver(Voyage voyage) {
        try{
            return this.dao.trouver(voyage);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Voyage modifier(Voyage voyage) {
        try{
            return this.dao.modifier(voyage);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Voyage supprimer(Integer id) {
        try{
            return this.dao.supprimer(id);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Voyage supprimer(Voyage voyage) {
        try{
            return this.dao.supprimer(voyage);
        }catch(Exception e){
            throw e;
        }
    }
    
}
