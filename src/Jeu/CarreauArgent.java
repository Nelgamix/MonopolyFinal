package Jeu;

import Ui.Texte;

public class CarreauArgent extends CarreauAction {
    private int montant;

    public CarreauArgent(Monopoly monopoly, int numero, String nomCarreau, int montant) {
	super(monopoly, numero, nomCarreau);
	this.montant = montant;
    }

    @Override
    public void action(Joueur j) {
        Texte.carreau_action(this);
        j.ajouterSousous(montant);
    }
    
}