package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import metier.*;

public class DAOCompte implements IDAO<Compte,Integer>{

	@Override
	public Compte findById(Integer id) {
		Compte c=null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("admin"))
				{
					c = new Admin(rs.getInt("id"),rs.getString("login"),rs.getString("password"), rs.getString("prenom"), rs.getString("nom"), rs.getString("surnom"));
				}
				else if(rs.getString("type_compte").equals("joueur"))
				{
					c = new Joueur(rs.getInt("id"),rs.getString("login"),rs.getString("password"), rs.getString("prenom"), rs.getString("nom"), rs.getString("surnom"));
				}
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public Compte findBySurnom(String surnom) {
		Compte c=null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte WHERE surnom=?");
			ps.setString(1, surnom);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("admin"))
				{
					c = new Admin(rs.getInt("id"),rs.getString("login"),rs.getString("password"), rs.getString("prenom"), rs.getString("nom"), rs.getString("surnom"));
				}
				else if(rs.getString("type_compte").equals("joueur"))
				{
					c = new Joueur(rs.getInt("id"),rs.getString("login"),rs.getString("password"), rs.getString("prenom"), rs.getString("nom"), rs.getString("surnom"));
				}
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Compte> findAll() {
		List<Compte> comptes=new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte");

			ResultSet rs = ps.executeQuery();
			Compte c = null;

			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("admin"))
				{
					c = new Admin(rs.getInt("id"),rs.getString("login"),rs.getString("password"), rs.getString("prenom"), rs.getString("nom"), rs.getString("surnom"));
				}
				else if(rs.getString("type_compte").equals("joueur"))
				{
					c = new Joueur(rs.getInt("id"),rs.getString("login"),rs.getString("password"), rs.getString("prenom"), rs.getString("nom"), rs.getString("surnom"));
				}
				comptes.add(c);
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return comptes;
	}

	@Override
	public Compte insert(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO compte (login, password, type_compte, prenom, nom, surnom) VALUES (?,?,?,?,?,?)");

			ps.setString(1, c.getLogin());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getTypeCompte().toString());
			ps.setString(4, c.getPrenom());
			ps.setString(5, c.getNom());
			ps.setString(6, c.getSurnom());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return c;

	}

	@Override
	public Compte update(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("UPDATE compte SET login=?,password=?,type_compte=?, prenom=?, nom=?, surnom=? WHERE id=?");

			ps.setString(1, c.getLogin());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getTypeCompte().toString());
			ps.setString(4, c.getPrenom());
			ps.setString(5, c.getNom());
			ps.setString(6, c.getSurnom());
			ps.setInt(7, c.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return c;
	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM compte WHERE id=?");

			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}

	public static Compte seConnecter(String login,String password) 
	{
		Compte c=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
 
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte WHERE login=? and password=?");
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("admin")) 
				{
					 c = new Admin(rs.getInt("id"), rs.getString("login"),rs.getString("password"));
				}
				else if(rs.getString("type_compte").equals("joueur"))
				{
					 c = new Joueur(rs.getInt("id"), rs.getString("login"),rs.getString("password"), rs.getString("prenom"), rs.getString("nom"), rs.getString("surnom"));
				}
			}
			
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public void ajoutJoueur(int idPartie, int idCompte) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO joueur (id_partie,id_compte) VALUES (?,?)");

			ps.setInt(1, idPartie);
			ps.setInt(2, idCompte);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
	}
	
}

