/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;

import entities.Ligne;
import java.util.List;

/**
 *
 * @author Caleb Lyc
 */
public interface LigneDao {
    public List<Ligne> lister();
    public void ajouter(Ligne ligne);
    public Ligne trouver(Integer numero);
    public Ligne trouver(String nom);
    public Ligne trouver(Ligne ligne);
    public Ligne modifier(Ligne ligne);
    public Ligne supprimer(Integer numero);
    public Ligne supprimer(Ligne ligne);
}
