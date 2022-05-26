package specifications.data;

import data.information.MOVE;
import tools.Position;

public interface EnemyService {

	/**
	 * Initialise un nouvel ennemi
	 * @param position position
	 * @param height hauteur
	 * @param width largeur
	 * @param step pas
	 * @param timerDestruction durée durant laquelle il peut rester en mode STATUS.DESTROY
	 * @param timerBullet fréquence de tir (durée minimale entre chaque bullet créée)
	 */
	public void initEnemy(Position position, double height, double width, double step, 
			long timerDestruction, long timerBullet);

	public double getHeight();
	public double getWidth();
	public Position getPosition();
	public double getStep();
	
	public void setHeight(double h);
	public void setWidth(double w);
	public void setPosition(Position p);
	
	/**
	 * met à jour son status
	 */
	public void updateStatus();
	
	public boolean isAlive();
	public boolean isTouched();
	public boolean isDead();
	
	public MOVE getAction();
	
	public void destroyEnemy();
	public boolean isDestroyed();
	
	/**
	 * Demande s'il est possible qu'il tire une nouvelle bullet
	 * @return vrai si possible, faux sinon
	 */
	public boolean canShoot();
	
	/** 
	 * Commence à compter le temps avant son prochain tir (commence le cooldown)
	 */
	public void resetTimerBullet();
	
}
