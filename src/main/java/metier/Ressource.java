package metier;


import java.util.List;

public abstract class Ressource {
	
	protected int stock;
	
	// Constructeur
	public Ressource(int stock) {
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Ressource [stock=" + stock + "]";
	}
	//----------------------------------------------------------------------
	// Methodes dev 
	
	public void actuAchat(List <Ressource> cost) {
		
		for (Ressource r: cost) 
		{
			if (r.getClass()==this.getClass()) 
			{
				this.stock-=r.stock;
			}
		}
	}
	
	public void actuGain (int gain)
	{
		this.stock+= gain;
	}
	
	public String toStringName() {
		return "";
	}

}
