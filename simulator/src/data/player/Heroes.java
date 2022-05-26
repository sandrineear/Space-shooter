package data.player;

import data.information.STATUS;
import specifications.data.HeroesService;
import tools.Position;

public class Heroes implements HeroesService{

	private Position heroesPosition;
	private int maxLife;
	private int life;
	private double heroesWidth, heroesHeight, heroesStep;	
	private STATUS status = STATUS.ALIVE;
	private long touchedTimer = 0;
	private long timer;
	private long timerBullet;
	private long shootBulletDelay;
	
	public void initHeroes(Position p, double height, double width, double step, int l, long timer, long timer2) {
		heroesPosition = p;
		maxLife = l; 
		life = maxLife;
		heroesHeight = height;
		heroesWidth = width;
		heroesStep = step;
		status = STATUS.ALIVE;
		this.timer = timer;
		timerBullet = System.currentTimeMillis();
		shootBulletDelay = timer2;
	}
	
	public int getLife() { return life; }
	public double getHeight() { return heroesHeight; }
	public double getWidth() { return heroesWidth; }
	public Position getPosition() { return heroesPosition; }
	public double getStep() { return heroesStep; }
	

	public void setHeight(double h) {  heroesHeight = h; }
	public void setWidth(double w) {  heroesWidth = w; }
	public void setPosition(Position p) { heroesPosition = p; }

	@Override
	public void touched() {
		life -= 1;
		if(life < 0) {
			status = STATUS.DEAD;
		} else {
			status = STATUS.TOUCHED;
			touchedTimer = System.currentTimeMillis();
		}
	}

	@Override
	public void updateStatus() {
		if(status == STATUS.DEAD || status == STATUS.ALIVE ) return;
		
		long current = System.currentTimeMillis();
		if((current - touchedTimer) > timer)
			status = STATUS.ALIVE;		
	}
	
	@Override
	public void gainLife() {
		if(life >= maxLife) return;
		else life += 1;
	}
	
	@Override
	public boolean maxLife() {
		return (life >= maxLife);
	}

	@Override
	public boolean isAlive() {
		return status == STATUS.ALIVE;
	}

	@Override
	public boolean isTouched() {
		return status == STATUS.TOUCHED;
	}
	
	@Override
	public boolean isDead() {
		return status == STATUS.DEAD;
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

}
