package metier;

public abstract class Compte {
	
	protected int id;
	protected String login;
	protected String password;
	protected TypeCompte typeCompte;
	protected String prenom;
	protected String nom;
	protected String surnom;
	
	
	public Compte(int id, String login, String password, TypeCompte typeCompte, String prenom, String nom, String surnom) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
		this.prenom = prenom;
		this.nom = nom;
		this.surnom = surnom;
	}
	
	public Compte(int id, String login, String password, String prenom, String nom, String surnom) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.surnom = surnom;
	}
		
	public Compte(String login, String password, TypeCompte typeCompte, String prenom, String nom, String surnom) {
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
		this.prenom = prenom;
		this.nom = nom;
		this.surnom = surnom;
	}
	
	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Compte(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public TypeCompte getTypeCompte() {
		return typeCompte;
	}
	
	public void setTypeCompte(TypeCompte typeCompte) {
		this.typeCompte = typeCompte;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getSurnom() {
		return surnom;
	}
	
	public void setSurnom(String surnom) {
		this.surnom = surnom;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + ", typeCompte=" + typeCompte
				+ ", prenom=" + prenom + ", nom=" + nom + ", surnom=" + surnom + "]";
	}

}
