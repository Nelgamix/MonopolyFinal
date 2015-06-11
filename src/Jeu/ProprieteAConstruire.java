package Jeu;

import Data.CouleurPropriete;
import Ui.Texte;
import java.util.ArrayList;

public class ProprieteAConstruire extends CarreauPropriete {
    private int nbMaisons = 0;
    private int nbHotels = 0;
    private CouleurPropriete couleur;
    private Groupe groupePropriete;
    protected ArrayList<Integer> prixAchat;

    public ProprieteAConstruire(Monopoly monopoly, int numero, String nomCarreau, String couleur, int prixAchat, ArrayList<Integer> loyerMaison, ArrayList<Integer> prixAchatA){
	super(monopoly, numero, nomCarreau, prixAchat, loyerMaison);
	this.prixAchat = prixAchatA;
	this.setCouleur(couleur);
    }

    @Override
    public void action(Joueur j) {
	Texte.carreau_action(this);
	String reponse;
	
	if (!isLibre()) {
	    if (getProprio() == j) {
		// Si il est propriétaire de la propriété courante
		if (possedeGroupe(j)) {
		    // Si il possède le groupe entier
		    if (peutConstruireMaison(j)) {
			// Si on peut construire une maison
			if (j.getCash() >= getLoyerMaison()) {
			    // Si le joueur a assez d'argent
			    Texte.joueur_afficherArgent(j.getNomJoueur(), j.getCash());
			    reponse = Texte.io("Voulez-vous construire une maison sur ces propriétés? (" + getPrixConstruction(1) + "€) (O/N)");
			    if (reponse.equals("O") || reponse.equals("o")) {
				construireMaison(j);
				Texte.debug_Value("Le joueur " + j.getNomJoueur() + " a bien ajouté une maison case " + this.getNomCarreau());
			    } else {

			    }
			}
		    } else if (peutConstruireHotel(j)) {
			// Sinon, si on peut construire un hotel
			if (j.getCash() >= getLoyerHotel()) {
			    Texte.joueur_afficherArgent(j.getNomJoueur(), j.getCash());
			    reponse = Texte.io("Voulez-vous construire un hotel sur cette propriété? (" + getPrixConstruction(2) + "€) (O/N)");
			    if (reponse.equals("O") || reponse.equals("o")) {
				construireHotel(j);
				Texte.debug_Value("Le joueur " + j.getNomJoueur() + " a bien acheté un hotel case " + this.getNomCarreau());
			    } else {

			    }
			}
		    } else {
			// On peut plus rien faire
		    }
		} else {
		    // Sinon, on fait rien
		}
	    } else {
		if (j.getCash() >= getLoyerMaison()) {
		    Texte.debug_Value("Le joueur " + j.getNomJoueur() + " a payé le loyer au joueur " + getProprio().getNomJoueur());
		    j.retirerSousous(getLoyerMaison());
		    getProprio().ajouterSousous(getLoyerMaison());
		} else {
		    Texte.debug_Value("Le joueur " + j.getNomJoueur() + " n'a pas assez d'argent pour payer le loyer...");
		}
	    }
	} else {
	    if (j.getCash() >= getPrix()) {
		// Si le cash du joueur est supérieur ou égal au prix de la propriete
		// Demande a acheter la propriete
		Texte.joueur_afficherArgent(j.getNomJoueur(), j.getCash());
		reponse = Texte.io("Voulez-vous acheter cette propriété? (" + getPrix() + "€) (O/N)");
		if (reponse.equals("O") || reponse.equals("o")) {
		    j.retirerSousous(getPrix());
		    j.addPropriete(this);
		    this.setProprio(j);
		    Texte.debug_Value("Le joueur " + j.getNomJoueur() + " a bien acheté la propriété " + this.getNomCarreau());
		} else {

		}
	    } else {
		// On fait rien
	    }
	}
    }
    
    public void setCouleur(String couleur) {
	this.couleur = Enum.valueOf(CouleurPropriete.class, couleur);
    }
    
    public CouleurPropriete getCouleur(){
        return couleur;
    }
    
    public void setGroupe(Groupe groupePropriete) {
        this.groupePropriete=groupePropriete;
    }
    
    public int getPrixConstruction(int type) {
	switch (type) {
	    case 1:
		return prixAchat.get(0);
	    case 2:
		return prixAchat.get(1);
	    default:
		return 0;
	}
    }
    
    public int getNbHotels() {
        return nbHotels;
    }

    public int getNbMaisons() {
        return nbMaisons;
    }
    
    public void setHotel() {
        this.nbMaisons = 0;
        this.nbHotels = 1;
    }
    
    public int getLoyerMaison(){
	int loyer = loyerMaison.get(0);
	
        if(nbMaisons == 1){
            loyer = loyerMaison.get(1);
        }
        
        if(nbMaisons == 2){
            loyer = loyerMaison.get(2);
        }
        
        if(nbMaisons == 3){
            loyer = loyerMaison.get(3);
        }
        
        if(nbMaisons == 4){
            loyer = loyerMaison.get(4);
        }
        
        return loyer;
    }
    
    public int getLoyerHotel() {
        return loyerMaison.get(5);
    }
    
    public boolean peutConstruireMaison(Joueur j) {
        CouleurPropriete c = this.getCouleur();
        Integer nb = 0;
        
        if (possedeGroupe(j) && minMaison().getNbMaisons() < 4 && j.getCash() >= this.getLoyerMaison() && nb < 3) {
            return true;
        }
	
        return false;
    }
    
    public boolean peutConstruireHotel(Joueur j) {
        CouleurPropriete c = getCouleur();
        
        if (possedeGroupe(j) && this.minMaison().getNbMaisons() == 4 && j.getCash() >= getLoyerHotel()) {
            return true;
        }
	
        return false;
    }
    
    public boolean possedeGroupe(Joueur j) {
        CouleurPropriete c = getCouleur();
	
	// proprietesAConstruire = Propriétes du joueur? donc elles sont forcement à lui, il faudrait aller chercher celles du jeu
        for(ProprieteAConstruire pe : getMonopoly().getCarreaux().getProprietesAConstruire()){
	    if (c == pe.getCouleur() && pe.getProprio() != j) {
		return false;
	    }
        }
	
        return true;
    }
    
    public void construireMaison(Joueur j) {
	ProprieteAConstruire p = minMaison();
	
	if (p == this) {
	    j.retirerSousous(getPrixConstruction(1));
	    nbMaisons++;
	}
    }
    
    public void construireHotel(Joueur j) {
	ProprieteAConstruire prop = minMaison();
	
	if (prop == this) {
	    j.retirerSousous(getPrixConstruction(2));
	    prop.setHotel();
	}
    }
    
    /**
     * Retourne la propriété avec le minimum de maison dans la couleur 
     * de la propriété courante
     * @return proriete avec le moins de maisons
     */
    public ProprieteAConstruire minMaison() {
        int min = 4;   
        ProprieteAConstruire prop = null;
	
        for(ProprieteAConstruire p : getMonopoly().getCarreaux().getProprietesAConstruire()){ 
	    if (couleur == p.getCouleur()) {
		if(p.getNbMaisons() <= min && p.getNbHotels()==0){
		    min = p.getNbMaisons();
		    prop = p;
		}
	    }
        }
	
        return prop;
    }
}