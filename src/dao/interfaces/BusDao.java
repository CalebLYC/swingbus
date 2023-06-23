/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;

import entities.Bus;
import java.util.List;

/**
 *
 * @author Caleb Lyc
 */
public interface BusDao {
    public List<Bus> lister();
    public void ajouter(Bus bus);
    public Bus trouver(Integer id);
    public Bus trouver(Bus bus);
    public Bus modifier(Bus bus);
    public Bus supprimer(Integer id);
    public Bus supprimer(Bus bus);
}
