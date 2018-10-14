package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Boisson;
import modele.Frite;
import modele.Menu;
import modele.Produit;
import modele.Salade;
import modele.Sandwich;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class Commande extends JFrame {

	private JPanel contentPane;
	public modele.Commande Lacommande = new modele.Commande(1);
	private static int boutonPetitPressed = 0;
	private static int boutonMoyenPressed = 1;
	private static int boutonGrandPressed = 0;
	private static int MenuPressed = 0;
	private static int MenuXLPressed = 0;
	private static Produit currentMenu = null;
	public double prix;
	public int currentIndex;
	public String currentProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande frame = new Commande();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private class MyListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			Produit pProduit = (Produit) value;
			String nom = pProduit.getNom();
			String labelText;

			List<Produit> listeProduit = Lacommande.getListeProduit();
			int indexProduit = 0;
			int quantite = 0;

			if (listeProduit.contains(pProduit)) {
				indexProduit = listeProduit.indexOf(pProduit);
				quantite = Lacommande.quantitelisteProduit.get(indexProduit);
			} else {
				quantite = 1;
			}

			if (pProduit.getClass().getName().equals("modele.Boisson")) {
				labelText = "<html>" + nom + " - Taille " + ((Boisson) pProduit).getTaille() + " - Nb : " + quantite;
			} else if (pProduit.getClass().getName().equals("modele.Frite")) {
				labelText = "<html>" + nom + " - Taille " + ((Frite) pProduit).getTaille() + " - Nb : " + quantite;
			} else if (pProduit.getClass().getName().equals("modele.Sandwich")) {
				labelText = "<html>" + nom + " - Taille " + ((Sandwich) pProduit).getTaille() + " - Nb : " + quantite;
			} else if (pProduit.getClass().getName().equals("modele.Menu")) {
				List<Produit> lesproduitsMenu = ((Menu) pProduit).getListeProduit();
				String txtProduits = "";
				for (Produit unProduit : lesproduitsMenu) {
					if (unProduit.getClass().getName().equals("modele.Boisson")) {
						txtProduits += "<br> - " + unProduit.getNom() + " - Taille "
								+ ((Boisson) unProduit).getTaille();
					} else if (unProduit.getClass().getName().equals("modele.Frite")) {
						txtProduits += "<br> - " + unProduit.getNom() + " - Taille " + ((Frite) unProduit).getTaille();
					} else if (unProduit.getClass().getName().equals("modele.Sandwich")) {
						txtProduits += "<br> - " + unProduit.getNom() + " - Taille "
								+ ((Sandwich) unProduit).getTaille();
					} else {
						txtProduits += "<br> - " + unProduit.getNom();
					}

					// verifMenu(pProduit);

				}
				labelText = "<html><body style='margin-top: 15px;margin-bottom: 15px;'>" + nom + txtProduits;
			} else {
				labelText = "<html>" + nom + " - Nb  : " + quantite;
			}

			setText(labelText);

			list.repaint();

			return this;
		}

	}

	/**
	 * Create the frame.
	 */
	public Commande() {
		setTitle("Application Speedy_Burger caisse");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Produit.produitFromBDD();

		ArrayList<Produit> lesProduits = Produit.ListeProduits;

		DefaultListModel listModel = new DefaultListModel();

		/*
		 * Boisson coca=new Boisson("Coca",1,1,""); Boisson fanta=new
		 * Boisson("Fanta",1,2,""); Boisson sprite=new Boisson("Sprite",1,3,""); Boisson
		 * eau=new Boisson("Eau",1,4,""); Boisson biere=new Boisson("Bière",1,5,"");
		 * Boisson cafe=new Boisson("Café",1,6,"");
		 * 
		 * Sandwich hamburger=new Sandwich("Hamburger",2,1,""); Sandwich fastburger=new
		 * Sandwich("FastBurger",2,2,""); Sandwich speedyburger=new
		 * Sandwich("SpeedyBurger",2,3,""); Sandwich baconburger=new
		 * Sandwich("BaconBurger",2,4,""); Sandwich chickenrun=new
		 * Sandwich("ChickenRun",2,5,""); Sandwich fishspeed=new
		 * Sandwich("FishSpeed",2,6,""); Sandwich chickenstick4=new
		 * Sandwich("Chicken Stick x4",2,7,""); Sandwich chickenstick7=new
		 * Sandwich("Chicken Stick x7",2,8,"");
		 * 
		 * Salade salacleo=new Salade("Salade Cléopâtre",2,1); Salade salachef=new
		 * Salade("Salade Chef",2,2); Salade salapoulet=new Salade("Salade Poulet",2,3);
		 * Salade salagrecque=new Salade("Salade Grecque",2,4);
		 */

		JButton btnMhamburger = new JButton("<HTML><BODY>Menu<Br> Hamburger</BODY></HTML>");
		btnMhamburger.setBounds(10, 65, 150, 90);
		contentPane.add(btnMhamburger);

		JButton btnMfastburger = new JButton("<HTML><BODY>Menu<Br> Fast_Burger</BODY></HTML>");
		btnMfastburger.setBounds(165, 65, 150, 90);
		contentPane.add(btnMfastburger);

		JButton btnMSpeedyburger = new JButton("<HTML><BODY>Menu<Br> SpeedyBurger</BODY></HTML>");
		btnMSpeedyburger.setBounds(320, 65, 150, 90);
		contentPane.add(btnMSpeedyburger);

		JButton btnMbaconburger = new JButton("<HTML><BODY>Menu<Br> BaconBurger</BODY></HTML>");
		btnMbaconburger.setBounds(475, 65, 150, 90);
		contentPane.add(btnMbaconburger);

		JButton btnMchickenburger = new JButton("<HTML><BODY>Menu<Br> Chickenrun</BODY></HTML>");
		btnMchickenburger.setBounds(630, 65, 150, 90);
		contentPane.add(btnMchickenburger);

		JButton btnMfishspeed = new JButton("<HTML><BODY>Menu<Br> Fichespeed</BODY></HTML>");
		btnMfishspeed.setBounds(785, 65, 150, 90);
		contentPane.add(btnMfishspeed);

		JButton btnMcleopatre = new JButton("<HTML><BODY>Menu<Br> Cléopatre</BODY></HTML>");
		btnMcleopatre.setBounds(940, 65, 150, 90);
		contentPane.add(btnMcleopatre);

		JButton btnMchickenstick = new JButton("<HTML><BODY>Menu<Br> Chicken Stick </BODY></HTML>");
		btnMchickenstick.setBounds(1095, 65, 150, 90);
		contentPane.add(btnMchickenstick);

		JLabel lblSaisieDeCommande = new JLabel("Saisie de commande");
		lblSaisieDeCommande.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaisieDeCommande.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSaisieDeCommande.setBounds(299, 11, 385, 36);
		contentPane.add(lblSaisieDeCommande);

		JLabel lblPrixTot = new JLabel("0");
		lblPrixTot.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPrixTot.setBounds(1683, 887, 59, 27);
		contentPane.add(lblPrixTot);

		JButton btncoca = new JButton("Coca");
		btncoca.setBounds(10, 180, 120, 90);
		btncoca.setBackground(Color.CYAN);
		contentPane.add(btncoca);

		JButton btnfanta = new JButton("Fanta");
		btnfanta.setBounds(135, 180, 120, 90);
		btnfanta.setBackground(Color.CYAN);
		contentPane.add(btnfanta);

		JButton btnSprite = new JButton("Sprite");
		btnSprite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(sprite.getNom());
			}
		});
		btnSprite.setBounds(260, 180, 120, 90);
		btnSprite.setBackground(Color.CYAN);
		contentPane.add(btnSprite);

		JButton btneau = new JButton("Eau");
		btneau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(eau.getNom());
			}
		});
		btneau.setBounds(385, 180, 120, 90);
		btneau.setBackground(Color.CYAN);
		contentPane.add(btneau);

		JButton btnbiere = new JButton("Bi\u00E8re");
		btnbiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(biere.getNom());
			}
		});
		btnbiere.setBounds(510, 180, 120, 90);
		btnbiere.setBackground(Color.CYAN);
		contentPane.add(btnbiere);

		JButton btncafe = new JButton("Caf\u00E9");
		btncafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(cafe.getNom());
			}
		});
		btncafe.setBackground(Color.CYAN);
		btncafe.setBounds(1101, 180, 120, 90);
		contentPane.add(btncafe);

		JButton btn_hamburger = new JButton("Hamburger");
		btn_hamburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(hamburger.getNom());
			}
		});
		btn_hamburger.setBounds(10, 295, 150, 90);
		btn_hamburger.setBackground(Color.yellow);
		contentPane.add(btn_hamburger);

		JButton btn_fastburger = new JButton("FastBurger");
		btn_fastburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(fastburger.getNom());
			}
		});
		btn_fastburger.setBounds(165, 295, 150, 90);
		btn_fastburger.setBackground(Color.yellow);
		contentPane.add(btn_fastburger);

		JButton btn_speedyburger = new JButton("SpeedyBurger");
		btn_speedyburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(speedyburger.getNom());
			}
		});
		btn_speedyburger.setBounds(320, 295, 150, 90);
		btn_speedyburger.setBackground(Color.yellow);
		contentPane.add(btn_speedyburger);

		JButton btn_baconburger = new JButton("BaconBurger");
		btn_baconburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(baconburger.getNom());
			}
		});
		btn_baconburger.setBounds(475, 295, 150, 90);
		btn_baconburger.setBackground(Color.yellow);
		contentPane.add(btn_baconburger);

		JButton btn_chickenrun = new JButton("ChickenRun");
		btn_chickenrun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(chickenrun.getNom());
			}
		});
		btn_chickenrun.setBounds(630, 295, 150, 90);
		btn_chickenrun.setBackground(Color.yellow);
		contentPane.add(btn_chickenrun);

		JButton btn_fishspeed = new JButton("FishSpeed");
		btn_fishspeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(fishspeed.getNom());
			}
		});
		btn_fishspeed.setBackground(Color.yellow);
		btn_fishspeed.setBounds(785, 295, 150, 90);
		contentPane.add(btn_fishspeed);

		JButton btn_chickenstick4 = new JButton("Chicken Stick X4");
		btn_chickenstick4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(chickenstick4.getNom());
			}
		});
		btn_chickenstick4.setBounds(940, 295, 150, 90);
		btn_chickenstick4.setBackground(Color.yellow);
		contentPane.add(btn_chickenstick4);

		JButton btn_chickenstick7 = new JButton("Chicken Stick X7");
		btn_chickenstick7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// listModel.addElement(chickenstick7.getNom());
			}
		});
		btn_chickenstick7.setBounds(1095, 295, 150, 90);
		btn_chickenstick7.setBackground(Color.yellow);
		contentPane.add(btn_chickenstick7);

		JButton btn_scleopatre = new JButton("<HTML><BODY>Salade<Br> Cl\u00E9opatre</BODY></HTML>");
		btn_scleopatre.setBounds(10, 410, 150, 90);
		btn_scleopatre.setBackground(Color.green);
		contentPane.add(btn_scleopatre);

		JButton btn_schef = new JButton("<HTML><BODY>Salade<Br> Chef</BODY></HTML>");
		btn_schef.setBounds(165, 410, 150, 90);
		btn_schef.setBackground(Color.green);
		contentPane.add(btn_schef);

		JButton btn_spoulet = new JButton("<HTML><BODY>Salade<Br> Poulet</BODY></HTML>");
		btn_spoulet.setBounds(320, 410, 150, 90);
		btn_spoulet.setBackground(Color.green);
		contentPane.add(btn_spoulet);

		JButton btn_sgrecque = new JButton("<HTML><BODY>Salade<Br> Grecque</BODY></HTML>");
		btn_sgrecque.setBounds(475, 410, 150, 90);
		btn_sgrecque.setBackground(Color.green);
		contentPane.add(btn_sgrecque);

		JLabel lblMenus = new JLabel(
				"Menus________________________________________________________________________________________________________________________________________________________________________________________");
		lblMenus.setBounds(10, 45, 1462, 14);
		contentPane.add(lblMenus);

		JLabel lblSandwichs = new JLabel(
				"Sandwichs____________________________________________________________________________________________________________________________________________________________________________________");
		lblSandwichs.setBounds(10, 275, 1462, 14);
		contentPane.add(lblSandwichs);

		JLabel lblBoissons = new JLabel(
				"Boissons___________________________________________________________________________________________________________________________________________________________________");
		lblBoissons.setBounds(10, 160, 1462, 14);
		contentPane.add(lblBoissons);

		JLabel lbSsalades = new JLabel(
				"Salades__________________________________________________________________________________");
		lbSsalades.setBounds(10, 390, 640, 14);
		contentPane.add(lbSsalades);

		JLabel lbl_frite = new JLabel("Frites_____________________________________________________________");
		lbl_frite.setBounds(785, 390, 479, 14);
		contentPane.add(lbl_frite);

		JButton btn_fritepetite = new JButton("<HTML><BODY>Frite<Br>PETITE</BODY></HTML>");
		btn_fritepetite.setBackground(Color.ORANGE);
		btn_fritepetite.setBounds(785, 410, 150, 90);
		contentPane.add(btn_fritepetite);

		JButton btn_fritemoyenne = new JButton("<HTML><BODY>Frite<Br>MOYENNE</BODY></HTML>");
		btn_fritemoyenne.setBackground(Color.ORANGE);
		btn_fritemoyenne.setBounds(940, 410, 150, 90);
		contentPane.add(btn_fritemoyenne);

		JButton btnfritegrande = new JButton("<HTML><BODY>Frite<Br>GRANDE</BODY></HTML>");
		btnfritegrande.setBackground(Color.ORANGE);
		btnfritegrande.setBounds(1095, 410, 150, 90);
		contentPane.add(btnfritegrande);

		JLabel lbl_desserts = new JLabel(
				"Desserts__________________________________________________________________________________");
		lbl_desserts.setBounds(10, 511, 640, 14);
		contentPane.add(lbl_desserts);

		JButton btn_sundaevanille = new JButton("<HTML><BODY>Sundae<Br> Vanille</BODY></HTML>");
		btn_sundaevanille.setBackground(Color.PINK);
		btn_sundaevanille.setBounds(10, 530, 130, 90);
		contentPane.add(btn_sundaevanille);

		JButton btn_sundaechoco = new JButton("<HTML><BODY>Sundae<Br> Chocolat</BODY></HTML>");
		btn_sundaechoco.setBackground(Color.PINK);
		btn_sundaechoco.setBounds(145, 530, 130, 90);
		contentPane.add(btn_sundaechoco);

		JButton btn_sundaefraise = new JButton("<HTML><BODY>Sundae<Br> Fraise</BODY></HTML>");
		btn_sundaefraise.setBackground(Color.PINK);
		btn_sundaefraise.setBounds(294, 530, 130, 90);
		contentPane.add(btn_sundaefraise);

		JButton btn_Milshakevanille = new JButton("<HTML><BODY>Milkshake<Br> Vanille</BODY></HTML>");
		btn_Milshakevanille.setBackground(Color.PINK);
		btn_Milshakevanille.setBounds(415, 530, 130, 90);
		contentPane.add(btn_Milshakevanille);

		JButton btn_Milshakebanane = new JButton("<HTML><BODY>Milkshake<Br> Banane</BODY></HTML>");
		btn_Milshakebanane.setBackground(Color.PINK);
		btn_Milshakebanane.setBounds(550, 530, 130, 90);
		contentPane.add(btn_Milshakebanane);

		JButton btn_milkshakecafe = new JButton("<HTML><BODY>Milkshake<Br> Caf\u00E9</BODY></HTML>");
		btn_milkshakecafe.setBackground(Color.PINK);
		btn_milkshakecafe.setBounds(685, 530, 130, 90);
		contentPane.add(btn_milkshakecafe);

		JButton btn_brownies = new JButton("Brownies");
		btn_brownies.setBackground(Color.PINK);
		btn_brownies.setBounds(820, 530, 130, 90);
		contentPane.add(btn_brownies);

		JButton btn_speedosglace = new JButton("SpeedosGalc\u00E9");
		btn_speedosglace.setBackground(Color.PINK);
		btn_speedosglace.setBounds(955, 530, 130, 90);
		contentPane.add(btn_speedosglace);

		JButton btn_compote = new JButton("Compote");
		btn_compote.setBackground(Color.PINK);
		btn_compote.setBounds(1091, 530, 130, 90);
		contentPane.add(btn_compote);

		JButton btn_tranchepomme = new JButton("Tranch_pomme");
		btn_tranchepomme.setBackground(Color.PINK);
		btn_tranchepomme.setBounds(1226, 530, 130, 90);
		contentPane.add(btn_tranchepomme);

		JLabel lbl_sauces = new JLabel(
				"Sauces__________________________________________________________________________________");
		lbl_sauces.setBounds(10, 625, 640, 14);
		contentPane.add(lbl_sauces);

		JButton btn_saucechinoise = new JButton("<HTML><BODY>Sauce<Br> Chinoise</BODY></HTML>");
		btn_saucechinoise.setBackground(new Color(255, 69, 0));
		btn_saucechinoise.setBounds(10, 645, 130, 90);
		contentPane.add(btn_saucechinoise);

		JButton btn_saucecurry = new JButton("<HTML><BODY>Sauce<Br> Curry</BODY></HTML>");
		btn_saucecurry.setBackground(new Color(255, 69, 0));
		btn_saucecurry.setBounds(145, 645, 130, 90);
		contentPane.add(btn_saucecurry);

		JButton btn_saucebarbecue = new JButton("<HTML><BODY>Sauce<Br> Barbecue</BODY></HTML>");
		btn_saucebarbecue.setBackground(new Color(255, 69, 0));
		btn_saucebarbecue.setBounds(280, 645, 130, 90);
		contentPane.add(btn_saucebarbecue);

		JButton btn_sauceketchup = new JButton("<HTML><BODY>Sauce<Br> Ketchup</BODY></HTML>");
		btn_sauceketchup.setBackground(new Color(255, 69, 0));
		btn_sauceketchup.setBounds(415, 645, 130, 90);
		contentPane.add(btn_sauceketchup);

		JButton btn_mayonnaise = new JButton("<HTML><BODY>Sauce<Br> Mayonnaise</BODY></HTML>");
		btn_mayonnaise.setBackground(new Color(255, 69, 0));
		btn_mayonnaise.setBounds(550, 645, 130, 90);
		contentPane.add(btn_mayonnaise);

		JButton btn_sauceorientale = new JButton("<HTML><BODY>Sauce<Br> Orientale</BODY></HTML>");
		btn_sauceorientale.setBackground(new Color(255, 69, 0));
		btn_sauceorientale.setBounds(685, 645, 130, 90);
		contentPane.add(btn_sauceorientale);

		JButton btn_saucehotsalsa = new JButton("<HTML><BODY>Sauce<Br> Hot Salsa</BODY></HTML>");
		btn_saucehotsalsa.setBackground(new Color(255, 69, 0));
		btn_saucehotsalsa.setBounds(820, 645, 130, 90);
		contentPane.add(btn_saucehotsalsa);

		JButton btn_aigredouce = new JButton("<HTML><BODY>Sauce<Br> Aigre douce</BODY></HTML>");
		btn_aigredouce.setBackground(new Color(255, 69, 0));
		btn_aigredouce.setBounds(955, 645, 130, 90);
		contentPane.add(btn_aigredouce);

		JLabel lblCommandeN = new JLabel("Commande n\u00B0 :");
		lblCommandeN.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCommandeN.setBounds(1500, 14, 302, 33);
		contentPane.add(lblCommandeN);

		JLabel lbl_caissier = new JLabel("Caissier :");
		lbl_caissier.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_caissier.setBounds(955, 14, 302, 33);
		contentPane.add(lbl_caissier);

		JButton btn_Menfant1 = new JButton("<HTML><BODY>Menu<Br> Hamburger</BODY></HTML>");
		btn_Menfant1.setBounds(10, 766, 150, 90);
		contentPane.add(btn_Menfant1);

		JButton btn_Menfant2 = new JButton("<HTML><BODY>Menu<Br> Chicken Stick X4</BODY></HTML>");
		btn_Menfant2.setBounds(165, 766, 150, 90);
		contentPane.add(btn_Menfant2);

		JButton btn_Menfant3 = new JButton("<HTML><BODY>Menu<Br> Chickenrun</BODY></HTML>");
		btn_Menfant3.setBounds(320, 766, 150, 90);
		contentPane.add(btn_Menfant3);

		JLabel lbl_menuenfant = new JLabel(
				"Menus enfants_______________________________________________________________________________");
		lbl_menuenfant.setBounds(10, 740, 668, 14);
		contentPane.add(lbl_menuenfant);

		JButton btnXl = new JButton("XL");
		btnXl.setBackground(new Color(128, 128, 128));
		btnXl.setBounds(1265, 65, 96, 90);
		contentPane.add(btnXl);

		JButton btnFille = new JButton("Fille");
		btnFille.setBackground(new Color(219, 112, 147));
		btnFille.setBounds(491, 765, 103, 91);
		contentPane.add(btnFille);

		JButton btn_bPetite = new JButton("Petite");
		btn_bPetite.setBackground(new Color(128, 128, 128));
		btn_bPetite.setBounds(640, 196, 109, 58);
		contentPane.add(btn_bPetite);

		JButton bt_bMoyenne = new JButton("Moyenne");
		bt_bMoyenne.setBackground(Color.GREEN);
		bt_bMoyenne.setBounds(759, 196, 109, 58);
		contentPane.add(bt_bMoyenne);

		JButton btn_BGrande = new JButton("Grande");
		btn_BGrande.setBackground(new Color(128, 128, 128));
		btn_BGrande.setBounds(878, 196, 109, 58);
		contentPane.add(btn_BGrande);

		List<JComponent> listeGroupeBtnTaille = new ArrayList<JComponent>();
		listeGroupeBtnTaille.add(btn_BGrande);
		listeGroupeBtnTaille.add(bt_bMoyenne);
		listeGroupeBtnTaille.add(btn_bPetite);

		JButton btnFermetureDeLa = new JButton("Fermeture de la caisse");
		btnFermetureDeLa.setIcon(new ImageIcon("rss/sortie50.png"));
		btnFermetureDeLa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFermetureDeLa.setBounds(48, 971, 292, 69);
		contentPane.add(btnFermetureDeLa);
		btnFermetureDeLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton btnValdierLaCommande = new JButton("VALIDER LA COMMANDE");
		btnValdierLaCommande.setIcon(new ImageIcon("rss/valider50.png"));
		btnValdierLaCommande.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnValdierLaCommande.setBounds(1563, 955, 324, 77);
		contentPane.add(btnValdierLaCommande);

		JButton btnAnnulerLaCommande = new JButton("ANNULER LA COMMANDE");
		btnAnnulerLaCommande.setIcon(new ImageIcon("rss/cancel50.png"));
		btnAnnulerLaCommande.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAnnulerLaCommande.setBounds(1063, 766, 316, 90);
		contentPane.add(btnAnnulerLaCommande);
		btnAnnulerLaCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lacommande.quantitelisteProduit.clear();
				Lacommande.listeProduit.clear();
				listModel.removeAllElements();
				prix = 0;
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);
			}
		});

		JButton btn_Param = new JButton("parametres");
		btn_Param.setIcon(new ImageIcon("rss/param50.png"));
		btn_Param.setBounds(360, 971, 234, 69);
		contentPane.add(btn_Param);

		JLabel lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon("rss/speedy_burger1.jpg"));
		lbllogo.setBounds(617, 766, 440, 260);
		contentPane.add(lbllogo);

		JLabel lblPrixTotal = new JLabel("Prix Total :");
		lblPrixTotal.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPrixTotal.setBounds(1586, 889, 109, 22);
		contentPane.add(lblPrixTotal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1429, 65, 449, 811);
		contentPane.add(scrollPane);

		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setCellRenderer(new MyListCellRenderer());

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				currentIndex = (list.getSelectedIndex());

			}
		});

		btn_bPetite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStateBtnTaille("petit", btn_bPetite, btn_BGrande, bt_bMoyenne);
			}
		});

		bt_bMoyenne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
			}
		});

		btn_BGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeStateBtnTaille("grand", btn_BGrande, bt_bMoyenne, btn_bPetite);
			}
		});

		btncoca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MenuPressed == 0) {

					Produit leproduit = null;

					if (boutonPetitPressed == 1) {
						leproduit = lesProduits.get(2);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonMoyenPressed == 1) {
						leproduit = lesProduits.get(0);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonGrandPressed == 1) {
						leproduit = lesProduits.get(1);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					}
					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Boisson") == 0) {
						String pTailleMenu = ((Menu) currentMenu).getTaille();
						Produit leproduit = null;

						if (pTailleMenu.equals("Normal")) {
							leproduit = lesProduits.get(0);
						} else if (pTailleMenu.equals("Grand")) {
							leproduit = lesProduits.get(1);
						} else if (pTailleMenu.equals("Petit")) {
							leproduit = lesProduits.get(2);
						}
						((Menu) currentMenu).addProduit(leproduit);
						System.out.println(((Menu) currentMenu).getListeProduit());

						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btnfanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MenuPressed == 0) {
					Produit leproduit = null;

					if (boutonPetitPressed == 1) {
						leproduit = lesProduits.get(3);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonMoyenPressed == 1) {
						leproduit = lesProduits.get(4);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonGrandPressed == 1) {
						leproduit = lesProduits.get(5);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					}
					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Boisson") == 0) {
						String pTailleMenu = ((Menu) currentMenu).getTaille();
						Produit leproduit = null;
						System.out.println(pTailleMenu);
						if (pTailleMenu.equals("Normal")) {
							leproduit = lesProduits.get(4);
						} else if (pTailleMenu.equals("Grand")) {
							leproduit = lesProduits.get(5);
						} else if (pTailleMenu.equals("Petit")) {
							leproduit = lesProduits.get(3);
						}
						((Menu) currentMenu).addProduit(leproduit);
						System.out.println(((Menu) currentMenu).getListeProduit());

						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btnSprite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MenuPressed == 0) {
					Produit leproduit = null;

					if (boutonPetitPressed == 1) {
						leproduit = lesProduits.get(6);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonMoyenPressed == 1) {
						leproduit = lesProduits.get(7);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonGrandPressed == 1) {
						leproduit = lesProduits.get(8);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					}
					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Boisson") == 0) {
						String pTailleMenu = ((Menu) currentMenu).getTaille();
						Produit leproduit = null;

						if (pTailleMenu.equals("Normal")) {
							leproduit = lesProduits.get(7);
						} else if (pTailleMenu.equals("Grand")) {
							leproduit = lesProduits.get(8);
						} else if (pTailleMenu.equals("Petit")) {
							leproduit = lesProduits.get(6);
						}
						((Menu) currentMenu).addProduit(leproduit);
						System.out.println(((Menu) currentMenu).getListeProduit());
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btneau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MenuPressed == 0) {
					Produit leproduit = null;

					if (boutonPetitPressed == 1) {
						leproduit = lesProduits.get(9);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonMoyenPressed == 1) {
						leproduit = lesProduits.get(10);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonGrandPressed == 1) {
						leproduit = lesProduits.get(11);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					}
					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Boisson") == 0) {
						String pTailleMenu = ((Menu) currentMenu).getTaille();
						Produit leproduit = null;

						if (pTailleMenu.equals("Normal")) {
							leproduit = lesProduits.get(10);
						} else if (pTailleMenu.equals("Grand")) {
							leproduit = lesProduits.get(11);
						} else if (pTailleMenu.equals("Petit")) {
							leproduit = lesProduits.get(9);
						}
						((Menu) currentMenu).addProduit(leproduit);
						System.out.println(((Menu) currentMenu).getListeProduit());
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btnbiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MenuPressed == 0) {
					Produit leproduit = null;

					if (boutonPetitPressed == 1) {
						leproduit = lesProduits.get(12);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonMoyenPressed == 1) {
						leproduit = lesProduits.get(13);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					} else if (boutonGrandPressed == 1) {
						leproduit = lesProduits.get(14);
						prix += leproduit.getPrix();
						String s = String.valueOf(prix);
						lblPrixTot.setText(s);
					}
					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Boisson") == 0) {
						String pTailleMenu = ((Menu) currentMenu).getTaille();
						Produit leproduit = null;
						
						if (pTailleMenu.equals("Petit") == false) {
							if (pTailleMenu.equals("Normal")) {
								leproduit = lesProduits.get(13);
							} else if (pTailleMenu.equals("Grand")) {
								leproduit = lesProduits.get(14);
							} 
							((Menu) currentMenu).addProduit(leproduit);
							System.out.println(((Menu) currentMenu).getListeProduit());
							verifMenuComplet(currentMenu);
						}

					
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btncafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(15);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_hamburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(16);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});
		btn_fastburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(17);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_speedyburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(18);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_baconburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(19);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_chickenrun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(20);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_fishspeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(21);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_chickenstick4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(22);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_chickenstick7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(23);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_scleopatre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(24);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_schef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(25);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_spoulet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(26);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_sgrecque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(27);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_fritepetite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(28);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});
		btn_fritemoyenne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(29);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btnfritegrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(30);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_sundaevanille.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(31);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
						System.out.println("test2");
					}

				}

			}
		});

		btn_sundaechoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Produit leproduit = null;
				leproduit = lesProduits.get(32);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_sundaefraise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(33);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_Milshakevanille.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(34);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_Milshakebanane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(35);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_milkshakecafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(36);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_brownies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(37);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_speedosglace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(38);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_compote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(39);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_tranchepomme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;
				leproduit = lesProduits.get(40);

				if (MenuPressed == 0) {
					prix += leproduit.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(leproduit, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);
				} else {
					if (verifMenu(currentMenu, "modele.Dessert") == 0) {
						((Menu) currentMenu).addProduit(leproduit);
						verifMenuComplet(currentMenu);
					} else {
						verifMenuComplet(currentMenu);
					}

				}

			}
		});

		btn_saucechinoise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(41);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_saucecurry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(42);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_saucebarbecue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(43);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_sauceketchup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(44);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_mayonnaise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(45);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_sauceorientale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(46);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_saucehotsalsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(47);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btn_aigredouce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produit leproduit = null;

				leproduit = lesProduits.get(48);
				prix += leproduit.getPrix();
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

				addProduitInCommande(leproduit, Lacommande, listModel);
				changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

			}
		});

		btnXl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuXLPressed = 1;
				btnXl.setBackground(Color.GREEN);

			}
		});

		btnMhamburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(50);
						lesandwich = lesProduits.get(16);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(49);
						lesandwich = lesProduits.get(16);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btnMfastburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(52);
						lesandwich = lesProduits.get(17);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(51);
						lesandwich = lesProduits.get(17);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btnMSpeedyburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(54);
						lesandwich = lesProduits.get(18);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(53);
						lesandwich = lesProduits.get(18);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btnMbaconburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(56);
						lesandwich = lesProduits.get(19);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(55);
						lesandwich = lesProduits.get(19);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btnMchickenburger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(64);
						lesandwich = lesProduits.get(20);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(63);
						lesandwich = lesProduits.get(20);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btnMfishspeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(58);
						lesandwich = lesProduits.get(21);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(57);
						lesandwich = lesProduits.get(21);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btnMcleopatre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(60);
						lesandwich = lesProduits.get(24);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(59);
						lesandwich = lesProduits.get(24);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btnMchickenstick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MenuPressed == 0) {

					Produit leproduit = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					if (MenuXLPressed == 1) {
						leproduit = (Menu) lesProduits.get(62);
						lesandwich = lesProduits.get(23);
						lafrite = lesProduits.get(30);

						MenuXLPressed = 0;
						btnXl.setBackground(Color.GRAY);
					} else {
						leproduit = (Menu) lesProduits.get(61);
						lesandwich = lesProduits.get(22);
						lafrite = lesProduits.get(29);
					}

					unMenu = new Menu(leproduit.getNom(), leproduit.getPrix(), leproduit.getId(), "menu",
							((Menu) leproduit).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btn_Menfant1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MenuPressed == 0) {

					Produit leMenu = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					leMenu = (Menu) lesProduits.get(65);
					lesandwich = lesProduits.get(66);
					lafrite = lesProduits.get(28);

					unMenu = new Menu(leMenu.getNom(), leMenu.getPrix(), leMenu.getId(), "menu",
							((Menu) leMenu).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btn_Menfant2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuPressed == 0) {

					Produit leMenu = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					leMenu = (Menu) lesProduits.get(67);
					lesandwich = lesProduits.get(22);
					lafrite = lesProduits.get(28);

					unMenu = new Menu(leMenu.getNom(), leMenu.getPrix(), leMenu.getId(), "menu",
							((Menu) leMenu).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}
			}
		});

		btn_Menfant3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MenuPressed == 0) {

					Produit leMenu = null;
					Produit lesandwich = null;
					Produit lafrite = null;
					Menu unMenu = null;

					leMenu = (Menu) lesProduits.get(68);
					lesandwich = lesProduits.get(69);
					lafrite = lesProduits.get(28);

					unMenu = new Menu(leMenu.getNom(), leMenu.getPrix(), leMenu.getId(), "menu",
							((Menu) leMenu).getTaille());

					prix += unMenu.getPrix();
					String s = String.valueOf(prix);
					lblPrixTot.setText(s);

					addProduitInCommande(unMenu, Lacommande, listModel);
					changeStateBtnTaille("moyen", bt_bMoyenne, btn_BGrande, btn_bPetite);

					MenuPressed = 1;
					currentMenu = unMenu;

					((Menu) currentMenu).addProduit(lesandwich);
					((Menu) currentMenu).addProduit(lafrite);
				}

			}
		});

		JButton btnSupprProduit = new JButton("SUPPRIMER PRODUIT");
		btnSupprProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantitéDeBase = Lacommande.quantitelisteProduit.get(currentIndex);

				Lacommande.retireQteProduit(currentIndex);
				prix -= Lacommande.getPrix(currentIndex);
				if (quantitéDeBase == 1) {
					Lacommande.quantitelisteProduit.remove(currentIndex);
					Lacommande.listeProduit.remove(currentIndex);
					listModel.remove(currentIndex);
				}
				String s = String.valueOf(prix);
				lblPrixTot.setText(s);

			}
		});
		btnSupprProduit.setIcon(new ImageIcon("Ressources/cancel50.png"));
		btnSupprProduit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupprProduit.setBounds(1063, 866, 316, 90);
		contentPane.add(btnSupprProduit);

	}

	public static void addProduitInCommande(Produit leproduit, modele.Commande lacommande2,
			DefaultListModel listModel) {

		if (lacommande2.listeProduit.contains(leproduit)) { // Si il y a déja le produit, recupérer quantité

			int index = lacommande2.listeProduit.indexOf(leproduit);
			int quantitéDeBase = lacommande2.quantitelisteProduit.get(index);

			lacommande2.ajouteQteProduit(index);

		} else {

			lacommande2.addProduit(leproduit);
			listModel.addElement(leproduit);
		}
	}

	public static void changeStateBtnTaille(String type, JButton buttonActivated, JButton button1, JButton button2) {
		if (type.equals("petit")) {
			boutonPetitPressed = 1;
			boutonMoyenPressed = 0;
			boutonGrandPressed = 0;
		} else if (type.equals("moyen")) {
			boutonMoyenPressed = 1;
			boutonPetitPressed = 0;
			boutonGrandPressed = 0;

		} else if (type.equals("grand")) {
			boutonGrandPressed = 1;
			boutonPetitPressed = 0;
			boutonMoyenPressed = 0;
		}

		buttonActivated.setBackground(Color.GREEN);
		button1.setBackground(Color.GRAY);
		button2.setBackground(Color.GRAY);
	}

	public static int verifMenu(Produit menu, String type) {
		int reponse = 0;

		List<Produit> listeProduitsMenu = ((Menu) menu).getListeProduit();
		for (Produit pProduit : listeProduitsMenu) {
			if (pProduit.getClass().getName().equals(type)) {
				reponse = 1; // Il y a déja un élement de ce type, donc on peut pas le rajouter
			}
		}
		return reponse;
	}

	public static void verifMenuComplet(Produit menu) {

		ArrayList<String> menuAdultes = new ArrayList<String>();
		menuAdultes.add("modele.Boisson");
		menuAdultes.add("modele.Frite");
		menuAdultes.add("modele.Sandwich");

		ArrayList<String> menuEnfant = new ArrayList<String>();
		menuEnfant.add("modele.Boisson");
		menuEnfant.add("modele.Frite");
		menuEnfant.add("modele.Sandwich");
		menuEnfant.add("modele.Dessert");


		String MenuTaille = ((Menu) menu).getTaille();

		if (MenuTaille.equals("Petit")) {

			List<Produit> listeProduitsMenu = ((Menu) menu).getListeProduit();

			for (Produit pProduit : listeProduitsMenu) {
				if (menuEnfant.contains(pProduit.getClass().getName())) {
					menuEnfant.remove(pProduit.getClass().getName());
				}
			}

		} else if (MenuTaille.equals("Normal")) {
			List<Produit> listeProduitsMenu = ((Menu) menu).getListeProduit();
			for (Produit pProduit : listeProduitsMenu) {
				if (menuAdultes.contains(pProduit.getClass().getName())) {
					menuAdultes.remove(pProduit.getClass().getName());
				}
			}
		}

		
		if (menuEnfant.size() == 0 || menuAdultes.size() == 0) {
			currentMenu = null;
			MenuPressed = 0;
		}

	}
}
