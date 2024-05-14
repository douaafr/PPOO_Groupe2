package controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.FichePersonnage;
import model.Statistique;
import model.Utilisateur;
import view.FenetreConsultFiche;
import view.MenuAccueilView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControllerConsultFiche {
	
	
	private Utilisateur utilisateur;
	private FichePersonnage fiche;
    private FenetreConsultFiche view;
    private MenuAccueilView mAV;
	
	public ControllerConsultFiche(Utilisateur user, FichePersonnage model, FenetreConsultFiche view, MenuAccueilView mav) {
		this.utilisateur = user;
		this.fiche = model;
		this.view = view;
		this.mAV = mav;
		initController();
	}

	private void initController() {
        view.addChangePortraitButtonListener(new ChangePortraitButtonListener());
        view.addAddStatButtonListener(new AddStatButtonListener());
        view.addAddCompButtonListener(new AddCompButtonListener());
        view.addAddEquipButtonListener(new AddEquipButtonListener());
        view.addSaveButtonListener(new SaveButtonListener());
    }

    class ChangePortraitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg"));
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fiche.getPortrait().setPath(selectedFile.getAbsolutePath());
                view.setPortrait(selectedFile.getAbsolutePath());
            }
        }
    }

    class AddStatButtonListener implements ActionListener {
    	@Override
	    public void actionPerformed(ActionEvent e) {
	        String statName = view.getNewStatField().getText();
	        String statValue = view.getNewStatValueField().getText();  // Récupère la valeur de la nouvelle statistique
	
	        // Efface les champs de texte après l'ajout
	        view.getNewStatField().setText("");
	        view.getNewStatValueField().setText("");
	
	        if (statName.isEmpty()) {
	            JOptionPane.showMessageDialog(view, "Le nom de la statistique ne peut pas être vide !");
	            return;
	        }
	
	        int value = statValue.isEmpty() ? 0 : parseStatValue(statValue); // Utilise 0 comme valeur par défaut si le champ valeur est vide
	        fiche.addStatistique(new Statistique(statName, value)); // Ajoute la statistique au modèle avec la valeur spécifiée ou par défaut
	        view.updateStats(fiche.getStatistiques());
	    }
	
	    private int parseStatValue(String statValue) {
	        try {
	            return Integer.parseInt(statValue);  // Convertit la valeur en entier
	        } catch (NumberFormatException nfe) {
	            JOptionPane.showMessageDialog(view, "La valeur de la statistique saisie n'est pas valide. Elle est initialisée par la valeur par défaut : 0.");
	            return 0;  // Retourne 0 si la conversion échoue
	        }
	    }
    }

    class AddCompButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String compName = view.getNewCompField().getText();
            if (compName.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Le nom de la compétence ne peut pas être vide !");
                return;
            }

            fiche.addCompetence(compName); // Ajoute la compétence au modèle
            view.getNewCompField().setText(""); // Efface le champ de texte après l'ajout
            // Mettre à jour la vue
            DefaultListModel<String> modelList = (DefaultListModel<String>) view.getCompetencesList().getModel();
            modelList.addElement(compName);
            view.updateCompetences(modelList);
        }
    }

    class AddEquipButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String equipName = view.getNewEquipField().getText();
            if (equipName.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Le nom de l'équipement ne peut pas être vide !");
                return;
            }

            fiche.addEquipement(equipName); // Ajoute l'équipement au modèle
            view.getNewEquipField().setText(""); // Efface le champ de texte après l'ajout
            // Mettre à jour la vue
            DefaultListModel<String> modelList = (DefaultListModel<String>) view.getEquipementList().getModel();
            modelList.addElement(equipName);
            view.updateEquipements(modelList);
        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = view.getNameField().getText();
            String pv = view.getPVField().getText();
            String age = view.getAgeField().getText();

            if (nom.isEmpty() || pv.isEmpty() || age.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vous devez remplir tous les champs obligatoires !");
                return;
            }

            fiche.setName(nom);
            try {
                fiche.setPointDeVie(Integer.parseInt(pv));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Les points de vie doivent être un nombre valide !");
                return;
            }

            try {
                fiche.setAge(Integer.parseInt(age));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "L'âge doit être un nombre valide !");
                return;
            }

            fiche.setBiographie(view.getBiographie().getText());
            utilisateur.sauvegarderFichesPersonnages();

            JOptionPane.showMessageDialog(view, "Données sauvegardées !");
            mAV.updateFicheList();
            view.setVisible(false);
        }
    }
}