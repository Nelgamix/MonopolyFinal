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
public class CarteMouvementAbsolu extends Carte {
    private Carreau c;
    private boolean prison;

    public CarteMouvementAbsolu(boolean prison, Famille attribut, String description,Carreau c, Monopoly monop) {
        super(attribut, description, monop);
        this.c = c;
        this.prison = prison;
    }

    @Override
    public void action(Joueur j) {
        if (!prison && c.getNumero() < j.getPositionCourante().getNumero()) {
            j.ajouterSousous(200);
        }
        j.setPositionCourante(c);
        if (c.getNomCarreau().equals("Simple Visite / En Prison")) {
            j.setInPrison(true);
        }
        System.out.println("Action mouvement absolu effectuée");
	j.actionner();
    }
}
