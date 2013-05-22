/**
 * 
 */
package it.uniroma3.sdr.smartAntenna.model;

import it.uniroma3.sdr.smartAntenna.Simulazione;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

/**
 * @author dipi
 *
 */
public class SwitchedBeam implements SmartAntenna {
	
	private Simulazione parent;
	private int posX, posY;
	private int color;
	private int size;
	private List<LoboSmart> lobi;
	private List<Signal> interferenze;
	
	public SwitchedBeam(Simulazione parent, int size, int posX, int posY, int color){
		this.parent = parent;
		this.size = size*4/3;
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.lobi = new ArrayList<LoboSmart>();
		this.interferenze = new ArrayList<Signal>();
	}

	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.model.SmartAntenna#draw()
	 */
	@Override
	public void draw() {
		this.parent.pushStyle();
		this.parent.fill(this.parent.color(0,0,255));
		this.parent.ellipse(posX, posY, 10, 10);
		this.parent.popStyle();
		for (LoboSmart l: this.lobi) {
			l.follow();
			// solo i segnali abbastanza vicini vengono rilevati:
			if (Simulazione.dist(this.getPosX(), this.getPosY(), l.getSignal().getPosX(), l.getSignal().getPosY()) < this.size + 10)
				l.setAcceso(true);
			else l.setAcceso(false);
			if (l.getHeigth() > this.size)
				l.setHeigth(this.size);
			l.draw();
		}
		
		if (this.parent.isShowInfo()) {
			this.parent.fill(0);
			String info = "Numero lobi: "+this.lobi.size();
			info += "\nLunghezza massima: "+this.size*3/4;
			this.parent.text(info, this.posX, this.posY);
			this.parent.fill(200,50,50);
			for (Signal s: this.interferenze)
				this.parent.line(this.posX, this.posY, s.getPosX(), s.getPosY());
		}
	}

	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.model.SmartAntenna#follow(java.util.List)
	 */
	@Override
	public void follow(List<Signal> signalsOfInterest) {
		/*
		 * Penso che sia abbastanza inefficiente... 
		this.lobi = new ArrayList<Lobo>();
		for (Signal s: signalsOfInterest)
			this.lobi.add(new Lobo(this.parent, this.parent.height/6, (int) PApplet.dist(this.posX, this.posY, s.getPosX(), s.getPosY())*3/2, this.posX, this.posY, this.color, s.getAtan(this)));
		*/
		if (this.lobi.size() != signalsOfInterest.size()) {
			this.lobi = new ArrayList<LoboSmart>();
			for (Signal s: signalsOfInterest)
				this.lobi.add(new LoboSmart(this.parent, this.parent.height/6, (int) PApplet.dist(this.posX, this.posY, s.getPosX(), s.getPosY())*3/2, this.posX, this.posY, this.color, s.getAtan(this), s));
		}
/*		else 
			for (int i = 0; i<signalsOfInterest.size(); i++){
				this.lobi.get(i).setRotation(signalsOfInterest.get(i).getAtan(this));
				this.lobi.get(i).setHeigth((int) PApplet.dist(this.posX, this.posY, signalsOfInterest.get(i).getPosX(), signalsOfInterest.get(i).getPosY())*3/2);
			}
			*/
	}

	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.model.SmartAntenna#azzera(java.util.List)
	 */
	@Override
	public void azzera(List<Signal> signalsNotOfInterest) {
		if (this.interferenze.size() != signalsNotOfInterest.size()) 
			this.interferenze = signalsNotOfInterest;
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

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public void setSize(int size) {
		this.size = size;
	}

}
