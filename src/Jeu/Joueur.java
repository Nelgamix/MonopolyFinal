package Jeu;

import Data.CouleurPropriete;
import Ui.Texte;
import java.util.ArrayList;

public final class Joueur {
    private String nomJoueur;
    private int cash;
    private Monopoly monopoly;
    private ArrayList<Compagnie> compagnies = new ArrayList<>();
    private ArrayList<Gare> gares = new ArrayList<>();
    private Carreau positionCourante;
    private ArrayList<ProprieteAConstruire> proprietesAConstruire = new ArrayList<>();
    private ArrayList<Integer> des = new ArrayList<>();
    private int cartesSortiePrison = 0;
    private boolean inPrison = false;
    private int lancePrison = 0;
    

    public Joueur(String pseudo) {
	this.setNomJoueur(pseudo);
        this.setCash(1500);
    }
    
    public void ajouterCarteSortiePrison() {
	System.out.println("Le joueur " + this.getNomJoueur() + " gagne une carte de sortie de prison!");
	this.cartesSortiePrison++;
    }
    
    public void retirerCarteSortiePrison() {
	System.out.println("Le joueur " + this.getNomJoueur() + " perd une carte de sortie de prison!");
	this.cartesSortiePrison--;
    }

    public int getLancePrison() {
        return lancePrison;
    }

    public void setLancePrison(int lancePrison) {
        this.lancePrison = lancePrison;
    }
    
    public void sortiePrison() {
        this.inPrison = false;
        this.lancePrison = 0;
    }

    public int getCash() {
	return this.cash;
    }
    
    public void setNomJoueur(String nomJoueur) {
	this.nomJoueur = nomJoueur;
    }
    
    public String getNomJoueur(){
	return(nomJoueur);
    }

    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
    }
    
    public boolean isInPrison() {
        return this.inPrison;
    }

    public void addDe(Integer de) {
	if (this.getDes().size() < 3) {
	    this.getDes().add(de);
	} else {
	    // message d'erreur (déjà 2 dés dans l'arraylist)
	}
    }
    
    public boolean isDouble() {
	if (this.getDes().size() == 2) {
	    return this.getDes().get(0).equals(this.getDes().get(1));
	} else {
	    return false;
	}
    }
    
    public ArrayList<Integer> getDes() {
	return des;
    }
    
    public int getPrison() {
        return this.cartesSortiePrison;
    }
    
    public boolean hasPrison() {
        return getPrison() != 0;
    }
    
    public boolean clearDes() {
	this.getDes().clear();
	if (this.getDes().isEmpty()) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public Integer getDesTotal() {
	Integer total = null;
	
	if (!this.getDes().isEmpty()) {
	    total = this.getDes().get(0);
	    if (this.getDes().get(1) != null) {
		total += this.getDes().get(1);
	    }
	}
	
	return total;
    }
    
    public void setPositionCourante(Carreau positionCourante) {
	this.positionCourante = positionCourante;
    }
    
    public Carreau getPositionCourante() {
	return positionCourante;
    }
    
    public void actionner() {
        this.positionCourante.action(this);
    }

    private void setCash(int cash) {
        this.cash = cash;
    }
    
    public void retirerSousous(int argent) {
	if (argent != 0) {
	    if (this.getCash() - argent >= 0) {
		this.setCash(this.getCash() - argent);
		Texte.joueur_perdreArgent(this.getNomJoueur(), Math.abs(argent));
	    } else {
		Texte.joueur_noMoney();
	    }

	    afficherArgent();
	}
    }
    
    public void setCartesPrison(int nbCarte) {
        this.cartesSortiePrison = nbCarte;
    }
    
    public boolean haveArgent() {
        return this.getCash() > 0;
    }
    
    public void ajouterSousous(int argent) {
	if (argent > 0) {
	    Texte.joueur_gagnerArgent(this.getNomJoueur(), argent);
	    this.setCash(this.getCash() + argent);
	    afficherArgent();
	} else {
	    this.retirerSousous(Math.abs(argent));
	}
    }
    
    public int getNbHotels() {
        int total = 0;
        
        for (ProprieteAConstruire p : proprietesAConstruire) {
            total += p.getNbHotels();
        }
        
        return total;
    }
    
    public int getNbMaison() {
        int total = 0;
        
        for (ProprieteAConstruire p : proprietesAConstruire) {
            total += p.getNbMaisons();
        }
        
        return total;
    }
    
    public Carreau getMoins() {
        int nb = 3;
        return monopoly.getCarreaux().getCarreauAt(this.getPositionCourante().getNumero() - 4);
    }
    
    public void afficherArgent() {
	Texte.joueur_afficherArgent(this.getNomJoueur(), this.getCash());
    }
    
    public boolean appartient(ProprieteAConstruire p) {
	for (ProprieteAConstruire pr : proprietesAConstruire) {
	    if (pr == p) {
		return true;
	    }
	}
	
        return false;
    }
    
    public int nombreHotelTotal(ProprieteAConstruire p) {
    
        CouleurPropriete c = null;
        c = p.getCouleur();
        Integer nb = null;
        for(ProprieteAConstruire pe : proprietesAConstruire){ 
	    if (c == p.getCouleur()) {
		nb = p.getNbHotels();
	    }
        }
        return nb;   
    }
    
    public void addPropriete(ProprieteAConstruire p) {
	proprietesAConstruire.add(p);
    }
    
    public int getNbGare() {
	return gares.size();
    }
    
    public void addGare(Gare g) {
	if (!gares.contains(g)) {
	    gares.add(g);
	}
    }
    
    public int getNbCompagnies() {
	return compagnies.size();
    }
    
    public void addCompagnie(Compagnie c) {
	if (!compagnies.contains(c)) {
	    compagnies.add(c);
	}
    }
    
    public ArrayList<ProprieteAConstruire> getPropriete(){
        return proprietesAConstruire;
    }
}