/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jeu;

import Data.*;

/**
 *
 * @author huchetn
 */
public abstract class Carte {
    private Famille attribut;
    private String description;
    protected Monopoly monopoly;

    public Carte(Famille attribut, String description, Monopoly monop) {
        this.attribut = attribut;
        this.description = description;
        this.monopoly = monop;
    }
    
    public abstract void action(Joueur j);

    public Famille getAttribut() {
        return attribut;
    }
    
    public String getDescription() {
        return description;
    }
    
}
