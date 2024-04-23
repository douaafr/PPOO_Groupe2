package view;

import javax.swing.*;

import model.Utilisateur;

import java.awt.*;
import java.awt.event.ActionListener;

public class MenuAccueilView extends JFrame {
    private JLabel nomUtilisateurLabel;
    private JButton deconnexionButton, modifierButton, ajouterFicheButton;
    private JPanel fichesPanel;

    public MenuAccueilView(String nomUtilisateur) {
        setTitle("Menu d'Accueil");
        setSize(800, 600); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        // Load the background image
        ImageIcon backgroundImageIcon = new ImageIcon("../backgrounds/menu.png"); // Assurez-vous que le chemin est correct
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Add the background label to the content pane
        setContentPane(backgroundLabel); // Définir le label comme content pane du JFrame

        // Initialize other UI components
        initComponents(nomUtilisateur);

        // Header
        nomUtilisateurLabel = new JLabel(nomUtilisateur);
        deconnexionButton = new JButton("Déconnexion");
        modifierButton = new JButton("Modifier Profil");

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(nomUtilisateurLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(modifierButton);
        buttonPanel.add(deconnexionButton);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Panel for character sheets
        fichesPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        JScrollPane scrollPane = new JScrollPane(fichesPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Button to add a character sheet
        ajouterFicheButton = new JButton("+");
        getContentPane().add(ajouterFicheButton, BorderLayout.SOUTH);
    }

    private void initComponents(String nomUtilisateur) {
        // Example component: A label for the username
        nomUtilisateurLabel = new JLabel(nomUtilisateur, SwingConstants.CENTER);
        nomUtilisateurLabel.setForeground(Color.WHITE); // Choose a color visible on the background
        nomUtilisateurLabel.setBounds(350, 20, 100, 30); // Adjust according to your layout
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Make sure Utilisateur has a constructor that takes a username and password
                Utilisateur utilisateurConnecte = new Utilisateur("NomUtilisateur", "MotDePasse");
                MenuAccueilView menuAccueilView = new MenuAccueilView(utilisateurConnecte.getId());
                menuAccueilView.setVisible(true);
            }
        });
    }
}
