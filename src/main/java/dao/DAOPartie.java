package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import com.mysql.jdbc.Statement;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import metier.Partie;

public class DAOPartie implements IDAO<Partie,Integer>{

	@Override
	public Partie findById(Integer idPartie) {
		Partie p=null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM partie WHERE id_partie=?");
			ps.setInt(1, idPartie);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				p = new Partie(rs.getInt("id"),rs.getInt("nbr_de_tour")); // A CHANGER
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public Partie findIdBySurnomJoueur(String surnom) {
		Partie p=null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM partie JOIN joueur ON partie.id = joueur.id_partie JOIN compte ON compte.id=joueur.id_compte WHERE compte.surnom=?");
			ps.setString(1, surnom);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				p = new Partie(rs.getInt("id"),rs.getInt("nbr_de_tour")); // A CHANGER
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Partie> findAll() {
		List<Partie> parties=new ArrayList();
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM partie");

			ResultSet rs = ps.executeQuery();
			Partie p = null;
			
			while(rs.next()) 
			{
				p = new Partie(rs.getInt("id"),rs.getInt("nbr_de_tour"));
				parties.add(p);
			}
			rs.close();
			ps.close();
			conn.close();
			}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return parties;
	}

	@Override
	public Partie insert(Partie p) {
		Partie partie = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO partie (id, nbr_de_tour) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, p.getId());
			ps.setInt(2, p.getNbrDeTour());
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
		
			while(generatedKeys.next())
			{
				partie = new Partie(generatedKeys.getInt(1),p.getNbrDeTour(),p.getJoueurs());
			}
			
			generatedKeys.close();
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return partie;
	
	}

	@Override
	public Partie update(Partie p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("UPDATE partie SET nbr_de_compte=? WHERE id=?");

			ps.setInt(1, p.getNbrDeTour());
			ps.setInt(2, p.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return p;
	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM partie WHERE id=?");

			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
	}
	
}
