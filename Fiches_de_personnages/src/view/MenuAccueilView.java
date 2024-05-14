package view;

import controller.MenuAccueilController;
import model.Utilisateur;
import model.Compte;
import model.FichePersonnage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MenuAccueilView extends JFrame {
	
    JButton btnChangePassword;
    JButton btnDeleteAccount;
    JButton btnSignOut;
    JButton btnAddFiche;
    private JList<FichePersonnage> listFiches;
    private DefaultListModel<FichePersonnage> listModel;
    private JLabel lblUsername;
    public Utilisateur utilisateur;
    public Compte c;

    public MenuAccueilView(Utilisateur utilisateur, Compte c) {
        this.utilisateur = utilisateur;
        this.c = c;
        initializeComponents();
        layoutComponents();
        setTitle("Menu d'Accueil");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        MenuAccueilController controller = new MenuAccueilController(this, utilisateur, c);
        btnChangePassword.addActionListener(controller);
        btnDeleteAccount.addActionListener(controller);
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
        Color medievalTextColor = new Color(182, 133, 92); // Beige plus foncé
        Font medievalFont = new Font("Times New Roman", Font.BOLD, 20); // Style d'écriture médiéval

        btnChangePassword = new JButton("Modifier mot de passe");
        btnDeleteAccount = new JButton("Supprimer le compte");
        btnSignOut = new JButton("Se déconnecter");
        btnAddFiche = new JButton("Ajouter une fiche");
        lblUsername = new JLabel("Utilisateur : "+utilisateur.getId());
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
        rightPanel.add(btnAddFiche);
        rightPanel.add(btnChangePassword);
        rightPanel.add(btnSignOut);

        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.add(btnDeleteAccount);

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
            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel();

            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof FichePersonnage) {
                FichePersonnage fichePersonnage = (FichePersonnage) value;
                label.setText(fichePersonnage.getName());
                label.setFont(new Font("Times New Roman", Font.BOLD, 18));
                label.setForeground(new Color(224, 183, 132));
                panel.add(label, BorderLayout.CENTER);

                // Ajouter un séparateur en bas sauf pour le dernier élément
                if (index < list.getModel().getSize() - 1) {
                    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
                    panel.add(separator, BorderLayout.SOUTH);
                }
            }
            panel.setOpaque(isSelected);
            return panel;
        }
    }

}
