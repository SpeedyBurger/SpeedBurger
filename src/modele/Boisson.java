package modele;

public class Boisson extends Produit {

	protected String taille;

	public void AfficherTaille() {

	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public Boisson(String nom, int prix, int id, String taille) {
		super(nom, prix, id);
		this.taille = taille;
	}

}