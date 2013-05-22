/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import it.uniroma3.sdr.smartAntenna.Simulazione;

/**
 * @author dipi
 *
 */
public class Lobo {
	
	private Simulazione parent;
	private int color;
	private int width, heigth;
	private int cx1, cy1, cx2, cy2;
	private int posX, posY;
	private float rotation;
	private boolean acceso;
	
	
	public Lobo(Simulazione parent, int sizeX, int sizeY, int posX, int posY,  int color, float rotation){
		this.parent = parent;
		this.width = sizeX;
		this.heigth = sizeY;
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.setRotation(rotation);
		this.setAcceso(true);
//		this.cx1 = this.posX - this.width/2;
//		this.cy1 = this.posY + this.heigth;
//		this.cx2 = this.posX + this.width/2;
//		this.cy2 = this.posY + this.heigth;
		
		this.cx1 = - this.width/3;
		this.cy1 =  this.heigth;
		this.cx2 =  this.width/3;
		this.cy2 =  + this.heigth;
	}
	
	public void draw(){
		this.parent.pushMatrix();
		this.parent.translate(this.posX, this.posY);
//		this.parent.translate(this.parent.width/2, this.parent.height/2);
//		this.parent.translate(this.parent.width/2 + PApplet.cos(this.rotation)*this.heigth/2, this.parent.height/2 + PApplet.sin(this.rotation)*this.heigth/2);
//		this.parent.translate(this.posX - PApplet.sin(this.rotation)*this.heigth/2, this.posY - PApplet.cos(this.rotation)*this.heigth/2);
		this.parent.rotate(this.rotation);
		
		this.parent.stroke(180);
		if (this.acceso)
			this.parent.fill(this.color);
		else 
			//this.parent.fill(0,0);	//TODO probabilmente la trasparenza non e' efficiente...
			this.parent.noFill();		// esatto, cosi' e' molto piu veloce (~ 20 fps in piu'... ovvero il 50%!)
//		this.parent.bezier(this.posX, this.posY, this.cx1, this.cy2, this.cx2, this.cy2, this.posX, this.posY);
		this.parent.bezier(0, 0, this.cx1, this.cy1, this.cx2, this.cy2, 0, 0);
		// linee della bezier:
//		this.parent.line(0, 0, this.cx1, this.cy1);
//		this.parent.line(0, 0, this.cx2, this.cy2);
	
		this.parent.fill(0);
		if (this.acceso && this.parent.isShowInfo())
			this.parent.line(0, 0, 0, this.heigth*3/4);
//		this.parent.ellipseMode(PApplet.CENTER);
//		this.parent.ellipse(0, 0, this.width, this.heigth);

		this.parent.popMatrix();
	
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public float getRotation() {
		return rotation;
	}

	public void setAcceso(boolean acceso) {
		this.acceso = acceso;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.cx1 = - this.width/3;
		this.cx2 =  this.width/3;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
		this.cy1 =  this.heigth;
		this.cy2 =  + this.heigth;
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

	public Simulazione getParent() {
		return parent;
	}

	public void setParent(Simulazione parent) {
		this.parent = parent;
	}

}
