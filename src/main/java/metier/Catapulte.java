package metier;

import java.util.ArrayList;
import java.util.List;

public class Catapulte extends Attaque {
	protected Bois b = new Bois(3);
	protected Pierre p = new Pierre(0);
	protected Minerais m = new Minerais(3);
	protected Charbon c = new Charbon(0);
	protected Gold g = new Gold(3);
	protected Fer f = new Fer(0);
	protected Cuivre cu = new Cuivre(0);
	
	protected double att;
	protected double def;
	
	public Catapulte() 
	
	{
		cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
		setDef(20);
		setAtt(20);
	}

	public Catapulte(String nom, int level, double def){
		super(nom, level, def);
	}
	
	public Catapulte(int idCompte, int idPartie,String nom, int level, double def)
	{
		super(idCompte, idPartie,nom, level, def);
	}

	public Bois getB() {
		return b;
	}

	public void setB(Bois b) {
		this.b = b;
	}

	public Pierre getP() {
		return p;
	}

	public void setP(Pierre p) {
		this.p = p;
	}

	public Minerais getM() {
		return m;
	}

	public void setM(Minerais m) {
		this.m = m;
	}

	public Charbon getC() {
		return c;
	}

	public void setC(Charbon c) {
		this.c = c;
	}

	public Gold getG() {
		return g;
	}

	public void setG(Gold g) {
		this.g = g;
	}

	public Fer getF() {
		return f;
	}

	public void setF(Fer f) {
		this.f = f;
	}

	public Cuivre getCu() {
		return cu;
	}

	public void setCu(Cuivre cu) {
		this.cu = cu;
	}
	//-----------------------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------------------
	protected Catapulte upgrade(Catapulte bati) 
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
		bati.setAtt(bati.getAtt()+50);
		bati.setDef(bati.getDef()+20);
		bati.setCost(tmpCost);
		bati.setLevel(bati.getLevel()+1);
		return bati;
	}
	@Override
	public String toStringName() {
		return "Catapulte";
	}
}
