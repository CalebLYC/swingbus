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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Caleb Lyc
 */
@Entity
@Table(name = "bus")
public class Bus implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "immatriculation", length = 10, unique = true)
    private String immatriculation;
    @Column(name = "max_places")
    private Integer maximalPlaces;
    @Column(name = "marque", length = 50, nullable = true)
    private String marque;
    @Column(name = "model", length = 50, nullable = true)
    private String model;
    @Column(name = "year", nullable = true)
    private Integer year;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "bus")
    private List<Voyage> voyages = new ArrayList<>();
    
    public Bus() {
    }

    public Bus(Integer id, String immatriculation, Integer maximalPlaces, String marque, String model, Integer year) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.maximalPlaces = maximalPlaces;
        this.model = model;
        this.marque = marque;
        this.year = year;
    }

    public Bus(String immatriculation, Integer maximalPlaces, String marque, String model, Integer year) {
        this.immatriculation = immatriculation;
        this.maximalPlaces = maximalPlaces;
        this.model = model;
        this.marque = marque;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Bus{" + "id=" + id + ", immatriculation=" + immatriculation + ", maximalPlaces=" + maximalPlaces + ", marque=" + marque + ", model=" + model + ", year=" + year + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Integer getMaximalPlaces() {
        return maximalPlaces;
    }

    public void setMaximalPlaces(Integer maximalPlaces) {
        this.maximalPlaces = maximalPlaces;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }
    
}
