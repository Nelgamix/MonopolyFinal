/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Jeu.Joueur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class SelectionJoueursUI extends JFrame {
    private JList listJoueurs;
    private JTextField saisieJoueur;
    private JButton ajouterJoueur;
    private JButton retirerJoueur;
    private ArrayList<Joueur> joueurs = new ArrayList<>();

    public SelectionJoueursUI() throws HeadlessException {
	super("Selection joueurs");
	
	initComponents();
	initListeners();
    }
    
    private void initComponents() {
	GridBagConstraints gbc = new GridBagConstraints();
	
	JPanel panListJ = new JPanel(new BorderLayout());
	JPanel panJoueurAct = new JPanel(new BorderLayout());
	
	this.setLayout(new GridBagLayout());
	
	listJoueurs = new JList();
	retirerJoueur = new JButton();
	panListJ.add(listJoueurs, BorderLayout.CENTER);
	panListJ.add(retirerJoueur, BorderLayout.SOUTH);
	
	saisieJoueur = new JTextField();
	ajouterJoueur = new JButton();
	panJoueurAct.add(saisieJoueur, BorderLayout.CENTER);
	panJoueurAct.add(ajouterJoueur, BorderLayout.CENTER);
	
	gbc.gridx = 0;
	gbc.gridy = 0;
	this.add(panListJ, gbc);
	
	gbc.gridx = 1;
	gbc.gridy = 0;
	this.add(panJoueurAct, gbc);
	
	this.setSize(400, 300);
	
    }
    
    private void initListeners() {
	ajouterJoueur.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.out.println("Ajouter joueur");
	    }
	});
	
	retirerJoueur.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.out.println("Retirer joueur");
	    }
	});
    }
    
    
    
}
