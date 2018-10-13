package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import modele.Produit;

public class Commande {

	private int id;
	public ArrayList<Produit> listeProduit = new ArrayList<Produit>();
	public ArrayList<Integer> quantitelisteProduit = new ArrayList<Integer>();

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
		this.quantitelisteProduit.add(1);
	}
	
	public void ajouteQteProduit(int index) {
		int quantite = this.quantitelisteProduit.get(index) + 1;
		this.quantitelisteProduit.set(index, quantite);
	}
	
	public void retireQteProduit(int index) {
		int quantite = this.quantitelisteProduit.get(index) - 1;
		this.quantitelisteProduit.set(index, quantite);
	}

	public Commande(int id) {
		this.id = id;
	}

}