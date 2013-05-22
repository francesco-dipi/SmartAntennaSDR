/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.UI;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author dipi
 * 
 */
public class CircleButton extends Button {
	
	public CircleButton(PApplet parent, String testo, PVector pos, int basecolor,
			int highlightcolor) {
		super(parent, testo, pos, basecolor, highlightcolor);
		// TODO Auto-generated constructor stub
	}

	private int diameter;

	@Override
	public boolean over(int mouseX, int mouseY) {
	    float disX = this.getPos().x - mouseX;
	    float disY = this.getPos().y - mouseY;
	    if(PApplet.sqrt(PApplet.sq(disX) + PApplet.sq(disY)) < this.diameter/2 ) {
	      return true;
	    } 
	    else
	      return false;
	}

	@Override
	public void draw() {
		// push dello stile e della matrice
		this.parent.pushStyle();
		this.parent.pushMatrix();

		// traslazione
		this.parent.translate(this.getPos().x, this.getPos().y);

		// cerchio del colore della bilia
		this.parent.fill(this.getCurrentcolor());
		this.parent.ellipseMode(PApplet.CENTER);
		this.parent.ellipse(0, 0, this.diameter, this.diameter);

		// pop dello stile e della matrice
		this.parent.popMatrix();
		this.parent.popStyle();
	}
	
	
	

}
