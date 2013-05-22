package it.uniroma3.sdr.smartAntenna.UI;

import processing.core.PImage;
import it.uniroma3.sdr.smartAntenna.Simulazione;

public class Crediti {
	
	private Simulazione parent;
	private String stringa = 
		"2011 - Francesco Di Paolo\n"+
		"\nProgetto per il corso di Software Defined Radio"+
		"\ntenuto dal Prof. Francesco Benedetto";
	private String dettagli =
		"\nSimulazione del funzionamento"+
		"\ndi antenne classiche e smart antenna\n"+
		"\nLinguaggio di programmazione utilizzato:"+
		"\nJava con la libreria Processing.core (processing.org)";
	private int posY;
	private PImage logoRoma3;
	
	public Crediti(Simulazione parent){
		this.parent = parent;
		// parte dal basso e va verso l'alto...
		this.posY = this.parent.height;
		this.logoRoma3 = this.parent.loadImage("Roma3Logo.png");
	}
	
	public void draw(){
		this.parent.pushMatrix();
		this.parent.pushStyle();
		this.parent.background(255,255,200);
		this.parent.fill(25, 35, 95);
		this.parent.textAlign(Simulazione.CENTER);
		this.parent.imageMode(Simulazione.CENTER);
		this.parent.setFontMedio();
		this.parent.image(this.logoRoma3, this.parent.width/2, this.posY-this.logoRoma3.height);
		this.parent.text(this.stringa, this.parent.width/2, this.posY);
//		this.parent.setFontPiccolo();
		this.parent.text(this.dettagli, this.parent.width/2, this.posY+180);
		this.posY--;
		if (this.posY <= -300)
			this.parent.setNav(0);
		this.parent.popStyle();
		this.parent.popMatrix();
	}

}
