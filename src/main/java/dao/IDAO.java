package dao;

import java.util.List;

public interface IDAO<T,K>{
	
	String urlBDD="jdbc:mysql://localhost:3306/notre_projet";
	String loginBDD="root";
	String passwordBDD="";
	
	public T findById(K id);
	
	public List<T> findAll();
	
	public T insert(T o);
	
	public T update(T o);
	
	public void delete(K id);
}
