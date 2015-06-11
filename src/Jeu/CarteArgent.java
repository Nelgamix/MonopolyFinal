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
public class CarteArgent extends Carte {
    private int argent;
    
    public CarteArgent(Famille attribut, String description, int argent, Monopoly monop) {
        super(attribut, description, monop);
        this.argent = argent;
    }

    @Override
    public void action(Joueur j) {
       j.ajouterSousous(argent);
       System.out.println("Action argent effectuée");
    }
    
}
