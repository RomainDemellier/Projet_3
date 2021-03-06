package fr.oc.projet3.joueurs.implementation;
import javax.swing.JOptionPane;

import fr.oc.projet3.jeux.abstractClass.Jeu;
import fr.oc.projet3.joueurs.abstractClass.Joueur;

/**
 * <b>Personne est une classe héritant de Joueur qui représente un joueur en chair et en os donc pas un ordinateur</b>
 * <p>une personne est caractérisée par les informations suivantes</p>
 * <ul>
 * <li>un entier représentant le nombre de chiffres de la combinaison à trouver</li>
 * <li>et la combinaison</li>
 * </ul>
 * @author romaindemellier
 *
 */

public class Personne extends Joueur {

	//private String combinaison;
	
	/**
	 * Constructeur de Personne
	 * @param n
	 * 			nombre de chiffres de la combinaison
	 * @param combi
	 * 			la combinaison
	 */
	
	public Personne(int n, String combi) {
		this.n = n;
		this.combinaison = combi;
	}
	

	/**
	 * @return un booléen : vrai si l'utilisateur a trouvé la combinaison
	 * 		   secrète ou si il décide de quitter le jeu
	 */
	public Boolean jouerPlusMoins() {
		int n = this.n;
		String combinaison = this.combinaison;
		String str = "";
		Boolean continuer = true;
		Boolean arreter = false;
		String resultat = "";
		JOptionPane jop = new JOptionPane();
		Jeu.afficheCombinaison(combinaison, 'p');
		
		do {
			str = jop.showInputDialog(null, "Veuillez saisir une combinaison de " + n + " chiffres");
			if(str == null) {
				//Si l'utilisateur a cliqué sur le bouton annuler de jop
				//str vaut null, on sort de la boucle et arreter vaut true
				arreter = true; 
				break;
			}
			if(str.charAt(0) == 'Q') {
				//Si l'utilisateur a saisi un Q pour quitter on sort de la boucle
				//et arreter vaut true
				continuer = false;
				arreter = true;
			} else if(str.length() != n || !Jeu.stringComposeChiffres(str)) {
				//Si la longueur de la chaîne saisie est différente de n ou
				//si cette chaîne n'est pas exclusivement composée de chiffres
				//on affiche un message via une boîte de dialogue et on retourne
				//au début de la boucle
				jop.showMessageDialog(null, "Vous n'avez pas saisi une combinaison de " + n + " chiffres", "Attention", JOptionPane.WARNING_MESSAGE);
				continue;
			} else {
				//Arrivé ici l'utilisateur a saisi une chaîne valide
				//On affecte false à continuer pour sortir de la boucle
				continuer = false;
				if(str.equals(combinaison)) {
					//Si l'utilisateur a trouvé la combinaison secrète on
					//l'indique via une boîte de dialogue et on affecte
					//true à arreter 
					resultat = comparaison(str);
					System.out.println("Proposition : " + str + " -> Réponse : " + resultat);
					jop.showMessageDialog(null, "Bravo, vous avez trouvé la combinaison !", "Gagné", JOptionPane.INFORMATION_MESSAGE);
					arreter = true;
				} else {
					//System.out.println("str : " + str);
					//Si l'utilisateur n'a pas trouvé la combinaison secrète
					//on affiche dans la console le résultat de comparaison(str)
					resultat = comparaison(str);
					System.out.println("Proposition : " + str + " -> Réponse : " + resultat + "\n");
					//System.out.println(resultat);
				}
			}
		} while (continuer);
		return arreter;
	}
	
	public Boolean jouerMastermind() {
		int n = this.n;
		String combinaison = this.combinaison;
		String proposition = "";
		Boolean continuer = true;
		Boolean arreter = false;
		String resultat = "";
		JOptionPane jop = new JOptionPane();
		Jeu.afficheCombinaison(combinaison, 'p');
		
		do {
			proposition = jop.showInputDialog(null, "Veuillez saisir une combinaison de " + n + " chiffres");
			if(proposition == null) {
				arreter = true;
				break;
			}
			if(proposition.charAt(0) == 'Q') {
				continuer = false;
				arreter = true;
			} else if(proposition.length() != n || !Jeu.stringComposeChiffres(proposition)) {
				jop.showMessageDialog(null, "Vous n'avez pas saisi une combinaison de " + n + " chiffres", "Attention", JOptionPane.WARNING_MESSAGE);
				continue;
			} else {
				continuer = false;
				if(proposition.equals(combinaison)) {
					
					jop.showMessageDialog(null, "Bravo, vous avez trouvé la combinaison !", "Gagné", JOptionPane.INFORMATION_MESSAGE);
					arreter = true;
				} else {
					int nbreBP = bienPlace(proposition);
					int nbreEP = estPresent();
					resultat = resultatMastermind(nbreBP,nbreEP);
					System.out.println("Proposition : " + proposition + " -> Réponse : " + resultat + "\n");
				}
			}
		} while (continuer);
		return arreter;
	}
}
