package Ui;

import Data.CouleurPropriete;
import Jeu.Carreau;
import Jeu.CarreauPropriete;
import Jeu.Gare;
import Jeu.Joueur;
import Jeu.Monopoly;
import Jeu.ProprieteAConstruire;
import java.util.ArrayList;
import java.util.Scanner;

public class Texte {
    public Monopoly monopoly;
    private static Scanner sc = new Scanner(System.in);

    public void AfficherInfo(String nom, int prix, String nomGroupe) {
	throw new UnsupportedOperationException();
    }

    public static void printerr(String toPrint) {
	System.err.println("ERROR: " + toPrint);
    }
    
    public static void jeu_demanderJoueur(int nbInscrits) {
	System.out.println("Entrez un nom de joueur (ou 0 pour commencer la partie -- " + nbInscrits + "/6 inscrits):");
	System.out.print(" #\t");
    }
    
    public static void joueur_afficherTirage(String nomJoueur, int tirage) {
	System.out.println(" TIRAGE: Le joueur " + nomJoueur + " a tiré un " + tirage + "!");
    }
    
    public static void debug_Value(String message) {
	System.out.println("\nDEBUG ===== " + message);
    }
    
    public static void joueur_afficherAvancee(String nomJoueur, int nombreCases) {
	System.out.println("Le joueur " + nomJoueur + " avance de " + nombreCases + ".");
    }
    
    public static void joueur_afficherPosition(String nomJoueur, int position, String nomCase) {
	System.out.println(" POSITION: Le joueur " + nomJoueur + " est actuellement à la case " + position + " (nommée " + nomCase + ").");
    }
    
    public static void joueur_rejoue(String nomJoueur) {
	System.out.println("Oh! Le joueur " + nomJoueur + " vient de faire un double! Il rejoue!");
    }
    
    public static void jeu_troisDoublePrison(String nomJoueur) {
	System.out.println("Oh non! Le joueur " + nomJoueur + " vient d'attendre son troisème double! Il va donc en prison!");
    }
    
    public static void jeu_aurevoir() {
	System.out.println("Merci d'avoir joué! A bientôt!");
    }
    
    public static void carreau_action(Carreau cases) {
        System.out.println("Action de " + cases.getClass().getCanonicalName());
    }
    
    public static void joueur_noMoney() {
        pln("T'es ruiné frère");
    }
    
    public static void pln(String afficher) {
        System.out.println(afficher);
    }
    
    public static void joueur_perdreArgent(String nomJ, int argent) {
        pln("Le joueur " + nomJ + " vient de perdre " + Math.abs(argent) + "€!");
    }
    
    public static void joueur_gagnerArgent(String nomJ, int argent) {
        pln("Le joueur " + nomJ + " vient de gagner " + argent + "€!");
    }
    
    public static void propriete_demanderAchat() {
        pln("Voulez-vous acheter cette propriété?: ");
    }
    
    public static void propriete_afficher(CarreauPropriete p) {
        pln("La propriété " + p.getNomCarreau() + " coûte " + p.getPrix());
    }
    
    public static void joueur_utiliserCarteSortiePrison() {
        pln("Vous avez une carte de sortie prison. Voulez vous l'utiliser? (O/N)");
    }
    
    public static void joueur_sortiePrison() {
        pln("Vous sortez de prison");
    }
    
    public static void joueur_sortiePrisonPayer() {
        pln("Vous sortez de prison et payez 50€");
    }
    
    public static void joueur_resterPrison() {
        pln("Vous restez en prison");
    }
    
    public static void jeu_tourGlobal(int tour) {
        pln("\n\n\t **** TOUR " + tour);
    }
    
    public static void jeu_tourJoueur(String j) {
        pln("\nTOUR DU JOUEUR " + j + " ----------------");
    }
    
    public static void jeu_tirageDes(String j) {
        pln("\n ** TIRAGE des dés pour le joueur " + j);
    }
    
    public static ArrayList<Joueur> jeu_inscrireJoueurs() {
	Joueur j;
	String text;
	ArrayList<Joueur> joueurs = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            jeu_demanderJoueur(joueurs.size());
            text = sc.nextLine();

            if (text.equals("0")) {
                break;
            }

            // Création du joueur
            j = new Joueur(text);
	    
            joueurs.add(j);
        }
	
	return joueurs;
    }           
    
    public static String io(String question) {
	pln(question);
	
	return sc.nextLine();
    }
    
    public static String input() {
	return sc.nextLine();
    }
    
    public static void joueur_afficherArgent(String nomJoueur, int argent) {
	pln("Le joueur " + nomJoueur + " possède " + argent + "€");
    }
    
    public static boolean jeu_demanderType() {
	String rep = io("Voulez-vous la version graphique? (O / N)");
	if (rep.equalsIgnoreCase("o")) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public static void propriete_construireMaison(ProprieteAConstruire p, Joueur j) {
	joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	String reponse = Texte.io("Voulez-vous construire une maison sur ces propriétés? (" + p.getPrixConstruction(1) + "€) (O/N)");
	if (reponse.equals("O") || reponse.equals("o")) {
	    p.construireMaison(j);
	    j.retirerSousous(p.getPrixConstruction(1));
	    pln("Le joueur " + j.getNomJoueur() + " a bien ajouté une maison case " + p.getNomCarreau());
	} else {
	    pln("Vous avez choisi de ne pas construire de maison.");
	}
    }
    
    public static void propriete_construireHotel(ProprieteAConstruire p, Joueur j) {
	joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	String reponse = Texte.io("Voulez-vous construire un hotel sur cette propriété? (" + p.getPrixConstruction(2) + "€) (O/N)");
	if (reponse.equals("O") || reponse.equals("o")) {
	    p.construireHotel(j);
	    j.retirerSousous(p.getPrixConstruction(2));
	    pln("Le joueur " + j.getNomJoueur() + " a bien acheté un hotel case " + p.getNomCarreau());
	} else {
	    pln("Vous avez choisi de ne pas construire d'hotel.");
	}
    }
    
    public static void propriete_payerLoyer(ProprieteAConstruire p, Joueur j) {
	if (j.getCash() >= p.getLoyerMaison()) {
	    pln("Le joueur " + j.getNomJoueur() + " a payé le loyer au joueur " + p.getProprio().getNomJoueur());
	    j.retirerSousous(p.getLoyerMaison());
	    p.getProprio().ajouterSousous(p.getLoyerMaison());
	} else {
	    pln("Le joueur " + j.getNomJoueur() + " n'a pas assez d'argent pour payer le loyer...");
	}
    }
    
    public static void propriete_acheter(ProprieteAConstruire p, Joueur j) {
	joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	String reponse = Texte.io("Voulez-vous acheter cette propriété? (" + p.getPrix() + "€) (O/N)");
	if (reponse.equals("O") || reponse.equals("o")) {
	    j.retirerSousous(p.getPrix());
	    j.addPropriete(p);
	    p.setProprio(j);
	    pln("Le joueur " + j.getNomJoueur() + " a bien acheté la propriété " + p.getNomCarreau());
	} else {

	}
    }
    
    public static void gare_acheter(Gare g, Joueur j) {
	joueur_afficherArgent(j.getNomJoueur(), j.getCash());
	String reponse = Texte.io("Voulez-vous acheter cette gare? (" + g.getPrix() + "€) (O/N)");
	if (reponse.equals("O") || reponse.equals("o")) {
	    j.retirerSousous(g.getPrix());
	    g.setProprio(j);
	    j.addGare(g);
	    pln("Le joueur " + j.getNomJoueur() + " a bien acheté la gare " + g.getNomCarreau());
	} else {

	}
    }
    
    public static void gare_payerLoyer(Gare g, Joueur j) {
	pln("Le joueur " + j.getNomJoueur() + " tombe sur une gare de " + g.getProprio().getNomJoueur() + " qui possede " + g.getProprio().getNbGare()
			+ " gares; il lui paye donc " + g.getPrixLoyer(g.getProprio()) + "€");
	if (j.getCash() >= g.getPrixLoyer(g.getProprio())) {
	    j.retirerSousous(g.getPrixLoyer(g.getProprio()));
	    g.getProprio().ajouterSousous(g.getPrixLoyer(g.getProprio()));
	} else {
	    pln("... cependant, il n'a pas assez d'argent");
	}
    }
    
    public static void jeu_debug(Monopoly m) {
	pln("Liste de choix pour la démonstration:");
	pln("  1. Prochain joueur: place sur une case propriété à construire");
	pln("  2. Prochain joueur: place sur une case propriété à construire, et possède le groupe");
	pln("  3. Prochain joueur: place sur une case propriété à construire, et possède le groupe avec 4 maisons sur chacune");
	pln("  4. Prochain joueur: place sur une gare"); // Gare
	pln("  5. Prochain joueur: place sur une gare alors qu'un autre joueur en possède déjà 4"); // Place n'importe ou
	pln("  6. Prochain joueur: place sur une case chance"); // Case chance / commu
	pln("  7. Prochain joueur: place sur une case via son une partie de son nom");
	pln("  8. ");
	pln("  9. ");
	pln(" 10. ");
	
	String choixS = input();
	int choix = 0;
	try {
	    choix = Integer.parseInt(choixS);
	} catch (NumberFormatException n) {
	    pln("Choix incorrect.");
	}
	
	switch (choix) {
	    case 1:
		m.getJoueurActuel().setPositionCourante(m.getCarreaux().getProprietesAConstruire().get(0));
		break;
	    case 2:
		m.getJoueurActuel().setPositionCourante(m.getCarreaux().getProprietesAConstruire().get(2));
		m.getCarreaux().getProprietesAConstruire().get(2).assignAllProprietesAt(m.getJoueurActuel());
		break;
	    case 3:
		m.getJoueurActuel().setPositionCourante(m.getCarreaux().getProprietesAConstruire().get(3));
		m.getCarreaux().getProprietesAConstruire().get(3).assignAllProprietesAt(m.getJoueurActuel());
		m.getCarreaux().getProprietesAConstruire().get(3).setGroupeMaxMaison();
		break;
	    case 4:
		m.getJoueurActuel().setPositionCourante(m.getCarreaux().getGares().get(0));
		break;
	    case 5:
		if (m.getJoueurs().getJoueurAt(0) != m.getJoueurActuel()) {
		    m.getJoueurActuel().setPositionCourante(m.getCarreaux().getGares().get(0));
		    for (Gare g : m.getCarreaux().getGares()) {
			g.setProprio(m.getJoueurs().getJoueurAt(0));
			m.getJoueurs().getJoueurAt(0).addGare(g);
		    }
		} else {
		    m.getJoueurActuel().setPositionCourante(m.getCarreaux().getGares().get(0));
		    for (Gare g : m.getCarreaux().getGares()) {
			g.setProprio(m.getJoueurs().getJoueurAt(1));
			m.getJoueurs().getJoueurAt(1).addGare(g);
		    }
		}
		break;
	    case 6:
		m.getJoueurActuel().setPositionCourante(m.getCarreaux().getCase("Chance"));
		break;
	    case 7:
		m.getJoueurActuel().setPositionCourante(m.getCarreaux().getCase(Texte.io("Numéro de la case")));
		break;
	    default:
		pln("Pas de choix");
		break;
	}
	
    }
    
    public static ProprieteAConstruire joueurChoix(Joueur j, ProprieteAConstruire p) {
        CouleurPropriete c = null;
        ArrayList<ProprieteAConstruire> prop = null;
        c = p.getCouleur();
        int min = 4;
        ProprieteAConstruire propArr = null;
        ProprieteAConstruire propArr1 = null;
        ProprieteAConstruire propArr2 = null;
        ProprieteAConstruire Areturn = null;
        String text;
        int numero = 0;
        
        for(ProprieteAConstruire pe : j.getPropriete()){
            if(pe.getCouleur()==c){
                prop.add(pe);
            }    
        }
        // Inteface command
        for(ProprieteAConstruire pe : prop){
            if(min >pe.getNbMaisons()){
                min=pe.getNbMaisons();
                if(propArr != null || propArr.getNbMaisons()>min){
                    propArr =pe;
                }else if(propArr1!= null){
                    propArr1 =pe;
                }else{
                    propArr2 =pe;
                }
            }
        }
        
        if(propArr2!=null){
            pln("Vous avez le choix entre 3 propriétés, veuillez choisir un numéro :");
            pln(propArr.getNomCarreau() + "numéro 1");
            pln(propArr1.getNomCarreau() + "numéro 2");
            pln(propArr2.getNomCarreau() + "numéro 3");
            text = sc.nextLine();
            
            try {
                        numero = Integer.parseInt(text);
		    } catch(Exception e) {
			pln("Veuillez rentrer un numéro et seulement un numéro s'il vous plait");
		    }
            switch(numero){
                case 1:
                    return propArr;
                case 2:
                        return propArr1;
                case 3:
                        return propArr2;  
            }
            
        }else if(propArr1!=null){
            pln("Vous avez le choix entre 2 propriétés, veuillez choisir un numéro :");
            pln(propArr.getNomCarreau() + "numéro 1");
            pln(propArr1.getNomCarreau() + "numéro 2");
            text = sc.nextLine();
            
            try {
                        numero = Integer.parseInt(text);
		    } catch(Exception e) {
			pln("Veuillez rentrer un numéro et seulement un numéro s'il vous plait");
		    }
            switch(numero){
                case 1:
                    return propArr;
                case 2:
                        return propArr1; 
            }
  
        }else{
            text =io("Vous pouvez construire que sur "+propArr.getNomCarreau());
            return propArr;
        }
        return Areturn;
    }
    
    public static void fermerScanner() {
	sc.close();
    }
}