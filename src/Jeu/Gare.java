package Jeu;

import Ui.Interface;
import java.util.ArrayList;

public class Gare extends CarreauPropriete {

    public Gare(Monopoly monopoly, int numero, String nomCarreau, int prixAchat, ArrayList<Integer> loyerMaison) {
	super(monopoly, numero, nomCarreau, prixAchat, loyerMaison);
    }

    @Override
    public void action(Joueur j) {
	Interface.carreau_action(this);
	
	String reponse;
	
	if (!isLibre()) {
	    if (getProprio() != j) {
		Interface.debug_Value("Le joueur " + j.getNomJoueur() + " tombe sur une gare de " + getProprio().getNomJoueur() + " qui possede " + getProprio().getNbGare()
			+ " gares; il lui paye donc " + getPrixLoyer(getProprio()) + "€");
		if (j.getCash() >= getPrixLoyer(getProprio())) {
		    j.retirerSousous(getPrixLoyer(getProprio()));
		    getProprio().ajouterSousous(getPrixLoyer(getProprio()));
		} else {
		    Interface.debug_Value("... cependant, il n'a pas assez d'argent");
		}
	    }
	} else {
	    // Achete?
	    Interface.joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	    reponse = Interface.io("Voulez-vous acheter cette gare? (" + getPrix() + "€) (O/N)");
	    if (reponse.equals("O") || reponse.equals("o")) {
		j.retirerSousous(getPrix());
		this.setProprio(j);
		j.addGare(this);
		Interface.debug_Value("Le joueur " + j.getNomJoueur() + " a bien acheté la gare " + this.getNomCarreau());
	    } else {

	    }
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