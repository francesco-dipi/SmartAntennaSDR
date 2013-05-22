/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.UI;

import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author dipi
 *
 */
public class Opzione {
	
	PApplet parent;
	
	private String testo;
	private String[] alternative;
	private boolean scelta;
	private CheckBoxButton checkBox;
	private int x, y;
	
	public Opzione(PApplet parent, int x, int y){
		this.parent = parent;
		this.alternative = null;
		this.testo = null;
		this.scelta = false;
		this.checkBox = new CheckBoxButton(parent, "", new PVector(x, y), new PVector(32, 32), 255, parent.color(0, 255, 0), false);
		this.x = x;
		this.y = y;
	}
	
	// Costruttore quasi completo
	public Opzione(PApplet parent, int x , int y, String testo, List<String> alternative){
		this(parent, x, y);
		this.testo = testo;
		this.alternative = (String[]) alternative.toArray();
	}
	
	// Costruttore quasi completo
	public Opzione(PApplet parent, int x, int y, String testo, boolean scelta){
		this(parent, x, y);
		this.testo = testo;
		this.scelta = scelta;
		this.checkBox = new CheckBoxButton(parent, "", new PVector(x, y), new PVector(32, 32), 255, parent.color(0, 255, 0), scelta);
	}
	
	// Costruttore completo
	public Opzione(PApplet parent, int x, int y, String testo, List<String> alternative, boolean scelta){
		this(parent, x, y, testo, alternative);
		this.checkBox = new CheckBoxButton(parent, "", new PVector(x, y), new PVector(32, 32), 255, parent.color(0, 255, 0), scelta);
		this.scelta = scelta;
	}
	
	public void draw(){
		
		this.parent.pushStyle();
		this.parent.pushMatrix();
		
		this.parent.textAlign(PApplet.RIGHT);
		this.parent.text(this.testo + ": ", this.x, this.y);
		
		this.parent.strokeWeight(3);
		this.parent.stroke(50);
		
		this.checkBox.draw();
				
		this.parent.rectMode(PApplet.CORNER);
		if (!this.scelta)
			this.parent.fill(this.parent.color(25, 25, 25));
		else
			this.parent.fill(3, 255, 3);
		
		
//		this.parent.rect(this.x+45, this.y, -32, -32);
		
		this.parent.popStyle();
		this.parent.popMatrix();
		
		/*
		this.parent.textAlign(PApplet.LEFT);
		this.parent.text(this.scelta, x, y);
		this.parent.textAlign(PApplet.CENTER);
		*/
	}
	
	/*
	public void sceltaSucc(){
		this.scelta++;
		this.scelta %= this.alternative.length;
	}
	
	public void sceltaPrec(){
		this.scelta--;
		this.scelta %= this.alternative.length;
	}
	*/
//	
//	public void cambiaScelta(){
//		this.scelta = !this.scelta;
//		this.checkBox.setChecked(scelta);
//	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String[] getAlternative() {
		return alternative;
	}

	public void setAlternative(String[] alternative) {
		this.alternative = alternative;
	}

	public boolean getScelta() {
		return this.checkBox.isChecked();
	}

	public void setScelta(boolean scelta) {
		this.checkBox.setChecked(scelta);
	}

	public Button getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBoxButton checkBox) {
		this.checkBox = checkBox;
	}
	
	/*
	public String getOpzione(){
		return this.alternative[this.scelta];
	}
	*/
	

}
