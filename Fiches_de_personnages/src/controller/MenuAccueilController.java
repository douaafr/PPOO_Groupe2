package controller;



import view.FenetreConnexion;
import view.FenetreFichePerso;
import view.MenuAccueilView;
import model.Utilisateur;
import model.Compte;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAccueilController {
    private MenuAccueilView view;
    private Utilisateur utilisateur;
	private Compte compte;

    public MenuAccueilController(MenuAccueilView view, Utilisateur utilisateur, Compte compte) {
        this.view = view;
        this.utilisateur = utilisateur;
        this.compte= compte;
        // Initialiser la vue avec le nom de l'utilisateur
        view.setNomUtilisateur(utilisateur.getId()); 

        // Déconnexion
        view.addDeconnexionListener(e -> performLogout());

        // Modifier le profil
       // view.addModifier(e -> openModifierMdp());

        // Ajouter une fiche de personnage
        view.addAjouterFicheListener(e -> addFichePersonnage());
    }

    private void performLogout() {
        
        if (utilisateur != null) { 
            
			compte.signOut(utilisateur);
        }

        
        JOptionPane.showMessageDialog(view, "Vous êtes maintenant déconnecté.", "Déconnexion", JOptionPane.INFORMATION_MESSAGE);

       
        view.dispose(); 
        FenetreConnexion fenetreConnexion = new FenetreConnexion();
        fenetreConnexion.setVisible(true);
    }


  /*  private void openModifierMdp() {
       FenetrePassword fenetreModification = new FenetrePassword(utilisateur);
        fenetreModification.setVisible(true);
    }*/

    private void addFichePersonnage() {
        
        FenetreFichePerso fenetreFichePerso = new FenetreFichePerso();
        fenetreFichePerso.setVisible(true);
    }




    public static void main(String[] args) {
        Compte compte = new Compte(); // Supposons que vous ayez une manière d'initialiser cela
        Utilisateur utilisateur = new Utilisateur("JohnDo", "password123");
        MenuAccueilView view = new MenuAccueilView(utilisateur.getId());
        new MenuAccueilController(view, utilisateur, compte);
        view.setVisible(true);
    
    }
    }
