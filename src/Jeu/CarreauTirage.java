package Jeu;

import Data.Famille;
import Ui.Texte;
import java.util.ArrayList;
import java.util.Random;

public class CarreauTirage extends CarreauAction {
    private ArrayList<Carte> cartes = new ArrayList<>();
    private Famille famille;
    
    public CarreauTirage(Monopoly monopoly, int numero, String nomCarreau) {
	super(monopoly, numero, nomCarreau);
        
        if (nomCarreau.toLowerCase().equals("chance")) {
            this.famille = Famille.chance;
        } else {
            this.famille = Famille.communaute;
        }
    }
    
    public void initializeCartable(ArrayList<Carte> cartable) {
        for (Carte c : cartable) {
            if (c.getAttribut() == this.famille) {
                cartes.add(c);
            }
        }
    }
    
    @Override
    public void action(Joueur j) {
        Texte.carreau_action(this);
        Random rand = new Random();
        Carte carteHasard = cartes.get(rand.nextInt(cartes.size()));
        System.out.println("Vous tirez au sort et obtenez la carte n°" + (cartes.indexOf(carteHasard) + 1) + ". (" + carteHasard.getDescription() + ")");
        carteHasard.action(j);
    }
    
}