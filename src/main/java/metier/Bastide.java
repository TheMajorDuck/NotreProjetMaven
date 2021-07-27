package metier;

public class Bastide extends Defense {

	public Bastide(){
		super("bastide", 1, 50);
	}
	
	public Bastide(String nom, int level, double def)
	{
		super(nom, level, def);
	}
	
	public Bastide(int idCompte, int idPartie,String nom, int level, double def)
	{
		super(idCompte, idPartie,nom, level, def);
	}
	@Override
	public String toStringName() {
		return "Bastide";
	}
}
