package metier;

import java.util.ArrayList;
import java.util.List;

public class Muraille  extends Defense{
	protected Bois b = new Bois(3);
	protected Pierre p = new Pierre(3);
	protected Minerais m = new Minerais(0);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(0);
	protected Fer f = new Fer(3);
	protected Cuivre cu = new Cuivre(0);
	
	private double def;
	
	
	public Muraille() 
	
	{
		setNom("muraille");
		cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
		setDef(100);
	}

	public Muraille(String nom, int level, int def){
		
		super(nom, level, def);
		
	}
	
	public void upgrade() 
	{	
		List<Ressource> tmpCost= new ArrayList<Ressource>();
		tmpCost = this.cost;
		for (Ressource r: tmpCost)
		{
			if (r.getStock()!=0) 
			{
				r.setStock(r.getStock()+3);
			}
		}
		this.setAtt(this.getAtt()+0);
		this.setDef(this.getDef()+100);
		this.setLevel(this.getLevel()+1);
		this.setCost(tmpCost);
		
		return;
	}
	
	@Override
	public String toStringName() {
		return "Muraille";
	}

}
