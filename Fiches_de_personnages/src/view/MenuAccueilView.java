package view;

import javax.swing.*;
import model.Utilisateur;
import java.awt.*;

public class MenuAccueilView extends JFrame {
    private JLabel nomUtilisateurLabel;
    private JButton deconnexionButton, modifierButton, ajouterFicheButton;
    private JPanel fichesPanel;

    public MenuAccueilView(String nomUtilisateur) {
        setTitle("Menu d'Accueil");
        setSize(800, 600); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Charger l'image d'arrière-plan
        ImageIcon originalBackground = new ImageIcon("backgrounds/menu.jpg");
        Image backgroundImage = originalBackground.getImage();

        JLabel backgroundLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Redimensionner l'image pour qu'elle remplisse le JLabel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundLabel.setLayout(new BorderLayout());

        setContentPane(backgroundLabel); // Définir le label comme content pane du JFrame

        // Initialize other UI components
        initComponents(nomUtilisateur);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(nomUtilisateurLabel, BorderLayout.WEST);
        headerPanel.setOpaque(false); // Set header panel to be transparent

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(modifierButton);
        buttonPanel.add(deconnexionButton);
        buttonPanel.setOpaque(false); // Set button panel to be transparent
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Panel for character sheets
        fichesPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        fichesPanel.setOpaque(false); // Set fiches panel to be transparent
        JScrollPane scrollPane = new JScrollPane(fichesPanel);
        scrollPane.setOpaque(false); // Set scroll pane to be transparent
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Button to add a character sheet
        ajouterFicheButton = new JButton("+");
        getContentPane().add(ajouterFicheButton, BorderLayout.SOUTH);
    }

    private void initComponents(String nomUtilisateur) {
        nomUtilisateurLabel = new JLabel(nomUtilisateur, SwingConstants.CENTER);
        nomUtilisateurLabel.setForeground(Color.WHITE);

        deconnexionButton = new JButton("Déconnexion");
        modifierButton = new JButton("Modifier Profil");
    }

  
}
