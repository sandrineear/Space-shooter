package algorithm;

import specifications.data.BulletService;
import specifications.data.EnemyService;
import specifications.data.IndestructibleService;
import specifications.data.PhantomService;
import tools.Position;

public class Mouvement {

	// -------------------------------------------------------------------------
	// Indestructibles
	// -------------------------------------------------------------------------

	/**
	 * deplace l'indestructible à gauche
	 * @param p l'indestructible
	 */
	public static void moveLeftIndestructible(IndestructibleService p) {
		p.setPosition(new Position(p.getPosition().x - p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace l'indestructible à droite
	 * @param p l'indestructible
	 */
	public static void moveRightIndestructible(IndestructibleService p) {
		p.setPosition(new Position(p.getPosition().x + p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace l'indestructible en haut
	 * @param p l'indestructible
	 */
	public static void moveUpIndestructible(IndestructibleService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y - p.getStep()));
	}

	/**
	 * deplace l'indestructible en bas
	 * @param p l'indestructible
	 */
	public static void moveDownIndestructible(IndestructibleService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y + p.getStep()));
	}

	// -------------------------------------------------------------------------
	// Phantoms
	// -------------------------------------------------------------------------

	/**
	 * deplace le phantom à gauche
	 * @param p le phantom
	 */
	public static void moveLeftPhantom(PhantomService p) {
		p.setPosition(new Position(p.getPosition().x - p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace le phantom à droite
	 * @param p le phantom
	 */	
	public static void moveRightPhantom(PhantomService p) {
		p.setPosition(new Position(p.getPosition().x + p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace le phantom en haut
	 * @param p le phantom
	 */
	public static void moveUpPhantom(PhantomService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y - p.getStep()));
	}

	/**
	 * deplace le phantom en bas
	 * @param p le phantom
	 */
	public static void moveDownPhantom(PhantomService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y + p.getStep()));
	}

	// -------------------------------------------------------------------------
	// Enemies
	// -------------------------------------------------------------------------

	/**
	 * deplace l'ennemi à gauche
	 * @param p l'ennemi
	 */
	public static void moveLeftEnemy(EnemyService p) {
		p.setPosition(new Position(p.getPosition().x - p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace l'ennemi à droite
	 * @param p l'ennemi
	 */
	public static void moveRightEnemy(EnemyService p) {
		p.setPosition(new Position(p.getPosition().x + p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace l'ennemi en haut
	 * @param p l'ennemi
	 */
	public static void moveUpEnemy(EnemyService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y - p.getStep()));
	}

	/**
	 * deplace l'ennemi en bas
	 * @param p l'ennemi
	 */
	public static void moveDownEnemy(EnemyService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y + p.getStep()));
	}

	// -------------------------------------------------------------------------
	// Bullet
	// -------------------------------------------------------------------------

	/**
	 * deplace la bullet à gauche
	 * @param p la bullet
	 */
	public static void moveLeftBullet(BulletService p) {
		p.setPosition(new Position(p.getPosition().x - p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace la bullet à droite
	 * @param p la bullet
	 */
	public static void moveRightBullet(BulletService p) {
		p.setPosition(new Position(p.getPosition().x + p.getStep(), p.getPosition().y));
	}

	/**
	 * deplace la bullet en haut
	 * @param p la bullet
	 */
	public static void moveUpBullet(BulletService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y - p.getStep()));
	}

	/**
	 * deplace la bullet en bas
	 * @param p la bullet
	 */
	public static void moveDownBullet(BulletService p) {
		p.setPosition(new Position(p.getPosition().x, p.getPosition().y + p.getStep()));
	}

}
