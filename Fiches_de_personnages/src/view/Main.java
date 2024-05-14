package view;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import model.Compte;

public class Main {

	public static void main(String args[]) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		Compte c = new Compte();
		FenetreConnexion fc = new FenetreConnexion(c);
		fc.pack();
		fc.setSize(800, 800);
		fc.setLocationRelativeTo(null);
		fc.setResizable(false);
		fc.setVisible(true);
	}
}
