package fr.oc.projet3.joueurs.implementation;
import javax.swing.JOptionPane;

import fr.oc.projet3.jeux.abstractClass.Jeu;
import fr.oc.projet3.jeux.implementation.RecherchePlusMoins;
import fr.oc.projet3.joueurs.abstractClass.Joueur;

public class Ordinateur extends Joueur {

	private String proposition = "";
	private String solution = "";
	private int nbreChiffre;
	private int nombreBienPlace;
	private int nombreEstPresent;
	private String listePresent;
	private String listeNonPresent;
	
	public Ordinateur(int n, String combi) {
		this.n = n;
		this.combinaison = combi;
		for(int i = 0;i < n;i++) {
			this.proposition += '5';
		}
	}
	
	public Ordinateur(int n, String combi, String propo, int nbreChiffre) {
		this.n = n;
		this.combinaison = combi;
		this.proposition = propo;
		this.nbreChiffre = nbreChiffre;
		this.nombreBienPlace = -1;
		this.nombreEstPresent = -1;
		this.listePresent = "";
		this.listeNonPresent = "";
	}
	
	public Boolean jouerPlusMoins() {
		int n = this.n;
		String combi = this.combinaison;
		Boolean gagne = false;
		String resultat = "";
		JOptionPane jop = new JOptionPane();
		Jeu.afficheCombinaison(combi, 'o');
		
		if(resultat.equals("")) {
			if(proposition.equals(combi)) {
				resultat = comparaison(proposition);
				System.out.println("Proposition : " + this.proposition + " -> Réponse : " + resultat);
				jop.showMessageDialog(null, "L'ordinateur a trouvé la combinaison !", "Trouvé", JOptionPane.INFORMATION_MESSAGE);
				gagne = true;
			}else {
				resultat = comparaison(this.proposition);
				System.out.println("Proposition de l'ordinateur : " + this.proposition + " -> Réponse : " + resultat + "\n");
				genereProposition(resultat);
			}
		} else {
			if(proposition.equals(combi)) {
				resultat = comparaison(this.proposition);
				System.out.println("Proposition de l'ordinateur : " + this.proposition + " -> Réponse : " + resultat);
				jop.showMessageDialog(null, "L'ordinateur a trouvé la combinaison !", "Trouvé", JOptionPane.INFORMATION_MESSAGE);
				gagne = true;
			} else {
				resultat = comparaison(this.proposition);
				System.out.println("Proposition : " + this.proposition + " -> Réponse : " + resultat + "\n");
				genereProposition(resultat);
			}
		}
		return gagne;
	}
	
	public Boolean jouerMastermind() {
		int n = this.n;
		String combi = this.combinaison;
		String propo = this.proposition;
		int nbreBP;
		int nbreEP;
		String resultat = "";
		JOptionPane jop = new JOptionPane();
		Jeu.afficheCombinaison(combinaison, 'o');
		
		if(this.nombreBienPlace == -1) {
			this.nombreBienPlace = this.bienPlace(propo);
			this.nombreEstPresent = this.estPresent();
			resultat = this.resultatMastermind(this.nombreBienPlace, this.nombreEstPresent);
			System.out.println("Proposition de l'ordinateur : " + proposition + " -> Réponse : " + resultat + "\n");
		} else {
			propo = generePropositionMastermind();
			nbreBP = bienPlace(propo);
			nbreEP = estPresent();
			char c;
			if(nbreBP > this.nombreBienPlace) {	
				/*if(nbreEP < this.nombreEstPresent) {
					System.out.println("Dans nbreEP <");
					this.listePresent = this.addCaractere(listePresent, c);
				}*/
				if(this.nombreEstPresent == 0 && nbreEP == 0) {
					c = this.proposition.charAt(this.solution.length());
					this.listeNonPresent = this.addCaractere(this.listeNonPresent, c);
				}
				this.nombreBienPlace = nbreBP;
				this.nombreEstPresent = nbreEP;
				this.proposition = propo;
				this.solution += this.proposition.charAt(this.solution.length());
				
			} else if(nbreBP < this.nombreBienPlace){
				c = propo.charAt(this.solution.length());
				this.solution += this.proposition.charAt(this.solution.length());
				if(nbreEP > this.nombreEstPresent) {
					//c = propo.charAt(this.solution.length());
					System.out.println("Dans nbreEP >");
					this.listePresent = this.addCaractere(listePresent, c);
				} if(nbreEP == 0) {
					this.listeNonPresent = this.addCaractere(this.listeNonPresent, c);
				}
			} else {
				if(this.nombreBienPlace > 0) {
					if(nbreEP > this.nombreEstPresent) {
						c = propo.charAt(this.solution.length());
						
						this.listePresent = this.addCaractere(listePresent, c);
					} else if(nbreEP < this.nombreEstPresent) {
						c = this.proposition.charAt(this.solution.length());
						
						this.listePresent = this.addCaractere(listePresent, c);
						if(nbreEP == 0) {
							c = propo.charAt(this.solution.length());
							this.listeNonPresent = this.addCaractere(this.listeNonPresent, c);
						}
					} else if(nbreEP == 0){
						c = this.proposition.charAt(this.solution.length());
						this.listeNonPresent = this.addCaractere(this.listeNonPresent, c);
						c = propo.charAt(this.solution.length());
						this.listeNonPresent = this.addCaractere(this.listeNonPresent, c);
					}
				} else {
					if(this.nombreEstPresent == 0) {
						c = this.proposition.charAt(this.solution.length());
						this.listeNonPresent = this.addCaractere(this.listeNonPresent, c);
					}
					if(nbreEP == 0) {
						c = propo.charAt(this.solution.length());
						this.listeNonPresent = this.addCaractere(this.listeNonPresent, c);
					}
				}
				this.proposition = propo;
				this.nombreBienPlace = nbreBP;
				this.nombreEstPresent = nbreEP;
			}
			//this.nombreEstPresent = nbreEP;
			resultat = this.resultatMastermind(this.nombreBienPlace, this.nombreEstPresent);
			System.out.println("Proposition de l'ordinateur : " + proposition + " -> Réponse : " + resultat);
			System.out.println("Liste des présents : " + this.listePresent);
			System.out.println("Liste des non présents : " + this.listeNonPresent);
			if(this.solution.length() == this.n) {
				jop.showMessageDialog(null, "L'ordinateur a trouvé la combinaison !", "Trouvé", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		}
		Boolean gagne = false;
		return gagne;
	}
	
	private void genereProposition(String res) {
		for(int i = 0;i < this.n;i++) {
			int q = RecherchePlusMoins.conversion(this.proposition.charAt(i));
			if(res.charAt(i) == '-') {
				q--;
			} else if(res.charAt(i) == '+') {
				q++;
			}
			char c = RecherchePlusMoins.conversion(q);
			char[] tab = this.proposition.toCharArray();
			tab[i] = c;
			this.proposition = String.valueOf(tab);
		}
	}
	
	private String generePropositionMastermind() {
		String propo = this.proposition;
		/*for(int i = 0;i < propo.length();i++) {
			if(this.solution.charAt(i) == '-') {
				incremente(propo,i);
			} else {
				continue;
			}
		}*/
		return incremente(propo,this.solution.length());
	}
	
	private String incremente(String propo, int index) {
		char c = propo.charAt(index);
		int n = Jeu.conversion(c);
		
		do {
			if(n == this.nbreChiffre - 1) {
				n = 0;
			} else {
				n++;
			}
		} while(this.listeNonPresent.indexOf(Jeu.conversion(n)) != -1);
		c = Jeu.conversion(n);
		if(index == propo.length() - 1) {
			propo = propo.substring(0, propo.length() - 1) + c;
		} else {
			propo = propo.substring(0, index)+ c + propo.substring(index + 1, propo.length());
		}
		return propo;
		
	}
	
	private String addCaractere(String liste,char c) {
		if(liste.indexOf(c) == -1) {
			liste += c;
		}
		return liste;
	}
}
