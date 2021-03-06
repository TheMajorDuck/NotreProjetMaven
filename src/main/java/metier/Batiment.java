package metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Batiment")
public abstract class Batiment {

	protected String nom;
	protected double def;
	protected double att=0;
	protected List <Ressource> cost= new ArrayList <Ressource>();
	protected int level=1;
	protected int idCompte;
	protected int idPartie;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
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
	
	public Batiment(int id, int idCompte, int idPartie,String nom, int level, double def,double att)
	{
		this.id = id;
		this.idCompte = idCompte;
		this.idPartie = idPartie;
		this.nom = nom;
		this.level = level;
		this.def = def;
		this.att = att;
	}
	
	public Batiment(int id, int idCompte, int idPartie,String nom, int level, double def)
	{
		this.id = id;
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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


	public void upgrade() 
	{		
		return;
	}

	protected void downgrade() {

	}
	
	
	public String toString() {		
		return String.format("Nom: "+ this.toStringName() + "  Niveau: " + this.level+ "  Defense: " +this.def + "  Attaque: " +this.att);
	}
	
	public String toStringName() {
		return "Batiment";
	}
		
	public String toStringWithCost() {		
		
		String outputText = String.format("Nom: "+ this.toStringName() + "  Niveau: " + this.level+ "  Defense: " +this.def + "  Attaque: " +this.att + "\n");
		
		for(Ressource ressource :cost){
						
			int costRessource = ressource.getStock();
			
			if(costRessource>0){
				
				String nameRessource = ressource.toStringName();
				outputText += String.format(nameRessource +": "+ costRessource+"  ");
				
			}
		}
		
		return outputText;
	}
	
	
}
