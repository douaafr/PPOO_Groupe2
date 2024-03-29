package model;
import java.util.*;
import org.mindrot.jbcrypt.BCrypt;
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
    
    private String password;
    private String id_account;

    /**
     * 
     */

    public Compte compte;
    public void setCompte (Compte compte_c) {
    	compte = compte_c;
    }
   
    
    public void setId(String id) {
    	id_account = id;
    }
    public String getId(){
    	return id_account;
    }

    
    public void setPwd(String mdp) {
        password = hashPassword(mdp);
    }
    public String getPwd() {
        return password;
        
    }
    public void changePwd(String oldPwd, String newPwd) {
    	if (mdpCorrect(oldPwd)) {
    		this.password = hashPassword(newPwd);
    	}
    }
    
    public boolean mdpCorrect(String mdp) {
        return BCrypt.checkpw(mdp, this.password);
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
    
    
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    
    public Utilisateur(String identifiant, String mdp ) {
    	id_account = identifiant;
    	password = hashPassword(mdp);
  
    }

}