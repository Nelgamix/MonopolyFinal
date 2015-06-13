/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Jeu.Carreau;
import Jeu.CarreauArgent;
import Jeu.CarreauMouvement;
import Jeu.CarreauTirage;
import Jeu.Compagnie;
import Jeu.Gare;
import Jeu.Monopoly;
import Jeu.ProprieteAConstruire;
import Ui.Texte;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class Carreaux {
    private Monopoly monopoly;
    private ArrayList<Carreau> carreaux;
    private final boolean debug = false;
    private ArrayList<Integer> loyers = new ArrayList<Integer>();

    public Carreaux(Monopoly monopoly) {
	this.monopoly = monopoly;
    }
    
    public void initializeCarreaux(String dataFilename) {
	carreaux = new ArrayList<>();
	
	try {
	    ArrayList<String[]> data = readDataFile(dataFilename, ",");

	    Carreau c;
	    String caseType;

	    for(int i = 0; i < data.size(); ++i) {
		caseType = data.get(i)[0];
		
		if (debug) {
		    System.out.println("CaseType: '" + caseType + "'");
		}

		int numero = 0;
		int prixAchat = 0;

		// Converti le numéro dans le fichier
		try {
		    numero = Integer.parseInt(data.get(i)[1]);
		} catch(Exception e) {
		    Texte.printerr("Impossible de récupérer un numéro de case correct");
		    e.printStackTrace();
		}

		if (caseType.compareTo("P") == 0) {
		    if (debug) {
			System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    }
		    
		    // Converti le prix du loyer dans le fichier
		    try {
                        prixAchat = Integer.parseInt(data.get(i)[4]);
		    } catch(Exception e) {
			Texte.printerr("Impossible de récupérer un prix de loyer correct");
			e.printStackTrace();
		    }
                    
                    ArrayList<Integer> loyers = new ArrayList<Integer>();
		    ArrayList<Integer> prixAchatA = new ArrayList<Integer>();
                    
                    for (int j = 5; j < 13; j++) {
			if (j < 11) {
			    loyers.add(Integer.parseInt(data.get(i)[j]));
			} else {
			    prixAchatA.add(Integer.parseInt(data.get(i)[j]));
			}
                    }

		    c = new ProprieteAConstruire(monopoly, numero, data.get(i)[2], data.get(i)[3], prixAchat, loyers, prixAchatA);
		    carreaux.add(c);
		} else if (caseType.compareTo("G") == 0) {
		    if (debug) {
			System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    }
		    
		    // Converti le prix du loyer dans le fichier
		    try {
			prixAchat = Integer.parseInt(data.get(i)[3]);
		    } catch(Exception e) {
			Texte.printerr("Impossible de récupérer un prix de loyer correct");
			e.printStackTrace();
		    }
                    
                    ArrayList<Integer> loyers = new ArrayList<Integer>();
                    
                    for( int t=1 ; t<4 ; t++ ){
                        loyers.add(t*25);
                    } 

		    c = new Gare(monopoly, numero, data.get(i)[2], prixAchat, loyers);
		    carreaux.add(c);
		} else if (caseType.compareTo("C") == 0) {
		    if (debug) {
			System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    }

		    // Converti le prix du loyer dans le fichier
		    try {
			prixAchat = Integer.parseInt(data.get(i)[3]);
		    } catch(Exception e) {
			Texte.printerr("Impossible de récupérer un prix de loyer correct");
			e.printStackTrace();
		    }
                    
                    ArrayList<Integer> loyers = new ArrayList<Integer>();
                    
                    for( int t=1 ; t<2 ; t++ ){
                        loyers.add(t);
                    }  

		    c = new Compagnie(monopoly, numero, data.get(i)[2], prixAchat, loyers);
		    carreaux.add(c);
		} else if (caseType.compareTo("CT") == 0) {
		    if (debug) {
			System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    }
		    
		    c = new CarreauTirage(monopoly, numero, data.get(i)[2]);
		    carreaux.add(c);
		} else if (caseType.compareTo("CA") == 0) {
		    if (debug) {
			System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    }
		    
		    // Converti le prix du loyer dans le fichier
		    try {
			prixAchat = Integer.parseInt(data.get(i)[3]);
		    } catch(Exception e) {
			Texte.printerr("Impossible de récupérer un prix de loyer correct");
			e.printStackTrace();
		    }

		    c = new CarreauArgent(monopoly, numero, data.get(i)[2], prixAchat);
		    carreaux.add(c);
		} else if (caseType.compareTo("CM") == 0) {
		    if (debug) {
			System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    }
		    
		    c = new CarreauMouvement(monopoly, numero, data.get(i)[2], getCase("prison"));
		    carreaux.add(c);
		} else {
		    System.err.println("[buildGamePlateau()] : Invalid Data type");
		}
	    }
	}
	catch (FileNotFoundException e) {
	    System.err.println("[buildGamePlateau()] : File is not found!");
	}
	catch (IOException e) {
	    System.err.println("[buildGamePlateau()] : Error while reading file!");
	}
    }
    
    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
	ArrayList<String[]> data = new ArrayList<String[]>();

	BufferedReader reader  = new BufferedReader(new FileReader(filename));
	String line = null;
	while((line = reader.readLine()) != null){
	    data.add(line.split(token));
	}
	reader.close();

	return data;
    }
    
    public ArrayList<ProprieteAConstruire> getProprietesAConstruire() {
	ArrayList<ProprieteAConstruire> toReturn = new ArrayList<>();
	
	for (Carreau c : carreaux) {
	    if (c instanceof ProprieteAConstruire) {
		toReturn.add((ProprieteAConstruire)c);
	    }
	}
	
	return toReturn;
    }
    
    public ArrayList<Gare> getGares() {
	ArrayList<Gare> toReturn = new ArrayList<>();
	
	for (Carreau c : carreaux) {
	    if (c instanceof Gare) {
		toReturn.add((Gare)c);
	    }
	}
	
	return toReturn;
    }
    
    public Carreau getCase(String nomCase) {
	Carreau caseATrouver = null;
        //System.out.println("We search for case " + nomCase);
	for (Carreau c : carreaux) {
	    if (c instanceof Carreau) {
		String weSeek = nomCase.toLowerCase();
		String inThis = c.getNomCarreau().toLowerCase();
		int j = 0;
		int k = 0;
		
		//System.out.println("Analysing case " + c.getNumero() + " - " + c.getNomCarreau());
		
		for (int i = 0; i < inThis.length(); i++) {
		    k = i;
		    while (j < weSeek.length() && k < inThis.length() && weSeek.charAt(j) == inThis.charAt(k)) {
			//System.out.println(weSeek.charAt(j) + " equals " + inThis.charAt(k));
			
			if (j == weSeek.length() - 1) {
			    //System.out.println("La case " + nomCase + " est à l'indice " + c.getNumero() + " - Nom: " + c.getNomCarreau());
			    caseATrouver = c;
			}
			
			k++;
			j++;
		    }
		    j = 0;
		}
	    }
	    if (caseATrouver != null) {
		//System.out.println(nomCase + " found. Breaking out. Prison break.");
		break;
	    }
	}
	
	if (caseATrouver == null) {
	    Texte.printerr("La case " + caseATrouver + " n'a pas été trouvée. Vérifiez votre plateau.");
	}
	
	return caseATrouver;
    }
    
    public ArrayList<Carreau> getCarreaux() {
	return carreaux;
    }
    
    public Carreau getCarreauAt(int place) {
	return this.carreaux.get(place);
    }
    
    public int getIndexOf(Carreau c) {
	return this.carreaux.indexOf(c);
    }
}
