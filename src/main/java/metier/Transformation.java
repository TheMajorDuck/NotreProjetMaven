package metier;


import java.util.List;

public abstract class Transformation extends Batiment{
	protected Bois b = new Bois(0);
	protected Pierre p = new Pierre(0);
	protected Minerais m = new Minerais(0);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(0);
	protected Fer f = new Fer(0);
	protected Cuivre cu = new Cuivre(0);
	
	
	public Transformation() 
	{
		super();
		cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
	}
	

	public Transformation(String nom, int level, double def){
		super(nom, level, def);
	}
	
	public Transformation(int idCompte, int idPartie,String nom, int level, double def)
	{
		super(idCompte, idPartie,nom, level, def);
	}
	
	public void transformation (Ressource r1, int nb, List <Ressource> re)
	{
	
	}
	protected Batiment upgrade(Batiment bati) 
	{		
		return bati;
	}

	
}
