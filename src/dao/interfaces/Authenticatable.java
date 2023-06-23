/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;

import entities.Administrateur;

/**
 *
 * @author Caleb Lyc
 */
public interface Authenticatable {
    public Administrateur login(Administrateur user);
    public Administrateur login(String username, String password);
    public void register(Administrateur user);
    public void logout(Administrateur user);
}
