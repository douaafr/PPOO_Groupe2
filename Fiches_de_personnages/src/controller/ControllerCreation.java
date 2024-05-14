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
	Compte c;
	JTextField identifiantField;
	JPasswordField motDePasseField;
	JPanel panel;
	JPanel messagePanel;
	
	public ControllerCreation(FenetreCreation f, JTextField id, JPasswordField pwd, JPanel p, JPanel jp, Compte c) {
		this.fc = f;
		this.identifiantField = id;
		this.motDePasseField = pwd;
		this.panel = p;
		this.messagePanel = jp;
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (((JButton) e.getSource()).getText().equals("S'inscrire")) {
	        String identifiant = identifiantField.getText();
	        char[] mdpChars = motDePasseField.getPassword();
	        String motDePasse = new String(mdpChars);
	        Arrays.fill(mdpChars, '0'); // Sécurité pour effacer le mot de passe de la mémoire

	        try {
	        	c.createAccount(identifiant, motDePasse);
	            fc.setVisible(false);
	        } catch (Exception e1) {
	            if (e1.getMessage().equals("L'identifiant et/ou le mot de passe ne sont pas saisis")) {
	                messagePanel.setVisible(true);
	                Timer timer = new Timer(3000, event -> {
	                    messagePanel.setVisible(false);
	                    identifiantField.setText("");
	                    motDePasseField.setText("");
	                    ((Timer) event.getSource()).stop();
	                });
	                timer.setRepeats(false);
	                timer.start();
	            } else if (e1.getMessage().equals("Un utilisateur avec cet identifiant existe déjà.")) {
	                panel.setVisible(true);
	                Timer timer = new Timer(3000, event -> {
	                    panel.setVisible(false);
	                    identifiantField.setText("");
	                    motDePasseField.setText("");
	                    ((Timer) event.getSource()).stop();
	                });
	                timer.setRepeats(false);
	                timer.start();
	            }
	        }
		}
	}
}
