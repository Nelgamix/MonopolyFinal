package Jeu;

import Ui.Texte;
import java.util.ArrayList;

public class Gare extends CarreauPropriete {

    public Gare(Monopoly monopoly, int numero, String nomCarreau, int prixAchat, ArrayList<Integer> loyerMaison) {
	super(monopoly, numero, nomCarreau, prixAchat, loyerMaison);
    }

    @Override
    public void action(Joueur j) {
	Texte.carreau_action(this);
	
	String reponse;
	
	if (!isLibre()) {
	    if (getProprio() != j) {
		Texte.gare_payerLoyer(this, j);
	    }
	} else {
	    // Achete?
	    Texte.gare_acheter(this, j);
	}
    }
    
    public int getPrixLoyer(Joueur j) {
	switch (j.getNbGare()) {
	    case 1:
		return 25;
	    case 2:
		return 50;
	    case 3:
		return 75;
	    case 4:
		return 100;
	    default:
		return 0;
	}
    }
}