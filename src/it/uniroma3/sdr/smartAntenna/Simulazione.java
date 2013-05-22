/**
 * Questa e' la classe principale della Simulazione
 * 
 */
package it.uniroma3.sdr.smartAntenna;

import java.awt.event.KeyEvent;

import it.uniroma3.sdr.smartAntenna.UI.Crediti;
import it.uniroma3.sdr.smartAntenna.model.Menu;
import it.uniroma3.sdr.smartAntenna.model.SimulazioneSmartAntenna;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * @author dipi
 * 
 *         Classe principale, inizializza l'interfaccia grafica, disegna
 *         l'elemento in primo piano (menu o simulazione) e gli passa tutti gli
 *         eventi del mouse e della tastiera si occupa anche dei font.
 * 
 */
public class Simulazione extends PApplet {

	public final static int MENU = 0;
	public final static int SIMULAZIONE = 1;
	public final static int CREDITI = 2;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7333420766011507143L;
	private Menu menu;
	private SimulazioneSmartAntenna sim;
	private Crediti credits;
	private PFont fontMedio;
	private PFont fontGrande;
	private PFont fontPiccolo;
	private boolean showHelp, showDebug, showInfo;

	/*
	 * 0 = menu 
	 * 1 = simulazione
	 * 2 = crediti
	 */
	private int nav;
	
	
//	 public static void main(String args[])
//	    {
//	      PApplet.main(new String[] { "--present", it.uniroma3.sdr.smartAntenna.Simulazione.class.getName() });
//	    } 

	public void setup() {

		size(1000, 800);
		this.nav = 0;

		this.menu = new Menu(this);
		this.sim = new SimulazioneSmartAntenna(this);
		this.credits = new Crediti(this);

		this.strokeWeight(0);
		this.textAlign(PApplet.CENTER);
		// this.textMode(PApplet.CENTER);
		this.rectMode(PApplet.CENTER);
		this.ellipseMode(PApplet.CENTER);
		// per vedere i font disponibili...
//		 String[] fontList = PFont.list();
//		 println(fontList);
		// TODO sul mac non ho il font ubuntu...
		this.fontGrande = this.createFont("Ubuntu Bold", 64);
		this.fontMedio = this.createFont("Ubuntu", 32);
		this.fontPiccolo = this.createFont("Ubuntu", 12);
		
//		this.fontGrande = this.createFont("Courier New", 64);
//		this.fontMedio = this.createFont("Courier New", 32);
		this.fontPiccolo = this.createFont("DejaVu Sans Mono", 12);
		
		
		this.textFont(this.fontMedio);

		// prove:
		noCursor();
	}

	public void draw() {
		switch (this.nav) {
		case 0:
			this.menu.draw();
			break;
		case 1:
			this.sim.draw();
			break;
		case 2:
			this.credits.draw();
			break;
		default:
			break;
		}
	}

	public void mousePressed() {
		switch (this.nav) {
		case 0:
			this.menu.mousePressed();
			break;
		case 1:
			this.sim.mousePressed();
		default:
			break;
		}
	}

	public void mouseReleased() {
		switch (this.nav) {
		case 0:
			this.menu.mouseReleased();
			break;
		case 1:
			this.sim.mouseReleased();
		default:
			break;
		}
	}

	public void keyPressed() {
		switch (this.keyCode) {
		// Tasto ESC
		case KeyEvent.VK_ESCAPE:
			this.key = 0;
			if (this.nav > 0)
				this.setNav(0);
			else this.stop();
			break;
			// INVIO
		case KeyEvent.VK_ENTER:
			this.key = 0;
			if (this.nav == 0)
				this.startSimulazione(this.menu.getScelte()[0], this.menu.getScelte()[1], this.menu.getScelte()[2]);
			break;
		case KeyEvent.VK_C:
			this.startCrediti();
			break;
		// tasti numerici -> velocita' simulazione
		case KeyEvent.VK_1:
			this.frameRate(1);
			break;
		case KeyEvent.VK_2:
			this.frameRate(3);
			break;
		case KeyEvent.VK_3:
			this.frameRate(5);
			break;
		case KeyEvent.VK_4:
			this.frameRate(7);
			break;
		case KeyEvent.VK_5:
			this.frameRate(10);
			break;
		case KeyEvent.VK_6:
			this.frameRate(15);
			break;
		case KeyEvent.VK_7:
			this.frameRate(20);
			break;
		case KeyEvent.VK_8:
			this.frameRate(30);
			break;
		case KeyEvent.VK_9:
			this.frameRate(60);
			break;
		default: 
			switch (this.nav) {
			case 0:
					this.menu.keyPressed();
					break;
				case 1:
					this.sim.keyPressed();
					break;

				default:
					break;
				}
		}
			//		text("released " + (int) (key) + " " + keyCode, 800, 90);
	}

	public void startCrediti() {
		this.credits = new Crediti(this);
		this.setNav(Simulazione.CREDITI);
	}

	public void stop() {
		PApplet.println("*** Stop ***");
		this.exit();
	}

	public void startSimulazione(boolean antennaClassica,
			boolean adaptiveArray, boolean switchedBeam) {
		int ac, aa, sb;
		if (antennaClassica)
			ac = 1;
		else
			ac = 0;
		if (adaptiveArray)
			aa = 1;
		else
			aa = 0;
		if (switchedBeam)
			sb = 1;
		else
			sb = 0;
		this.sim = new SimulazioneSmartAntenna(this, ac, aa, sb);
		this.setNav(Simulazione.SIMULAZIONE);
	}

	public int getNav() {
		return nav;
	}

	public void setNav(int nav) {
		this.nav = nav;
	}

	public PFont getFontMedio() {
		return fontMedio;
	}

	public void setFontMedio(PFont font) {
		this.fontMedio = font;
	}

	public PFont getFontGrande() {
		return fontGrande;
	}

	public void setFontPiccolo(PFont fontPiccolo) {
		this.fontPiccolo = fontPiccolo;
	}
	
	public PFont getFontPiccolo() {
		return fontPiccolo;
	}

	public void setFontGrande(PFont fontGrande) {
		this.fontGrande = fontGrande;
	}


	public void setFontGrande() {
		this.textFont(this.fontGrande);
	}

	public void setFontMedio() {
		this.textFont(this.fontMedio);
	}
	
	public void setFontPiccolo() {
		this.textFont(this.fontPiccolo);
	}

	public boolean isShowHelp() {
		return showHelp;
	}

	public void setShowHelp(boolean showHelp) {
		this.showHelp = showHelp;
	}

	public boolean isShowDebug() {
		return showDebug;
	}

	public void setShowDebug(boolean showDebug) {
		this.showDebug = showDebug;
	}

	public boolean isShowInfo() {
		return showInfo;
	}

	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}
}
