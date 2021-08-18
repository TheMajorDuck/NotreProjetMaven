package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Context {

	private static Context _instance=null;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("notre_projet");
	
	
	private Context() {}
	
	
	public static Context getInstance() 
	{
		if(_instance==null) {_instance=new Context();}
		return _instance;
	}


	public EntityManagerFactory getEmf() {
		return emf;
	}

	
	public void closeEmf() 
	{
		emf.close();
	}
	
	
}
