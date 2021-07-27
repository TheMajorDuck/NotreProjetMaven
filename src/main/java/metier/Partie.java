package metier;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dao.*;

public class Partie {

	static DAOCompte daoCompte = new DAOCompte();
	static DAOPartie daoPartie = new DAOPartie();
	static DAOBatiment daoBatiment = new DAOBatiment();
	static int cptPartie=0;

	private int id;
	private int nbrDeTour=100;
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
	
	public int initPartie(int nbJoueur, Compte connected, Partie p)
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
					daoCompte.ajoutJoueur(p.getId(),j.getId());
					
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
					
					Compte joueur = daoCompte.insert(j);
					daoCompte.ajoutJoueur(p.getId(),joueur.getId());
					
					System.out.println("\nBienvenue "+j.getPrenom()+" "+j.getNom()+", vous êtes le Joueur "+(i+1)+" ! ");
					System.out.println("\nDans cette partie vous serez "+j.getSurnom());
					joueurs.add((Joueur)j);
				}
				
			}
				
		}
	
		return cptPartie++;
	}
	
	public void startPartie(Partie p){
		
		p.partieEnCours=true;
		Joueur vainqueur = null;
		
		while(p.partieEnCours){
			for(int i = 1;i<=nbrDeTour;i++)
			{
				for(int j=0;j<joueurs.size();j++)
				{
					Joueur j1 = joueurs.get(j);
					System.out.println("\nTour "+(i)+" - " + j1.getPrenom() + " " + j1.getNom() + " " + j1.getSurnom() + "\n");
					j1.setTourEnCours(true);
					j1.piocherRessources();
					while(j1.getTourEnCours()==true)
					{
						j1.joueTour(p);
					}
					finDePartie(p);savePartie(p);
					if(p.partieEnCours==false)
					{
						vainqueur = finDePartie(p);
						break;
					}
				}
				if(p.partieEnCours==false)
				{
					break;
				}
			}
			if(p.partieEnCours==false)
			{
				break;
			}
		}
		if(p.partieEnCours==false)
		{
			menuFinDePartie(p,vainqueur);
		}
	}
	

	public Joueur finDePartie(Partie p)
	{
		Joueur vainqueur = null;
		for(Joueur joueur : joueurs)
		{
			int somme = 0;
			
			for(int i=0;i<joueurs.size();i++)
			{
				somme+=(joueurs.get(i)).getDef();
			}
			
			if(somme-joueur.getDef()==0)
			{
				p.partieEnCours=false;
				vainqueur = joueur;
			}
			
			else 
			{
				for(Batiment b : joueur.getConstruction())
				{
					if(b.toStringName().equals("Merveille"))
					{
						if(b.getLevel()==5)
						{
							p.partieEnCours=false;
							vainqueur = joueur;
						}
					}
				}
			}
		}
		return vainqueur;
	}
	
	public void menuFinDePartie(Partie p, Joueur vainqueur)
	{
		System.out.println("\n\nEt nous avons notre grand vainqueur : "+vainqueur.getSurnom()+" ("+vainqueur.getPrenom()+" "+vainqueur.getNom()+") !!!\n");
		
		int width = 100;
        int height = 30;

        //BufferedImage image = ImageIO.read(new File("/Users/mkyong/Desktop/logo.jpg"));
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("Victoire", 10, 20);

        //save this image
        //ImageIO.write(image, "png", new File("/users/mkyong/ascii-art.png"));

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "0");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }
	}

	public void savePartie(Partie p)
	{
		for(Joueur j : joueurs)
		{
			File monFichier = new File("partie"+p.getId()+"_joueur"+j.getId()+".txt");		
			
			try(FileOutputStream fos = new FileOutputStream(monFichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) 
			{
				oos.writeObject(daoBatiment.findAllByIdCompteIdPartie(daoPartie.findIdBySurnomJoueur(j.getSurnom()).getId(), daoCompte.findBySurnom(j.getSurnom()).getId()));
				
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void loadPartie(Partie p)
	{
		
	}
	
}
