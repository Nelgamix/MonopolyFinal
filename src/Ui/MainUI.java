/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class MainUI extends JPanel {
    private WindowUI window;
    
    private ArrayList<JButton> buttons = new ArrayList<>();
    
    public MainUI(WindowUI window) {
	this.window = window;
	
	initComponents();
	initListeners();
    }
    
    private void initComponents() {
	this.setLayout(new BorderLayout());
	
        JPanel north = new JPanel(new GridLayout(1,11));
        JPanel south = new JPanel(new GridLayout(1,11));
        JPanel east = new JPanel(new GridLayout(9,1));
        JPanel west = new JPanel(new GridLayout(9,1));
        this.setOpaque(false);
        south.setOpaque(false);
        north.setOpaque(false);
        east.setOpaque(false);
        west.setOpaque(false);
	
	JButton but = null;
	JButton [] buttonsC = new JButton[40];
	int j = 10;
	
	buttons.ensureCapacity(40);
	
	for (int i = 0; i < 40; i++) {
	    but = new JButton();
            but.setPreferredSize(new Dimension(85,85));
            but.setIcon(new ImageIcon(window.PATH + j + ".png"));
            but.setOpaque(false);
	    buttonsC[j] = but;
	    
	    if (j <= 10) {
		south.add(but);
		j--;
		if (j < 0) {
		    j = 19;
		}
	    } else if (j <= 19) {
		west.add(but);
		j--;
		if (j < 11) {
		    j = 20;
		}
	    } else if (j >= 20 && j <= 30) {
		north.add(but);
		j++;
		if (j > 30) {
		    j = 31;
		}
	    } else if (j > 30) {
		east.add(but);
		j++;
	    }
	}
	
	for (JButton b : buttonsC) {
	    buttons.add(b);
	}
	
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(east, BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);
    }
    
    private void initListeners() {
	for (JButton b : buttons) {
            b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Bouton " + buttons.indexOf((JButton)e.getSource()));
                }
            });
        }
    }
    
    
    
}
