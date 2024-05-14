package model;
import java.util.*;
import java.io.*;
/**
 * 
 */
public class Compte implements Serializable{

    /**
     * Default constructor
     */
	
	private static final long serialVersionUID = 1L;
    public Compte() {
    	chargerComptes();
    }

    
    public Utilisateur utilisateur;
    public Vector<Utilisateur> listUtilisateurs = new Vector <Utilisateur>();
    
    public Utilisateur createAccount(String identifiant, String motDePasse) throws Exception {
        Utilisateur utilisateurExistant = chercherUtilisateur(identifiant);

        if (utilisateurExistant == null) {
        	if (!identifiant.isBlank() && !motDePasse.isBlank()) {
        		Utilisateur nouvelUtilisateur = new Utilisateur(identifiant, motDePasse);
                listUtilisateurs.add(nouvelUtilisateur);
                sauvegarderComptes();
                return nouvelUtilisateur;
        	} else {
        		throw new Exception("L'identifiant et/ou le mot de passe ne sont pas saisis");
        	}
        } else {
            throw new Exception("Un utilisateur avec cet identifiant existe déjà.");
        }
    }
    
    public void deleteAccount(Utilisateur user) {
    	listUtilisateurs.remove(user);
    	sauvegarderComptes();
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
    
    public Utilisateur signIn(String identifiant, String motDePasse) {
        Utilisateur utilisateur = chercherUtilisateur(identifiant);
        if (utilisateur != null && utilisateur.mdpCorrect(motDePasse)) {
            utilisateur.chargerFichesPersonnages(); 
            return utilisateur;
        }
        return null;
    }

    /**
     * 
     */
    public void signOut(Utilisateur utilisateur) {
    	if (utilisateur != null) {
            utilisateur.sauvegarderFichesPersonnages();
            sauvegarderComptes(); 
        }
    }
    /**
     * 
     */
    
 // Méthode pour sérialiser la liste des utilisateurs
    public void sauvegarderComptes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("comptes.ser"))) {
            oos.writeObject(listUtilisateurs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour désérialiser la liste des utilisateurs
    public void chargerComptes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("comptes.ser"))) {
            listUtilisateurs = (Vector<Utilisateur>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier de comptes non trouvé. Initialisation d'une nouvelle liste.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     */
    
    
    public boolean changePwd(String oldPwd, String newPwd, Utilisateur utilisateur) {
      if (utilisateur.mdpCorrect(oldPwd)) { 
          utilisateur.password = utilisateur.hashPassword(newPwd);
          sauvegarderComptes();
          return true;
      } else {
          throw new IllegalArgumentException("L'ancien mot de passe est incorrect.");
      }
  }
    
    public Compte (Utilisateur utilisateur) {
    	this.utilisateur = utilisateur;
    }
  


}