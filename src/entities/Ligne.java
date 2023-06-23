/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Caleb Lyc
 */
@Entity
@Table(name = "lignes")
public class Ligne implements Serializable{
    @Id
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "nom", length = 100, unique = true)
    private String nom;
    @Column(name = "distance")
    private float distance;
    @Column(name = "depart", length = 100)
    private String depart;
    @Column(name = "destination", length = 100, unique = true)
    private String destination;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "ligne")
    private List<Voyage> voyages = new ArrayList<>();

    public Ligne() {
    }

    public Ligne(int numero, String nom, float distance, String depart, String destination) {
        this.numero = numero;
        this.nom = nom;
        this.distance = distance;
        this.depart = depart;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Ligne{" + "numero=" + numero + ", nom=" + nom + ", distance=" + distance + ", depart=" + depart + ", destination=" + destination + '}';
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }
    
    
}
