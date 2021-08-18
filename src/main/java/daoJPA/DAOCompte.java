package daoJPA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAOCompte;
import metier.Admin;
import metier.Compte;
import metier.Joueur;

public class DAOCompte implements IDAOCompte{
//JPA
	@Override
	public Compte findById(Integer id) {
		
		return null;
	}
	
	public Compte findBySurnom(String surnom) {
		
		return null;
	}

	@Override
	public List<Compte> findAll() {
		
		return null;
	}

	@Override
	public Compte insert(Compte c) {
		
		return null;

	}

	@Override
	public Compte update(Compte c) {
		
		return null;
	}

	@Override
	public void delete(Integer id) {
		
	}

	public static Compte seConnecter(String login,String password) 
	{
		
		return null;
	}
	
	public void ajoutJoueur(int idPartie, int idCompte) {
		
		
	}
	
}
