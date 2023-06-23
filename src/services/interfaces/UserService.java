/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.interfaces;

import entities.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Caleb Lyc
 */
public interface UserService {
    public List<User> lister();
    public void ajouter(User user);
    public void ajouter(String nom, String prenom, Date dateNaissance);
    public User trouver(Integer id);
    public User trouver(User user);
    public User modifier(Integer id,String nom, String prenom, Date dateNaissance);
    public User modifier(User user);
    public User supprimer(Integer id);
    public User supprimer(User user);
}
