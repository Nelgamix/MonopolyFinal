package Jeu;
import Data.CouleurPropriete;
import java.util.ArrayList;

public class Groupe {
	private int prixAchatMaison;
	private int prixAchatHotel;
	private CouleurPropriete couleur;
	public ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();

        public Groupe() {
            
        }
	
        public void infoBatimentsGroupe(){
            for( ProprieteAConstruire c : proprietes ){
                System.out.println("Nombre de maison :"+c.getNomCarreau()+":"+c.getNbMaisons());
                System.out.println("Nombre d'Hotel :"+c.getNomCarreau()+":"+c.getNbHotels());
            }
        }
        /*public void peutOnConstruireUneMaison(){
            
            int nbrmaison=0;
            int min =4;
            int d;
            
        }
        */
}