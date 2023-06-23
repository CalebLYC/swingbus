/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.implementations;

import dao.implementations.UserDaoImpl;
import dao.interfaces.UserDao;
import entities.User;
import java.util.Date;
import java.util.List;
import services.interfaces.UserService;

/**
 *
 * @author Caleb Lyc
 */
public class UserServiceImpl implements UserService{
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> lister() {
        try{
            return this.dao.lister();
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void ajouter(User user) {
        try{
            this.dao.ajouter(user);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public void ajouter(String nom, String prenom, Date dateNaissance) {
        try{
            this.dao.ajouter(nom, prenom, dateNaissance);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public User trouver(Integer id) {
        try{
            return this.dao.trouver(id);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public User trouver(User user) {
        try{
            return this.dao.trouver(user);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public User modifier(Integer id, String nom, String prenom, Date dateNaissance) {
        try{
            return this.dao.modifier(id, nom, prenom, dateNaissance);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public User modifier(User user) {
        try{
            return this.dao.modifier(user);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public User supprimer(Integer id) {
        try{
            return this.dao.supprimer(id);
        } catch(Exception e){
            throw e;
        }
    }

    @Override
    public User supprimer(User user) {
        try{
            return this.dao.supprimer(user);
        } catch(Exception e){
            throw e;
        }
    }
        
}
