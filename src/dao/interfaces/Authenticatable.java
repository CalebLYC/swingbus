/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;

import entities.User;

/**
 *
 * @author Caleb Lyc
 */
public interface Authenticatable {
    public boolean login(Authenticatable user);
    public Authenticatable login(String username, String password);
    public void register(Authenticatable user);
    public void logout(Authenticatable user);
}
