package Ui;

import Jeu.Carreau;
import Jeu.CarreauPropriete;
import Jeu.Joueur;
import Jeu.Monopoly;
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
    
    public static void fermerScanner() {
	sc.close();
    }
}