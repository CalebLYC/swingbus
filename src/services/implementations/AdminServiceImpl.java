/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.implementations;

import dao.implementations.AdminDaoImpl;
import dao.interfaces.AdminDao;
import dao.interfaces.Authenticatable;
import entities.Administrateur;
import java.util.List;
import services.interfaces.AdminService;
import services.interfaces.AuthenticatableService;

/**
 *
 * @author Caleb Lyc
 */
public class AdminServiceImpl implements AdminService, AuthenticatableService{
    private AdminDao dao = new AdminDaoImpl();

    @Override
    public List<Administrateur> lister() {
        try{
            return this.dao.lister();
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void ajouter(Administrateur admin) {
        try{
            this.dao.ajouter(admin);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Administrateur trouver(Integer id) {
        try{
            return this.dao.trouver(id);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Administrateur trouver(Administrateur admin) {
        try{
            return this.dao.trouver(admin);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Administrateur modifier(Administrateur admin) {
        try{
            return this.dao.modifier(admin);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Administrateur supprimer(Integer id) {
        try{
            return this.dao.supprimer(id);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Administrateur supprimer(Administrateur admin) {
        try{
            return this.dao.supprimer(admin);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public boolean login(Authenticatable user) {
        try{
            return this.dao.login(user);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public Authenticatable login(String username, String password) {
        try{
            return this.dao.login(username, password);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void register(Authenticatable user) {
        try{
            this.dao.register(user);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void logout(Authenticatable user) {
        try{
            this.dao.logout(user);
        } catch(Exception e){
            throw e;
        }
    }
    
}
