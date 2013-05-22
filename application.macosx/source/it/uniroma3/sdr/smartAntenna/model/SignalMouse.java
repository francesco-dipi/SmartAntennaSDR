/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import it.uniroma3.sdr.smartAntenna.Simulazione;
import processing.core.PVector;

/**
 * @author dipi
 *
 */
public class SignalMouse extends Signal {

	private static SignalMouse signalMouse;
	
	private SignalMouse(Simulazione parent){
		super(parent, new PVector(parent.mouseX, parent.mouseY), new PVector(0,0), true);
	}
	
	public static SignalMouse getInstance(Simulazione parent) {
		if (signalMouse == null)
			signalMouse = new SignalMouse(parent);
		return signalMouse;
	}
	
	@Override
	public void update(){
		this.setPosX(this.getParent().mouseX);
		this.setPosY(this.getParent().mouseY);
	}

}
