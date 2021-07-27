package test;

import java.util.ArrayList;
import java.util.List;

import metier.*;

public class Experiement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Joueur> liste = new ArrayList();
		Joueur thib = new Joueur(1,"thib","thib","Thibault","David","tibidi");
		Joueur val = new Joueur(2,"val","val","Valentin","David","valou");
		Joueur jc = new Joueur(3,"jc","jc","Jean-Charles","David","charlie");
		
		liste.add(thib);
		liste.add(val);
		liste.add(jc);
		
		Partie p1 = new Partie(1,15,liste);
		
		Merveille m = new Merveille("Merveille",5,100);
		
		(jc.getConstruction()).add(m);
		
		p1.startPartie(p1);
		
	}

}
