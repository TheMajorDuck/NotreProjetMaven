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
	protected List<Ressource> stock = new ArrayList <Ressource>();
	protected List<Batiment> construction = new ArrayList <Batiment>();
	
	private int def = 0;
	private int att = 0;
	private boolean tourEnCours;
	
	static DAOBatiment daoBatiment = new DAOBatiment();
	static DAOPartie daoPartie = new DAOPartie();

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
		this.construction = actuDef();
	}
	
	public Joueur (int id, String login, String password)
	{
		super(id, login, password);
	}
	
	public Joueur (String login, String password, String prenom, String nom, String surnom)
	{
		super(login,password, TypeCompte.Joueur, prenom, nom, surnom);
		Bastide bastide = new Bastide();
		this.construction.add(bastide);
		this.construction = actuDef();
	}

	public Joueur (String login, String password)
	{
		super(login, password);
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
	
	public boolean getTourEnCours() {
		return tourEnCours;
	}

	public void setTourEnCours(boolean tourEnCours) {
		this.tourEnCours = tourEnCours;
	}

	public boolean verification (Batiment bat) // Verification du stock de ressources du joueur permet d'acheter un batiment (renvoie un bool)
	{
		for (Ressource rj : this.stock)
		{
			for (Ressource r: bat.getCost())
			{
				if (r.getClass().getName().equals(rj.getClass().getName()) && r.getStock()>rj.getStock())
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void attaque (Joueur enemi) // Attaque de tous les batiment d'un autre joueur
	{
		double ptAtt = this.att/enemi.getConstruction().size();
		for (Batiment b : enemi.getConstruction())
		{
			b.setDef(b.getDef()-ptAtt);
		}
	}
	
	public void attaque (Joueur enemi, Batiment bat, double valeurAttaque) // Attaque d'un batiment d'un autre joueur
	{
		bat.setDef(bat.getDef()-valeurAttaque);
		
		enemi.setConstruction(enemi.actuDef());
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
	
	/*public void transformation(Transformation trans, Ressource r, int nb)
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
	}*/
	
	public void transformation(String batiment,int nbRessource,String nomRessource)
	{

		if (batiment.equals("Four"))
		{
			for (Batiment b: construction)
			{
				if (b instanceof Four)
				{
					((Four) b).transformation(nomRessource, nbRessource, this.stock);
				}
			}
		}
		else if(batiment.equals("Fonderie"))
		{
			for (Batiment b: construction)
			{
				if (b instanceof Fonderie)
				{
					((Fonderie) b).transformation(nomRessource, nbRessource, this.stock);
				}
			}
		}
		
		
	}
	
	public void constructBat(Batiment bat, Partie p)  // Construction d'un batiment (ajout � la liste/actuAtt/ActuDef/ActuRessources)
	{
		List<Batiment> tmp_constructions = getConstruction();
		Batiment batiment = daoBatiment.ajoutBatiment(this.getId(),p.getId(),bat);
		tmp_constructions.add(batiment);
		setConstruction(tmp_constructions);	
		
		setConstruction(this.actuDef());
		setConstruction(this.actuAtt());

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
		
		System.out.println("\nVous avez obtenu " + bois + " bois, " + pierre + " pierre(s), " + minerais + " minerais !");	
		
		for (Batiment b : construction)
		{
			if(b instanceof Carriere)
			{
				pierre+=3;
				System.out.println("\nVotre carriere vous a rapport� 3 pierres suppl�mentaires ("+pierre+" pierre(s) au total !)\n");	
			}
			else if (b instanceof Mine)
			{
				minerais+=3;
				System.out.println("\nVotre mine vous a rapport� 3 minerais suppl�mentaires ("+minerais+" minerais au total !)\n");	
			}
			else if (b instanceof Scierie)
			{
				bois+=3;
				System.out.println("\nVotre scierie vous a rapport� 3 bois suppl�mentaires ("+bois+" bois au total !)\n");	
			}
		}
		b.actuGain(bois);
		p.actuGain(pierre);
		m.actuGain(minerais);
	}
	
	public void menuJoueur(Partie p){
		
		System.out.printf("%s\n","MENU JOUEUR" + " - " + this.prenom + " " + this.nom + " " + this.surnom);
		System.out.printf("%s\n","1- Construire");
		System.out.printf("%s\n","2- Ameliorer");
		System.out.printf("%s\n","3- Attaquer");
		System.out.printf("%s\n","4- Tranformer ressources");
		System.out.printf("%s\n","5- Fin de tour");
		
		afficheListeRessources();
		
		int choix = saisieInt("Choisir un menu");
		
		
		
		switch(choix) 
		{
			case 1 : menuConstruction(p); break;
			case 2 : menuAmeliorer(p); break; // TODO: a finir en int�grant la m�thode pour upgrader le batiment
			case 3 : menuAttaquer(p);break; // TODO: Menu attaque
			case 4 : menuTransformation(p);break; // TODO: Menu Transformatino
			case 5 : menuFinDeTour(p);break; // TODO: fin de tour
			default : System.out.println("Mauvaise valeur");
		}
		
	}
	
	
	private void menuTransformation(Partie p) {
		boolean batimentTranformationOK=false;
		String batiment;
		int nbRessource=0;
		String nomRessource ="null";
		
		System.out.println("Votre liste de batiment de transformation:");
		
		for (Batiment b : construction)
		{
			if(b instanceof Transformation)
			{
				System.out.println(b.toStringName());
				batimentTranformationOK=true;
			}
		}
		
		if(batimentTranformationOK==false)
		{
			System.out.println("Vous n'avez pas de batiment de construction");
			menuJoueur(p);
		}
		
		batiment = saisieString("Quel batiment souhaitez-vous utiliser?");
		if (batiment.equals("Four")) 
		{
			int i = stock.get(0).getStock();
			if (i<=0) 
			{
				System.out.println("Le four ne peut pas �tre utiliser! Vous n'avez pas de bois!");
			}
			else 
			{
				nomRessource = "charbon";
				nbRessource = saisieInt("Vous avez " + i + "bois, combien voulez-vous en transformer?");
			}	
		}
		else if (batiment.equals("Fonderie"))
		{
			afficheListeRessources();
			nbRessource = saisieInt("Combien de ressources voulez-vous transformer ?");
			nomRessource = saisieString("Quels ressources voulez-vous produire (charbon/gold/fer/cuivre)?");
		}
		transformation(batiment,nbRessource,nomRessource);
	}

	public void menuAmeliorer(Partie p){
		
		System.out.printf("%s\n","MENU AMELIORATION" + " - " + this.prenom + " " + this.nom + " " + this.surnom);
		
		displayOwnedConstWithUpdatePossibilities();
		displayOwnedConstWithUpdateNoPossibilities();
		
		System.out.printf("%s\n","0- Menu precedent");
		int choix = saisieInt("Choisir un batiment a ameliorer (saisir l'index)");
		if(choix==0){
			menuJoueur(p);
		} else {
			Batiment batiment = this.construction.get(choix-1);
			if (batiment instanceof Batiment){
				//construction(batiment); // TODO: call update
				//Batiment newBatiment = batiment.upgrade(batiment);
				//this.construction.set(choix-1, newBatiment);
				
				if(verification(batiment)) {
					
					for (Ressource r : this.stock)	//modification du stock de ressources du joueur en fonction du cout (cf. methode actuAchat de la classe ressources)
					{
						r.actuAchat(batiment.getCost());
					}
					
					batiment.upgrade();
					
					setConstruction(this.actuDef());
					setConstruction(this.actuAtt());
					
					
					
					System.out.println("Un batiment " + batiment.toStringName() + " a ete amelioré(e)");
				} else {
					System.out.println("Pas assez de thune pour ameliorer " + batiment.toStringName() + " !!!!");
				}
				
				

			} else {
				System.out.println("Ceci n'est pas un batiment");
			}
		}

	}
	
	public boolean listeBatimentContains(String testedValue) {

		boolean output = false;
		for (ListeBatiment b : ListeBatiment.values()) {
			if (b.name().equals(testedValue)) {
				output = true;
			} 
		}
		return output;
	}
	
	public void displayOwnedConstWithUpdatePossibilities(){
		
		afficheListeRessources();
		System.out.printf("%s","POSSIBILITE D'AMELIORATION" + "\n");
		
		for (int i = 0; i < this.construction.size(); i++) {
			int idx = i+1;
			Batiment batiment = this.construction.get(i);
			
			if(listeBatimentContains(batiment.toStringName())) {
				ListeBatiment b = ListeBatiment.valueOf(batiment.toStringName());
	    		if(verification(batiment) && b.isAmeliorable()){
					System.out.println(idx + "-" + batiment.toStringWithCost());
					System.out.println("--------------------------------");
				}	
			}
		}
		
		System.out.println("-------------------------------- \n");
	}
	
	
	public void displayOwnedConstWithUpdateNoPossibilities(){
		
		System.out.printf("%s","Liste de vos batiments" + "\n");
		for (Batiment b: this.construction) {
			System.out.println(b.toStringName() +" Niveau: "+ b.getLevel()+"  Def:" +b.getDef()+ "  Att:"+b.getAtt());
		}
	
		//System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
			
		for(Batiment batiment : construction){
			if(verification(batiment)==false){
				System.out.println(batiment.toStringWithCost());
			}
		}
	}
	
	
	public void menuConstruction(Partie p){
		
		
		System.out.printf("%s\n","MENU CONSTRUCTION" + " - " + this.prenom + " " + this.nom + " " + this.surnom);
		System.out.println("---- \n");
		
		displayOwnedConstruction();
		System.out.println("-------------------------------- \n");
		displayConstructionPossibilites();
		displayConstructionNoPossibilites();
		
		System.out.printf("%s\n","0- Menu precedent");
		String choix = saisieString("Choisir un batiment a construire");
		
		if(choix.equals("0")){
			menuJoueur(p);
			//System.out.println(choix);
		} else {
			//System.out.println(choix);
			Batiment batiment = stringToBatiment(choix);	
			//System.out.println(batiment.toStringName());
			if (batiment instanceof Batiment){
				
				//System.out.println(batiment.toStringName());
				if (verification(batiment)) {
					
					//System.out.println(batiment.toStringName());
					constructBat(batiment,p);
					System.out.println("Un batiment " + batiment.toStringName() + " a ete construit \n");

				}
				
			} else {
				System.out.println("Ceci n'est pas un batiment");
			}
		}

		
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
				default: System.out.println("Ceci n'est pas un batiment");break;
			}
			
		
		return batiment;
	}

	public boolean getBatimentAttaque()
	{
		int cpt=0;
		
		for(Batiment b : construction)
		{
			if(b instanceof Attaque)
			{
				if (((Attaque)b).isUsed()==false)
					{
					cpt++;
					}
			}
			else{}
		}

		if(cpt>0)
		{return true;}
		else 
		{return false;}
	}
	
	public double choixBatimentAttaque()
	{
		int cptLigne = -1;
		int cptBatAttaque =0;
		List<Integer> listeLigne= new ArrayList<Integer>();
		double valeurAttaque = 0;
		
		//Affiche la liste des batiments d'attaque et leur used
		System.out.println("Vos batiments d'attaque:");
		for (Batiment b : construction)
		{
			cptLigne++;
			if(b instanceof Attaque)
			{
				cptBatAttaque++;
				listeLigne.add(cptLigne);
				if(((Attaque) b).isUsed())
				{
					System.out.println("Batiment n�" +cptBatAttaque+ ": " + b + " / Batiment d'attaque d�j� utilis�");
				}
				else 
				{
					System.out.println("Batiment n�" +cptBatAttaque+ ": " + b + " / Batiment d'attaque disponible");
				}
			}
		}
		System.out.printf("%s\n","MENU CHOIX DU/DES BATIMENT(S) D'ATTAQUE:");
		System.out.printf("%s\n","1- Attaquer avec tous les batiments disponibles");
		System.out.printf("%s\n","2- Choisir un batiment d'attaque");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
			case 1 : valeurAttaque = attaqueAvecTousBatiments(); break;
			case 2 : valeurAttaque = attaqueAvecUnBatiment(listeLigne); break; 
			default : System.out.println("Mauvaise valeur");
		}
		
		return valeurAttaque;
	}
	
	private double attaqueAvecTousBatiments() {
		double valeurAttaque = 0;
		for (Batiment b : construction)
		{
			if(b instanceof Attaque || (((Attaque) b).isUsed()==false))
			{
				valeurAttaque = b.getAtt();
				((Attaque) b).setUsed(true);
			}
		}
		System.out.println("La valeur total de votre attaque est de " + valeurAttaque + "points");
		return valeurAttaque;
	}
	
	private double attaqueAvecUnBatiment(List<Integer> listeLigne) {
		double valeurAttaque = 0;
		int choix = saisieInt("Avec quel batiment voulez-vous attaquer? (n� du batiment)");
		int ligneBatiment = listeLigne.get(choix-1);
		int cpt = 0;
		
		for (Batiment b : construction)
		{
			if (cpt==ligneBatiment)
			{
				if (((Attaque) b).isUsed()) 
				{
					System.out.println("Ce batiment n'est pas disponible pour attaquer");
					attaqueAvecUnBatiment(listeLigne);
				}
				else 
				{
					valeurAttaque = b.getAtt();
					((Attaque) b).setUsed(true);
					System.out.println("La valeur de votre attaque est de " + valeurAttaque + "points");
				}
				
			}
		}
		
		return valeurAttaque;
	}



	public void displayBatimentAttaque()
	{
		System.out.printf("%s","Liste de vos batiments d'attaque" + "\n");
		//System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att", "used");
		
		for(Batiment batiment : construction){
			if(batiment instanceof Attaque)
			{
				System.out.println(batiment);
			}
		}
	}
	
	
	public void displayOwnedConstruction(){
		
		System.out.printf("%s","Liste de vos batiments" + "\n"); // TODO: "de vos batiment" mais la fonction est appel�e aussi pour afficher les batiments de l'ennemi dans la fonction menuAttaqueChoixBatiment
		//System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
		
		for(Batiment batiment : this.construction){
			System.out.println(batiment);
		}
		
		System.out.println("-------------------------------- \n");
		
	}
	
	public void afficheListeRessources() {
		System.out.println("Ressources disponibles:");
		for (Ressource r: this.stock) {
			System.out.print(r.toStringName() + ": "+r.getStock() + "  ");
		}
		
		System.out.println("\n-------------------------------- \n");
	}
	
	public void displayConstructionPossibilites(){
		
		afficheListeRessources();
		System.out.printf("%s","POSSIBILITE DE CONSTRUCTION" + "\n");
		for (ListeBatiment b : ListeBatiment.values()) { 
    		Batiment batiment = stringToBatiment(b.toString());
    		if(verification(batiment)){
    			System.out.println(batiment.toStringName());
				System.out.println(batiment.toStringWithCost());
				System.out.println("--------------------------------");
			}
		}
		
		System.out.println("-------------------------------- \n");
		
	}
	
	public void displayConstructionNoPossibilites(){
		
		System.out.printf("%s","IMPOSSIBLE DE CONSTRUIRE:" + "\n");
		//System.out.printf("%25s %5s %5s %5s\n", "Nom", "level", "def", "att");
				
		for (ListeBatiment b : ListeBatiment.values()) { 
    		Batiment batiment = stringToBatiment(b.toString());
    		if(verification(batiment)==false){
				System.out.println(batiment.toStringWithCost());
				System.out.println("-------------------------------- \n");
			}
		}
		
		System.out.println("---- \n");
		
	}
	
	
	public void menuAttaquer(Partie p)
	{
		//V�rifie si un batiment d'attaque disponible pour attaquer existe

		if(this.getBatimentAttaque())
		{
			
			for (Joueur j: p.getJoueurs())
			{
				if (j.surnom!=this.surnom)
				{
					j.displayOwnedConstruction();
				}
			}
		
			System.out.printf("%s","MENU ATTAQUER" + " - " + this.prenom + " " + this.nom + " " + this.surnom + "\n");
			System.out.printf("%s","0- Menu precedent");
			System.out.printf("%s","1- Attaquer!!!!!" + "\n");
			int choix = saisieInt("Choisir une option");
		
			switch(choix) 
			{
				case 0: menuJoueur(p) ;break;
				case 1: double valeurAttaque = choixBatimentAttaque();menuAttaqueJoueur(p,valeurAttaque);menuAttaquer(p);break;
				default : System.out.println("Mauvaise valeur");menuAttaquer(p);
			}
			
		}
		else
		{
			System.out.println("Vous n'avez pas de b�timent d'attaque disponible pour attaquer, quel dommage...'");

			menuJoueur(p);
		}
	}

	
	public void menuAttaqueJoueur(Partie p, double valeurAttaque)
	{
		int joueur = saisieInt("Quel joueur souhaites-tu attaquer? (1,2,3,...)");
		Joueur ennemi = p.getJoueurs().get(joueur-1);
				
		System.out.printf("%s\n","MENU ATTAQUER" + " - " + this.prenom + " " + this.nom + " " + this.surnom);

		//System.out.printf("%s\n","0- Menu pr�c�dent"); // ne peut pas revenir en arri�re car batiment attaque choisit et les boolean sont chang�s en used==true
		System.out.printf("%s\n","1- Attaquer tous les  batiments?");
		System.out.printf("%s\n","2- Attaquer un  seul batiment?");

		int choix = saisieInt("Choisir une option");
		
		switch(choix) 
		{
			//case 0: menuAttaquer(p); ;break;
			case 1: attaqueJoueur(ennemi,p,valeurAttaque);break;
			case 2: menuAttaqueChoixBatiment(ennemi,p,valeurAttaque);break;
			default : System.out.println("Mauvaise valeur");
		}
	}
	
	public void menuAttaqueChoixBatiment(Joueur e, Partie p, double valeurAttaque)
	{

		// Affiche liste des batiments du joueur attaqu�
		e.displayOwnedConstruction();

		String nomBat = saisieString("Quel batiment voulez vous attaquer(nom)?");
		int numBat = saisieInt("Le combienti�me de "+ nomBat +"voulez vous attaquer?");
		int i=0;
		Boolean batExiste;
		Batiment bat;
		
		for (Batiment b : e.construction)
		{
			if (b.nom.equals(nomBat)){
				i++;
				batExiste = true;
				if(i==numBat)
				{
					attaque(e, b, valeurAttaque);
				}
			}
		}
		
		if(batExiste = false)
		{

			System.out.println("Le joueur attaqu� ne poss�de pas ce batiment!'");
			menuAttaqueChoixBatiment(e,p,valeurAttaque);
		}
		if (i!=numBat)
		{
			System.out.println("Le num�ro de batiment n'existe pas!'");
			menuAttaqueChoixBatiment(e,p,valeurAttaque);
		}
		 
		menuAttaquer(p);

	}
	
	public void attaqueJoueur(Joueur e, Partie p, double valeurAttaque)
	{
		double nbBatiment = e.construction.size();
		double degatBatiment = (valeurAttaque - valeurAttaque%nbBatiment)/nbBatiment;
		double degatReste= valeurAttaque%nbBatiment;
		double i = 0;
		
		for (Batiment b : e.construction)
		{
			i++;
			if(i<=degatReste) 
			{
				attaque(e, b, degatBatiment+1);
			}
			else 
			{
				attaque(e, b, degatBatiment);
			}
		}
		menuAttaquer(p);
	}
		
	public void menuFinDeTour(Partie p)
	{
		this.setTourEnCours(false);
		System.out.printf("%s","FIN DE TOUR" + " - " + this.prenom + " " + this.nom + " " + this.surnom + "\n");
		System.out.printf("%s","Vous avez effectue votre action de jeu, au prochain joueur de jouer !");
		
		// TO DO : Mettre le boolean des batiments d'attaque � false
		for(Batiment batiment : this.construction){
			if(batiment instanceof Attaque)
			{
				((Attaque) batiment).setUsed(false);
			}
		}
	}	
	
}
