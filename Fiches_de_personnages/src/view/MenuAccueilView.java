package view;

import javax.swing.*;

import controller.MenuAccueilController;
import model.Utilisateur;
import model.Compte;
import model.FichePersonnage;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MenuAccueilView extends JFrame {
	 JButton btnChangePassword;
	 JButton btnSignOut;
	 JButton btnAddFiche;
	private JList<FichePersonnage> listFiches;
	private DefaultListModel<FichePersonnage> listModel;
	private JLabel lblUsername;
	public Utilisateur utilisateur;
	public Compte cpt;

	public MenuAccueilView(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
		initializeComponents();
		layoutComponents();
		setTitle("Menu d'Accueil");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		MenuAccueilController controller = new MenuAccueilController(this, utilisateur);
		btnChangePassword.addActionListener(controller);
		btnSignOut.addActionListener(controller);
		btnAddFiche.addActionListener(controller);
	}

	private void initializeComponents() {
		// Définir la couleur et le style d'écriture pour le thème médiéval
		Color medievalTextColor = new Color(255, 204, 153); // Couleur beige/marron clair
		Font medievalFont = new Font("Times New Roman", Font.BOLD, 20); // Style d'écriture médiéval

		btnChangePassword = new JButton("Modifier le mot de passe");
		btnSignOut = new JButton("Se déconnecter");
		btnAddFiche = new JButton("Ajouter une fiche");
		lblUsername = new JLabel(utilisateur.getId());
		lblUsername.setForeground(medievalTextColor);
		lblUsername.setFont(medievalFont);
		listModel = new DefaultListModel<>();
		listFiches = new JList<>(listModel);
		listFiches.setOpaque(false); // Rendre la liste transparente
		listFiches.setCellRenderer(new TransparentListCellRenderer()); 
		updateFicheList();
	}

	private void layoutComponents() {
		BackgroundPanel backgroundPanel = new BackgroundPanel("backgrounds/Menu.jpg");
		backgroundPanel.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setOpaque(false);
		rightPanel.add(btnChangePassword);
		rightPanel.add(btnSignOut);

		JPanel southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.add(btnAddFiche);

		topPanel.add(lblUsername, BorderLayout.WEST);
		topPanel.add(rightPanel, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane(listFiches);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false); // Rendre le viewport transparent

		backgroundPanel.add(topPanel, BorderLayout.NORTH);
		backgroundPanel.add(scrollPane, BorderLayout.CENTER);
		backgroundPanel.add(southPanel, BorderLayout.SOUTH);

		setContentPane(backgroundPanel);
	}

	private void updateFicheList() {
		listModel.clear();
		for (FichePersonnage fiche : utilisateur.getFichesPersonnages()) {
			listModel.addElement(fiche);
		}
	}

	class BackgroundPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPanel(String filePath) {
			try {
				backgroundImage = ImageIO.read(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}
	}

	private class TransparentListCellRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			setOpaque(isSelected); // Rendre transparent sauf lors de la sélection
			return this;
		}
	}
	

	
	

}
