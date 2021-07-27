package test;

import metier.*;

public class Experiement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Forteresse f = new Forteresse();
		
		System.out.println(f.getCost());
		
		Joueur j = new Joueur("bla", "bla");
		
		j.constructBat(f);
		j.constructBat(f);
		j.constructBat(f);
		
		j.getConstruction();
		
		//j.displayOwnedConstruction();

	}

}
