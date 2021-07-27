package metier;

import java.util.ArrayList;
import java.util.List;

public abstract class Batiment {

	protected String nom;
	protected double def;
	protected double att=0;
	protected List <Ressource> cost= new ArrayList <Ressource>();
	protected int level=1;
	protected int idCompte;
	protected int idPartie;
	
	protected Bois b = new Bois(0);
	protected Pierre p = new Pierre(0);
	protected Minerais m = new Minerais(0);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(0);
	protected Fer f = new Fer(0);
	protected Cuivre cu = new Cuivre(0);
	
	
	public Batiment() {
		//cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
	}
	
	public Batiment(String nom, int level, double def)
	{
		this.nom = nom;
		this.level = level;
		this.def = def;
	}
	
	public Batiment(int idCompte, int idPartie,String nom, int level, double def)
	{
		this.idCompte = idCompte;
		this.idPartie = idPartie;
		this.nom = nom;
		this.level = level;
		this.def = def;
	}
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public double getDef() {
		return def;
	}


	public void setDef(double d) {
		this.def = d;
	}
	
	public double getAtt() {
		return att;
	}


	public void setAtt(double att) {
		this.att = att;
	}

	public List<Ressource> getCost() {
		return cost;
	}


	public void setCost(List<Ressource> cost) {
		this.cost = cost;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}

	public int getIdPartie() {
		return idPartie;
	}


	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

	public int getIdCompte() {
		return idCompte;
	}


	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}


	protected Batiment upgrade(Batiment bati) 
	{		
		return bati;
	}

	protected void downgrade() {

	}
	
	
	public String toString() {		
		return String.format("Nom: "+ this.nom + "  Niveau: " + this.level+ "  Defense: " +this.def + "  Attaque: " +this.att);
	}
	
	public String toStringName() {
		return "Batiment";
	}
		
	public String toStringWithCost() {		
		
		String outputText = String.format("Nom: "+ this.toStringName() + "  Niveau: " + this.level+ "  Defense: " +this.def + "  Attaque: " +this.att);
		
		for(Ressource ressource :cost){
						
			int costRessource = ressource.getStock();
			
			if(costRessource>0){
				
				String nameRessource = ressource.toStringName();
				outputText += String.format("\n Cout en "+ nameRessource +": "+ costRessource);
				
			}
		}
		
		return outputText;
	}
	
	
}
