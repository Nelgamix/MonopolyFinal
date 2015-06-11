/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jeu;

import Data.Famille;

/**
 *
 * @author huchetn
 */
public class CarteAnniv extends Carte {

    public CarteAnniv(Famille attribut, String description, Monopoly monop) {
        super(attribut, description, monop);
    }

    @Override
    public void action(Joueur j) {
        for (Joueur jo : monopoly.getJoueurs().getJoueurs()) {
            jo.retirerSousous(10);
        }
	
        j.ajouterSousous(10 * monopoly.getJoueurs().getSize());
	
        System.out.println("Action anniv effectuée");
    }
    
    
    
}
