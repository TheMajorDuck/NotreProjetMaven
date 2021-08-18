package metier;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public abstract class Ressource {
	
	protected int stock;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	// Constructeur
	
	public Ressource(int stock) {
		this.stock = stock;
	}
	
	public Ressource() {
	}
	//getters & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
				this.setStock(this.stock-r.stock);
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
