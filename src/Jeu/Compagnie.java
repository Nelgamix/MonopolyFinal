package Jeu;

import Ui.Texte;
import java.util.ArrayList;

public class Compagnie extends CarreauPropriete {
    
    public Compagnie(Monopoly monopoly, int numero, String nomCarreau, int prixAchat, ArrayList<Integer> loyerMaison) {
	super(monopoly, numero, nomCarreau, prixAchat, loyerMaison);
    }   

    @Override
    public void action(Joueur j) {
	Texte.carreau_action(this);
	
	String reponse;
	
	if (!isLibre()) {
	    if (getProprio() != j) {
		Texte.debug_Value("Le joueur " + j.getNomJoueur() + " tombe sur une compagnie appartenant a " + getProprio().getNomJoueur() + "."
			+ "Ce joueur a tiré " + j.getDesTotal() + ", et le propriétaire de la compagnie possède " + getProprio().getNbCompagnies()
			+ " compagnies. Il paye donc " + getPrixLoyer(getProprio(), j.getDesTotal()));
		if (j.getCash() >= getPrixLoyer(getProprio(), j.getDesTotal())) {
		    j.retirerSousous(getPrixLoyer(getProprio(), j.getDesTotal()));
		    getProprio().ajouterSousous(getPrixLoyer(getProprio(), j.getDesTotal()));
		} else {
		    Texte.debug_Value("... cependant, il n'a pas assez d'argent");
		}
	    }
	} else {
	    // Achete?
	    Texte.joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	    reponse = Texte.io("Voulez-vous acheter cette compagnie? (" + getPrix() + "€) (O/N)");
	    if (reponse.equals("O") || reponse.equals("o")) {
		j.retirerSousous(getPrix());
		this.setProprio(j);
		j.addCompagnie(this);
		Texte.debug_Value("Le joueur " + j.getNomJoueur() + " a bien acheté la compagnie " + this.getNomCarreau());
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