package Jeu;

import Data.Carreaux;
import Data.Cartes;
import Data.Joueurs;
import java.util.ArrayList;
import java.util.Random;
import Ui.Texte;
import java.io.IOException;
import java.util.Objects;

public class Monopoly {
    // ATTRIBUTS ----------------------------------------------------------
    private Joueur j;
    private Joueur first;
    private Joueur lastJ;
    private Cartes cartes = new Cartes(this);
    private Carreaux carreaux = new Carreaux(this);
    private Joueurs joueurs = new Joueurs(this);
    private int nbMaisons = 32;
    private int nbHotels = 12;
    
    // Constants
    private static final int tempsPauseJoueurs = 2000;
    private static final int tempsPauseTirage = 500;
    private static final int toursJeu = 20;
    int doubleCompt = 0;
    
    public Monopoly() {
	carreaux.initializeCarreaux(getPath());
        cartes.initializeCartes();
	joueurs.inscriptionJoueurs();
	if (joueurs.estVide()) {
	    terminerJeu();
	} else if (joueurs.getSize() < 2) {
	    System.out.println("Le jeu doit comporter au moins 2 joueurs.");
	    terminerJeu();
	}
	tirageDes();
        j = quiCommence();
	jeu();
    }
    
    public Carreaux getCarreaux() {
	return carreaux;
    }
    
    public Joueurs getJoueurs() {
	return joueurs;
    }
    
    public Joueur getJoueurActuel() {
	return j;
    }
    
    public Cartes getCartes() {
	return cartes;
    }
    
    public void getJoueurInPrison(String nom) {
        Joueur joueur = joueurs.getJoueurName(nom);
        if (joueur != null) {
            doubleCompt = 3;
        }
    }
    
    private void jeu() {
	int i = 0;
	
	while (i < toursJeu) {
	    if (j == first && j != lastJ) {
		i++;
		Texte.jeu_tourGlobal(i);
	    }
	    
	    Texte.jeu_tourJoueur(j.getNomJoueur());
	    
	    lastJ = j;
	    
	    if (Texte.io("En attente de votre signal pour lancer les dés... (D pour debug)").equalsIgnoreCase("D")) {
		Texte.jeu_debug(this);
		
		if (doubleCompt > 2) {
		    Texte.jeu_troisDoublePrison(j.getNomJoueur());
		    j.setInPrison(true);
		    j.setPositionCourante(carreaux.getCase("prison"));
		}
		
		Texte.joueur_afficherPosition(j.getNomJoueur(), j.getPositionCourante().getNumero(), j.getPositionCourante().getNomCarreau());
		j.actionner();
	    } else {
		tirageDes(j);
		if (doubleCompt > 2) {
		    Texte.jeu_troisDoublePrison(j.getNomJoueur());
		    j.setInPrison(true);
		    j.setPositionCourante(carreaux.getCase("prison"));
		}

		if (!j.isInPrison()) {
		    jouerUnCoup();
		} else if (doubleCompt < 3) {
		    jouerPrison();
		}
	    }
            
	    if (!j.isDouble() || j.isInPrison()) {
		j = joueurs.getJoueurAt((joueurs.getIndexOf(j) + 1) % joueurs.getSize());
		doubleCompt = 0;
	    } else {
		Texte.joueur_rejoue(j.getNomJoueur());
		doubleCompt++;
	    }
	    
	    // PAUSE
	    try {
		Thread.sleep(tempsPauseJoueurs);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }

    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    
    private void jouerPrison() {
        if (j.hasPrison()) {
            Texte.joueur_utiliserCarteSortiePrison();
            String rep = Texte.input();
            if (rep.equals("O") || rep.equals("o")) {
                j.retirerCarteSortiePrison();
                jouerUnCoup();
                j.sortiePrison();
            } else {
                
            }
        }
	
        if (j.isInPrison()) {
            j.setLancePrison(j.getLancePrison() + 1);
            int lance = j.getLancePrison();
            tirageDes(j);
            int tirageDes = j.getDesTotal();

            if (j.isDouble()) {
                Texte.joueur_sortiePrison();
                j.sortiePrison();
                jouerUnCoup(tirageDes);
            } else if (lance == 3) {
                Texte.joueur_sortiePrisonPayer();
                j.retirerSousous(50);
                j.sortiePrison();
                jouerUnCoup(tirageDes);
            } else {
                Texte.joueur_resterPrison();
            }
        }
    }
    
    private void jouerUnCoup() {
        Carreau arrivee;
        if (carreaux.getCarreaux().indexOf(j.getPositionCourante()) + j.getDesTotal() > 40) {
            arrivee = carreaux.getCarreauAt(0);
            int reste = (j.getPositionCourante().getNumero() + j.getDesTotal()) - 40;
            j.setPositionCourante(arrivee);
            j.actionner();
            j.setPositionCourante(carreaux.getCarreauAt(reste));
	    Texte.joueur_afficherPosition(j.getNomJoueur(), j.getPositionCourante().getNumero(), j.getPositionCourante().getNomCarreau());
            j.actionner();
        } else {
            arrivee = carreaux.getCarreauAt((carreaux.getCarreaux().indexOf(j.getPositionCourante()) + j.getDesTotal()) % 40);
            j.setPositionCourante(arrivee);
            Texte.joueur_afficherPosition(j.getNomJoueur(), j.getPositionCourante().getNumero(), j.getPositionCourante().getNomCarreau());
            j.actionner();
        }
    }
    
    private void jouerUnCoup(int tirage) {
        Carreau arrivee;
        if (carreaux.getIndexOf(j.getPositionCourante()) + j.getDesTotal() > 40) {
            arrivee = carreaux.getCarreauAt(0);
            int reste = (j.getPositionCourante().getNumero() + j.getDesTotal()) - 40;
            j.setPositionCourante(arrivee);
            Texte.joueur_afficherPosition(j.getNomJoueur(), j.getPositionCourante().getNumero(), j.getPositionCourante().getNomCarreau());
            j.actionner();
            j.setPositionCourante(carreaux.getCarreauAt(reste));
            j.actionner();
        } else {
            arrivee = carreaux.getCarreauAt((carreaux.getIndexOf(j.getPositionCourante()) + j.getDesTotal()) % 39);
            j.setPositionCourante(arrivee);
            Texte.joueur_afficherPosition(j.getNomJoueur(), j.getPositionCourante().getNumero(), j.getPositionCourante().getNomCarreau());
            j.actionner();
        }
    }

    private void afficheCase() {
	System.out.println("----------------------------------------------------------------------------------------");
	for(Carreau c : carreaux.getCarreaux()) {
	    System.out.println(c.getNomCarreau());
	}
    }

    private void afficheJoueur() {
	System.out.println("----------------------------------------------------------------------------------------");
	for(Joueur j : joueurs.getJoueurs()) {
	    System.out.println(j.getNomJoueur());
	}
    }
    
    private void tirageDes() {
	// Math.rand not so rand, so...
	Random ran = new Random();
	ArrayList<Integer> lances;
	Integer resultatDe;

	for(Joueur j : joueurs.getJoueurs()) {
	    Texte.jeu_tirageDes(j.getNomJoueur());
	    tirageDes(j);
	}
    }

    private void tirageDes(Joueur j) {
	// To do: Redo random system, as it is pretty not random atm (Mousse random system)
	Random ran = new Random();
	int resultatDes = 0;
	
	j.clearDes();

	for (int i = 0; i < 2; i++) {
	    resultatDes = Math.abs(ran.nextInt(5) + 1);
	    j.addDe(resultatDes);
	    Texte.joueur_afficherTirage(j.getNomJoueur(), resultatDes);

	    try {
		Thread.sleep(tempsPauseTirage);
	    } catch (InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
    }

    private Joueur quiCommence() {
	Integer max = 0;
	Integer totalJ = 0;
	Random ran = new Random();
	int random = 0;
	Joueur jo = null;
	
	for(Joueur j : joueurs.getJoueurs()) {
	    totalJ = j.getDesTotal();
	    
	    if (totalJ > max) {
		jo = j;
		max = totalJ;
	    } else if (Objects.equals(totalJ, max)) {
		// TRES ARBITRAIRE ET ALEATOIRE
		random = Math.abs(ran.nextInt() % 2);
		if (random == 0) {
		    jo = j;
		}
		// FIN ARBITRAIRE ET ALEATOIRE
		
		max = totalJ;
	    }
	}
	
	int indexJo = joueurs.getIndexOf(jo);
	
	ArrayList<Joueur> joueursCo = new ArrayList<>();
	joueursCo.addAll(joueurs.getJoueurs());
	joueurs.vider();
	
	for (int i = 0; i < joueursCo.size(); i++) {
	    joueurs.ajouterJoueur(joueursCo.get(indexJo));
	    
	    indexJo++;
	    
	    if (indexJo == joueursCo.size()) {
		indexJo = 0;
	    }
	}
	
	first = jo;
	
	System.out.println("----------------------------------------------------------------------------------------");
	System.out.print("Joueur qui commence : ");
	System.out.println(jo.getNomJoueur() + " avec " + jo.getDesTotal() + " points.");
	
	return jo;
    }
    
    public void terminerJeu() {
	Texte.fermerScanner();
	Texte.jeu_aurevoir();
	System.exit(0);
    }
    
    public static String getPath() {
	String current = "";
	
	try {
	    current = new java.io.File(".").getCanonicalPath();
	} catch(IOException e) {
	    e.printStackTrace();
	}
	
	System.out.println("Location of the file: " + current + "/data.txt");
	
	return current + "/data.txt";
    }
    
}

