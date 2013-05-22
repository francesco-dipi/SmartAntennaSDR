/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import it.uniroma3.sdr.smartAntenna.Simulazione;
import it.uniroma3.sdr.smartAntenna.UI.Button;
import it.uniroma3.sdr.smartAntenna.UI.Opzione;
import it.uniroma3.sdr.smartAntenna.UI.RectButton;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author dipi
 * 
 * Questa classe rappresenta il menu, ha una serie di opzioni con un checkbox e
 * il pulsantone START.
 * 
 */
public class Menu {

	private Simulazione parent;
	private Opzione[] opzioni;
	private Button start, crediti;

	// List<Opzione> opzioni;

	public Menu(PApplet parent) {
		this.parent = (Simulazione) parent;
		this.opzioni = new Opzione[3];
		this.opzioni[0] = new Opzione(this.parent, this.parent.width/2, this.parent.height/5, "Antenna Classica", false);
		this.opzioni[1] = new Opzione(this.parent, this.parent.width/2, 3*this.parent.height/10, "Antenna Adaptive Array", true);
		this.opzioni[2] = new Opzione(this.parent, this.parent.width/2, 2*this.parent.height/5, "Antenna Switched Beam", false);
		
		this.start = new RectButton(this.parent, "START", new PVector(this.parent.width/2, this.parent.height*2/3), new PVector(300,75), this.parent.color(255, 125, 125), this.parent.color(255, 0, 0), this.parent.color(255, 155, 0));
		this.crediti = new RectButton(this.parent, "info", new PVector(this.parent.width-90, 53), new PVector(120, 45), this.parent.color(255, 125, 125), this.parent.color(255, 0, 0), this.parent.color(255, 155, 0));
	}

	public void draw() {
		this.parent.pushMatrix();
		this.parent.background(255, 255, 200);
//		this.parent.fill(100);
//		this.parent.rect(this.parent.width/2, this.parent.height/2, this.parent.width/2, this.parent.height/2);
		
		this.parent.fill(0);
		this.parent.setFontMedio();
		
		this.parent.textAlign(Simulazione.CENTER);
		this.parent.text("Selezionare le antenne da visualizzare:", this.parent.width/2, 75);
		
		for (int i = 0; i < this.opzioni.length; i++)
			this.opzioni[i].draw();
		this.start.draw();
		this.crediti.draw();
		
		this.parent.ellipse(this.parent.mouseX, this.parent.mouseY, 10, 10);
		
//		this.start.update(this.parent.mouseX, this.parent.mouseY);
		
//		this.parent.mous
		this.parent.popMatrix();
		
	}

	public void mousePressed() {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased() {
		if (this.start.isOver())
			this.parent.startSimulazione(this.opzioni[0].getScelta(), this.opzioni[1].getScelta(), this.opzioni[2].getScelta());
		else
			if (this.crediti.isOver())
				this.parent.startCrediti();
			else for (Opzione o: this.opzioni)
				o.getCheckBox().pressed();
	}
	
	public void keyPressed() {
		
	}
	
	public boolean[] getScelte(){
		boolean[] scelte = new boolean[3];
		scelte[0] = this.opzioni[0].getScelta();
		scelte[1] = this.opzioni[1].getScelta();
		scelte[2] = this.opzioni[2].getScelta();
		return scelte;
	}

}
