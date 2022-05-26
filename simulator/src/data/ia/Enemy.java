package data.ia;

import data.information.MOVE;
import data.information.STATUS;
import specifications.data.EnemyService;
import tools.Position;

public class Enemy implements EnemyService {
	private Position position;
	private double height;
	private double width;
	private double step;
	private long timer;
	private STATUS status;
	private long touchedTimer = 0;
	private long destroyTime;
	private long timerBullet;
	private long shootBulletDelay;
	
	@Override
	public void initEnemy(Position p, double h, double w, double s, long t, long t2) {
		this.position = p;
		this.height = h;
		this.width = w;
		this.step = s;
		this.timer = t;
		this.status = STATUS.ALIVE;
		this.timerBullet = System.currentTimeMillis();
		this.shootBulletDelay = t2;
	}
	
	@Override 
	public double getHeight() { return this.height; }
	
	@Override 
	public double getWidth() { return this.width; }
	
	@Override 
	public Position getPosition() { return position; }
	
	@Override 
	public double getStep() { return step; }
	

	@Override 
	public void setHeight(double h) {  height = h; }
	
	@Override 
	public void setWidth(double w) {  width = w; }
	
	@Override 
	public void setPosition(Position p) { position = p; }

	@Override
	public void updateStatus() {
		if(status == STATUS.DEAD || status == STATUS.ALIVE ) return;
		
		long current = System.currentTimeMillis();
		if((current - touchedTimer) > timer)
			status = STATUS.DEAD;		
	}

	@Override
	public boolean isAlive() {
		if(status == STATUS.ALIVE) return true;
		return false;
	}

	@Override
	public boolean isTouched() {
		if(status == STATUS.TOUCHED) return true;
		return false;
	}
	
	@Override
	public boolean isDead() {
		if(status == STATUS.DEAD) return true;
		return false;
	}

	@Override
	public MOVE getAction() {
		checktimer();
		if (status == STATUS.ALIVE) return MOVE.LEFT; 	
		else return MOVE.STAY;
	}

	@Override
	public void destroyEnemy() {
		status = STATUS.DESTROY;
		destroyTime = System.currentTimeMillis();
	}

	@Override
	public boolean isDestroyed() {
		if(status == STATUS.DESTROY) return true;
		return false;
	}
	
	
	@Override
	public boolean canShoot() {
		if(System.currentTimeMillis() - timerBullet > shootBulletDelay)
			return true;
		return false;
	}
	
	@Override
	public void resetTimerBullet() {
		timerBullet = System.currentTimeMillis();
	}
	

	
	private void checktimer() {
		if(status != STATUS.DESTROY)  
			return; 	
		long current = System.currentTimeMillis();
		if((current - destroyTime) > timer)
			status = STATUS.DEAD;			
	}

}
