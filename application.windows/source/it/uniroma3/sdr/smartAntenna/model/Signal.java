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
public class Signal {
	
	private Simulazione parent;
	private int posX, posY;
	private PVector vel;
	private boolean isSOI;
	private int color;
	
	public Signal(Simulazione parent, PVector posIniziale, PVector velIniziale, boolean isSOI){
		this.parent = parent;
		this.posX = (int) posIniziale.x;
		this.posY = (int) posIniziale.y;
		this.vel = velIniziale;
		this.isSOI = isSOI;
		if (this.isSOI)
			this.color = this.parent.color(30, 255, 30);
		else this.color = this.parent.color(255, 30, 30);
	}
	
	public void draw(){
//		this.parent.pushMatrix();
		this.parent.fill(this.color);
		this.parent.ellipseMode(Simulazione.CENTER);
		this.parent.ellipse(this.posX, this.posY, 10, 10);
		if (this.parent.isShowInfo()){
			this.parent.textAlign(Simulazione.RIGHT);
			this.parent.fill(0);
			String info =
				  "Posizione:   "+this.posX+", "+this.posY+
				"\nVelocita':   ["+(float)((int)(this.vel.x*10))/10+", "+(float)(((int)this.vel.y*10))/10+"]";
			this.parent.text(info, this.posX-10, this.posY);
		}
		this.update();
//		this.parent.popMatrix();
	}
	
	public void update(){
		this.posX += this.vel.x;
		this.posY += this.vel.y;
		if (this.posX<=0 || this.posX>=this.parent.width)
			this.vel.x *= -1;
		if (this.posY<=0 || this.posY>=this.parent.height)
			this.vel.y *= -1;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public PVector getVel() {
		return vel;
	}

	public void setVel(PVector vel) {
		this.vel = vel;
	}

	public boolean isSOI() {
		return isSOI;
	}

	public void setSOI(boolean isSOI) {
		this.isSOI = isSOI;
	}

	public float getAtan(SmartAntenna antenna) {
		return (2*Simulazione.PI-Simulazione.atan2(this.posX-antenna.getPosX(), this.posY-antenna.getPosY()))%(2*Simulazione.PI);
	}
	
	public float getAtan(int posX, int posY) {
		return (2*Simulazione.PI-Simulazione.atan2(this.posX-posX, this.posY-posY))%(2*Simulazione.PI);
	}

	public Simulazione getParent() {
		return parent;
	}

	public void setParent(Simulazione parent) {
		this.parent = parent;
	}
	
	

}
