package view;

import java.awt.*;
import javax.swing.*;

public class FenetreConnexion extends JFrame {
	
	JLabel labelBienvenue = new JLabel("Bienvenue", JLabel.CENTER);
	JLabel id = new JLabel("Identifiant");
	JLabel mdp = new JLabel("Mot de passe");
	JTextField idField = new JTextField("");
	JTextField mdpField = new JTextField("");
	JButton connexion = new JButton("se connecter");
	JButton creationCompte = new JButton("créer un compte");
	
	public FenetreConnexion() {
		this.setTitle("Bienvenue");
		JPanel panelText = new JPanel();
		panelText.setLayout(new GridLayout(2,2));
		JPanel panelBoutons = new JPanel();
		panelBoutons.add(connexion);
		panelBoutons.add(creationCompte);
		
		this.setLayout(new GridLayout(2,1));
		this.add(labelBienvenue);
		this.add(panelBoutons);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
