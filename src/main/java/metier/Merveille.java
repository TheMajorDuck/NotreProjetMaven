package metier;

import java.util.ArrayList;
import java.util.List;

public class Merveille  extends Batiment{
	
	protected Bois b = new Bois(2);
	protected Pierre p = new Pierre(2);
	protected Minerais m = new Minerais(2);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(2);
	protected Fer f = new Fer(2);
	protected Cuivre cu = new Cuivre(2);
	
	private double def = 100;
	
	public Merveille() 
	
	{
		super();
		cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
	}

	public Merveille(String nom, int level, double def){
		super(nom, level, def);
	}
	
	public Merveille(int idCompte, int idPartie,String nom, int level, double def)
	{
		super(idCompte, idPartie,nom, level, def);
	}
	
	protected Merveille upgrade(Merveille bati) 
	{	
		List  <Ressource> tmpCost= new ArrayList<Ressource>();
		tmpCost = this.cost;
		for (Ressource r: tmpCost)
		{
			if (r.getStock()!=0) 
			{
				r.setStock(r.getStock()+2);
			}
		}
		bati.setAtt(bati.getAtt()+0);
		bati.setDef(bati.getDef()+100);
		bati.setLevel(bati.getLevel()+1);
		bati.setCost(tmpCost);
		return bati;
	}
	
}
