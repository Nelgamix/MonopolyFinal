package Jeu;

import Ui.Texte;

public abstract class CarreauAction extends Carreau {
    public CarreauAction(Monopoly monopoly, int numero, String nomCarreau) {
	super(monopoly, numero, nomCarreau);
    }
    
    @Override
    public void action(Joueur j) {
        Texte.carreau_action(this);
    }
    
}