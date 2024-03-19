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
    
    public String password;
    public String id_account;

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
    
   
    
    public void setId(String id) {
    	id_account = id;
    }
    public String getId(){
    	return id_account;
    }

    
    public void setPwd(String mdp) {
        password = mdp;
    }
    public String getPwd() {
        return password;
        
    }
    public void changePwd(String oldPwd, String newPwd) {
    	if (mdpCorrect(oldPwd)) {
    		oldPwd = newPwd;
    	}
    }
    
    public boolean mdpCorrect(String mdp) {
    	if (mdp.equals(password)) {
    		return true;
    	}else {
    		return false;
    	}
    }

    
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
    
    public Utilisateur(String identifiant, String mdp ) {
    	id_account = identifiant;
    	password = mdp;
  
    }

}