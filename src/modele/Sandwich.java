package modele;

public class Sandwich extends Produit {

	protected String taille;

	public void AfficherTaille() {
	}

	public Sandwich(String nom, double prix, int id, String pTypeProduit, String taille) {
		super(nom, prix, id, pTypeProduit);
		this.taille = taille;
	}

}