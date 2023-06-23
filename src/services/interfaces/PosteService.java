/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.interfaces;

import entities.Poste;
import java.util.List;

/**
 *
 * @author Caleb Lyc
 */
public interface PosteService {
    public List<Poste> lister();
    public void ajouter(Poste poste);
    public Poste trouver(Integer id);
    public Poste trouver(String libelle);
    public Poste trouver(Poste poste);
    public Poste modifier(Poste poste);
    public Poste supprimer(Integer id);
    public Poste supprimer(Poste poste);
}
