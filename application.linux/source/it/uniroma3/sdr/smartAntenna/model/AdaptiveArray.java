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
public class AdaptiveArray implements SmartAntenna {
	
	private Simulazione parent;
	private List<Lobo> lobiAccesi, lobiSpenti;
	private int color;
	private int size;
	private int posX, posY;
	// Un delta e' l'ampiezza di un piccolo angolo di competenza di un singolo lobo
	private float delta;

	public AdaptiveArray(Simulazione parent, int numLobi, int size, int posX, int posY, int color){
		this.parent = parent;
		this.size = size*4/3;
		this.lobiSpenti = new ArrayList<Lobo>();
		for (int i = 0; i < numLobi; i++)
			this.lobiSpenti.add(new Lobo(parent, (int)(4*PApplet.PI*this.size/numLobi*2), this.size, posX, posY, color, 2*PApplet.PI*((float)i/(float)numLobi)));
		for (Lobo l: this.lobiSpenti)
			l.setAcceso(false);
		this.lobiAccesi = new ArrayList<Lobo>();
		this.delta = 2*PApplet.PI/numLobi;
		this.posX = posX;
		this.posY = posY;
	}
	
	
	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.model.SmartAntenna#draw()
	 */
	@Override
	public void draw() {
		for(Lobo l: this.lobiSpenti)
			l.draw();
		for(Lobo l: this.lobiAccesi)
			l.draw();
		
		if (this.parent.isShowInfo()) {
			/*
			int lobiAccesi = 0;
			for (Lobo l: this.lobi)
				if (l.isAcceso())
					lobiAccesi++;
			*/
			this.parent.fill(0);
			String info =
				  "Lunghezza lobi:       "+this.size*3/4+
				"\nNumero lobi:          "+this.lobiSpenti.size()+
				"\nLobi Accesi:          "+this.lobiAccesi.size()+
				"\nAmpiezza angolo lobo: "+PApplet.degrees(this.delta);
			
			this.parent.text(info, this.posX, this.posY);
		}
	}

	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.model.SmartAntenna#follow(it.uniroma3.sdr.smartAntenna.model.Signal)
	 */
	@Override
	public void follow(List<Signal> signalsOfInterest) {
		/*
		 * funziona ma lo modifico per renderlo piu' efficiente 
		 
		for (Lobo l: this.lobi){
			l.setAcceso(false);
			for (Signal s: signalsOfInterest)
				if (PApplet.abs(s.getAtan(this)-l.getRotation())<=this.delta || PApplet.abs(s.getAtan(this)-l.getRotation()) >= Simulazione.PI*2-this.delta) // il secondo controllo e' necessario per evitare un buggetto che non copriva mezzo delta...
					l.setAcceso(true);
		}
		*/
		// ok, il problema dell'efficienza era dovuto alla trasparenza dei lobi spenti...
		for (Lobo l:this.lobiAccesi)
			l.setAcceso(false);
		this.lobiAccesi.clear();
		for (Signal s: signalsOfInterest)
			// solo i segnali abbastanza vicini vengono rilevati:
			if (Simulazione.dist(this.posX, this.posY, s.getPosX(), s.getPosY()) < this.size + 10)
				this.accendi(this.lobiSpenti.get((int) ((s.getAtan(this)+this.delta/2)/this.delta)%this.lobiSpenti.size()));
		for (Lobo l:this.lobiAccesi)
			l.setAcceso(true);
	}

	private void accendi(Lobo lobo) {
		if (!lobo.isAcceso())
			this.lobiAccesi.add(lobo);
		lobo.setAcceso(true);
	}


	/* (non-Javadoc)
	 * @see it.uniroma3.sdr.smartAntenna.model.SmartAntenna#azzera(it.uniroma3.sdr.smartAntenna.model.Signal)
	 */
	@Override
	public void azzera(List<Signal> signalsNotOfInterest) {
		// TODO Auto-generated method stub

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
		
		for (Lobo l: this.lobiSpenti) {
			this.size = size;
			l.setWidth((int)(4*PApplet.PI*this.size/this.lobiSpenti.size()*2));
			l.setHeigth(this.size);
		}
	}

}
