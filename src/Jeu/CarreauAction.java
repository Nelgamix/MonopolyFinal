package Jeu;

import Ui.Interface;

public abstract class CarreauAction extends Carreau {
    public CarreauAction(Monopoly monopoly, int numero, String nomCarreau) {
	super(monopoly, numero, nomCarreau);
    }
    
    @Override
    public void action(Joueur j) {
        Interface.carreau_action(this);
    }
    
}