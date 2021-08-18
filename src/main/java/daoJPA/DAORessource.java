package daoJPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.IDAORessource;
import metier.Compte;
import metier.Ressource;
import util.Context;

public class DAORessource implements IDAORessource {

	@Override
	public Ressource findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Ressource r = em.find(Ressource.class,id);
		em.close();
		return r;
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

	@Override
	public Ressource update(Ressource o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ressource> findAllByIdCompteIdPartie(Integer idCompte, Integer idPartie) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
