/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
/**
 *
 * @author Caleb Lyc
 */
@Entity
@DiscriminatorValue(value = "PERSO")
public class Personnel extends User {
   @JoinColumn(name = "poste_id", nullable = true)
   @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
   private Poste poste;
   @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "personnel")
   private List<Voyage> voyages = new ArrayList<>();

    public Personnel() {
    }

    public Personnel(Integer id, String nom, String prenom, Date dateNaissance, Poste poste) {
        super(id, nom, prenom, dateNaissance);
        this.poste = poste;
    }

    public Personnel(String nom, String prenom, Date dateNaissance, Poste poste) {
        super(nom, prenom, dateNaissance);
        this.poste = poste;
    }

    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + dateNaissance + '}' + ", poste=" + poste.getLibelle() + '}';
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }
    
}
