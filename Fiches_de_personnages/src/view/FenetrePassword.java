package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerPassword;
import model.Compte;
import model.Utilisateur;

public class FenetrePassword extends JFrame {
	public Compte cpt;
	public Utilisateur user;
	BufferedImage backgroundImage;
	JLabel bienvenueLabel = new JLabel("Informations compte");
	JPasswordField oldPwdField = new JPasswordField(20);
	JPasswordField newPwdField = new JPasswordField(20);
	JLabel oldPwdLabel = new JLabel("Mot de passe:");
	JLabel newPwdLabel = new JLabel("Nouveau mot de passe:");
	JButton confirmBoutton = new JButton("Confirmer");
	JPanel panelInfo = new JPanel();

	public FenetrePassword(Utilisateur user, Compte cpt) {
		this.user = user;
		this.cpt = cpt;
		// Initialisation de la fenêtre
		this.setTitle("Informations compte");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Charger l'image de fond
		try {
			// Charger l'image à partir d'un fichier
			backgroundImage = ImageIO.read(new File("backgrounds/Menu.jpg"));
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
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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
		oldPwdField.setFont(new Font("Garamond", Font.PLAIN, 16));
		newPwdField.setFont(new Font("Garamond", Font.PLAIN, 16));

		// Personnalisation des libellés pour les champs avec le style médiéval
		oldPwdLabel.setForeground(medievalTextColor);
		oldPwdLabel.setFont(medievalFont);

		newPwdLabel.setForeground(medievalTextColor);
		newPwdLabel.setFont(medievalFont);

		// Personnalisation des boutons de connexion et de création de compte
		confirmBoutton.setBackground(new Color(101, 67, 33));
		confirmBoutton.setFont(medievalFont);
		confirmBoutton.setForeground(medievalTextColor);

		// Définition du layout null
		contentPane.setLayout(null);

		// Ajout des éléments à l'interface utilisateur
		bienvenueLabel.setBounds(218, 298, 365, 82); // Position et taille de JLabel "Bienvenue"
		contentPane.add(bienvenueLabel);

		// oldPwdLabel.setForeground(new Color(139, 69, 19));
		oldPwdLabel.setBounds(284, 420, 200, 30); // Position et taille de JLabel "Identifiant"
		contentPane.add(oldPwdLabel);

		oldPwdField.setBounds(284, 461, 230, 30); // Position et taille de JTextField pour l'identifiant
		contentPane.add(oldPwdField);

		// newPwdLabel.setForeground(new Color(139, 69, 19));
		newPwdLabel.setBounds(284, 514, 200, 20); // Position et taille de JLabel "Mot de passe"
		contentPane.add(newPwdLabel);

		newPwdField.setBounds(284, 545, 230, 30); // Position et taille de JTextField pour le mot de passe
		contentPane.add(newPwdField);

		confirmBoutton.setBounds(291, 650, 180, 30); // Position et taille du bouton de connexion
		contentPane.add(confirmBoutton);

		// Définir le panneau avec image de fond comme contenu de la fenêtre
		setContentPane(contentPane);

		// A afficher si l'id ou le mdp incorrect
		panelInfo.setOpaque(false);
		panelInfo.setBounds(216, 586, 327, 38);
		panelInfo.setVisible(false); // Masquer le panel au début
		contentPane.add(panelInfo);

		JLabel lblNewLabel = new JLabel("Mot de passe incorrect");
		panelInfo.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

		
		ControllerPassword controller = new ControllerPassword (this, oldPwdField, newPwdField, panelInfo,user, cpt);
		
		confirmBoutton.addActionListener(controller);
	}

	/*public static void main(String[] args) {
		FenetrePassword frame = new FenetrePassword();
		frame.pack();
		frame.setSize(800, 800); // Définir la taille souhaitée ou utiliser frame.pack() pour ajuster en fonction
									// des composants
		frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
		frame.setResizable(false); // Rendre la fenêtre non redimensionnable
		frame.setVisible(true); // Rendre la fenêtre visible
	}*/

	
}
