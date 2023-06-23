/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.implementations;

import dao.implementations.BusDaoImpl;
import dao.interfaces.BusDao;
import entities.Bus;
import java.util.List;
import services.interfaces.BusService;

/**
 *
 * @author Caleb Lyc
 */
public class BusServiceImpl implements BusService{
    private BusDao dao = new BusDaoImpl();

    @Override
    public List<Bus> lister() {
        try{
            return this.dao.lister();
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void ajouter(Bus bus) {
        try{
            this.dao.ajouter(bus);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Bus trouver(Integer id) {
        try{
            return this.dao.trouver(id);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Bus trouver(Bus bus) {
        try{
            return this.dao.trouver(bus);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Bus modifier(Bus bus) {
        try{
            return this.dao.modifier(bus);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Bus supprimer(Integer id) {
       try{
           return this.dao.supprimer(id);
       }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Bus supprimer(Bus bus) {
       try{
            return this.dao.supprimer(bus);
       }catch(Exception e){
            throw e;
        }
    }
    
}
