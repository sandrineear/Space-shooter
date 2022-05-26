/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/ReadService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications.viewer;

import tools.Position;
import tools.Sound;

import java.util.ArrayList;

import specifications.data.BulletService;
import specifications.data.EnemyService;
import specifications.data.IndestructibleService;
import specifications.data.PhantomService;

public interface ReadService {
	
	/**
	 * Recupere la position du héro
	 * @return position
	 */
	public Position getHeroesPosition();

	/**
	 * récupère la largeur du héros
	 * @return longueur
	 */
	public double getHeroesWidth();

	/**
	 * Récupère la hauteur du héros
	 * @return longueur
	 */
	public double getHeroesHeight();

	/**
	 * Récupère le nombre de pas
	 * @return nombre de pas
	 */
	public int getStepNumber();

	/**
	 * Récupère le score
	 * @return score
	 */
	public int getScore();

	/**
	 * Donne la liste des phantoms
	 * @return liste des phantoms
	 */
	public ArrayList<PhantomService> getPhantoms();

	/**
	 * Donne le son
	 * @return son
	 */
	public Sound.SOUND getSoundEffect();

	/**
	 * Donne le nombre de points de vie
	 * @return points de vie
	 */
	public int getLife();

	/**
	 * Donne la liste des indestructibles
	 * @return liste des indestructibles
	 */
	public ArrayList<IndestructibleService> getIndestructibles();

	/**
	 * Donne le nombre de hit points
	 * @return hit points
	 */
	public int getHitPoints();
	
	/**
	 * Donne la liste des bullets
	 * @return liste des bullets
	 */
	public ArrayList<BulletService> getBullet();
	
	/**
	 * Indique si le vaisseau est touché
	 * @return true si touché, false sinon
	 */
	public boolean isTouched();
	
	/**
	 * Indique si le vaisseau est vivant
	 * @return true si vivant, false sinon
	 */
	public boolean isAlive();
	
	/**
	 * Indique si le vaisseau est mort
	 * @return true si mort, false sinon
	 */
	public boolean isDead();
	
	/**
	 * Récupère la liste des ennemis
	 * @return liste des ennemis
	 */
	public ArrayList<EnemyService> getEnemies();
	
}
