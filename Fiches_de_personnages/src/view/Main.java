package view;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main {

	public static void main(String args[]) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		FenetreConnexion fc = new FenetreConnexion();
		fc.pack();
		fc.setSize(800, 800);
		fc.setLocationRelativeTo(null);
		fc.setVisible(true);
	}
}
