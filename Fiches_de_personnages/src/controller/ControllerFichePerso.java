package controller;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

import model.Utilisateur;
import model.FichePersonnage;
import model.Statistique;
import view.FenetreFichePerso;


public class ControllerFichePerso {
	
	private Utilisateur utilisateur;
	private FichePersonnage fiche;
    private FenetreFichePerso view;

    public ControllerFichePerso(Utilisateur user, FichePersonnage model, FenetreFichePerso view) {
    	this.utilisateur = user;
        this.fiche = model;
        this.view = view;
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
                //fiche.setPortrait(new Portrait());
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
            view.updateEquipments(modelList);
        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	fiche.setName(view.getNameField().getText());
            fiche.setPointDeVie(Integer.parseInt(view.getPVField().getText()));
            fiche.setAge(Integer.parseInt(view.getAgeField().getText()));
            fiche.setBiographie(view.getBiographie().getText());
            utilisateur.addFichePersonnage(fiche);
            
            JOptionPane.showMessageDialog(view, "Données sauvegardées !");
        }
    }
}