package metier;

public class IdComptePartie {

private int idCompte;
private int idPartie;

public IdComptePartie(int idCompte,int idPartie) {
	this.idCompte=idCompte;
	this.idPartie=idPartie;
}

public int getIdCompte() {
	return idCompte;
}

public void setIdCompte(int idCompte) {
	this.idCompte = idCompte;
}

public int getIdPartie() {
	return idPartie;
}

public void setIdPartie(int idPartie) {
	this.idPartie = idPartie;
}


}
