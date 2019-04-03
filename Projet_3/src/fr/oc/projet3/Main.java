package fr.oc.projet3;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import fr.oc.projet3.jeux.abstractClass.Jeu;
import fr.oc.projet3.jeux.implementation.Mastermind;
import fr.oc.projet3.jeux.implementation.RecherchePlusMoins;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*logger.info("Ceci est un message d'info");
		logger.debug("Ceci est un message de debug");
		
		System.out.println("Début du programme !");*/
		
		final Properties prop = new Properties();
		InputStream input = null;
		int nbreCases = -1;
		int nbreEssais = -1;
		int nbreChiffre = -1;
		
		try {

			input = new FileInputStream("src/config.properties");
			

			// load a properties file
			prop.load(input);
			
			nbreCases = Integer.parseInt(prop.getProperty("jeu.nombreCases"));
			nbreEssais = Integer.parseInt(prop.getProperty("jeu.nombreEssais"));
			nbreChiffre = Integer.parseInt(prop.getProperty("mastermind.nombreChiffre"));

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String str = "";
		Boolean continuer = true;
		Boolean config = (nbreCases != -1 && nbreEssais != -1 && nbreChiffre != -1);
		JOptionPane jop = new JOptionPane();
		JOptionPane jop2 = new JOptionPane();
		JOptionPane jop3 = new JOptionPane();
		int nbreChiffres;
		String[] jeux = {"recherche+-","mastermind"};
		//String jeuChoisi = (String)jop2.showInputDialog(null, "Veuillez choisir le jeu", "Choix du jeu", JOptionPane.QUESTION_MESSAGE, null, jeux, jeux[0]);
		String[] modes = {"challenger","defenseur","duel"};
		//String mode = (String)jop2.showInputDialog(null, "Veuillez choisir le mode de jeu", "Choix du mode", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
		//if(jeuChoisi != null && mode != null) {
			do {
				Jeu jeu;
				String jeuChoisi = (String)jop2.showInputDialog(null, "Veuillez choisir le jeu", "Choix du jeu", JOptionPane.QUESTION_MESSAGE, null, jeux, jeux[0]);
				String mode;
				if(jeuChoisi != null) {
					mode = (String)jop2.showInputDialog(null, "Veuillez choisir le mode de jeu", "Choix du mode", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
				} else {
					mode = null;
				}
				//String mode = (String)jop2.showInputDialog(null, "Veuillez choisir le mode de jeu", "Choix du mode", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
				if(jeuChoisi != null && mode != null) {
					if(jeuChoisi.equals("recherche+-") && config) {
						jeu = new RecherchePlusMoins(mode,nbreCases,nbreEssais);
					} else {
						jeu = new Mastermind(mode,nbreCases,nbreEssais,nbreChiffre);
					}
				}
				
				int n = jop3.showConfirmDialog(null, "Voulez vous jouer à nouveau ?", "Continuer ou non", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(n == 1) {
					continuer = false;
				}
			} while(continuer);
		//}
	}

}
