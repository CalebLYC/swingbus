/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Caleb Lyc
 */
@Entity
@Table(name = "voyages")
public class Voyage implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_passagers")
    private Integer nombrePassagers;
    @Column(name = "date_heure")
    private Date date;
    @Column(name = "vers_peripherie")
    private boolean versPeripherie;
    @JoinColumn(name = "ligne_numero", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Ligne ligne;
    @JoinColumn(name = "bus_id", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Bus bus;
    @ManyToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(
            name = "voyages_personnel",
            joinColumns = @JoinColumn(name = "voyage_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "personnel_id", nullable = true)
    )
    private List<Personnel> personnel = new ArrayList<>();
    
    public Voyage() {
    }

    public Voyage(Integer id, Integer nombrePassagers, Date date, boolean versPeripherie, Ligne ligne, Bus bus) {
        this.id = id;
        this.nombrePassagers = nombrePassagers;
        this.date = date;
        this.versPeripherie = versPeripherie;
        this.ligne = ligne;
        this.bus = bus;
    }

    public Voyage(Integer nombrePassagers, Date date, boolean versPeripherie, Ligne ligne, Bus bus) {
        this.nombrePassagers = nombrePassagers;
        this.date = date;
        this.versPeripherie = versPeripherie;
        this.ligne = ligne;
        this.bus = bus;
    }

    public Voyage(Date date, boolean versPeripherie, Ligne ligne, Bus bus) {
        this.date = date;
        this.versPeripherie = versPeripherie;
        this.ligne = ligne;
        this.bus = bus;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", nombrePassagers=" + nombrePassagers + ", date=" + date + ", versPeripherie=" + versPeripherie + ", ligne=" + ligne + ", bus=" + bus + ", personnel=" + personnel + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNombrePassagers() {
        return nombrePassagers;
    }

    public void setNombrePassagers(Integer nombrePassagers) {
        this.nombrePassagers = nombrePassagers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isVersPeripherie() {
        return versPeripherie;
    }

    public void setVersPeripherie(boolean versPeripherie) {
        this.versPeripherie = versPeripherie;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<Personnel> getPersonnel() {
        return personnel;
    }

    public void setPersonnel(List<Personnel> personnel) {
        this.personnel = personnel;
    }


}
