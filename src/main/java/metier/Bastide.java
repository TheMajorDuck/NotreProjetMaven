package metier;

import javax.persistence.Entity;

@Entity
public class Bastide extends Defense {
	
	public Bastide(){
		setNom("bastide");
		setLevel(1);
		setDef(50);
	}
	
	public Bastide(String nom, int level, double def)
	{
		super(nom, level, def);
	}
	
	public Bastide(int id,int idCompte, int idPartie,String nom, int level, double def)
	{
		super(id,idCompte, idPartie,nom, level, def);
	}
	
	@Override
	public String toStringName() {
		return "Bastide";
	}
}
