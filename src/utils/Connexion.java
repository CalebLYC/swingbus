/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Caleb Lyc
 */
public class Connexion {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;
    private static Connexion instance;
    
    private Connexion(){
        emf = Persistence.createEntityManagerFactory("swingbusPU");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }
    
    public static Connexion getInstance(){
        if(instance == null){
            instance = new Connexion();
        }
        return instance;
    }
    
    public static EntityManager getManager(){
        Connexion c = getInstance();
        return c.em;
    }
    
    public static EntityTransaction getTransaction(){
        Connexion c = getInstance();
        return c.et;
    }
}
