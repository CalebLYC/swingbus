/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.interfaces;

import entities.Administrateur;
import java.util.List;

/**
 *
 * @author Caleb Lyc
 */
public interface AdminService {
    public List<Administrateur> lister();
    public void ajouter(Administrateur admin);
    public Administrateur trouver(Integer id);
    public Administrateur trouver(Administrateur admin);
    public Administrateur modifier(Administrateur admin);
    public Administrateur supprimer(Integer id);
    public Administrateur supprimer(Administrateur admin);
}
