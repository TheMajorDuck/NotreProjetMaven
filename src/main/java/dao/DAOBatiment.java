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
					case "Bastide" : b = new Bastide(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Carriere" : b = new Carriere(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Catapulte" : b = new Catapulte(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Fonderie" : b = new Fonderie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Forteresse" : b = new Forteresse(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Four" : b = new Four(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Merveille" : b = new Merveille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Mine" : b = new Mine(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Muraille" : b = new Muraille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Scierie" : b = new Scierie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
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
					case "Bastide" : b = new Bastide(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Carriere" : b = new Carriere(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Catapulte" : b = new Catapulte(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Fonderie" : b = new Fonderie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Forteresse" : b = new Forteresse(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Four" : b = new Four(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Merveille" : b = new Merveille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Mine" : b = new Mine(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Muraille" : b = new Muraille(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
					case "Scierie" : b = new Scierie(rs.getString("nom"), rs.getInt("niveau"), rs.getInt("pts_de_defense"));break;
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
		Batiment batiment = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO batiment (id_compte, id_partie,nom, niveau, pts_de_defense, pts_d_attaque) VALUES (?,?,?,?,?,?)");
			ps.setInt(1,idCompte);
			ps.setInt(2,idPartie);
			ps.setString(3,b.getNom());
			ps.setInt(4,b.getLevel());
			ps.setDouble(5,b.getDef());
			ps.setDouble(6,b.getAtt());
			
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			
			while(generatedKeys.next())
			{
				String nomBatiment = b.toStringName();
				
				switch(nomBatiment)
				{
					case "Bastide" : batiment = new Bastide(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Carriere" : batiment = new Carriere(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Catapulte" : batiment = new Catapulte(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef(),b.getAtt());break;
					case "Fonderie" : batiment = new Fonderie(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Forteresse" : batiment = new Forteresse(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Four" : batiment = new Four(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Merveille" : batiment = new Merveille(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Mine" : batiment = new Mine(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Muraille" : batiment = new Muraille(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					case "Scierie" : batiment = new Scierie(generatedKeys.getInt(1),idCompte,idPartie,b.getNom(),b.getLevel(),b.getDef());break;
					default : System.out.println("Ceci n'est pas un batiment");
				}
				
			}
			
			generatedKeys.close();

			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return batiment;
	}

	@Override
	public Batiment update(Batiment b) {
		if(b.getDef()>0)
		{
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("UPDATE batiment SET nom=?, niveau=?, pts_de_defense=? WHERE id=?");
			ps.setString(1,b.toStringName().toLowerCase());
			ps.setInt(2,b.getLevel());
			ps.setDouble(3,b.getDef());
			ps.setInt(4,b.getId());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		}
		else
		{
			deleteBatiment(b);
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
			PreparedStatement ps = conn.prepareStatement("DELETE FROM batiment WHERE id=?");

			ps.setInt(1, b.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
	}

}
