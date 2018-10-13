package modele;

public class Frite extends Produit {

	protected String taille;

	public void AfficherTaille() {
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public Frite(String nom, double prix, int id, String pTypeProduit, String taille) {
		super(nom, prix, id, pTypeProduit);
		this.taille = taille;
	}

}