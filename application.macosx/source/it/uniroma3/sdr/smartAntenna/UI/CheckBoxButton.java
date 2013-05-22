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
public class CheckBoxButton extends Button {
	
	private boolean checked;
	private int sizeX, sizeY;
	
	public CheckBoxButton(PApplet parent, String testo, PVector pos, PVector size, int basecolor, int highlightcolor, boolean checked){
		super(parent, testo, pos, basecolor, highlightcolor);
		if (checked)
			this.press();
		this.sizeX = (int) size.x;
		this.sizeY = (int) size.y;
	}

	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.UI.Button#over(int, int)
	 */
	@Override
	public boolean over(int mouseX, int mouseY) {
		if (mouseX >= this.getPos().x - this.sizeX/2 && mouseX <= this.getPos().x + this.sizeX/2 && 
		      mouseY >= this.getPos().y - this.sizeY/2 && mouseY <= this.getPos().y + this.sizeY/2) {
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
		this.parent.pushStyle();
		
		this.parent.strokeWeight(3);
		this.parent.stroke(this.getBorderColor());
		
		this.parent.fill(this.getCurrentcolor());
		this.parent.rectMode(PApplet.CENTER);
		this.parent.rect(/*this.size.x, this.size.y, */this.getPos().x, this.getPos().y, this.sizeX, this.sizeY);
		
		this.parent.fill(0);
		this.parent.setFontGrande();
		this.parent.textAlign(PApplet.CENTER);
		this.parent.text(this.getTesto(), this.getPos().x, this.getPos().y+this.sizeY/3);		
		this.parent.popStyle();
	}
	
	public boolean pressed() {
		if (this.over(this.parent.mouseX, this.parent.mouseY)) {
			this.press();
			return true;
		} else 
			return false;
	}
	
	public void press(){
		this.checked = !this.checked;
		this.chancgeColor();
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	

}
