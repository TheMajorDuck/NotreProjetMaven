package metier;

public enum ListeBatiment {
	
	Scierie(true),Muraille(true),Mine(true),Carriere(true),Merveille(true),Four(false),Forteresse(true),Fonderie(false),Catapulte(true);
	
	private boolean ameliorable;

	
	private ListeBatiment(boolean ameliorable) {
		this.ameliorable = ameliorable;
	}

	public boolean isAmeliorable() {
		return ameliorable;
	}

	public void setAmeliorable(boolean ameliorable) {
		this.ameliorable = ameliorable;
	}
	
	
}
