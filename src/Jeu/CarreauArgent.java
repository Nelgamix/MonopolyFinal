package Jeu;

import Ui.Interface;

public class CarreauArgent extends CarreauAction {
    private int montant;

    public CarreauArgent(Monopoly monopoly, int numero, String nomCarreau, int montant) {
	super(monopoly, numero, nomCarreau);
	this.montant = montant;
    }

    @Override
    public void action(Joueur j) {
        Interface.carreau_action(this);
        j.ajouterSousous(montant);
    }
    
}