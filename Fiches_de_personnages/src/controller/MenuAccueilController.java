package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Utilisateur;
import model.Compte;
import model.FichePersonnage;
import view.MenuAccueilView;
import view.FenetreConnexion;
import view.FenetreCreation;
import view.FenetreFichePerso;
import java.util.List;

public class MenuAccueilController {
    private MenuAccueilView view;
    private Utilisateur utilisateur;
    private Compte compte;

    public MenuAccueilController(MenuAccueilView view, Utilisateur utilisateur, Compte compte) {
        this.view = view;
        this.utilisateur = utilisateur;
        this.compte = compte;
        initController();
        loadFichePersonnages();
    }

    private void initController() {
        view.getDeconnexionButton().addActionListener(e -> performLogout());
        view.getAjouterFicheButton().addActionListener(e -> addFichePersonnage());
    }
    private void loadFichePersonnages() {
        List<FichePersonnage> fiches = utilisateur.getFichesPersonnages();
        view.displayFiches(fiches);
    }

    private void performLogout() {
        System.out.println("Déconnexion en cours");
        if (utilisateur != null) {
            compte.signOut(utilisateur);
        }
        JOptionPane.showMessageDialog(view, "Vous êtes maintenant déconnecté.", "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
        view.dispose();
        new FenetreConnexion().setVisible(true);
    }
    private void addFichePersonnage() {
        FenetreFichePerso fenetre = new FenetreFichePerso(utilisateur, new FichePersonnage());
        fenetre.setVisible(true);
        fenetre.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadFichePersonnages(); // Recharge les fiches après ajout ou modification
            }
        });
    }
}
