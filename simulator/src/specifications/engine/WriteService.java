/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/WriteService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications.engine;

import tools.Position;
import tools.Sound;

import java.util.ArrayList;

import specifications.data.BulletService;
import specifications.data.EnemyService;
import specifications.data.IndestructibleService;
import specifications.data.PhantomService;

public interface WriteService {
	
	/**
	 * Modifie la position du héros
	 * @param p nouvelle position
	 */
	public void setHeroesPosition(Position p);

	/**
	 * Modifie le nombre de pas
	 * @param p nouveau nombre de pas
	 */
	public void setStepNumber(int n);

	/**
	 * Ajoute un phantom à la position p
	 * @param p position du phantom à ajouter
	 */
	public void addPhantom(Position p);

	/**
	 * Remplace la liste de phantoms par une nouvelle liste
	 * @param phantoms nouvelle liste de phantoms
	 */
	public void setPhantoms(ArrayList<PhantomService> phantoms);

	/**
	 * Modifie le son
	 * @param s le son
	 */
	public void setSoundEffect(Sound.SOUND s);

	/**
	 * ajoute des points au score
	 * @param score nombre de points à ajouter
	 */
	public void addScore(int score);
	
	/**
	 * ajoute un indestructibles à la position p
	 * @param p position de l'indestructible
	 */
	public void addIndestructible(Position p);

	/**
	 * Remplace la liste de indestructibles par une nouvelle liste
	 * @param indestructibles nouvelle liste
	 */
	public void setIndestructibles(ArrayList<IndestructibleService> indestructibles);
	
	/**
	 * ajoute des points aux hits points
	 * @param hit nombre de point à ajouter
	 */
	public void addHitPoints(int hit);
	
	/**
	 * Remplace la liste de bullets par une nouvelle liste
	 * @param bullets nouvelle liste
	 */
	public void setBullet(ArrayList<BulletService> bullets);
	
	/**
	 * met à jour le status du héros
	 */
	public void updateStatusHero();
	
	/**
	 * met à jour le status des phantoms
	 */
	public void updateStatusPhantoms();
	
	/**
	 * met à jour le status des ennemis
	 */
	public void updateStatusEnemies();
	
	/**
	 * met à jour le nombre de vie
	 */
	public void updateLife();
	
	/**
	 * le héros a été touché
	 */
	public void touched();
	
	/**
	 * ajoute une bullet provenant de l'ennemi
	 * @param enemy ennemi dont provient la bullet
	 */
	public void addEnnemiesBullet(EnemyService enemy);
	
	/**
	 * ajoute une bullet provenant du héros
	 */
	public void addHeroesBullet();
	
	/**
	 * ajoute un ennemi à la position p
	 * @param p position où apparait l'ennemi
	 */
	public void addEnemy(Position p);

	/**
	 * Remplace la liste d'ennemis par une nouvelle liste
	 * @param enemies nouvelle liste
	 */
	public void setEnemies(ArrayList<EnemyService> enemies);
	
	
}
