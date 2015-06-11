package Jeu;

import Data.Controleur;

public class Main implements Runnable {
    Controleur controleur;

    @Override
    public void run() {
	controleur = new Controleur();
    }
    
    public static void main(String[] args) {
	java.awt.EventQueue.invokeLater(new Main());
    }
    
}
