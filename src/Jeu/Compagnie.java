package Jeu;

import Ui.Interface;
import java.util.ArrayList;

public class Compagnie extends CarreauPropriete {
    
    public Compagnie(Monopoly monopoly, int numero, String nomCarreau, int prixAchat, ArrayList<Integer> loyerMaison) {
	super(monopoly, numero, nomCarreau, prixAchat, loyerMaison);
    }   

    @Override
    public void action(Joueur j) {
	Interface.carreau_action(this);
	
	String reponse;
	
	if (!isLibre()) {
	    if (getProprio() != j) {
		Interface.debug_Value("Le joueur " + j.getNomJoueur() + " tombe sur une compagnie appartenant a " + getProprio().getNomJoueur() + "."
			+ "Ce joueur a tiré " + j.getDesTotal() + ", et le propriétaire de la compagnie possède " + getProprio().getNbCompagnies()
			+ " compagnies. Il paye donc " + getPrixLoyer(getProprio(), j.getDesTotal()));
		if (j.getCash() >= getPrixLoyer(getProprio(), j.getDesTotal())) {
		    j.retirerSousous(getPrixLoyer(getProprio(), j.getDesTotal()));
		    getProprio().ajouterSousous(getPrixLoyer(getProprio(), j.getDesTotal()));
		} else {
		    Interface.debug_Value("... cependant, il n'a pas assez d'argent");
		}
	    }
	} else {
	    // Achete?
	    Interface.joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	    reponse = Interface.io("Voulez-vous acheter cette compagnie? (" + getPrix() + "€) (O/N)");
	    if (reponse.equals("O") || reponse.equals("o")) {
		j.retirerSousous(getPrix());
		this.setProprio(j);
		j.addCompagnie(this);
		Interface.debug_Value("Le joueur " + j.getNomJoueur() + " a bien acheté la compagnie " + this.getNomCarreau());
	    } else {

	    }
	}
    }
    
    public int getPrixLoyer(Joueur j, int lance) {
	switch (j.getNbCompagnies()) {
	    case 1:
		return 4 * lance;
	    case 2:
		return 10 * lance;
	    default:
		return 0;
	}
    }
    
}