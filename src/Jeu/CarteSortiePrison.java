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
public class CarteSortiePrison extends Carte {

    public CarteSortiePrison(Famille attribut, String desc, Monopoly monop) {
        super(attribut, desc, monop);
    }

    @Override
    public void action(Joueur j) {
        j.ajouterCarteSortiePrison();
        System.out.println("Action sortie prison effectuée");
    }

}
