package metier;

import java.util.ArrayList;
import java.util.List;

public abstract class Defense extends Batiment {
	protected Bois b = new Bois(0);
	protected Pierre p = new Pierre(0);
	protected Minerais m = new Minerais(0);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(0);
	protected Fer f = new Fer(0);
	protected Cuivre cu = new Cuivre(0);
	
	
	public Defense() {
		//cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
	}

	public Defense(String nom, int level, double def){
		super(nom, level, def);
	}
	
	public Defense(int idCompte, int idPartie,String nom, int level, double def)
	{
		super(idCompte, idPartie,nom, level, def);
	}
	
	public void upgrade() 
	{	
		List  <Ressource> tmpCost= new ArrayList<Ressource>();
		tmpCost = this.cost;
		for (Ressource r: tmpCost)
		{
			if (r.getStock()!=0) 
			{
				r.setStock(r.getStock()+3);
			}
		}
		this.setAtt(this.getAtt()+0);
		this.setDef(this.getDef()+20);
		this.setCost(tmpCost);
		this.setLevel(this.getLevel()+1);
		return;
	}
	
	
	
	@Override
	public String toStringName() {
		return "def";
	}
}
