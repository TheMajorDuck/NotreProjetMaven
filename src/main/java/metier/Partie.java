package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dao.*;

public class Partie {

	static DAOCompte daoCompte = new DAOCompte();

	private int id;
	private int nbrDeTour;
	private boolean partieEnCours;
	private List<Joueur> joueurs= new ArrayList<Joueur>();
	
	public Partie(int id, int nbrDeTour)
	{
		this.id = id;
		this.nbrDeTour = nbrDeTour;
	}
	
	public Partie(int id, int nbrDeTour, List<Joueur> joueurs)
	{
		this.id = id;
		this.nbrDeTour = nbrDeTour;
		this.joueurs = joueurs;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getNbrDeTour() {
		return nbrDeTour;
	}

	public void setNbrDeTour(int nbrDeTour) {
		this.nbrDeTour = nbrDeTour;
	}	
	
	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}
	
	public void initPartie(int nbJoueur, Compte connected)
	{
		for(int i = 0;i<nbJoueur;i++)
		{
			if(i == 0)
			{
				System.out.println("\nJoueur 1 : ");
				System.out.println("\nBienvenue "+connected.getPrenom()+" "+connected.getNom()+", vous êtes le Joueur 1, quelle chance ! ");
				System.out.println("\nDans cette partie vous serez "+connected.getSurnom());
				joueurs.add((Joueur)connected);
			}	
			else
			{
				System.out.println("\nJoueur "+(i+1)+" : ");
				String compteCree = saisieString("Ce joueur a-t-il déjà un compte ? (y/n)");
				
				if(compteCree.equals("y"))
				{
					String surnomJ = saisieString("Veuillez indiquer le surnom du joueur :");
					Compte j = daoCompte.findBySurnom(surnomJ);
					System.out.println("\nBienvenue "+j.getPrenom()+" "+j.getNom()+", vous êtes le Joueur "+(i+1)+" ! ");
					System.out.println("\nDans cette partie vous serez "+j.getSurnom());
					joueurs.add((Joueur)j);
				}
				else
				{
					String login = saisieString("\nSaisissez votre login : ");
					String password = saisieString("\nSaisissez votre mot de passe : ");
					String prenom = saisieString("\nVeuillez indiquez votre prenom : ");
					String nom = saisieString("\nVeuillez indiquez votre nom : ");
					String surnom = saisieString("\nChoisissez le nom sous lequel vous souhaitez être reconnu durant la partie : ");
					Joueur j = new Joueur(login, password, prenom, nom, surnom);
					daoCompte.insert(j);
					System.out.println("\nBienvenue "+j.getPrenom()+" "+j.getNom()+", vous êtes le Joueur "+(i+1)+" ! ");
					System.out.println("\nDans cette partie vous serez "+j.getSurnom());
					joueurs.add((Joueur)j);
				}
				
			}
				
		}
	
	}
		
	
	
	
	public void startPartie(Partie p){
		
		partieEnCours=true;		
		
		while(partieEnCours){
			for(int i = 1;i<=nbrDeTour;i++)
			{
				for(int j=0;j<joueurs.size();j++)
				{
					Joueur j1 = joueurs.get(j);
					System.out.println("\nDébut de tour de "+j1.getSurnom());
					j1.setTourEnCours(true);
					while(j1.getTourEnCours()==true)
					{
						j1.joueTour(p);
					}
					System.out.println("\nTour terminé pour "+j1.getSurnom());
				}
				
			}
		}
	}
	
	
	
}
