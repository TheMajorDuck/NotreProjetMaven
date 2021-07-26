package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import dao.*;
import metier.*;


public class Joueur extends Compte{
	
	private Bois b = new Bois(0);
	private Pierre p = new Pierre(0);
	private Minerais m = new Minerais(0);
	private Charbon c = new Charbon(0);
	private Gold g = new Gold(0);
	private Fer f = new Fer(0);
	private Cuivre cu = new Cuivre(0);
	protected List <Ressource> stock= new ArrayList <Ressource>();
	protected List <Batiment> construction= new ArrayList <Batiment>();
	
	private int def=0;
	private int att=0;

	public static int saisieInt(String msg) 
		{
			Scanner sc = new Scanner(System.in);
			System.out.println(msg);
			return sc.nextInt();
		}
		
	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}
	
	public Joueur (int id, String login, String password, String prenom, String nom, String surnom)
	{
		super(id,login,password, TypeCompte.Joueur,prenom, nom, surnom);
		stock.add(b);stock.add(p);stock.add(m);stock.add(c);stock.add(g);stock.add(f);stock.add(cu);
		Bastide bastide = new Bastide();
		this.construction.add(bastide);
		this.construction=actuDef();
	}
	
	public Joueur (int id, String login, String password)
	{
		super(id, login,password);
	}
	
	public Joueur (String login, String password, String prenom, String nom, String surnom)
	{
		super(login,password, TypeCompte.Joueur, prenom, nom, surnom);
	}

	public Joueur (String login, String password)
	{
		super(login,password);
	}
	
	
	public List<Ressource> getStock() {
		return stock;
	}

	public void setStock(List<Ressource> stock) {
		this.stock = stock;
	}

	public List<Batiment> getConstruction() {
		return construction;
	}

	public void setConstruction(List<Batiment> construction) {
		this.construction = construction;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public boolean verification (Batiment bat) // Verification du stock de ressources du joueur permet d'acheter un batiment (renvoie un bool)
	{
		int nbBat = 0;
		int nbJoueur = 0;
		for (Ressource r: bat.getCost())
		{
			if (r.getStock()>0)
			{
				nbBat++;
			}
		}
		for (Ressource rj : this.stock)
		{
			for (Ressource r: bat.getCost())
			{
				if (r.getClass().getName().equals(rj.getClass().getName()) && r.getStock()==rj.getStock())
				{
					nbJoueur++;
				}
			}
		}
		if (nbBat == nbJoueur) {return true;}
		else {return false;}
	}
	
	public void attaque (Joueur enemi) // Attaque de tous les batiment d'un autre joueur
	{
		double ptAtt = this.att/enemi.getConstruction().size();
		for (Batiment b : enemi.getConstruction())
		{
			b.setDef(b.getDef()-ptAtt);
		}
	}
	
	public void attaque (Joueur enemi, Batiment bat) // Attaque d'un batiment d'un autre joueur
	{
		for (Batiment b : enemi.getConstruction())
		{
			if (b.getClass()==bat.getClass()) {b.setDef(b.getDef()-this.att);}
		}
		
	}

	public void attaque (Joueur enemi, Attaque at, Batiment de) //Attaque d'un batiment d'un autre joueur par un batiment d'attaque
	{
		if (at.isUsed()==false)
		{
		for (Batiment b : enemi.getConstruction())
		{
			if (b.getClass()==de.getClass()) {b.setDef(b.getDef()-at.getAtt());}
			at.setUsed(true);
		}
		}else {System.out.println("Ce batiment d'attaque est indisponible");}
				
	}
	
	public void transformation(Transformation trans, Ressource r, int nb)
	{
		if (trans instanceof Four)
		{
			for (Batiment b: construction)
			{
				if (b instanceof Four)
				{
					((Four) b).transformation(r, nb, this.stock);
				}
			}
		}
		else if(trans instanceof Fonderie)
		{
			for (Batiment b: construction)
			{
				if (b instanceof Fonderie)
				{
					((Fonderie) b).transformation(r, nb, this.stock);
				}
			}
		}
		else {
			System.out.println("Ce batiment ne permet pas de transfomer des ressources");
		}
	}
	
	public void construction (Batiment bat)  // Construction d'un batiment (ajout à la liste/actuAtt/ActuDef/ActuRessources)
	{
		this.construction.add(bat);
		this.construction = actuDef();
		this.construction = actuAtt();
		for (Ressource r : this.stock)	//modification du stock de ressources du joueur en fonction du cout (cf. methode actuAchat de la classe ressources)
		{
			r.actuAchat(bat.getCost());
		}
	}
	
	public ArrayList <Batiment> actuAtt()  //Permet d'actualiser les point d'attaque du joueur ainsi que la liste des batiments du joueur (ATTENTION RENVOI UNE LISTE !!)
	{
		ArrayList <Batiment> constru= new ArrayList <Batiment>();
		this.att=0;
		for (Batiment b : construction)
		{
			if (b.getDef()>0) {constru.add(b);}
		}
		for (Batiment b : constru)
		{
			this.att += b.getAtt();
		}
		return constru;
	}
	
	public ArrayList <Batiment> actuDef() //Permet d'actualiser les point de defense du joueur ainsi que la liste des batiments du joueur (ATTENTION RENVOI UNE LISTE !!)
	{
		ArrayList <Batiment> constru= new ArrayList <Batiment>();
		this.def=0;
		for (Batiment b : construction)
		{
			if (b.getDef()>0) {constru.add(b);}
		}
		for (Batiment b : constru)
		{
			this.def += b.getDef();
		}
		return constru;
	}
	
	
	public void joueTour(Partie p){
		
		// TODO: piocher ressseConnecter(login, password)ource
		piocherRessources();
     
		// TODO: choix de jeu menuJoueur (1- construire 2-attaquer 3-fin de tour)
		menuJoueur(p);
	}
	
	public void piocherRessources()
	{
		int bois = 0;
		int pierre = 0;
		int minerais = 0;
		
		for(int i=0; i<10; i++)
		{
			int d = (int)Math.round(Math.random()*2);
			switch(d)
			{
				case 0 : bois++; break;
				case 1 : pierre++;  break;
				case 2 : minerais++;  break;
			}
		}
		
		b.actuGain(bois);
		p.actuGain(pierre);
		m.actuGain(minerais);
		
		System.out.println("vous avez obtenue " + bois + " bois" + pierre + " pierre(s)" + minerais + " minerais!");	
	}
	
	public void menuJoueur(Partie p){
		
		System.out.printf("%s\n","MENU JOUEUR" + " - " + this.prenom + " " + this.nom + " " + this.surnom);
		System.out.printf("%s\n","1- Construire");
		System.out.printf("%s\n","2- Améliorer");
		System.out.printf("%s\n","3- Attaquer");
		System.out.printf("%s\n","4- Fin de tour");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
			case 1 : menuConstruction(p); break;
			case 2 : menuAmeliorer(p); break; // TODO: a finir en intégrant la méthode pour upgrader le batiment
			case 3 : menuAttaquer(p);break; // TODO: Menu attaque
			case 4 : break; // TODO: fin de tour
			default : System.out.println("Mauvaise valeur");
		}
		
		menuJoueur(p);
		
	}
	
	
	public void menuAmeliorer(Partie p){
		
		System.out.printf("%s\n","MENU AMELIORATION" + " - " + this.prenom + " " + this.nom + " " + this.surnom);
		
		displayOwnedConstWithUpdatePossibilities();
		displayOwnedConstWithUpdateNoPossibilities();
		
		System.out.printf("%s\n","0- Menu précédent");
		String choix = saisieString("Choisir un bâtiment à améliorer");
		if(choix.equals("0")){
			menuJoueur(p);
		} else {
			Batiment batiment = stringToBatiment(choix);	
			if (batiment instanceof Batiment){
				//construction(batiment); // TODO: call update
				System.out.println("Un bâtiment " + batiment.getNom() + " a été améliorer");
			} else {
				System.out.println("Ceci n'est pas un batiment");
			}
		}

		menuAmeliorer(p);
		
	}
	
	
	public void displayOwnedConstWithUpdatePossibilities(){
		
		System.out.printf("%s","Liste de vos batiments" + "\n");
		System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
			
		for(Batiment batiment : construction){
			if(verification(batiment)){
				System.out.println(batiment.toStringWithCost());
			}
		}
	}
	
	
	public void displayOwnedConstWithUpdateNoPossibilities(){
		
		System.out.printf("%s","Liste de vos batiments" + "\n");
		System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
			
		for(Batiment batiment : construction){
			if(verification(batiment)==false){
				System.out.println("\u001B[31m" + batiment.toStringWithCost() + "\u001B[0m");
			}
		}
	}
	
	
	public void menuConstruction(Partie p){
		
		
		System.out.printf("%s\n","MENU CONSTRUCTION" + " - " + this.prenom + " " + this.nom + " " + this.surnom);
		
		displayOwnedConstruction();
		displayConstructionPossibilites();
		displayConstructionNoPossibilites();
		
		System.out.printf("%s\n","0- Menu précédent");
		String choix = saisieString("Choisir un bâtiment à construire");
		if(choix.equals("0")){
			menuJoueur(p);
		} else {
			Batiment batiment = stringToBatiment(choix);	
			if (batiment instanceof Batiment){
				construction(batiment);
				System.out.println("Un bâtiment " + batiment.getNom() + " a été construit");
			} else {
				System.out.println("Ceci n'est pas un batiment");
			}
		}

		menuConstruction(p);
		
	}
	
	public static Batiment stringToBatiment(String batimentName){	
		Batiment batiment=null;
		switch(batimentName)
			{
				case "Bastide" : batiment = new Bastide();break;
				case "Carriere" : batiment = new Carriere();break;
				case "Catapulte" : batiment = new Catapulte();break;
				case "Fonderie" : batiment = new Fonderie();break;
				case "Forteresse" : batiment = new Forteresse();break;
				case "Four" : batiment = new Four();break;
				case "Merveille" : batiment = new Merveille();break;
				case "Mine" : batiment = new Mine();break;
				case "Muraille" : batiment = new Muraille();break;
				case "Scierie" : batiment = new Scierie();break;
			}
			
		
		return batiment;
	}

	public boolean getBatimentAttaque()
	{
		int cpt=0;
		
		for(Batiment b : construction)
		{
			if(b instanceof Attaque){cpt++;}
			else{}
		}

		if(cpt>0)
		{return true;}
		else 
		{return false;}
	}
	
	/*public Batiment choixBatimentAttaque()
	{
		
		return batiment;
	}*/
	
	
	public void displayBatimentAttaque()
	{
		System.out.printf("%s","Liste de vos batiments d'attaque" + "\n");
		System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att", "used");
		
		for(Batiment batiment : construction){
			if(batiment instanceof Attaque)
			{
				System.out.println(batiment);
			}
		}
	}
	
	
	public void displayOwnedConstruction(){
		
		System.out.printf("%s","Liste de vos batiments" + "\n");
		System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
		
		for(Batiment batiment : construction){
			System.out.println(batiment);
		}
	}
	
	public void displayConstructionPossibilites(){
		
		System.out.printf("%s","Liste des possibilité de construction" + "\n");
		System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
		
		for (ListeBatiment b : ListeBatiment.values()) { 
			System.out.println(b);
    		Batiment batiment = stringToBatiment(b.toString());
    		System.out.println(batiment.getCost());
    		if(verification(batiment)){
				System.out.println(batiment.toStringWithCost());
			}
		}
		
	}
	
	public void displayConstructionNoPossibilites(){
		
		System.out.printf("%s","Liste des possibilité de construction" + "\n");
		System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
				
		for (ListeBatiment b : ListeBatiment.values()) { 
    		Batiment batiment = stringToBatiment(b.toString());
    		if(verification(batiment)==false){
				System.out.println("\u001B[31m" + batiment.toStringWithCost() + "\u001B[0m");
			}
		}
		
	}
	
	
	public void menuAttaquer(Partie p)
	{
		// TO DO : Afficher liste batiments autres joueurs
		
		if(this.getBatimentAttaque())
		{
			p.getJoueurs();
		
			for (Joueur j: p.getJoueurs())
			{
				if (j.surnom!=this.surnom)
				{
					j.displayOwnedConstruction();
				}
			}
		
			System.out.printf("%s","MENU ATTAQUER" + " - " + this.prenom + " " + this.nom + " " + this.surnom + "\n");
			System.out.printf("%s","0- Menu précédent");
			System.out.printf("%s","1- Attaquer!!!!!" + "\n");
			int choix = saisieInt("Choisir une option");
		
			switch(choix) 
			{
				case 0: menuJoueur(p) ;break;
				case 1: /*choixBatimentAttaque()*/;menuAttaqueJoueur(p);break;
				default : System.out.println("Mauvaise valeur");
			}
			menuAttaquer(p);
		}
		else
		{
			System.out.println("Vous n'avez pas de bâtiment d'attaque, quel dommage...'");
			menuJoueur(p);
		}
	}

	
	public void menuAttaqueJoueur(Partie p)
	{
		int joueur = saisieInt("Quel joueur souhaites-tu attaquer? (1,2,3,...)");
		Joueur ennemi = p.getJoueurs().get(joueur-1);
				
		System.out.printf("%s\n","MENU ATTAQUER" + " - " + this.prenom + " " + this.nom + " " + this.surnom);
		System.out.printf("%s\n","0- Menu précédent");
		System.out.printf("%s\n","1- Attaquer un  batiment?");
		System.out.printf("%s\n","2- Attaquer tous les  batiments?");
		int choix = saisieInt("Choisir une option");
		
		switch(choix) 
		{
			case 0: menuAttaquer(p); ;break;
			case 1: menuAttaqueJoueur(ennemi);break;
			case 2: menuAttaqueChoixBatiment(ennemi,p);break;
			default : System.out.println("Mauvaise valeur");
		}
	}
	
	public void menuAttaqueChoixBatiment(Joueur j, Partie p)
	{
		// Affiche liste des batiments du joueur attaqué
		j.displayOwnedConstruction();
		String nomBat = saisieString("Quel batiment voulez vous attaquer(nom)?");
		int numBat = saisieInt("Le combientième de "+ nomBat +"voulez vous attaquer?");
		int i=0;
		Boolean batOk;
		Batiment bat;
		
		for (Batiment b : j.construction)
		{
			if (b.nom.equals(nomBat)){
				i++;
				batOk = true;
				if(i==numBat)
				{
					bat = b;
					attaque(j, bat);
				}
			}
		}
		
		if(batOk = false)
		{
			System.out.println("Le joueur attaqué ne possède pas ce batiment!'");
			menuAttaqueChoixBatiment(j,p);
		}
		if (i!=numBat)
		{
			System.out.println("Le numéro de batiment n'existe pas!'");
			menuAttaqueChoixBatiment(j,p);
		}
		 
		menuAttaqueJoueur(p);
	}
	
	public void menuAttaqueJoueur(Joueur j)
	{
		// TO DO : Afficher un menu pour demander au joueur avec quel batiment il souhaite attaquer
		
	}
		
	public void menuFinDeTour(Partie p)
	{
		System.out.printf("%s","FIN DE TOUR" + " - " + this.prenom + " " + this.nom + " " + this.surnom + "\n");
		System.out.printf("%s","Vous avez effectué votre action de jeu, au prochain joueur de jouer !");
		
		// TO DO : Afficher le récapitulatif de l'action réalisée par le joueur
	}	
	
}
