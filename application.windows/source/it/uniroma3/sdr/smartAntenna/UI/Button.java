/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.UI;

import it.uniroma3.sdr.smartAntenna.Simulazione;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author dipi
 *
 */
public abstract class Button {

	private PVector pos;
	private int basecolor, highlightcolor;
	private int currentcolor;
	private boolean over;
	private boolean pressed;
	Simulazione parent;
	private String testo;
	private int borderColor;
	
	
	public Button(PApplet parent, String testo, PVector pos, int basecolor, int highlightcolor){
		this.parent = (Simulazione) parent;
		this.setTesto(testo);
		this.pos = pos;
		this.basecolor = basecolor;
		this.highlightcolor = highlightcolor;
		this.pressed = false;
		this.over = false;
		this.currentcolor = basecolor;
		this.borderColor = 0;
	}

	public void update(int mouseX, int mouseY) {
		this.over = this.over(mouseX, mouseY);
		
		if (this.over) {
			this.setCurrentcolor(this.highlightcolor);
		} else {
			this.setCurrentcolor(this.basecolor);
		}
	}

	public boolean pressed() {
		if (this.over) {
			return true;
		} else 
			return false;
	}
	
	public void chancgeColor(){
		if (this.currentcolor == this.basecolor)
			this.currentcolor = this.highlightcolor;
		else this.currentcolor = this.basecolor;
			
	}

	public abstract boolean over(int mouseX, int mouseY);
	
	public abstract void draw();

	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public void setCurrentcolor(int currentcolor) {
		this.currentcolor = currentcolor;
	}

	public int getCurrentcolor() {
		return currentcolor;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getTesto() {
		return testo;
	}

	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}

	public int getBorderColor() {
		return borderColor;
	}

}
