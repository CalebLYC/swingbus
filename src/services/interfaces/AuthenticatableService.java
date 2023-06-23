/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.interfaces;

import dao.interfaces.Authenticatable;
import entities.Administrateur;

/**
 *
 * @author Caleb Lyc
 */
public interface AuthenticatableService {

    public Administrateur login(Administrateur user);

    public Administrateur login(String username, String password);

    public void register(Administrateur user);

    public void logout(Administrateur user);
}
