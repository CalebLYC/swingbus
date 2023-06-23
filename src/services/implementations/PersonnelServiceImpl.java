/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.implementations;

import dao.implementations.PersonnelDaoImpl;
import dao.interfaces.PersonnelDao;
import entities.Personnel;
import java.util.List;
import services.interfaces.PersonnelService;

/**
 *
 * @author Caleb Lyc
 */
public class PersonnelServiceImpl implements PersonnelService{
    private PersonnelDao dao = new PersonnelDaoImpl();

    @Override
    public List<Personnel> lister() {
        try{
            return this.dao.lister();
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void ajouter(Personnel personnel) {
        try{
            this.dao.ajouter(personnel);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Personnel trouver(Integer id) {
        try{
            return this.dao.trouver(id);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Personnel trouver(Personnel personnel) {
        try{
            return this.dao.trouver(personnel);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Personnel modifier(Personnel personnel) {
        try{
            return this.trouver(personnel);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Personnel supprimer(Integer id) {
        try{
            return this.dao.supprimer(id);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Personnel supprimer(Personnel personnel) {
        try{
            return this.dao.supprimer(personnel);
        } catch(Exception e){
            throw e;
        }
    }
    
}
