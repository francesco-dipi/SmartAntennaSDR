/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import processing.core.PApplet;
import it.uniroma3.sdr.smartAntenna.Simulazione;

/**
 * @author dipi
 *
 */
public class LoboSmart extends Lobo {

	private Signal signal;
	
	public LoboSmart(Simulazione parent, int sizeX, int sizeY, int posX,
			int posY, int color, float rotation) {
		super(parent, sizeX, sizeY, posX, posY, color, rotation);
		this.setSignal(null);
	}
	
	public LoboSmart(Simulazione parent, int sizeX, int sizeY, int posX,
			int posY, int color, float rotation, Signal signal) {
		super(parent, sizeX, sizeY, posX, posY, color, rotation);
		this.setSignal(signal);
	}

	public void setSignal(Signal signal) {
		this.signal = signal;
	}

	public Signal getSignal() {
		return signal;
	}
	
	public void draw() {
		super.draw();
		if (super.getParent().isShowInfo()) {
			super.getParent().fill(0);
			String info =
				  "Lunghezza lobo:             " + super.getHeigth()*3/4+
				"\nDistanza segnale - antenna: " + (int)PApplet.dist(super.getPosX(), super.getPosY(), this.signal.getPosX(), this.signal.getPosY());
			
			super.getParent().text(info, this.signal.getPosX()+10, this.signal.getPosY());
		}
		
	}

	public void follow() {
		super.setRotation(this.signal.getAtan(super.getPosX(), super.getPosY()));
		super.setHeigth((int) PApplet.dist(super.getPosX(), super.getPosY(), this.signal.getPosX(), this.signal.getPosY())*3/2);
	}

}
