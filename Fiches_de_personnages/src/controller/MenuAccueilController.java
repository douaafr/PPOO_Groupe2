// Dans MenuAccueilController.java

import view.MenuAccueilView;
import model.Utilisateur;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAccueilController {
    private MenuAccueilView view;
    private Utilisateur utilisateur;

    public MenuAccueilController(MenuAccueilView view, Utilisateur utilisateur) {
        this.view = view;
        this.utilisateur = utilisateur;

        // Initialiser la vue avec le nom de l'utilisateur
        view.setNomUtilisateur(utilisateur.getId()); 

        // Déconnexion
        view.addDeconnexionListener(e -> performLogout());

        // Modifier le profil
        view.addModifierProfilListener(e -> openModifierProfil());

        // Ajouter une fiche de personnage
        view.addAjouterFicheListener(e -> addFichePersonnage());
    }

    private void performLogout() {
        JOptionPane.showMessageDialog(view, "Vous êtes maintenant déconnecté.", "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void openModifierProfil() {
        // Implémenter l'ouverture de la fenêtre de modification de profil
        // Vous pouvez créer une nouvelle vue et l'afficher ici
        JOptionPane.showMessageDialog(view, "Modification de profil non implémentée.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addFichePersonnage() {
        // Implémenter l'ajout d'une fiche de personnage
        JOptionPane.showMessageDialog(view, "Ajout de fiche de personnage non implémenté.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
