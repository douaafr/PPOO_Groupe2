package controller;

import java.awt.event.*;
import javax.swing.*;
import view.MenuAccueilView;
import view.FenetrePassword;
import view.FenetreConnexion;
import view.FenetreFichePerso;
import view.FenetreConsultFiche;
import model.Compte;
import model.FichePersonnage;
import model.Utilisateur;

public class MenuAccueilController implements ActionListener {
	
    MenuAccueilView view;
    Utilisateur utilisateur;
    Compte compte;
    JPopupMenu popupMenu;
    JMenuItem consultMenuItem;
    JMenuItem deleteMenuItem;

    public MenuAccueilController(MenuAccueilView view, Utilisateur utilisateur, Compte c) {
        this.view = view;
        this.utilisateur = utilisateur;
        this.compte = c;
        createPopupMenu();
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        consultMenuItem = new JMenuItem("Consulter");
        deleteMenuItem = new JMenuItem("Supprimer");

        consultMenuItem.addActionListener(e -> {
            FichePersonnage selectedFiche = view.getSelectedFichePersonnage();
            if (selectedFiche != null) {
                FenetreConsultFiche consultFiche = new FenetreConsultFiche(utilisateur, selectedFiche, view);
                consultFiche.setController();
                consultFiche.pack();
        		consultFiche.setSize(1155, 800);
        		consultFiche.setLocationRelativeTo(null);
        		consultFiche.setResizable(false);
        		consultFiche.setVisible(true);
            }
        });

        deleteMenuItem.addActionListener(e -> {
            FichePersonnage selectedFiche = view.getSelectedFichePersonnage();
            if (selectedFiche != null) {
            	// Définir les textes en français pour le JOptionPane
                UIManager.put("OptionPane.yesButtonText", "Oui");
                UIManager.put("OptionPane.noButtonText", "Non");
                UIManager.put("OptionPane.messageDialogTitle", "Confirmation");
                int confirm = JOptionPane.showConfirmDialog(view, "Êtes-vous sûr de vouloir supprimer cette fiche ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    utilisateur.deleteFiche(selectedFiche.getIdFiche());
                    view.updateFicheList();
                }
            }
        });

        popupMenu.add(consultMenuItem);
        popupMenu.add(deleteMenuItem);
    }

    public void showPopupMenu(MouseEvent e) {
        int index = view.getListFiches().locationToIndex(e.getPoint());
        view.getListFiches().setSelectedIndex(index);
        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getText().equals("Modifier mot de passe")) {
            FenetrePassword fenetrePassword = new FenetrePassword(utilisateur,compte);
            fenetrePassword.pack();
            fenetrePassword.setSize(800, 800);
            fenetrePassword.setLocationRelativeTo(null);
            fenetrePassword.setResizable(false);
            fenetrePassword.setVisible(true);
        } else if (source.getText().equals("Se déconnecter")) {
            // Appelle la méthode de déconnexion du modèle Utilisateur
            compte.signOut(utilisateur); // Assurez-vous que cette méthode gère la déconnexion correctement
            FenetreConnexion fenetreConnexion = new FenetreConnexion(compte);
            view.dispose(); // Fermer la fenêtre actuelle
        } else if (source.getText().equals("Ajouter une fiche")) {
            FichePersonnage fichePersonnage = new FichePersonnage(utilisateur.getFichesPersonnages().size() + 1);
            FenetreFichePerso fenetreFichePerso = new FenetreFichePerso(utilisateur, fichePersonnage, view);
            fenetreFichePerso.setController();
            fenetreFichePerso.pack();
            fenetreFichePerso.setSize(1155, 800);
            fenetreFichePerso.setLocationRelativeTo(null);
            fenetreFichePerso.setResizable(false);
            fenetreFichePerso.setVisible(true);
        } else if (source.getText().equals("Supprimer le compte")) {
            UIManager.put("OptionPane.yesButtonText", "Oui");
            UIManager.put("OptionPane.noButtonText", "Non");
            UIManager.put("OptionPane.messageDialogTitle", "Confirmation");
            int confirm = JOptionPane.showConfirmDialog(view, "Êtes-vous sûr de vouloir supprimer ce compte ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
            	if (utilisateur.getFichesPersonnages() != null && !utilisateur.getFichesPersonnages().isEmpty()) {
            		for (FichePersonnage fiche : utilisateur.getFichesPersonnages()) {
            			utilisateur.deleteFiche(fiche.idFiche);
            		}
            	}
            	compte.deleteAccount(utilisateur);
            	view.dispose();
            }
        }
    }
}
