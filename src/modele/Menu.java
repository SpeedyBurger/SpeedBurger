package modele;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Produit {

	protected String nom;
	protected double prix;
	protected int id;
	public ArrayList<Produit> listeProduit = new ArrayList<Produit>();
	protected String taille;

	public String getTaille() {
		return taille;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
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
	
	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(ArrayList<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}
	
	public void addProduit(Produit pProduit) {
		this.listeProduit.add(pProduit);
	}
	
	public void deleteProduit(Produit pProduit) {
		this.listeProduit.remove(pProduit);
	}

	public Menu(String nom, double prix, int id, String pTypeProduit) {
		super(nom, prix, id, pTypeProduit);
		this.nom = nom;
		this.prix = prix;
		this.id = id;
	}

}