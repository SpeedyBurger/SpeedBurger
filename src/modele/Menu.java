package modele;

import java.util.ArrayList;

public class Menu extends Produit {

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

public Menu(String nom, int prix, int id) {
	super(nom,prix,id);
	this.nom = nom;
	this.prix = prix;
	this.id = id;
}


  

}