package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenetreConnexion extends JFrame {
	
	BufferedImage backgroundImage;
    
	public FenetreConnexion() {
		// Initialisation de la fenêtre
		this.setTitle("Bienvenue");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        
        //Créer un label bienvenue
        JLabel bienvenueLabel = new JLabel("Bienvenue");
        bienvenueLabel.setForeground(medievalTextColor);
        bienvenueLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));

        // Créer les champs d'identifiant et de mot de passe
        JTextField identifiantField = new JTextField(20);
        JPasswordField motDePasseField = new JPasswordField(20);

        // Créer les libellés pour les champs avec le style médiéval
        JLabel identifiantLabel = new JLabel("Identifiant:");
        identifiantLabel.setForeground(medievalTextColor);
        identifiantLabel.setFont(medievalFont);

        JLabel motDePasseLabel = new JLabel("Mot de passe:");
        motDePasseLabel.setForeground(medievalTextColor);
        motDePasseLabel.setFont(medievalFont);
        
        // Créer les boutons de connexion et de création de compte
        JButton connexionBoutton = new JButton("Se connecter");
        connexionBoutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        JButton creationCPTButton = new JButton("Créer un compte");
        connexionBoutton.setBackground(new Color(101, 67, 33));
        connexionBoutton.setFont(medievalFont);
        connexionBoutton.setForeground(medievalTextColor);
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
        
        connexionBoutton.setBounds(199, 650, 150, 30); // Position et taille du bouton de connexion
        contentPane.add(connexionBoutton);
        
        creationCPTButton.setBounds(392, 650, 180, 30); // Position et taille du bouton de création de compte
        contentPane.add(creationCPTButton);
        
        // Définir le panneau avec image de fond comme contenu de la fenêtre
        setContentPane(contentPane);
    }
}
