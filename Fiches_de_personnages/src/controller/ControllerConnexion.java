package controller;

import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import view.FenetreConnexion;
import view.FenetreCreation;
import view.MenuAccueilView;
import model.Compte;
import model.Utilisateur;

public class ControllerConnexion implements ActionListener {
	
	FenetreConnexion fc;
	Compte c;
	JTextField identifiantField;
	JPasswordField motDePasseField;
	JPanel panel;
	
	public ControllerConnexion(FenetreConnexion f, JTextField id, JPasswordField pwd, JPanel p, Compte c) {
		this.fc = f;
		this.identifiantField = id;
		this.motDePasseField = pwd;
		this.panel = p;
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText().equals("Se connecter")) 
		{
			//fc.cpt = c; 
			String identifiant = identifiantField.getText();
			char[] mdpChars = motDePasseField.getPassword();
			String motDePasse = new String(mdpChars);
			Arrays.fill(mdpChars, '0'); // Pour effacer le tableau de caractère
			Utilisateur user = c.signIn(identifiant, motDePasse);
			if (user == null) {
				// Afficher id ou mpd
				panel.setVisible(true);
				// Au bout de temps efface la notif et ce qui a été saisie par l'utilisateur
				int temps = 3000;
				Timer timer = new Timer(temps, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // Masquer le JPanel
		                panel.setVisible(false);

		                // Effacer le contenu des champs de texte
		                identifiantField.setText("");
		                motDePasseField.setText("");

		                // Arrêter le timer après expiration
		                ((Timer) e.getSource()).stop();
		            }
		        });
		        timer.setRepeats(false); // Ne se répète pas
		        timer.start();
			} else {
				// Effacer le contenu des champs de texte
                identifiantField.setText("");
                motDePasseField.setText("");
				MenuAccueilView mav = new MenuAccueilView(user, c);
				mav.pack();
				mav.setSize(800, 800);
				mav.setLocationRelativeTo(null);
				mav.setResizable(false);
				mav.setVisible(true);
				fc.setVisible(false);
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Créer un compte")) 
		{
			FenetreCreation fenCrea = new FenetreCreation(c);
			fenCrea.pack();
			fenCrea.setSize(800, 800);
			fenCrea.setLocationRelativeTo(null);
			fenCrea.setResizable(false);
			fenCrea.setVisible(true);
		}
	}
}
