package Jeu;

import Ui.Interface;

public class CarreauMouvement extends CarreauAction {
    private Carreau prison;

    public CarreauMouvement(Monopoly monopoly, int numero, String nomCarreau, Carreau prison) {
	super(monopoly, numero, nomCarreau);
        this.prison = prison;
    }

    @Override
    public void action(Joueur j) {
        Interface.carreau_action(this);
        j.setPositionCourante(prison);
        j.setInPrison(true);
        System.out.println("Le joueur " + j.getNomJoueur() + " est allé en prison (ce batar)");
    }
    
}