package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import metier.*;

public class DAOBatiment implements IDAO<Batiment,Integer>{

	@Override
	public Batiment findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*Table Batiment = > id, nom
				1,four
				2, Mur
	
	Table possession => id, id Partie, id Joueur, nomBatiment,pvRestant
		1, 1, 1 ,Four
		2, 1, 1, Four*/
	
	@Override
	public List<Batiment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Batiment> findAllByIdCompteIdPartie(Integer idCompte, Integer idPartie)
	{
		List<Batiment> batiments=new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM batiment WHERE id_compte=? AND id_partie=?");
			ps.setInt(1,idCompte);
			ps.setInt(2,idPartie);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
					
				String nomBatiment = rs.getString("nom");
				Batiment b = null;
				
				switch(nomBatiment)
				{
					case "bastide" : b = new Bastide(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "carriere" : b = new Carriere(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "catapulte" : b = new Catapulte(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "fonderie" : b = new Fonderie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "forteresse" : b = new Forteresse(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "four" : b = new Four(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "merveille" : b = new Merveille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "mine" : b = new Mine(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "muraille" : b = new Muraille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "scierie" : b = new Scierie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					default : System.out.println("Ceci n'est pas un batiment");
				}
				
				batiments.add(b);
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return batiments;
	}
	
	public List<Batiment> findAllByBatiment(Integer idCompte, Integer idPartie, String nom)
	{
		List<Batiment> batiments=new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM batiment WHERE id_compte=? AND id_partie=? AND nom=?");
			ps.setInt(1,idCompte);
			ps.setInt(2,idPartie);
			ps.setString(3,nom);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{				
				String nomBatiment = rs.getString("nom");
				Batiment b = null;
				
				switch(nomBatiment)
				{
					case "bastide" : b = new Bastide(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "carriere" : b = new Carriere(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "catapulte" : b = new Catapulte(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "fonderie" : b = new Fonderie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "forteresse" : b = new Forteresse(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "four" : b = new Four(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "merveille" : b = new Merveille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "mine" : b = new Mine(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "muraille" : b = new Muraille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "scierie" : b = new Scierie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					default : System.out.println("Ceci n'est pas un batiment");
				}
				
				batiments.add(b);
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return batiments;
	}


	@Override
	public Batiment insert(Batiment b) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO batiment (id_compte, id_partie,nom, niveau, pts_de_defense) VALUES (?,?,?,?,?)");
			ps.setInt(1,b.getIdCompte());
			ps.setInt(2,b.getIdPartie());
			ps.setString(3,b.getNom());
			ps.setInt(4,b.getLevel());
			ps.setDouble(5,b.getDef());
			
			ps.executeUpdate();

			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public Batiment ajoutBatiment(int idCompte, int idPartie, Batiment b) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO batiment (id_compte, id_partie,nom, niveau, pts_de_defense) VALUES (?,?,?,?,?)");
			ps.setInt(1,idCompte);
			ps.setInt(2,idPartie);
			ps.setString(3,b.getNom());
			ps.setInt(4,b.getLevel());
			ps.setDouble(5,b.getDef());
			
			ps.executeUpdate();

			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Batiment update(Batiment b) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("UPDATE batiment SET nom=?, niveau=?, pts_de_defense=?) WHERE id_compte=? AND id_partie=?");
			ps.setString(1,b.getNom());
			ps.setInt(2,b.getLevel());
			ps.setDouble(3,b.getDef());
			ps.setInt(4,b.getIdCompte());
			ps.setInt(5,b.getIdPartie());
			
			ps.executeUpdate();

			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	@Override
	public void delete(Integer id) 
	{
		
	}
	
	public void deleteBatiment(Batiment b) {
	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM batiment WHERE id_partie=? and id_compte=?");

			ps.setInt(1, b.getIdPartie());
			ps.setInt(2, b.getIdCompte());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
	}

}
