package view;

import javax.swing.*;
import model.Utilisateur;
import model.FichePersonnage;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class MenuAccueilView extends JFrame {
    private JLabel nomUtilisateurLabel;
    private JButton deconnexionButton, modifierButton, ajouterFicheButton;
    private JPanel fichesPanel;
    private JList<String> fichesList;
    private DefaultListModel<String> fichesModel;
    private JPanel mainPanel;
    public Utilisateur user;

    public MenuAccueilView(Utilisateur user) {
    	this.user = user;
    	
    	 initComponents(user.getId());
    	 
        initFicheList(user.getFichesPersonnages());
        setTitle("Menu d'Accueil");
        setSize(800, 600); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
      


        
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
        initComponents(user.getId());

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
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));

        
        JScrollPane scrollPane = new JScrollPane(fichesList);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        
     //
        /* Panel for character sheets
        fichesPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        fichesPanel.setOpaque(false); // Set fiches panel to be transparent
        JScrollPane scrollPane = new JScrollPane(fichesPanel);
        scrollPane.setOpaque(false); // Set scroll pane to be transparent
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        */
        // Button to add a character sheet
        ajouterFicheButton = new JButton("Ajouter une fiche");
        getContentPane().add(ajouterFicheButton, BorderLayout.SOUTH);
    }
   private void initFicheList(List<FichePersonnage> fiches) {
	   //for (FichePersonnage fiche : fiches)
        if (fiches instanceof Iterable) {
            fichesModel = new DefaultListModel<>();
            for (Object item : (Iterable) fiches) {
                if (item instanceof FichePersonnage) {
                    FichePersonnage fiche = (FichePersonnage) item;
                    fichesModel.addElement(fiche.getName());
                }
            }
            fichesList = new JList<>(fichesModel);
            fichesList.setOpaque(false);
            fichesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } else {
            System.err.println("La liste fournie n'est pas iterable");
        }
    }
   /* private void initFicheCards(List<FichePersonnage> fiches) {
        for (FichePersonnage fiche : fiches) {
            JPanel card = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel(fiche.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

            // Set the image
            ImageIcon icon = new ImageIcon(fiche.getPortrait().getPath());
            JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

            card.add(nameLabel, BorderLayout.NORTH);
            card.add(imageLabel, BorderLayout.CENTER);

            // Optionally add a border
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            mainPanel.add(card);
        }
    }*/

	

    private void initComponents(String nomUtilisateur) {
        nomUtilisateurLabel = new JLabel(nomUtilisateur, SwingConstants.CENTER);
        nomUtilisateurLabel.setForeground(Color.WHITE);

        deconnexionButton = new JButton("Déconnexion");
        modifierButton = new JButton("Modifier le mot de passe");
    }
    public void displayFiches(List<FichePersonnage> fiches) {
        fichesModel.clear();
        for (FichePersonnage fiche : fiches) {
            fichesModel.addElement(fiche.getName());
        }
    }
    
  
    /*public void addDeconnexionListener(ActionListener listener) {
        deconnexionButton.addActionListener(listener);
    }


    public void addModifier(ActionListener listener) {
        modifierButton.addActionListener(listener);
    }
*/
    
    public JButton getDeconnexionButton() {
        return deconnexionButton;
    }

    public JButton getAjouterFicheButton() {
        return ajouterFicheButton;
    }

    public JButton getModifierButton() {
        return modifierButton;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        nomUtilisateurLabel.setText(nomUtilisateur);
    }
    
    public static void main(String[] args) {
        // Simulate fetching some character sheets
    	Utilisateur user = new Utilisateur("Test", "mdp");
        user.addFichePersonnage(new FichePersonnage("Wizard", 1));
        user.addFichePersonnage(new FichePersonnage("Warrior", 2));
        user.addFichePersonnage(new FichePersonnage("Archer", 3));

        // Run the GUI in the Swing event dispatch thread
        SwingUtilities.invokeLater(() -> {
            MenuAccueilView view = new MenuAccueilView(user);
            view.setVisible(true);
        });
    }
    }

 
	

  