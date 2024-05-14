package model;
import java.util.*;
import org.mindrot.jbcrypt.BCrypt;
import java.io.*;


/**
 * 
 */
public class Utilisateur implements Serializable{

    /**
     * Default constructor
     */
	
	private static final long serialVersionUID = 1L;
    public Utilisateur() {
    }
    
     public String password;
     public String id_account;
    private File ficheFile; // Transient pour exclure de la sérialisation

    /**
     * 
     */
    
    public Utilisateur(String identifiant, String mdp) {
    	//this.compte = compte;
        this.id_account = identifiant;
        this.password = hashPassword(mdp);
        this.ficheFile = new File("fiches_" + id_account + ".ser"); // Fichier unique par utilisateur
        if (this.ficheFile.exists()) {
            chargerFichesPersonnages(); // Charger les fiches à la création
        }
    }
    /**
     * 
     */
    
 // Sérialise la liste des fiches de personnages
    public void sauvegarderFichesPersonnages() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheFile))) {
            oos.writeObject(listFichesPersonnages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Désérialise la liste des fiches de personnages
    public void chargerFichesPersonnages() {
        if (ficheFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheFile))) {
                listFichesPersonnages = (Vector<FichePersonnage>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Compte compte = new Compte();
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
//    public void changePwd(String oldPwd, String newPwd) {
//        if (mdpCorrect(oldPwd)) { 
//            this.password = hashPassword(newPwd);
//            compte.sauvegarderComptes();
//        } else {
//            throw new IllegalArgumentException("L'ancien mot de passe est incorrect.");
//        }
//    }
    
    public boolean mdpCorrect(String mdp) {
        return BCrypt.checkpw(mdp, this.password);
    }

    
    public Vector<FichePersonnage> listFichesPersonnages = new Vector <FichePersonnage>();


    public void addFichePersonnage(String nameFiche) {
        FichePersonnage fiche = new FichePersonnage(nameFiche, listFichesPersonnages.size() + 1);
        listFichesPersonnages.add(fiche);
        sauvegarderFichesPersonnages();
    }
    
    public void addFichePersonnage(FichePersonnage fiche) {
        listFichesPersonnages.add(fiche);
        sauvegarderFichesPersonnages();
    }
    
    public Vector<FichePersonnage> getFichesPersonnages() {
    	return new Vector<>(listFichesPersonnages);	
    }
    
    public FichePersonnage getFicheById(int id_fiche) {
    	for (FichePersonnage fiche : listFichesPersonnages) {
    		if (fiche.getIdFiche() == id_fiche) {
				return fiche;
			}
    	}
    	return null;
    }
    
    public boolean deleteFiche(int idFiche) {
        boolean removed = listFichesPersonnages.removeIf(fiche -> fiche.getIdFiche() == idFiche);
        if (removed) {
            sauvegarderFichesPersonnages();
        }
        return removed;
    }
    
    
    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    

}