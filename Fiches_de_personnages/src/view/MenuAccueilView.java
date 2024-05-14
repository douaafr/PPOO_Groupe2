package view;

import controller.MenuAccueilController;
import model.Utilisateur;
import model.FichePersonnage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        listFiches.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    controller.showPopupMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    controller.showPopupMenu(e);
                }
            }
        });
    }

    private void initializeComponents() {
        // Définir la couleur et le style d'écriture pour le thème médiéval
        Color medievalTextColor = new Color(204, 153, 102); // Beige plus foncé
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
        listFiches.setCellRenderer(new FichePersonnageListCellRenderer());
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

    public void updateFicheList() {
        listModel.clear();
        for (FichePersonnage fiche : utilisateur.getFichesPersonnages()) {
            listModel.addElement(fiche);
        }
    }

    public FichePersonnage getSelectedFichePersonnage() {
        return listFiches.getSelectedValue();
    }

    public JList<FichePersonnage> getListFiches() {
        return listFiches;
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

    private class FichePersonnageListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof FichePersonnage) {
                FichePersonnage fichePersonnage = (FichePersonnage) value;
                setText(fichePersonnage.getName()); // Afficher le nom de la fiche personnage
                setFont(new Font("Times New Roman", Font.BOLD, 24)); // Définir une police plus grande
                setForeground(new Color(204, 153, 102)); // Définir la couleur du texte (beige plus foncé)
            }
            setOpaque(isSelected); // Rendre transparent sauf lors de la sélection
            return this;
        }
    }
}
