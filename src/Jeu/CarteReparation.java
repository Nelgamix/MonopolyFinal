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
public class CarteReparation extends Carte {
    private int prixHotel;
    private int prixMaison;

    public CarteReparation(Famille attribut, String description, int prixHotel, int prixMaison, Monopoly monop) {
        super(attribut, description, monop);
        this.prixHotel = prixHotel;
        this.prixMaison = prixMaison;
    }

    @Override
    public void action(Joueur j) {
        j.retirerSousous(j.getNbHotels() * prixHotel + j.getNbMaison() * prixMaison);
        System.out.println("Action reparation effectuée");
    }
    
}
