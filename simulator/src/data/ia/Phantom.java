/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/ia/MoveLeftPhantom.java 2015-03-11 buixuan.
 * ******************************************************/
package data.ia;

import data.information.MOVE;
import data.information.STATUS;
import specifications.data.PhantomService;
import tools.Position;

public class Phantom implements PhantomService {
	private Position position;
	private double phantomWidth = 0;
	private double phantomHeight = 0;
	private double phantomStep = 1;
	private STATUS status = STATUS.ALIVE;
	private long timer;
	private long destroyTime;
	
	
	public void initPhantom(Position p, double w, double h, double s, long timer) {
		this.phantomWidth = w;
		this.phantomHeight = h;
		this.position = p;
		this.phantomStep = s;
		status = STATUS.ALIVE;
		this.timer = timer;
	}

	@Override
	public Position getPosition() { return position; }
	
	@Override
	public double getStep() { return phantomStep; }
	
	@Override
	public double getPhantomHeight() { return this.phantomHeight; }

	@Override
	public double getPhantomWidth() {	return this.phantomWidth; }

	@Override
	public MOVE getAction() { 
		if (status == STATUS.ALIVE) return MOVE.LEFT; 	
		else return MOVE.STAY;
	}

	@Override
	public void setPosition(Position p) { position = p;	}

	@Override
	public void destroyPhantom() {
		status = STATUS.DESTROY;
		destroyTime = System.currentTimeMillis();		
	}
	
	@Override
	public void updatePhantom() {
		if(status != STATUS.DESTROY)  
			return; 	
		long current = System.currentTimeMillis();
		if((current - destroyTime) > timer)
			status = STATUS.DEAD;			
	}

	@Override
	public boolean isDead() {
		if(status == STATUS.DEAD) return true;
		return false;
	}
	
	@Override
	public boolean isDestroyed() {
		if(status == STATUS.DESTROY) return true;
		return false;
	}
	
	@Override
	public boolean isAlive() {
		if(status == STATUS.ALIVE) return true;
		return false;
	}
	
	
	
	




	
	
}
