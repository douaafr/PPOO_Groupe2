package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Compte;
import controller.ControllerCreation;

public class FenetreCreation extends JFrame {
	
	public Compte cpt;
	BufferedImage backgroundImage;
	JLabel bienvenueLabel = new JLabel("Bienvenue");
	JTextField identifiantField = new JTextField(20);
	JPasswordField motDePasseField = new JPasswordField(20);
	JLabel identifiantLabel = new JLabel("Identifiant:");
	JLabel motDePasseLabel = new JLabel("Mot de passe:");
	JButton creationCPTButton = new JButton("S'inscrire");
	JPanel panelConnexion = new JPanel();
	JPanel panelMessage = new JPanel();
    
	public FenetreCreation(Compte c) {
		this.cpt = c;
		// Initialisation de la fenêtre
		this.setTitle("Bienvenue");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Charger l'image de fond
        try {
            // Charger l'image à partir d'un fichier
        	backgroundImage = ImageIO.read(new File("backgrounds/page_connexion.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Création d'un panneau avec une image de fond
        JPanel contentPane = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dessiner l'image en tant que fond et redimensionner si nécessaire
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),  this);
                }
            }
        };

        // Définir la couleur et le style d'écriture pour le thème médiéval
        Color medievalTextColor = new Color(255, 204, 153); // Couleur beige/marron clair
        Font medievalFont = new Font("Times New Roman", Font.BOLD, 20); // Style d'écriture médiéval
        
        // Personnalisation du label bienvenue
        bienvenueLabel.setForeground(medievalTextColor);
        bienvenueLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));

        // Personnalisation des champs d'identifiant et de mot de passe
        identifiantField.setFont(new Font("Garamond", Font.PLAIN, 16));
        motDePasseField.setFont(new Font("Garamond", Font.PLAIN, 16));

        // Personnalisation des libellés pour les champs avec le style médiéval
        identifiantLabel.setForeground(medievalTextColor);
        identifiantLabel.setFont(medievalFont);

        motDePasseLabel.setForeground(medievalTextColor);
        motDePasseLabel.setFont(medievalFont);
        
        // Personnalisation des boutons de connexion et de création de compte
        creationCPTButton.setBackground(new Color(101, 67, 33));
        creationCPTButton.setFont(medievalFont);
        creationCPTButton.setForeground(medievalTextColor);
        
        // Définition du layout null
        contentPane.setLayout(null);
        
        // Ajout des éléments à l'interface utilisateur
        bienvenueLabel.setBounds(298, 298, 180, 82); // Position et taille de JLabel "Bienvenue"
        contentPane.add(bienvenueLabel);
        
        //identifiantLabel.setForeground(new Color(139, 69, 19));
        identifiantLabel.setBounds(284, 420, 111, 30); // Position et taille de JLabel "Identifiant"
        contentPane.add(identifiantLabel);
        
        identifiantField.setBounds(284, 461, 194, 30); // Position et taille de JTextField pour l'identifiant
        contentPane.add(identifiantField);
        
        //motDePasseLabel.setForeground(new Color(139, 69, 19));
        motDePasseLabel.setBounds(284, 514, 127, 20); // Position et taille de JLabel "Mot de passe"
        contentPane.add(motDePasseLabel);
        
        motDePasseField.setBounds(284, 545, 194, 30); // Position et taille de JTextField pour le mot de passe
        contentPane.add(motDePasseField);
        
        creationCPTButton.setBounds(291, 650, 180, 30); // Position et taille du bouton de création de compte
        contentPane.add(creationCPTButton);
        
        // Définir le panneau avec image de fond comme contenu de la fenêtre
        setContentPane(contentPane);
        
        // A afficher si l'id ou le mdp incorrect
        panelConnexion.setOpaque(false);
        panelConnexion.setBounds(216, 586, 327, 38);
        panelConnexion.setVisible(false); // Masquer le panel au début
        contentPane.add(panelConnexion);
        
        JLabel lblNewLabel = new JLabel("Cet identifiant existe déjà");
        panelConnexion.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(255, 69, 0));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        // A afficher si au moins l'un des champs est vide
        panelMessage.setOpaque(false);
        panelMessage.setBounds(216, 586, 327, 38);
        panelMessage.setVisible(false); // Masquer le panel au début
        contentPane.add(panelMessage);
        
        JLabel lblNewLabelMessage = new JLabel("Les champs ne peuvent pas être vides");
        panelMessage.add(lblNewLabelMessage);
        lblNewLabelMessage.setForeground(new Color(255, 69, 0));
        lblNewLabelMessage.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        
        //creation d'une instance de gestion d'evenement
        ControllerCreation controller = new ControllerCreation(this, identifiantField, motDePasseField, panelConnexion, panelMessage, cpt);
        
        //association avec les boutons
      	creationCPTButton.addActionListener(controller);
    }
}
