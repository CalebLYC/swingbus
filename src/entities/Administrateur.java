/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

/**
 *
 * @author Caleb Lyc
 */
@DiscriminatorValue(value = "ADMIN")
public class Administrateur extends User{
    
    @Column(name = "username", length = 255)
    private String username;
    @Column(name = "password", length = 255)
    private String password;

    public Administrateur() {
    }

    public Administrateur(Integer id, String nom, String prenom, Date date_naissance) {
        super(id, nom, prenom, date_naissance);
    }

    public Administrateur(String nom, String prenom, Date date_naissance) {
        super(nom, prenom, date_naissance);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
