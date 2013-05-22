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
public class RectButton extends Button {
	
	private PVector size;
	
	public RectButton(PApplet parent, String testo, PVector pos, PVector size, int basecolor, int highlightcolor) {
		super(parent, testo, pos, basecolor, highlightcolor);
		this.size = size;
	}
	
	public RectButton(PApplet parent, String testo, PVector pos, PVector size, int baseColor, int highlightColor, int borderColor) {
		this(parent, testo, pos, size, baseColor, highlightColor);
		super.setBorderColor(borderColor);
	}
	
	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.UI.Button#over(processing.core.PVector)
	 */
	@Override
	public boolean over(int mouseX, int mouseY) {
	    if (mouseX >= this.getPos().x - this.size.x/2 && mouseX <= this.getPos().x + this.size.x/2 && 
	      mouseY >= this.getPos().y - this.size.y/2 && mouseY <= this.getPos().y + this.size.y/2) {
	      return true;
	    } 
	    else
	      return false;
	}

	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.UI.Button#draw()
	 */
	@Override
	public void draw() {

//		if (this.over(this.parent.mouseX, this.parent.mouseY))
		this.update(this.parent.mouseX, this.parent.mouseY);
		this.parent.pushStyle();
		
		this.parent.strokeWeight(3);
		this.parent.stroke(this.getBorderColor());
		
		this.parent.fill(this.getCurrentcolor());
		this.parent.rectMode(PApplet.CENTER);
		this.parent.rect(/*this.size.x, this.size.y, */this.getPos().x, this.getPos().y, this.size.x, this.size.y);
		
		this.parent.fill(0);
		if (this.getTesto().equals(this.getTesto().toUpperCase()))
			this.parent.setFontGrande();
		else this.parent.setFontMedio();
		this.parent.textAlign(PApplet.CENTER);
		this.parent.text(this.getTesto(), this.getPos().x, this.getPos().y+this.size.y/3);		
		this.parent.popStyle();
	}

}
