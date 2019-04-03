package fr.oc.projet3.joueurs.abstractClass;

import fr.oc.projet3.jeux.implementation.RecherchePlusMoins;
import fr.oc.projet3.jeux.abstractClass.*;

/**
 * Joueur est une classe abstraite représentant un joueur
 * humain ou ordinateur
 * @author romaindemellier
 *
 */

public abstract class Joueur {
	
	/**
	 * Entier représentant le nombre de cases de la combinaison
	 */
	protected int n;
	/**
	 * String représentant la combinaison à trouver
	 */
	protected String combinaison;
	
	/**
	 * String qui va servir à stocker les caractères de la proposition
	 * qui ne sont pas bien placés, initialisé à ""
	 */
	protected String resteProposition;
	
	/**
	 * String qui va servir à stocker les caractères de la combinaison
	 * qui n'ont pas été trouvés
	 */
	protected String resteCombinaison;

	/**
	 * méthode abstraite définie dans les classes Personne et Ordinateur
	 * @return booléen vrai si on a trouvé la combinaison ou si on
	 * veut arrêter
	 */
	public abstract Boolean jouerPlusMoins();
	/**
	 * méthode abstraite définie dans les classes Personne et Ordinateur
	 * @return booléen vrai si on a trouvé la combinaison ou si on
	 * veut arrêter
	 */
	public abstract Boolean jouerMastermind();
	
	/**
	 * méthode qui va comparer la chaîne saisie par l' utilisateur ou
	 * générée par l'ordinateur selon le mode de jeu avec la combinaison secrète
	 * @param str chaîne saisie ou générée
	 * @return String composée de '+', '-', '='
	 */
	protected String comparaison(String str) {
		//res va contenir le résultat; c'est lui qui sera renvoyé; 
		//initialisé à une chaîne vide
		String res = "";
		for(int i = 0;i < this.n;i++) {
			//Conversion de combinaison.charAt(i) en entier
			int chiffreCombi = Jeu.conversion(this.combinaison.charAt(i));
			//Conversion de str.charAt(i) en entier
			int chiffreSaisi = Jeu.conversion(str.charAt(i));
			if(chiffreCombi > chiffreSaisi) {
				//Si le chiffre de la combinaison > à celui saisi
				//alors on ajoute "+" à res
				res += "+";
			} else if (chiffreCombi < chiffreSaisi) {
				//Si le chiffre de la combinaison < à celui saisi
				//alors on ajoute "-" à res
				res += "-";
			} else {
				//Si ils sont égaux on ajoute "=" à res 
				res += "=";
			}
		}
		return res;
	}
	
	/**
	 * méthode qui permet de connaître le nombre de caractères bien placés
	 * ainsi que celui des caractères présents
	 * @param str chaîne saisie ou générée
	 * @return un message indiquant le nombre de caractères bien placés
	 * et le nombre de caractères présents
	 */
	
	protected int bienPlace(String str) {
		
		String combi = this.combinaison;
		this.resteProposition = "";
		this.resteCombinaison = "";
		
		//cette variable va contenir les caractères de la proposition
		//qui ne sont pas bien placés
		//String resteProposition = "";
		
		//cette variable va contenir les caractères de la combinaison 
		//secrète qui n'ont pas été trouvés
		//String resteCombinaison = "";
		
		//compteur va nous indiquer le nombre de caractères bien placés
		int compteur = 0;
		int l = str.length();
		String message = "";
		
		for(int i = 0;i < l;i++) {
			if(str.charAt(i) == combi.charAt(i)) {
				//Si les deux caractères sont égaux on incrémente compteur
				compteur++;
			} else {
				//Si les deux caractères sont différents on les place
				//respectivement dans resteProposition et dans resteCombinaison
				resteProposition += str.charAt(i);
				resteCombinaison += combi.charAt(i);
			}
		}
		return compteur;
		/*if(compteur > 0) {
			//Si il y a au moins un caractère bien placé on concatène le
			//message (vide pour l'instant) avec compteur
			message += compteur;
		}
		
		if(compteur > 1) {
			//Si plusieurs caractères sont bien placés
			message += " sont bien placés";
		}
		
		if(compteur == 1) {
			//Si un seul caractère est bien placé
			message += " est bien placé";
		}
		//on retourne le résultat de 
		//estPresent(resteProposition,resteCombinaison,message)
		return estPresent(resteProposition,resteCombinaison,message);*/
	}
	
	/**
	 * Méthode qui permet de connaître le nombre de caractères présents
	 * @param restePropo est la proposition sans les caractères présents
	 * @param resteCombi est la combinaison secrète les caractères trouvés
	 * @param message contient le message indiquant le nombre de carctères
	 * 		  présents
	 * @return un message qui va contenir le message de la méthode bienPlace
	 * 	 	   plus le nombre de caractères bien placés	
	 */
	
	protected int estPresent() {
		
		
		//compteur va nous indiquer le nombre de caractères présents
		int compteur = 0;
		
		//On parcourt restePropo
		for(int i = 0;i < this.resteProposition.length();i++) {
			
			//On teste la présence du caractère restePropo.charAt(i)
			//dans resteCombi
			int presenceIndex = this.resteCombinaison.indexOf(this.resteProposition.charAt(i));
			if(presenceIndex != -1) {
				//System.out.println("Presence à l'index : " + presenceIndex);
				//Si le caractère est présent on incrémente compteur
				compteur++;
				//Puis on retire ce caractère de resteCombi
				//On distingue deux cas
				//Soit le caractère est au bout de la chaîne
				//Soit il n'est pas en bout de chaîne
				if(presenceIndex == this.resteCombinaison.length() - 1) {
					this.resteCombinaison = this.resteCombinaison.substring(0, this.resteCombinaison.length() - 1);
				} else {
					this.resteCombinaison = this.resteCombinaison.substring(0, presenceIndex) +
								 this.resteCombinaison.substring(presenceIndex + 1, this.resteCombinaison.length());
				}
			}
		}
		
		return compteur;
	}
	
	public String resultatMastermind(int nbreBP, int nbreEP) {
		String resultat = "";
		resultat += nbreBP;
		if(nbreBP > 1 || nbreBP == 0) {
			resultat += " sont bien placés , ";
		} else {
			resultat += " est bien placé , ";
		}
		resultat += nbreEP;
		if(nbreEP > 1 || nbreEP == 0) {
			resultat += " sont présents.";
		} else {
			resultat += " est présent.";
		}
		return resultat;
	}
	
}
