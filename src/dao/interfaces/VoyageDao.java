/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;

import entities.Voyage;
import java.util.List;

/**
 *
 * @author Caleb Lyc
 */
public interface VoyageDao {
    public List<Voyage> lister();
    public void ajouter(Voyage voyage);
    public Voyage trouver(Integer id);
    public Voyage trouver(Voyage voyage);
    public Voyage modifier(Voyage voyage);
    public Voyage supprimer(Integer id);
    public Voyage supprimer(Voyage voyage);
}
