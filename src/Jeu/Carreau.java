package Jeu;

public abstract class Carreau {
    private int numero;
    private String nomCarreau;
    private Monopoly monopoly;

    public Carreau(Monopoly monopoly, int numero, String nomCarreau) {
	this.setNumero(numero);
	this.setNomCarreau(nomCarreau);
	this.setMonopoly(monopoly);
    }

    public void setNumero(int numero){
	this.numero=numero;
    }

    public int getNumero(){
	return numero;
    }

    public String getNomCarreau() {
	return nomCarreau;
    }

    public Monopoly getMonopoly() {
	return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
	this.monopoly = monopoly;
    }

    public void setNomCarreau(String nomCarreau) {
	this.nomCarreau = nomCarreau;
    }
    
    public abstract void action(Joueur j);
    
}