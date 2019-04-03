package fr.oc.projet3.jeux.implementation;
import javax.swing.JOptionPane;

import fr.oc.projet3.jeux.abstractClass.Jeu;
import fr.oc.projet3.joueurs.abstractClass.Joueur;
import fr.oc.projet3.joueurs.implementation.Ordinateur;
import fr.oc.projet3.joueurs.implementation.Personne;

public class Mastermind extends Jeu {

	private int nbreChiffre;
	
	public Mastermind(String mode, int nbreC, int nbreE, int nbreChi) {
		this.mode = mode;
		this.nbreCases = nbreC;
		this.nbreEssai = nbreE;
		this.nbreChiffre = nbreChi;
		String str = "";
		Boolean continuer = false;
		JOptionPane jop = new JOptionPane();
		JOptionPane jop2 = new JOptionPane();
		String combi1 = "";
		String combi2 = "";
		String[] tab = {"Continuer","Quitter"};
		
			Boolean gagne = false;
			if(mode.equals("challenger")) {
				String combi = this.genereCombinaison();
				//this.afficheCombinaison(combi);
				this.joueur = new Personne(this.nbreCases, combi);
				do {
					gagne = this.joueur.jouerMastermind();
				} while(!gagne);
				
			} else if(mode.equals("defenseur")) {
				String combinaison = this.chooseCombi(this.nbreChiffre);
				String proposition = this.genereCombinaison(this.nbreChiffre);
				if(combinaison.charAt(0) != 'Q' && !combinaison.equals("NULL")) {
					//this.joueur = new Ordinateur(nbreCases, combinaison, proposition,nbreChiffre);
					this.joueur = new Ordinateur(nbreCases, combinaison, "77777777",nbreChiffre);
					do {
						gagne = this.joueur.jouerMastermind();
						if(gagne) break;
						int choix = jop2.showOptionDialog(null, "Continuer ?", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, tab, tab[0]);
						if(choix == 1) {
							break;
						}
					} while(!gagne);
				} 
			}else {
					combi1 = this.genereCombinaison(this.nbreChiffre);
					String proposition = this.genereCombinaison(this.nbreChiffre);
					Joueur joueur1 = new Personne(nbreCases, combi1);
					combi2 = this.chooseCombi(this.nbreChiffre);
					Joueur joueur2= new Ordinateur(nbreCases, combi2, proposition, this.nbreChiffre);
					JOptionPane j = new JOptionPane();
					do {
						if(aQuiLeTour.equals("personne")) {
							j.showMessageDialog(null, "A vous de jouer !", "Changement de joueur", JOptionPane.INFORMATION_MESSAGE);
							gagne = joueur1.jouerMastermind();
							aQuiLeTour = "ordinateur";
						} else {
							j.showMessageDialog(null, "C'est au tour de l'ordinateur", "Changement de joueur", JOptionPane.INFORMATION_MESSAGE);
							gagne = joueur2.jouerMastermind();
							aQuiLeTour = "personne";
						}
					} while(!gagne);
		}
				
	}
}
