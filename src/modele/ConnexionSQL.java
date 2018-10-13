package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnexionSQL {
	//D�claration des attributs
	private String nomBDD, login, mdp, url;
	private Connection conn;
	private ResultSet result;
	private Statement state;
	
	//D�claration d'un constructeur d'objet ConnexionSQL
	public ConnexionSQL(String pNomBDD, String pLogin, String pMdp, String pUrl) {
		this.nomBDD = pNomBDD;
		this.login = pLogin;
		this.mdp = pMdp;
		this.url = pUrl;
	}
	
	public ConnexionSQL() {
		this.nomBDD = "speedyburgertest";
		this.login = "root";
		this.mdp = "";
		this.url = "127.0.0.1";
		this.SeConnecter();
	}
	
	//M�thode permettant la connexion au serveur
	public void SeConnecter(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");

		    String chemin = "jdbc:mysql://" + this.url + "/" + this.nomBDD;
		    
		     //conn = DriverManager.getConnection(chemin + "?user=" + this.login + "&password=" + this.mdp);
		    conn = DriverManager.getConnection(chemin, this.login, this.mdp);
		    
		    System.out.println("Connexion  � la BDD effective !");    
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//M�thode qui execute la requ�te pass� en param�tre retournant un r�sultat de requ�te
	public ResultSet requeteRetourneDonnees(String pRequete) throws SQLException{
		//Cr�ation d'un objet Statement
	    state = conn.createStatement(); 
	    //L'objet ResultSet contient le r�sultat de la requ�te SQL
	    result = state.executeQuery(pRequete);
	    //On r�cup�re les MetaData
	    
	    return result;
	}	
	
	//requete executant une requete sans retour (UPDATE,INSERT..)
	public void requeteSansDonnes(String pRequete) throws SQLException{
		//Cr�ation d'un objet Statement
	    Statement state = conn.createStatement();
	    //L'objet ResultSet contient le r�sultat de la requ�te SQL
	    state.execute(pRequete);
	    state.close();
	}
	
	//M�thode fermant la connexion au serveur
	public void fermerConnexion() throws SQLException{
		state.close();
		result.close();
	}
}
	

