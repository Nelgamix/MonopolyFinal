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
		Texte.debug_Value("Le joueur " + j.getNomJoueur() + " tombe sur une gare de " + getProprio().getNomJoueur() + " qui possede " + getProprio().getNbGare()
			+ " gares; il lui paye donc " + getPrixLoyer(getProprio()) + "€");
		if (j.getCash() >= getPrixLoyer(getProprio())) {
		    j.retirerSousous(getPrixLoyer(getProprio()));
		    getProprio().ajouterSousous(getPrixLoyer(getProprio()));
		} else {
		    Texte.debug_Value("... cependant, il n'a pas assez d'argent");
		}
	    }
	} else {
	    // Achete?
	    Texte.joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	    reponse = Texte.io("Voulez-vous acheter cette gare? (" + getPrix() + "€) (O/N)");
	    if (reponse.equals("O") || reponse.equals("o")) {
		j.retirerSousous(getPrix());
		this.setProprio(j);
		j.addGare(this);
		Texte.debug_Value("Le joueur " + j.getNomJoueur() + " a bien acheté la gare " + this.getNomCarreau());
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