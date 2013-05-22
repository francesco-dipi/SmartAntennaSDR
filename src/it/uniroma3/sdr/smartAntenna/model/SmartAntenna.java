/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import java.util.List;

/**
 * @author dipi
 *
 */
public interface SmartAntenna extends Antenna {
	
	public void draw();
	
	public void follow(List<Signal> signalsOfInterest);
	
	public void azzera(List<Signal> signalsNotOfInterest);

	public int getPosX();

	public int getPosY();
	
	public int getSize();
	public void setSize(int size);

}
