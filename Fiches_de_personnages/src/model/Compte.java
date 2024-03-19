package model;
import java.util.*;
import java.io.Serializable;

/**
 * 
 */
public class Compte implements Serializable{

    /**
     * Default constructor
     */
    public Compte() {
    }

    /**
     * 
     */
    public String id_account;
    public void setId(String id) {
    	id_account = id;
    }
    public String getId(){
    	return id_account;
    }

    /**
     * 
     */
    public String password;
    
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

    /**
     * 
     */
    public Utilisateur user;
    public void setUser (Utilisateur user_c) {
    	user = user_c;
    }

    /**
     * 
     */
    public void createAccount(String identifiant, String mdp, Utilisateur utilisateur) {
    	id_account = identifiant;
        password = mdp;
        user = utilisateur;
        
    }

    /**
     * 
     */
 
    public boolean signIn(String identifiant, String mdp) {
    	if (id_account.equals(identifiant) && mdpCorrect(mdp)) {
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * 
     */
    public void signOut() {
        
    }
    /**
     * 
     */
    public void save() {
        // TODO implement here
    }

    /**
     * 
     */
    
  public boolean mdpCorrect(String mdp) {
    	if (mdp.equals(password)) {
    		return true;
    	}else {
    		return false;
    	}
    }
  

    public Compte(String identifiant, String mdp) {
    	id_account = identifiant;
    	password = mdp;
    }
   
}