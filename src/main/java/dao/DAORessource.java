package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Bastide;
import metier.Batiment;
import metier.Bois;
import metier.Carriere;
import metier.Catapulte;
import metier.Charbon;
import metier.Cuivre;
import metier.Fer;
import metier.Fonderie;
import metier.Forteresse;
import metier.Four;
import metier.Gold;
import metier.Merveille;
import metier.Mine;
import metier.Minerais;
import metier.Muraille;
import metier.Pierre;
import metier.Ressource;
import metier.Scierie;

public class DAORessource implements IDAO<Ressource,Integer> {

	@Override
	public Ressource findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Ressource> findAllByIdCompteIdPartie(Integer idCompte, Integer idPartie)
	{
		List<Ressource> ress =new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ressource WHERE id_compte=? AND id_partie=?");
			ps.setInt(1,idCompte);
			ps.setInt(2,idPartie);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
					
				String nomRessource = rs.getString("nom");
				Ressource r = null;
				
				switch(nomRessource)
				{
					case "Bois" : r = new Bois(rs.getInt("quantite"));break;
					case "Charbon" : r= new Charbon(rs.getInt("quantite"));break;
					case "Cuivre" : r = new Cuivre(rs.getInt("quantite"));break;
					case "Fer" : r = new Fer(rs.getInt("quantite"));break;
					case "Gold" : r = new Gold(rs.getInt("quantite"));break;
					case "Minerais" : r = new Minerais(rs.getInt("quantite"));break;
					case "Pierre" : r = new Pierre(rs.getInt("quantite"));break;
					default : System.out.println("Ceci n'est pas une ressource");
				}
				
				ress.add(r);
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ress;
	}
	
	
	@Override
	public List<Ressource> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ressource insert(Ressource o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Ressource ajoutRessource(int idCompte, int idPartie, Ressource r) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ressource (id_compte, id_partie,nom, quantite) VALUES (?,?,?,?)");
			ps.setInt(1,idCompte);
			ps.setInt(2,idPartie);
			ps.setString(3,r.toStringName());
			ps.setInt(4,r.getStock());
			
			ps.executeUpdate();

			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Ressource update(Ressource o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Ressource updateRessource(int idCompte, int idPartie, Ressource r) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("UPDATE ressource SET nom=?, quantite=? WHERE id_compte=? AND id_partie=? AND nom=?");
			ps.setString(1,r.toStringName());
			ps.setInt(2,r.getStock());
			ps.setInt(3,idCompte);
			ps.setInt(4,idPartie);
			ps.setString(5, r.toStringName());
					
			ps.executeUpdate();

			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
