package metier;


import java.util.List;

public class Fonderie  extends Transformation {
	protected Bois b = new Bois(0);
	protected Pierre p = new Pierre(3);
	protected Minerais m = new Minerais(0);
	protected Charbon c = new Charbon(6);
	protected Gold g = new Gold(0);
	protected Fer f = new Fer(0);
	protected Cuivre cu = new Cuivre(0);
	
	
	public Fonderie() 
	
	{
		setNom("fonderie");
		cost.add(b);cost.add(p);cost.add(m);cost.add(c);cost.add(g);cost.add(f);cost.add(cu);
		setDef(20);
	}

	public Fonderie(String nom, int level, double def){
		super(nom, level, def);
	}

	public Fonderie(int id,int idCompte, int idPartie,String nom, int level, double def)
	{
		super(id,idCompte, idPartie,nom, level, def);
	}
	@Override
	public void transformation (String r1, int nb,  List <Ressource> re) // r1 -> la ressource que l'on veut obtenir
	{															// nb -> la quantité de charbon que l'on veut obtenir
		if (r1.equalsIgnoreCase("Charbon"))								// j -> le joueur qui opère la transformation
		{
			for (Ressource r : re)
			{
				if (r instanceof Bois && r.getStock()>= nb)
				{
					r.setStock(r.getStock()-nb);
					for (Ressource r2 : re)
					{
						if (r2 instanceof Charbon)
						{
							r2.setStock(nb+r2.getStock());
						}
					}
				}
				else if (r instanceof Bois && r.getStock()< nb) 
				{
					System.out.println("Vous n'avez pas assez de bois");
				}
			}
		}
		else if(r1.equalsIgnoreCase("gold") || r1.equalsIgnoreCase("fer") || r1.equalsIgnoreCase("cuivre"))
		{

			for (Ressource r : re)
			{
				if (r instanceof Minerais && r.getStock()>= nb)
				{
					r.setStock(r.getStock()-nb);
					for (Ressource r2 : re)
					{
						if (r2 instanceof Gold && r1.equalsIgnoreCase("gold"))
						{
							r2.setStock(nb+r2.getStock());
						}
						if (r2 instanceof Fer && r1.equalsIgnoreCase("fer"))
						{
							r2.setStock(nb+r2.getStock());
						}
						if (r2 instanceof Cuivre && r1.equalsIgnoreCase("cuivre"))
						{
							r2.setStock(nb+r2.getStock());
						}
					}
				}
				else if (r instanceof Minerais && r.getStock()< nb) {System.out.println("Vous n'avez pas assez de minerais");}
			}

		}else {System.out.println("\n Ce batiment n'est pas une pierre philosophale !");}
	}

	@Override
	public String toStringName() {
		return "Fonderie";
	}

}
