package model;
import java.util.*;
import java.io.Serializable;

/**
 * 
 */
public class Utilisateur implements Serializable{

    /**
     * Default constructor
     */
    public Utilisateur() {
    }

    /**
     * 
     */
    public String firstName;
    
    public void setFirstName(String prenom) {
        firstName = prenom;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     */
   
    public String lastName;

    public void setLastName(String nom) {
       lastName = nom;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * 
     */
   
    public Compte compte;
    public void setCompte (Compte compte_c) {
    	compte = compte_c;
    }

    /**
     * 
     */
    public Vector<FichePersonnage> listFichesPersonnages = new Vector <FichePersonnage>();


    public void addFichePersonnage(FichePersonnage fichePersonnage) {
    	listFichesPersonnages.add(fichePersonnage);
    }
    
    public List<FichePersonnage> getFichesPersonnages() {
    	return listFichesPersonnages;
    }
    
    public FichePersonnage getFichePersonnage(int i) {
    	return listFichesPersonnages.get(i);
    }
    
    public Utilisateur(String prenom, String nom) {
    	firstName = prenom;
    	lastName = nom;
    }

}