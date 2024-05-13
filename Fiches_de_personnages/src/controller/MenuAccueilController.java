package controller;

import java.awt.event.*;
import javax.swing.*;
import view.MenuAccueilView;
import view.FenetrePassword;
import view.FenetreConnexion;
import view.FenetreFichePerso;
import model.Compte;
import model.FichePersonnage;
import model.Utilisateur;

public class MenuAccueilController implements ActionListener {

     MenuAccueilView view;
     Utilisateur utilisateur;
     Compte c = new Compte();
     FichePersonnage fichePersonnage;

    public MenuAccueilController(MenuAccueilView view, Utilisateur utilisateur) {
        this.view = view;
        this.utilisateur = utilisateur;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();

        if (source.getText().equals("Modifier le mot de passe")) {
            FenetrePassword fenetrePassword = new FenetrePassword();
            fenetrePassword.pack();
			fenetrePassword.setSize(800, 800);
			fenetrePassword.setLocationRelativeTo(null);
			fenetrePassword.setResizable(false);
			fenetrePassword.setVisible(true);
        } else if (source.getText().equals("Se déconnecter")) {
            // Appelle la méthode de déconnexion du modèle Utilisateur
            c.signOut(utilisateur);  // Assurez-vous que cette méthode gère la déconnexion correctement
            FenetreConnexion fenetreconnexion = new FenetreConnexion();
            fenetreconnexion.pack();
			fenetreconnexion.setSize(800, 800);
			fenetreconnexion.setLocationRelativeTo(null);
			fenetreconnexion.setResizable(false);
			fenetreconnexion.setVisible(true);// Ou ouvrir la FenetreConnexion si vous souhaitez revenir à l'écran de connexion
        } else if (source.getText().equals("Ajouter une fiche")) {
            FenetreFichePerso fenetreFichePerso = new FenetreFichePerso(utilisateur, fichePersonnage);
            fenetreFichePerso.pack();
			fenetreFichePerso.setSize(800, 800);
			fenetreFichePerso.setLocationRelativeTo(null);
			fenetreFichePerso.setResizable(false);
			fenetreFichePerso.setVisible(true);
        }
    }
}





//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import model.Utilisateur;
//import model.Compte;
//import model.FichePersonnage;
//import view.MenuAccueilView;
//import view.FenetreConnexion;
//import view.FenetreCreation;
//import view.FenetreFichePerso;
//import java.util.List;
//
//public class MenuAccueilController {
//    private MenuAccueilView view;
//    private Utilisateur utilisateur;
//    private Compte compte;
//
//    public MenuAccueilController(MenuAccueilView view, Utilisateur utilisateur, Compte compte) {
//        this.view = view;
//        this.utilisateur = utilisateur;
//        this.compte = compte;
//        initController();
//        loadFichePersonnages();
//    }
//
//    private void initController() {
//        view.getDeconnexionButton().addActionListener(e -> performLogout());
//        view.getAjouterFicheButton().addActionListener(e -> addFichePersonnage());
//    }
//    private void loadFichePersonnages() {
//        List<FichePersonnage> fiches = utilisateur.getFichesPersonnages();
//        view.displayFiches(fiches);
//    }
//
//    private void performLogout() {
//        System.out.println("Déconnexion en cours");
//        if (utilisateur != null) {
//            compte.signOut(utilisateur);
//        }
//        JOptionPane.showMessageDialog(view, "Vous êtes maintenant déconnecté.", "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
//        view.dispose();
//        new FenetreConnexion().setVisible(true);
//    }
//    private void addFichePersonnage() {
//        FenetreFichePerso fenetre = new FenetreFichePerso(utilisateur, new FichePersonnage());
//        fenetre.setVisible(true);
//        fenetre.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                loadFichePersonnages(); // Recharge les fiches après ajout ou modification
//            }
//        });
//    }
//}
