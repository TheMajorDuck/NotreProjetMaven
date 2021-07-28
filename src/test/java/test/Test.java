package test;

import java.util.Scanner;

import dao.DAOBatiment;
import dao.DAOCompte;
import dao.DAOPartie;
import metier.Admin;
import metier.Compte;
import metier.Joueur;
import metier.Partie;

public class Test {

	static Compte connected=null;
	static DAOCompte daoCompte = new DAOCompte();
	static DAOPartie daoPartie = new DAOPartie();
	static DAOBatiment daoBatiment = new DAOBatiment();
	
	
	public static int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();

	}
	
	public static double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}
	
	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}
	
	public static void main(String[] args) 
	{	
		menuPrincipal();
	}

	public static void menuPrincipal() {

		System.out.println("\nAppli Building Game");
		System.out.println("1- Se connecter");
		System.out.println("2- Fermer l'appli");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
			case 1 : seConnecter();break;
			case 2 : System.exit(0);break;
		}
		
		menuPrincipal();

	}
	
	public static void seConnecter() {
		String compteCree = saisieString("\nAvez-vous deja cree un compte ? (y/n)");
		
		if(compteCree.equalsIgnoreCase("y"))
		{
			String login = saisieString("\nSaisir login");
			String password = saisieString("Saisir password");
			connected = daoCompte.seConnecter(login, password);

			if(connected instanceof Admin) 
			{
				menuAdmin();
			}
			else if(connected instanceof Joueur) 
			{
				menuJoueur();
			}
			else 
			{
				System.out.println("Identifiants invalides !");
			}
		}
		else
		{
			String login = saisieString("\nSaisissez votre login : ");
			String password = saisieString("\nSaisissez votre mot de passe : ");
			String prenom = saisieString("\nVeuillez indiquez votre prenom : ");
			String nom = saisieString("\nVeuillez indiquez votre nom : ");
			String surnom = saisieString("\nChoisissez le nom sous lequel vous souhaitez �tre reconnu durant la partie : ");
			Joueur j = new Joueur(login, password, prenom, nom, surnom);
			daoCompte.insert(j);
			
			connected = daoCompte.seConnecter(login,password);
			
			if(connected instanceof Admin) 
			{
				menuAdmin();
			}
			else if(connected instanceof Joueur) 
			{
				menuJoueur();
			}
			else 
			{
				System.out.println("Identifiants invalides !");
			}
		}
	}

	public static void menuAdmin() {
		System.out.println("\nMenu Admin");
		System.out.println("1- ");
		System.out.println("2- ");
		System.out.println("3- Se deconnecter");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : ;break;
		case 2 : ;break;
		case 3 : connected=null;menuPrincipal();break;
		}
		menuAdmin();
	}
	
	public static void menuJoueur() {
		System.out.println("\nMenu Joueur");
		System.out.println("1- Demarrer une nouvelle partie");
		System.out.println("2- Reprendre une partie existante");
		System.out.println("3- Se deconnecter");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : nouvPartie();break;
		case 2 : loadPartie();break;
		case 3 : connected=null;menuPrincipal();break;
		}
		menuJoueur();
	}
	
	public static Partie nouvPartie()
	{
		int nbJoueur = saisieInt("\nChoisissez le nombre de joueurs (entre 2 et 4)");
		//int nbTour = saisieInt("\nChoisissez le nombre de tours pour votre partie (entre 5 et 10)");
		int nbTour = 100;
		
		connected.toString();
		
		Partie nouvPartie = new Partie();
		Partie p = daoPartie.insert(nouvPartie);
		p.initPartie(nbJoueur, connected,p);
		daoCompte.ajoutJoueur(p.getId(),connected.getId());
		
		menuPartie(p);
		return p;
	}
	
	public static void menuPartie(Partie p)
	{
		System.out.println("\nVous avez demarre une nouvelle partie de 'Notre Projet' ! ");
		System.out.println("Etes-vous pret a vous entretuer ?");
		System.out.println("1- Oui je vais tous vous demonter !! ");
		System.out.println("2- Laisse-moi quelques minutes stp");
		System.out.println("3- Se deconnecter");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : p.startPartie(p);break;
		case 2 : menuPartie(p);break;
		case 3 : connected=null;menuPrincipal();break;
		}
	}
		
	public static void loadPartie()
	{
		
	}
}
