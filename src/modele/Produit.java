package modele;

import java.util.ArrayList;

public class Produit {

	protected String nom;

	protected int prix;

	protected int id;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void AfficherNom() {
	}

	public void AfficherPrix() {
	}

	public Produit(String nom, int prix, int id) {
		this.nom = nom;
		this.prix = prix;
		this.id = id;
	}

}