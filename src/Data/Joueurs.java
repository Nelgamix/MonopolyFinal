/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Jeu.Joueur;
import Jeu.Monopoly;
import Ui.Texte;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class Joueurs {
    private Monopoly monopoly;
    private Carreaux carreaux;
    private ArrayList<Joueur> joueurs = new ArrayList<>();

    public Joueurs(Monopoly monopoly) {
	this.monopoly = monopoly;
	this.carreaux = monopoly.getCarreaux();
    }
    
    public void inscriptionJoueurs() {
	joueurs = Texte.jeu_inscrireJoueurs();
	
	for (Joueur j : joueurs) {
            j.setPositionCourante(carreaux.getCarreauAt(0));
	}
    }
    
    public boolean estVide() {
	return this.joueurs.isEmpty();
    }
    
    public ArrayList<Joueur> getJoueurs() {
	return joueurs;
    }
    
    public Joueur getJoueurAt(int index) {
	return joueurs.get(index);
    }
    
    public int getIndexOf(Joueur j) {
	return joueurs.indexOf(j);
    }
    
    public Joueur getJoueurName(String nom) {
        Joueur toReturn = null;
        
        for (Joueur j : joueurs) {
            if (nom.equals(j.getNomJoueur())) {
                toReturn = j;
            }
        }
        
        return toReturn;
    }
    
    public int getSize() {
	return joueurs.size();
    }
    
    public void vider() {
	this.joueurs.clear();
    }
    
    public void ajouterJoueur(Joueur j) {
	this.joueurs.add(j);
    }
    
    
}
