package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Compte;
import view.FenetreCreation;

public class ControllerCreation implements ActionListener {
    private Compte compte;
    private JTextField zoneTexteId;
    private JTextField zoneTexteMdp;
    private JPanel panel;
    private JButton btnInscrire;

    public ControllerCreation(Compte compte, JTextField zoneTexteId, JTextField zoneTexteMdp, JPanel panel, JButton btnInscrire) {
        this.compte = compte;
        this.zoneTexteId = zoneTexteId;
        this.zoneTexteMdp = zoneTexteMdp;
        this.panel = panel;
        this.btnInscrire = btnInscrire;
        this.btnInscrire.addActionListener(this);

        // Configure the success label but do not add it to panel yet
        JLabel creationLabel = new JLabel("Compte créé avec succès!");
        creationLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
        creationLabel.setForeground(new Color(184, 115, 51));
        creationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new BorderLayout());
        panel.add(creationLabel, BorderLayout.CENTER);
        creationLabel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Only perform action if the source is the 'S'inscrire' button
        if (e.getSource() == btnInscrire) {
            if (!zoneTexteId.getText().isEmpty() && !zoneTexteMdp.getText().isEmpty()) {
                try {
					compte.createAccount(zoneTexteId.getText(), zoneTexteMdp.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                zoneTexteId.setText("");
                zoneTexteMdp.setText("");
                panel.removeAll();
                JLabel successLabel = (JLabel) panel.getComponent(0); // Assuming the label is the first component
                successLabel.setVisible(true);
                panel.add(successLabel);
                panel.revalidate();
                panel.repaint();
            } else {
                JOptionPane.showMessageDialog(panel, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
