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
public class CarteMouvementRelatif extends Carte {

    public CarteMouvementRelatif(Famille attribut, String description, Monopoly monop) {
        super(attribut, description, monop);
    }

    @Override
    public void action(Joueur j) {
        j.setPositionCourante(monopoly.getCarreaux().getCarreaux().get(j.getPositionCourante().getNumero() - 4));
        System.out.println("Action mouvement relatif effectuée");
	j.actionner();
    }

}
