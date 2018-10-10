package modele;

public class Sandwich extends Produit {

	protected String taille;

	public void AfficherTaille() {
	}

	public Sandwich(String nom, int prix, int id, String taille) {
		super(nom, prix, id);
		this.taille = taille;
	}

}