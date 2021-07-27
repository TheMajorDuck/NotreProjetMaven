package metier;

import java.util.ArrayList;
import java.util.List;

public class Mine  extends Production {
	protected Bois b = new Bois(3);
	protected Pierre p = new Pierre(0);
	protected Minerais m = new Minerais(3);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(0);
	protected Fer f = new Fer(0);
	protected Cuivre cu = new Cuivre(0);
	
	private double def;
	
	public Mine() 
	
	{
		cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
		setDef(20);
	}

	public Mine(String nom, int level, double def){
		super(nom, level, def);
	}

	protected Mine upgrade(Mine bati) 
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
		bati.setAtt(bati.getAtt()+0);
		bati.setDef(bati.getDef()+20);
		bati.setCost(tmpCost);
		bati.setLevel(bati.getLevel()+1);
		return bati;
	}
	@Override
	public String toStringName() {
		return "Mine";
	}
}
