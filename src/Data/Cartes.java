/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Jeu.Carreau;
import Jeu.CarreauTirage;
import Jeu.Carte;
import Jeu.CarteAnniv;
import Jeu.CarteArgent;
import Jeu.CarteMouvementAbsolu;
import Jeu.CarteMouvementRelatif;
import Jeu.CarteReparation;
import Jeu.CarteSortiePrison;
import Jeu.Monopoly;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class Cartes {
    private Monopoly monopoly;
    private Carreaux carreaux;
    private ArrayList<Carte> cartes = new ArrayList<>();

    public Cartes(Monopoly monopoly) {
	this.monopoly = monopoly;
    }
    
    public void initializeCartes() {
	this.carreaux = monopoly.getCarreaux();
	
	Carte c;
	c = new CarteSortiePrison(Famille.chance, "Carte sortie de prison", monopoly);
        cartes.add(c);
        c = new CarteSortiePrison(Famille.communaute, "Carte sortie de prison", monopoly);
        cartes.add(c);
        c = new CarteMouvementRelatif(Famille.chance, "Recule de 3 cases", monopoly);
        cartes.add(c);
        c = new CarteReparation(Famille.chance, "Taxe de réparation : payez 40€ maison et 115€ par hôtel", -115, -40, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.chance, "Amende : tu roules trop vite paye 15€ !", -15, monopoly);
        cartes.add(c);
        c = new CarteReparation(Famille.chance, "Taxe de réparation : payez 25€ maison et 100€ par hôtel", -100, -25, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.chance, "Amende : tu es bourré paye 20€", -20, monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(false,Famille.chance, "Allez jusqu'a la case départ", carreaux.getCarreauAt(0), monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(true,Famille.chance, "Va en prison et tu prends pas les 200€ si tu passes par la case départ", carreaux.getCase("prison"), monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(false,Famille.chance, "Allez jusqu'a l'Avenue Henri-Martin et prenez les 200€ si vous passez par la case départ", carreaux.getCase("Henri-martin"), monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(false,Famille.chance, "Allez jusqu'a la gare de Lyon et prenez les 200€ si vous passez par la case départ", carreaux.getCase("Gare de Lyon"), monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.chance, "Frais de scolarité payez 150€", -150, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.chance, "Vous avez gagné le grand prix de mots croisés. Recevez 100€",100, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.chance, "La banque vous verse 50€",50, monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(true, Famille.chance, "Rendez vous a la Rue de la Paix", carreaux.getCase("Rue de la paix"), monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.chance, "Votre immeuble et votre prêt vous rapporte. Recevez 150€ !",150, monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(false,Famille.chance, "Allez jusqu'au Boulevard de la Villette et prenez les 200€ si vous passez par la case départ", carreaux.getCase("villette"), monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "Amende tu fermes ta gueule paye 10€ !", -10, monopoly);
        cartes.add(c);
        c = new CarteAnniv(Famille.communaute, "C'est ton anniversaire, tout le monde te donne de l'argent", monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "La banque vous verse 200€", 200, monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(true,Famille.communaute, "Retournez à belleville", carreaux.getCase("belleville"), monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "Paye medecin. 50€ non remboursé", -50, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "On te rembourse 20€", 20, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "Paye hopital, 100€ non remboursé", -100, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "Vous héritez de 100€", 100, monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(true,Famille.communaute, "Va en prison et tu prends pas les 200€ si tu passes par la case départ", carreaux.getCase("prison"), monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "Paye la police d'assurance, 50€", -50, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "Une vente vous rapporte 50€", 50, monopoly);
        cartes.add(c);
        c = new CarteMouvementAbsolu(false,Famille.communaute, "Allez jusqu'a la case départ", carreaux.getCarreaux().get(0), monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "25€", 25, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "Reçois les alloc 100€", 100, monopoly);
        cartes.add(c);
        c = new CarteArgent(Famille.communaute, "10€", 10, monopoly);
        cartes.add(c);
	
	for (Carreau ca : carreaux.getCarreaux()) {
            if (ca instanceof CarreauTirage) {
                CarreauTirage car = (CarreauTirage) ca;
                car.initializeCartable(cartes);
            }
        }
    }
    
    public ArrayList<Carte> getCartes() {
	return cartes;
    }
    
    public void removeCarte(Carte c) {
	cartes.remove(c);
    }
    
}
