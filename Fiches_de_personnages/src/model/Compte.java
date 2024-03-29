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
     /**
     * 
     */
    public Utilisateur utilisateur;
    public Vector<Utilisateur> listUtilisateurs = new Vector <Utilisateur>();
    
    public void createAccount(String identifiant, String motDePasse) {
    	Utilisateur nouvelUtilisateur = new Utilisateur (identifiant, motDePasse);
    	listUtilisateurs.add(nouvelUtilisateur);
    	
    }

    /**
     * 
     */
 
    public Utilisateur chercherUtilisateur(String identifiant) {
        for (Utilisateur utilisateur : listUtilisateurs) {
            if (utilisateur.getId().equals(identifiant)) {
                return utilisateur;
            }
        }
        return null; // Aucun utilisateur trouvé avec cet identifiant
    }
    
    /*public boolean existUtilisateur(String identifiant) {
    	int i = 0;
       	boolean found=false;
       	while( found==false) {
       		if ((identifiant != listUtilisateurs.get(i).id_account) && (i<listUtilisateurs.size())) {
       			i++;
       		}else if ((identifiant == listUtilisateurs.get(i).id_account) && (i<listUtilisateurs.size())){
       			found=true;
       		} else {
       			found=false;
       		}
       	 }
       	return found;
    } */
    
    public boolean signIn(String identifiant, String motDePasse) {
    	Utilisateur utilisateur = chercherUtilisateur(identifiant);
    	return utilisateur != null && utilisateur.mdpCorrect(motDePasse);
    }

    /**
     * 
     */
    public void signOut() {
        
    }
    /**
     * 
     */
    
    public Compte (Utilisateur utilisateur) {
    	this.utilisateur = utilisateur;
    }
  


}