package Ui;

import Data.Controleur;
import java.awt.*;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
 

public class WindowUI extends JFrame {
    public final String PATH = getPath();
    private Controleur controleur;
    
    private MainUI mainPanel;

    public WindowUI(Controleur con) {
	this.controleur = con;
	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon(PATH + "fond.png")));
        setLayout(new FlowLayout());
        initComponents();
        pack();
    }
    
    private String getPath() {
        String current = "";

        try {
            current = new java.io.File(".").getCanonicalPath() + "/fotau/";
        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("Location of the folder: " + current);

        return current;
    }
    
    public void initComponents() {
        mainPanel = new MainUI(this);
	
	getContentPane().add(mainPanel);
    }
    
}