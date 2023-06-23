/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.interfaces;

import entities.Personnel;
import java.util.List;

/**
 *
 * @author Caleb Lyc
 */
public interface PersonnelService {
    public List<Personnel> lister();
    public void ajouter(Personnel personnel);
    public Personnel trouver(Integer id);
    public Personnel trouver(Personnel personnel);
    public Personnel modifier(Personnel personnel);
    public Personnel supprimer(Integer id);
    public Personnel supprimer(Personnel personnel);
}
