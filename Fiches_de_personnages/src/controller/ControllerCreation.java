package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import model.Compte;
import model.Utilisateur;
import view.FenetreConnexion;
import view.FenetreCreation;
import view.MenuAccueilView;

public class ControllerCreation implements ActionListener {

	FenetreCreation fc;
	Compte c = new Compte();
	JTextField identifiantField;
	JPasswordField motDePasseField;
	JPanel panel;
	
	public ControllerCreation(FenetreCreation f, JTextField id, JPasswordField pwd, JPanel p) {
		this.fc = f;
		this.identifiantField = id;
		this.motDePasseField = pwd;
		this.panel = p;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText().equals("S'inscrire")) 
		{
			fc.cpt = c; 
			String identifiant = identifiantField.getText();
			char[] mdpChars = motDePasseField.getPassword();
			String motDePasse = new String(mdpChars);
			Arrays.fill(mdpChars, '0'); // Pour effacer le tableau de caractère
			Utilisateur user = null;
			try {
				user = c.createAccount(identifiant, motDePasse);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
				FenetreConnexion mav = new FenetreConnexion();
				mav.pack();
				mav.setSize(800, 800);
				mav.setLocationRelativeTo(null);
				mav.setResizable(false);
				mav.setVisible(true);
				fc.setVisible(false);
			}
		}
	}
}
		


