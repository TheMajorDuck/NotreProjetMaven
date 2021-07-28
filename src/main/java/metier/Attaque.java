package metier;

public abstract class Attaque extends Batiment{

	protected boolean used = false;
	
	protected Bois b = new Bois(0);
	protected Pierre p = new Pierre(0);
	protected Minerais m = new Minerais(0);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(0);
	protected Fer f = new Fer(0);
	protected Cuivre cu = new Cuivre(0);
	
	public Attaque(){
	}
	
	public Attaque(String nom, int level, double def){
		super(nom, level, def);
	}
	
	public Attaque(int id,int idCompte, int idPartie,String nom, int level, double def, double att)
	{
		super(id,idCompte, idPartie,nom, level, def,att);
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	protected Batiment upgrade(Batiment bati) 
	{		
		return bati;
	}
	@Override
	public String toStringName() {
		return "Attaque";
	}
	
}
