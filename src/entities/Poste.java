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
@Table(name = "postes")
public class Poste implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "libelle", length = 100, unique = true)
    private String libelle;
    @Column(name = "description", length = 255, nullable = true)
    private String description;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "poste")
    private List<Personnel> personnel = new ArrayList<>();

    public Poste() {
    }

    public Poste(Integer id, String libele, String description) {
        this.id = id;
        this.libelle = libele;
        this.description = description;
    }

    public Poste(String libele, String description) {
        this.libelle = libele;
        this.description = description;
    }
    

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", libele=" + libelle + ", description=" + description + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libele) {
        this.libelle = libele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Personnel> getPersonnel() {
        return personnel;
    }

    public void setPersonnel(List<Personnel> personnel) {
        this.personnel = personnel;
    }
    
}
