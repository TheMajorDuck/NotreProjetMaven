package metier;

public class Admin extends Compte{

public Admin(String login, String password) {
		super(login, password);
	}

	public Admin(int id,String login, String password) {
		super(id,login, password);
	}
	
	public Admin (int id, String login, String password, String prenom, String nom, String surnom)
	{
		super(id,login,password,TypeCompte.Admin, prenom, nom, surnom);
	}
	
	public Admin (String login, String password, String prenom, String nom, String surnom)
	{
		super(login,password,TypeCompte.Admin, prenom, nom, surnom);
	}
		
	@Override
	public String toString() {
		return "Admin [id=" + id + ", login=" + login + ", password=" + password + "]";

}
}
