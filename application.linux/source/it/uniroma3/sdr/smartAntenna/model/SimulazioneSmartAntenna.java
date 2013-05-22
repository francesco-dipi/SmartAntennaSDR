/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import it.uniroma3.sdr.smartAntenna.Simulazione;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author dipi
 *
 */
public class SimulazioneSmartAntenna {
	
	private Simulazione parent;
	
	private AntennaClassica antennaClassica;
	private SmartAntenna adaptiveArray, switchedBeam;
	private List<Signal> sois, snois;
	private boolean mousePressed, inPausa;
	
	public SimulazioneSmartAntenna(PApplet parent){
		this.parent = (Simulazione) parent;
		this.antennaClassica = null;
		this.mousePressed = false;
	}
	
	public SimulazioneSmartAntenna(Simulazione simulazione,
			int antennaClassica, int adaptiveArray, int switchedBeam) {
		this(simulazione);
		int numAntenne = antennaClassica + adaptiveArray + switchedBeam;
		int size = 0;
		if (numAntenne > 0)	// altrimenti deselezionando tutte le antenne salta l'eccezione division by zero...
			//size = this.parent.height*3/8/numAntenne;
			size = this.parent.height/3 - this.parent.height/15*numAntenne;
		if (antennaClassica > 0)
			this.antennaClassica = new AntennaClassica(simulazione, size, this.parent.width/(numAntenne+1), this.parent.height/(2+numAntenne/3), this.parent.color(255,30,30));
		if (adaptiveArray > 0)
			this.adaptiveArray = new AdaptiveArray(simulazione, 60-(numAntenne*10), size*2, this.parent.width*numAntenne/(numAntenne+1), this.parent.height/(2+numAntenne/3), this.parent.color(255,30,30));
		if (switchedBeam > 0)
			this.switchedBeam = new SwitchedBeam(simulazione, size*7/3, this.parent.width*(1+antennaClassica-numAntenne/3)/(3-numAntenne%2), this.parent.height*(1+numAntenne/3)/(2+numAntenne/3), this.parent.color(255,30,30));
		
		this.sois = new ArrayList<Signal>();
		this.snois = new ArrayList<Signal>();
		
	}
	
	public void addSOI(){
		this.sois.add(new Signal(this.parent, new PVector(this.parent.random(this.parent.width), this.parent.random(this.parent.height)), new PVector(this.parent.random(10), this.parent.random(10)), true));
	}
	
	public void addSNOI(){
		this.snois.add(new Signal(this.parent, new PVector(this.parent.random(this.parent.width), this.parent.random(this.parent.height)), new PVector(this.parent.random(10), this.parent.random(10)), false));
	}

	public void draw(){
		
		this.parent.background(255);
		this.parent.setFontPiccolo();
		
		if (this.antennaClassica != null)
			this.antennaClassica.draw();
		
		if (this.adaptiveArray != null){
			this.adaptiveArray.follow(this.sois);
			this.adaptiveArray.azzera(this.snois);
			this.adaptiveArray.draw();
		}
		
		if (this.switchedBeam != null){
			this.switchedBeam.follow(this.sois);
			this.switchedBeam.azzera(this.snois);
			this.switchedBeam.draw();
		}
		
		for (Signal s: this.sois)
			s.draw();
		for (Signal s: this.snois)
			s.draw();
		

		this.parent.fill(125);
		this.parent.textAlign(PApplet.LEFT);
		this.parent.text("H - Help",30,30);
		
		if (this.parent.isShowDebug())
			this.showDebug();
		if (this.parent.isShowHelp())
			this.showHelp();
	}

	private void showDebug() {
		this.parent.fill(125);
		this.parent.setFontPiccolo();
		this.parent.textAlign(PApplet.LEFT);
		this.parent.text(
				  "FPS:            "+this.parent.frameRate+
				"\nNumero SOI:     "+this.sois.size()+
				"\nNumero SNOI:    "+this.snois.size()+
				"\nTotale segnali: "+(this.snois.size()+this.sois.size()),this.parent.width*2/3, this.parent.height-80);
				
		/*
		this.parent.text("FPS:            "+this.parent.frameRate,this.parent.width/2, this.parent.height-80);
		this.parent.text("Numero SOI:     "+this.sois.size(), this.parent.width/2, this.parent.height-60);
		this.parent.text("Numero SNOI:    "+this.snois.size(), this.parent.width/2, this.parent.height-40);
		this.parent.text("Totale segnali: "+(this.snois.size()+this.sois.size()), this.parent.width/2, this.parent.height-20);
		*/
		this.parent.setFontMedio();
	}

	private void showHelp() {
		this.parent.fill(75);
		this.parent.setFontPiccolo();
		this.parent.textAlign(PApplet.LEFT);
		this.parent.text(
				  "S      - Aggiungi un nuovo segnale con posizione e velocita' random"+
				"\nI      - Aggiungi un nuovo segnale-interferenza con posizione e velocita' random"+
				"\nCLICK  - Aggiungi un nuovo segnale con posizione comandata dal mouse" +
				"\nR      - Rimuovi tutti i segnali prensenti"+
				"\n"+
				"\n1-9    - Regola velocita' simulazione"+
				"\nP      - Pausa/riprende la simulazione"+
				"\nD      - Mostra informazioni di dedug" +
				"\nSPAZIO - Mostra informazioni sulla simulazione" +
				"\n" +
				"\nESC    - Torna al menu' principale",30,this.parent.height-210);
		this.parent.setFontMedio();
	}
	
	private void rimuoviSegnali(){
		this.sois = new ArrayList<Signal>();
		this.snois = new ArrayList<Signal>();
	}

	public void mousePressed() {
		if (!this.mousePressed)
			this.sois.add(SignalMouse.getInstance(this.parent));
		else
			this.addSOI();
		this.mousePressed = true;
	}

	public void mouseReleased() {
		// TODO Auto-generated method stub
		
	}
	
	public void ingrandisciAntenne(){
		if (this.antennaClassica != null)
			this.antennaClassica.setSize(this.antennaClassica.getSize()+5);
		if (this.adaptiveArray != null)
			this.adaptiveArray.setSize(this.adaptiveArray.getSize()+10);
		if (this.switchedBeam != null)
			this.switchedBeam.setSize(this.switchedBeam.getSize()+10);
	}
	
	public void diminuisciAntenne(){
		if (this.antennaClassica != null && this.antennaClassica.getSize()>0)
			this.antennaClassica.setSize(this.antennaClassica.getSize()-5);
		if (this.adaptiveArray != null && this.adaptiveArray.getSize()>0)
			this.adaptiveArray.setSize(this.adaptiveArray.getSize()-10);
		if (this.switchedBeam != null && this.switchedBeam.getSize()>0)
			this.switchedBeam.setSize(this.switchedBeam.getSize()-10);
	}
	
	public void keyPressed() {
		switch (this.parent.keyCode) {
		case KeyEvent.VK_S:
			this.addSOI();			
			break;
		case KeyEvent.VK_I:
			this.addSNOI();
			break;
		case KeyEvent.VK_H:
			this.parent.setShowHelp(!this.parent.isShowHelp());
			break;
		case KeyEvent.VK_D:
			this.parent.setShowDebug(!this.parent.isShowDebug());
			break;
		case KeyEvent.VK_SPACE:
			this.parent.setShowInfo(!this.parent.isShowInfo());
			break;
		case KeyEvent.VK_R:
			 this.rimuoviSegnali();
			 this.mousePressed = false;
			 break;
		case KeyEvent.VK_P:
		case KeyEvent.VK_PAUSE:
			this.pausa();
			break;
		case KeyEvent.VK_PLUS:
			this.ingrandisciAntenne();
			break;
		case KeyEvent.VK_MINUS:
			this.diminuisciAntenne();
			break;
		default:
			break;
		}
	}

	private void pausa() {
		this.inPausa = !this.inPausa;
		if (this.inPausa)
			this.parent.noLoop();
		else this.parent.loop();
	}

}
