package algorithm;

import data.information.SIDE;
import specifications.data.BulletService;
import specifications.data.DataService;
import specifications.data.EnemyService;
import specifications.data.IndestructibleService;
import specifications.data.PhantomService;

public class GestionCollision {

	/**
	 * Collision entre le héros et le phantom
	 * @param data le Data pour avoir le héro
	 * @param p le phantom
	 * @return vrai le héro entre en collision avec le phantom, faux sinon
	 */
	public static boolean collisionHeroesPhantom(DataService data, PhantomService p) {
		return (p.isAlive()) && ((data.getHeroesPosition().x - p.getPosition().x) * (data.getHeroesPosition().x - p.getPosition().x)
				+ (data.getHeroesPosition().y - p.getPosition().y)
						* (data.getHeroesPosition().y - p.getPosition().y) < 0.25
								* (data.getHeroesWidth() + p.getPhantomWidth())
								* (data.getHeroesWidth() + p.getPhantomWidth()));
	}
	
	/**
	 * Collision entre le héros et l'indestructible
	 * @param data le Data où se trouve le héro
	 * @param p l'indestructible
	 * @return vrai le héro entre en collision avec l'indestructible, faux sinon
	 */
	public static boolean collisionHeroesIndestructible(DataService data, IndestructibleService p) {
		return ((data.getHeroesPosition().x - p.getPosition().x) * (data.getHeroesPosition().x - p.getPosition().x)
				+ (data.getHeroesPosition().y - p.getPosition().y)
						* (data.getHeroesPosition().y - p.getPosition().y) < 0.25
								* (data.getHeroesWidth() + p.getIndestructibleWidth())
								* (data.getHeroesWidth() + p.getIndestructibleWidth()));
	}
	
	/**
	 * Collision entre le héros et l'ennemi
	 * @param data le Data où se trouve le héro
	 * @param p l'ennemi
	 * @return vrai le héro entre en collision avec l'ennemi, faux sinon
	 */
	public static boolean collisionHeroesEnemy(DataService data, EnemyService p) {
		return (p.isAlive()) && ((data.getHeroesPosition().x - p.getPosition().x) * (data.getHeroesPosition().x - p.getPosition().x)
				+ (data.getHeroesPosition().y - p.getPosition().y)
						* (data.getHeroesPosition().y - p.getPosition().y) < 0.25
								* (data.getHeroesWidth() + p.getWidth())
								* (data.getHeroesWidth() + p.getWidth()));
	}

	/**
	 * collision entre le héro et la bullet provenant de l'ennemi
	 * @param data le Data où se trouve le héro
	 * @param b bullet provenant de l'ennemi
	 * @return vrai si collision, faux si pas de collision ou si la bullet ne provient pas de l'ennemi
	 */
	public static boolean collisionHeroesBullet(DataService data, BulletService b) {
		return (b.getType() == SIDE.Ennemy) &&
			((data.getHeroesPosition().x - b.getPosition().x) * (data.getHeroesPosition().x - b.getPosition().x)
				+ (data.getHeroesPosition().y - b.getPosition().y) * (data.getHeroesPosition().y - b.getPosition().y) 
				< 0.25 * (data.getHeroesWidth() + b.getWidth()) * (data.getHeroesWidth() + b.getWidth()));
	}
	
	/**
	 * collision entre la bullet du héro et un phantom
	 * @param data Data où se trouve la liste des phantoms
	 * @param b la bullet
	 * @return un phantom si il entre en collision avec la bullet, null si pas de collision ou la 
	 * bullet ne provient pas du héro
	 */
	public static PhantomService collisionPhantomBullet(DataService data, BulletService b) {
		if(b.getType() != SIDE.Heroes) return null;
		for(PhantomService p : data.getPhantoms()) {
			if((p.isAlive()) && (p.getPosition().x - b.getPosition().x) * (p.getPosition().x - b.getPosition().x)
				+ (p.getPosition().y - b.getPosition().y) * (p.getPosition().y - b.getPosition().y) 
						< 0.25 * (p.getPhantomWidth() + b.getWidth()) * (p.getPhantomWidth() + b.getWidth()))
				return p;
			}
		return null;	
	}
	
	/**
	 * collision entre la bullet du héro et un indestructible
	 * @param data Data où se trouve la liste des indestructible
	 * @param b la bullet
	 * @return vrai s'il entre en collision avec la bullet, faux si pas de collision ou la 
	 * bullet ne provient pas du héro
	 */
	public static boolean collisionIndestructibleBullet(DataService data, BulletService b) {
		if(b.getType() != SIDE.Heroes) return false;
		for(IndestructibleService p : data.getIndestructibles()) {
			if((p.getPosition().x - b.getPosition().x) * (p.getPosition().x - b.getPosition().x)
				+ (p.getPosition().y - b.getPosition().y) * (p.getPosition().y - b.getPosition().y) 
						< 0.25 * (p.getIndestructibleWidth() + b.getWidth()) * (p.getIndestructibleWidth() + b.getWidth()))
				return true;
			}
		return false;	
	}
	
	/**
	 * collision entre la bullet du héro et un ennemi
	 * @param data Data où se trouve la liste des ennemis
	 * @param b la bullet
	 * @return l'ennemi s'il entre en collision avec la bullet, null si pas de collision ou la 
	 * bullet ne provient pas du héro
	 */	
	public static EnemyService collisionHeroesBulletEnemy(DataService data, BulletService b) {
		if(b.getType() != SIDE.Heroes) return null;
		for(EnemyService p : data.getEnemies()) {
			if((p.getPosition().x - b.getPosition().x) * (p.getPosition().x - b.getPosition().x)
				+ (p.getPosition().y - b.getPosition().y) * (p.getPosition().y - b.getPosition().y) 
						< 0.25 * (p.getWidth() + b.getWidth()) * (p.getWidth() + b.getWidth()))
				return p;
			}
		return null;	
	}
	
}
