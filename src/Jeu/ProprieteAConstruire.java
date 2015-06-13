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
        ProprieteAConstruire p = null;
	
	if (!isLibre()) {
	    if (getProprio() == j) {
		// Si il est propriétaire de la propriété courante
		if (possedeGroupe(j)) {
		    // Si il possède le groupe entier
		    if (peutConstruireMaison(j)) {
			// Si on peut construire une maison
			if (j.getCash() >= getPrixConstruction(1) && getMonopoly().getNbMaisons() > 0) {
			    // Si le joueur a assez d'argent
			    p=Texte.joueurChoix(j, this);
                            Texte.propriete_construireMaison(p, j);
			}
		    } else if (peutConstruireHotel(j)) {
			// Sinon, si on peut construire un hotel
			if (j.getCash() >= getPrixConstruction(2) && getMonopoly().getNbHotels() > 0) {
			    Texte.propriete_construireHotel(this, j);
			}
		    } else {
			// On peut plus rien faire pour lui
		    }
		} else {
		    // Sinon, on fait rien
		}
	    } else {
		Texte.propriete_payerLoyer(this, j);
	    }
	} else {
	    if (j.getCash() >= getPrix()) {
		// Si le cash du joueur est supérieur ou égal au prix de la propriete
		// Demande a acheter la propriete
		Texte.propriete_acheter(this, j);
	    } else {
		// On fait rien
	    }
	}
    }
    
    public void assignAllProprietesAt(Joueur j) {
	for (ProprieteAConstruire c : getMonopoly().getCarreaux().getProprietesAConstruire()) {
	    if (c.getCouleur() == this.getCouleur()) {
		c.setProprio(j);
		j.addPropriete(c);
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
        Integer nb = 0;
        
        if (possedeGroupe(j) && minMaison().getNbMaisons() < 4 && j.getCash() >= this.getLoyerMaison() && nb < 3) {
            return true;
        }
	
        return false;
    }
    
    public boolean peutConstruireHotel(Joueur j) {
        if (possedeGroupe(j) && this.minMaison().getNbMaisons() == 4 && j.getCash() >= getLoyerHotel()) {
            return true;
        }
	
        return false;
    }
    
    public boolean possedeGroupe(Joueur j) {
        CouleurPropriete c = getCouleur();
	
        for(ProprieteAConstruire pe : getMonopoly().getCarreaux().getProprietesAConstruire()) {
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
            getMonopoly().setNbMaisons(getMonopoly().getNbMaisons() - 1);
	    nbMaisons++;
	}
    }
    
    public void setMaxMaison() {
	getMonopoly().setNbMaisons(getMonopoly().getNbMaisons() - 4);
	nbMaisons = 4;
    }
    
    public void setGroupeMaxMaison() {
	for (ProprieteAConstruire c : getMonopoly().getCarreaux().getProprietesAConstruire()) {
	    if (c.getCouleur() == this.getCouleur()) {
		c.setMaxMaison();
	    }
	}
    }
    
    public void construireHotel(Joueur j) {
	ProprieteAConstruire prop = minMaison();
	
	if (prop == this) {
	    j.retirerSousous(getPrixConstruction(2));
            getMonopoly().setNbHotels(getMonopoly().getNbHotels() - 1);
            getMonopoly().setNbMaisons(getMonopoly().getNbMaisons() + 4);
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
		if(p.getNbMaisons() <= min && p.getNbHotels() == 0){
		    min = p.getNbMaisons();
		    prop = p;
		}
	    }
        }
	
        return prop;
    }
}