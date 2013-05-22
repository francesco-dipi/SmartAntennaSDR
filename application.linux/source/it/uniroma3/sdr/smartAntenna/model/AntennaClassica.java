/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import it.uniroma3.sdr.smartAntenna.Simulazione;
import processing.core.PApplet;

/**
 * @author dipi
 *
 */
public class AntennaClassica implements Antenna {
	
	private Simulazione parent;
	private int size; //size = raggio
	private int color;
	private int posX, posY;
	
	public AntennaClassica(Simulazione parent, int size, int posX, int posY){
		this.parent = parent;
		this.size = size;
		this.posX = posX;
		this.posY = posY;
		this.color = 0;
	}
	
	public AntennaClassica(Simulazione parent, int size, int posX, int posY, int color){
		this(parent, size, posX, posY);
		this.color = color;
	}
	
	

	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.model.Antenna#draw()
	 */
	@Override
	public void draw() {
		this.parent.pushStyle();
		
		this.parent.fill(this.color);
		this.parent.strokeWeight(0);
		this.parent.ellipseMode(PApplet.CENTER);
		
		this.parent.ellipse(this.posX, this.posY, this.size*2, this.size*2);
		
		this.parent.popStyle();
		
		if (this.parent.isShowInfo()) {
			String info = "Copertura: " + this.size;
			this.parent.fill(0);
			this.parent.text(info, this.posX, this.posY);
		}
	}

	public void drawInfo() {
		
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
