package test;

import metier.*;

public class Experiement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Forteresse f = new Forteresse();
		
		//System.out.println(f.getCost());
		
		Joueur j = new Joueur (1, "test", "test", "test", "test", "test");
		
		j.constructBat(f);
		j.constructBat(f);
//		j.constructBat(f);
		
		System.out.println(f.getDef());
		
		
		
		System.out.println(j.getConstruction());
		
		//j.displayOwnedConstruction();

	}

}
