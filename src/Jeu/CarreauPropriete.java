package Jeu;

import Data.CouleurPropriete;
import Ui.Texte;
import java.util.ArrayList;

public abstract class CarreauPropriete extends Carreau {
    private int loyerBase;
    private CouleurPropriete couleur;
    private Joueur proprietaire;
    protected ArrayList<Integer> loyerMaison;
    private int prixAchat;
    
    public CarreauPropriete(Monopoly monopoly, int numero, String nomCarreau, int prixAchat, ArrayList<Integer> loyerMaison) {
	super(monopoly, numero, nomCarreau);
	this.loyerMaison = loyerMaison;
	this.prixAchat = prixAchat;
	this.setLoyerBase(loyerBase);
    }

    public String getNomGroupe() {
	throw new UnsupportedOperationException();
    }

    public void devientProprietaire(Joueur newProprio) {
	this.proprietaire = newProprio;
    }
    
    public void setProprio(Joueur j) {
	this.proprietaire = j;
    }
    
    public boolean isLibre() {
	return proprietaire == null;
    }

    public int getPrix() {
	return prixAchat;
    }

    public void setLoyerBase(int loyerBase) {
	this.loyerBase = loyerBase;
    }
    
    @Override
    public void action(Joueur j) {
        Texte.carreau_action(this);
    }
    
    public Joueur getProprio(){
        return this.proprietaire;
    }
}