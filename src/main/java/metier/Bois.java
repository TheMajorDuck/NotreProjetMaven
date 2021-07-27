package metier;


import java.util.List;

public class Bois extends Ressource{

	
	@Override
	public String toStringName() {
		return "Bois";
	}


	public Bois(int stock) {
		super(stock);
		// TODO Auto-generated constructor stub
	}


	@Override
	public int getStock() {
		// TODO Auto-generated method stub
		return super.getStock();
	}

	@Override
	public void setStock(int stock) {
		// TODO Auto-generated method stub
		super.setStock(stock);
	}

	@Override
	public void actuAchat(List<Ressource> cost) {
		// TODO Auto-generated method stub
		super.actuAchat(cost);
	}

	@Override
	public void actuGain (int gain) {
		// TODO Auto-generated method stub
		super.actuGain(gain);
	}

	

}
