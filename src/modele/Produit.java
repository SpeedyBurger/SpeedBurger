package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.ConnexionSQL;
import modele.Produit;

public class Produit {

	protected String nom;
	protected double prix;
	protected int id;
	
	public String typeProduit;
	
	public static ArrayList<Produit> ListeProduits = new ArrayList<Produit>();

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
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

	public Produit(String nom, double prix, int id, String pTypeProduit) {
		this.nom = nom;
		this.prix = prix;
		this.id = id;
		this.typeProduit = pTypeProduit;
		
		ListeProduits.add(this);
	}
	
	public void addToListeProduits() {
		ListeProduits.add(this);
	}

	public static void produitFromBDD() {

		ConnexionSQL bdd = new ConnexionSQL();

		try {
			ResultSet resultat = bdd.requeteRetourneDonnees("SELECT * FROM Produit");

			int ProduitId;
			String ProduitNom;
			double ProduitPrix;
			String ProduitType;
			String ProduitTaille;

			if (resultat.first()) {
				do {
					ProduitId = resultat.getInt(1);
					ProduitNom = resultat.getString(2);
					ProduitPrix = resultat.getDouble(3);
					ProduitType = resultat.getString(4);
					ProduitTaille = resultat.getString(5);
					
					// System.out.println("ID = " + ProduitId + " Nom = " + ProduitNom + " Prix = " + ProduitPrix + " Type = " + ProduitType + " Taille = " + ProduitTaille);
					

					if (ProduitType.equals("menu")) {
						new Menu(ProduitNom, ProduitPrix, ProduitId, ProduitType, ProduitTaille);
					} else if (ProduitType.equals("sandwitch")) {
						new Sandwich(ProduitNom, ProduitPrix, ProduitId, ProduitType, ProduitTaille);
					} else if (ProduitType.equals("frite")) {
						new Frite(ProduitNom, ProduitPrix, ProduitId, ProduitType, ProduitTaille);
					} else if (ProduitType.equals("boisson")) {
						new Boisson(ProduitNom, ProduitPrix, ProduitId, ProduitType, ProduitTaille);
					} else if (ProduitType.equals("salade")) {
						new Salade(ProduitNom, ProduitPrix, ProduitId, ProduitType);
					} else if (ProduitType.equals("dessert")) {
						new Dessert(ProduitNom, ProduitPrix, ProduitId, ProduitType);
					} else if (ProduitType.equals("sauce")) {
						new Sauce(ProduitNom, ProduitPrix, ProduitId, ProduitType);
					}

				} while (resultat.next());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}