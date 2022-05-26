package specifications.data;

import tools.Position;

public interface HeroesService {

	/**
	 * Initialise le héro
	 * @param position position initiale
	 * @param heroesheight hauteur
	 * @param heroeswidth largeur
	 * @param step pas
	 * @param life nombre de vie maximale
	 * @param timer durée durant laquelle il peut rester en mode STATUS.TOUCHED
	 * @param timerBullet fréquence de tir (durée minimale entre chaque bullet créée)
	 */
	void initHeroes(Position position, double heroesheight, double heroeswidth, double step,
			int life, long timer, long timerBullet);
	
	public int getLife();
	public double getHeight();
	public double getWidth();
	public Position getPosition();
	public double getStep();
	
	public void setHeight(double h);
	public void setWidth(double w);
	public void setPosition(Position p);
	
	/**
	 * le héro est touché
	 */
	public void touched();
	
	/**
	 * met à jour son status
	 */
	public void updateStatus();
	
	
	public boolean isAlive();
	public boolean isTouched();
	public boolean isDead();
	
	/** 
	 * Gagne une vie
	 */
	public void gainLife();
	
	/**
	 * Indique si la vie du héro est maximale
	 * @return vrai si max, false sinon
	 */
	public boolean maxLife();
	
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