package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import model.Compte;
import controller.ControllerCreation;
import java.awt.*;
import java.awt.event.*;

public class FenetreCreation extends JFrame {
	Compte compte;
	JTextField textFieldIdentifiant, textFieldMdp;
	JButton btnInscrire;
	JLabel labelIdentifiant, labelMdp, titleLabel;
	JPanel panel, centerPanel;

	public FenetreCreation() {
		setTitle("Création de Compte");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initUI();
		//setupController();
	}

	private void initUI() {
		// Créer le panel avec un gestionnaire de mise en page BorderLayout
		panel = new BackgroundPanel(new ImageIcon("backgrounds/page_connexion.jpg").getImage());
		panel.setLayout(new BorderLayout());
		add(panel);

		// Créer un panneau central pour aligner les composants au centre
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setOpaque(false);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(120, 80, 120, 80)); // Marges externes

		// Ajouter de la glue pour pousser le contenu vers le bas
		centerPanel.add(Box.createVerticalGlue());

		// Ajouter un espace supplémentaire pour positionner le titre plus bas
		centerPanel.add(Box.createVerticalStrut(100)); // Augmentez cette valeur pour abaisser davantage le titre

		// Titre de la fenêtre
		titleLabel = new JLabel("Créez votre Compte");
		titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
		titleLabel.setForeground(new Color(184, 115, 51));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(titleLabel);

		centerPanel.add(Box.createVerticalStrut(20)); // Espace après le titre

		// Créer des glue pour pousser les composants au centre
		centerPanel.add(Box.createVerticalGlue());

		// Couleur et police médiévale
		Color medievalTextColor = new Color(255, 204, 153);
		Font medievalFont = new Font("Times New Roman", Font.BOLD, 20);

		// Champ Identifiant
		labelIdentifiant = new JLabel("Identifiant:");
		labelIdentifiant.setForeground(medievalTextColor);
		labelIdentifiant.setFont(medievalFont);
		labelIdentifiant.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(labelIdentifiant);

		textFieldIdentifiant = new JTextField(20);
		textFieldIdentifiant.setMaximumSize(new Dimension(200, 30));
		textFieldIdentifiant.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(textFieldIdentifiant);
		centerPanel.add(Box.createVerticalStrut(20));

		// Champ Mot de passe
		labelMdp = new JLabel("Mot de passe:");
		labelMdp.setForeground(medievalTextColor);
		labelMdp.setFont(medievalFont);
		labelMdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(labelMdp);

		textFieldMdp = new JPasswordField(20);
		textFieldMdp.setMaximumSize(new Dimension(200, 30));
		textFieldMdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(textFieldMdp);
		centerPanel.add(Box.createVerticalStrut(30)); // Espace avant le bouton

		// Bouton S'inscrire
		JButton btnInscrire = new JButton("S'inscrire");
		btnInscrire.setBackground(new Color(101, 67, 33));
		btnInscrire.setFont(medievalFont);
		btnInscrire.setForeground(medievalTextColor);
		// btnInscrire.setFont(medievalFont);
		// btnInscrire.setForeground(medievalTextColor);
		btnInscrire.setAlignmentX(Component.CENTER_ALIGNMENT);
		//btnInscrire.setOpaque(false);
		//btnInscrire.setContentAreaFilled(false);
		btnInscrire.setBorderPainted(true);
		btnInscrire.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnInscrire.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnInscrire.setBackground(new Color(101, 67, 33));
				btnInscrire.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent e) {
				btnInscrire.setBackground(new Color(101, 67, 33));
				btnInscrire.setForeground(medievalTextColor);
			}
		});
		centerPanel.add(btnInscrire);

		// Ajouter un autre glue pour centrer verticalement
		centerPanel.add(Box.createVerticalGlue());

		// Ajouter le panneau central au panneau principal
		panel.add(centerPanel, BorderLayout.CENTER);
	}
//	 private void setupController() {
//	        // Pass this view's components to the controller
//	        ControllerCreation controller = new ControllerCreation(compte, textFieldIdentifiant, textFieldMdp, panel, btnInscrire);
//	        btnInscrire.addActionListener(controller);
//	    }

	class BackgroundPanel extends JPanel {
		private Image image;

		public BackgroundPanel(Image image) {
			this.image = image;
			setLayout(new BorderLayout());
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			FenetreCreation fenetre = new FenetreCreation();
			fenetre.setVisible(true);
		});
	}
}
	

 


