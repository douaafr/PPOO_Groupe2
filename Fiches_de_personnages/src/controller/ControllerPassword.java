package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import model.Compte;
import model.Utilisateur;
import view.MenuAccueilView;
import view.FenetrePassword;
import view.MenuAccueilView;

public class ControllerPassword implements ActionListener {

	FenetrePassword fc;
	Compte c ;
	JPasswordField oldPwdField;
	JPasswordField newPwdField;
	JPanel panel;
	Utilisateur us;

	public ControllerPassword(FenetrePassword f, JPasswordField oldPwd, JPasswordField pwd, JPanel p, Utilisateur utilisateur, Compte c) {
		this.c = c;
		this.fc = f;
		this.oldPwdField = oldPwd;
		this.newPwdField = pwd;
		this.panel = p;
		us = utilisateur;
	}

	public void actionPerformed(ActionEvent e) {
		if (((JButton) e.getSource()).getText().equals("Confirmer")) {
			char[] ancienMdpChars = oldPwdField.getPassword();
			char[] nvMdpChars = newPwdField.getPassword();
			String ancienMotDePasse = new String(ancienMdpChars);
			String nvMotDePasse = new String(nvMdpChars);

			Arrays.fill(ancienMdpChars, '0'); // Pour effacer le tableau de caractère
			Arrays.fill(nvMdpChars, '0'); // Pour effacer le tableau de caractère
			//Utilisateur user = null;
			boolean change = false;
			try {
				change = c.changePwd(ancienMotDePasse, nvMotDePasse,fc.user);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (change == false) {
				// Afficher oldPwd ou mpd
				panel.setVisible(true);
				// Au bout de temps efface la notif et ce qui a été saisie par l'utilisateur
				int temps = 3000;
				Timer timer = new Timer(temps, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Masquer le JPanel
						panel.setVisible(false);

						// Effacer le contenu des champs de texte
						oldPwdField.setText("");
						newPwdField.setText("");

						// Arrêter le timer après expiration
						((Timer) e.getSource()).stop();
					}
				});
				timer.setRepeats(false); // Ne se répète pas
				timer.start();
			} else {
//				MenuAccueilView mav = new MenuAccueilView(user);
//				mav.pack();
//				mav.setSize(800, 800);
//				mav.setLocationRelativeTo(null);
//				mav.setResizable(false);
//				mav.setVisible(true);
				fc.setVisible(false);
				
			}

		}
	}
}
