/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: engine/Engine.java 2015-03-11 buixuan.
 * ******************************************************/
package engine;

import tools.HardCodedParameters;
import tools.User;
import tools.Position;
import tools.Sound;
import specifications.binderService.RequireDataService;
import specifications.data.BulletService;
import specifications.data.DataService;
import specifications.data.EnemyService;
import specifications.data.IndestructibleService;
import specifications.data.PhantomService;
import specifications.engine.EngineService;

import java.util.Timer;
import java.util.TimerTask;

import algorithm.GestionCollision;
import algorithm.Mouvement;
import data.information.MOVE;
import data.information.SIDE;

import java.util.Random;
import java.util.ArrayList;

public class Engine implements EngineService, RequireDataService {
	private static final double 
			friction = HardCodedParameters.friction, 
			heroesStep = HardCodedParameters.heroesStep;

	private Timer engineClock;
	private DataService data;
	private Random gen;
	private boolean moveLeft, moveRight, moveUp, moveDown, shoot;
	private double heroesVX, heroesVY;


	@Override
	public void bindDataService(DataService service) {
		data = service;
	}

	@Override
	public void init() {
		engineClock = new Timer();
		gen = new Random();
		moveLeft = false;
		moveRight = false;
		moveUp = false;
		moveDown = false;
		shoot = false;
		heroesVX = 0;
		heroesVY = 0;
	}

	@Override
	public void start() {
		engineClock.schedule(new TimerTask() {
			public void run() {
							
				if (gen.nextInt(20) < 3)
					spawnPhantom();
				
				if (gen.nextInt(25) < 3)
					spawnIndestructible();
				
				if (gen.nextInt(40) < 2)
					spawnEnemy();

				updateStatusHeroes();
				updateSpeedHeroes();
				updateCommandHeroes();
				updatePositionHeroes();
				
			
				data.setSoundEffect(Sound.SOUND.None);

				updatePhantoms();
				updateIndestructible();
				updateEnemy();
				updateBullet();
				updateLife();
				
				// Lancement des bullets par le joueur (héro)
				if(shoot == true)
					data.addHeroesBullet();
	
				data.setStepNumber(data.getStepNumber() + 1);	
				
			}
		}, 0, HardCodedParameters.enginePaceMillis);
	}

	@Override
	public void stop() {
		engineClock.cancel();
	}

	
	
	// -------------------------------------------------------------------------
	// Command Heroes
	// -------------------------------------------------------------------------	

	@Override
	public void setHeroesCommand(User.COMMAND c) {
		if (c == User.COMMAND.LEFT)
			moveLeft = true;
		if (c == User.COMMAND.RIGHT)
			moveRight = true;
		if (c == User.COMMAND.UP)
			moveUp = true;
		if (c == User.COMMAND.DOWN)
			moveDown = true;
		if (c == User.COMMAND.SPACE)
			shoot = true;
	}

	@Override
	public void releaseHeroesCommand(User.COMMAND c) {
		if (c == User.COMMAND.LEFT)
			moveLeft = false;
		if (c == User.COMMAND.RIGHT)
			moveRight = false;
		if (c == User.COMMAND.UP)
			moveUp = false;
		if (c == User.COMMAND.DOWN)
			moveDown = false;
		if (c == User.COMMAND.SPACE)
			shoot = false;
	}
	
	
	// -------------------------------------------------------------------------
	// Update Heroes
	// -------------------------------------------------------------------------	

	/**
	 * ajuste la vitesse du héros
	 */
	private void updateSpeedHeroes() {
		heroesVX *= friction;
		heroesVY *= friction;
	}

	/**
	 * enregistre les déplacements en fonction des commande du joueur
	 */
	private void updateCommandHeroes() {
		if (moveLeft)
			heroesVX -= heroesStep;
		if (moveRight)
			heroesVX += heroesStep;
		if (moveUp)
			heroesVY -= heroesStep;
		if (moveDown)
			heroesVY += heroesStep;
		if (shoot)
			data.addHeroesBullet();
	}

	/**
	 * met à jour la position du héros
	 */
	private void updatePositionHeroes() {
		// Ajout de la limite du terrain de jeu pour l'avion
		if (data.getHeroesPosition().x + heroesVX > HardCodedParameters.leftLimit
				&& data.getHeroesPosition().x + heroesVX < HardCodedParameters.rightLimit
				&& data.getHeroesPosition().y + heroesVY > HardCodedParameters.upLimit
				&& data.getHeroesPosition().y + heroesVY < HardCodedParameters.downLimit) {
			data.setHeroesPosition(
					new Position(data.getHeroesPosition().x + heroesVX, data.getHeroesPosition().y + heroesVY));
		}
	}
	
	private void updateStatusHeroes() {
		data.updateStatusHero();
	}
	
	private void updateLife() {
		data.updateLife();
	}	
	
	
	// -------------------------------------------------------------------------
	// Update IA
	// -------------------------------------------------------------------------	
	
	/**
	 * met à jour la liste des phantoms
	 */
	private void updatePhantoms() {
		ArrayList<PhantomService> phantoms = new ArrayList<PhantomService>();
		
		// met à jour le status des phantoms
		data.updateStatusPhantoms();
		
		for (PhantomService p : data.getPhantoms()) {
			
			// si le phantom n'est pas mort, on peut le garder
			if(!p.isDead()) {
				
				// bouge le phantom
				if (p.getAction() == MOVE.LEFT)
					Mouvement.moveLeftPhantom(p);
				if (p.getAction() == MOVE.RIGHT)
					Mouvement.moveRightPhantom(p);
				if (p.getAction() == MOVE.UP)
					Mouvement.moveUpPhantom(p);
				if (p.getAction() == MOVE.DOWN)
					Mouvement.moveDownPhantom(p);
	
				// s'il y a collision, le héro est touché et le phantom est mort, sinon on le garde
				// uniquement s'il ne sort pas de la limite du jeu
				if (GestionCollision.collisionHeroesPhantom(data,p)) {
					data.touched();
					data.setSoundEffect(Sound.SOUND.HeroesGotHit);
				} else {
					if (p.getPosition().x > HardCodedParameters.leftLimit)
						phantoms.add(p);
				}
			}
		}
		data.setPhantoms(phantoms);	
	}
	
	/**
	 * met à jour la liste des indestructible
	 */
	private void updateIndestructible() {
		ArrayList<IndestructibleService> indestructibles = new ArrayList<IndestructibleService>();
		
		// déplace les indestructibles
		for (IndestructibleService p : data.getIndestructibles()) {
			if (p.getAction() == MOVE.LEFT)
				Mouvement.moveLeftIndestructible(p);
			if (p.getAction() == MOVE.RIGHT)
				Mouvement.moveRightIndestructible(p);
			if (p.getAction() == MOVE.UP)
				Mouvement.moveUpIndestructible(p);
			if (p.getAction() == MOVE.DOWN)
				Mouvement.moveDownIndestructible(p);

			// s'il y a collision, le héro est touché
			// on garde l'indestructible s'il ne sort pas de la limite du jeu et qu'il n'y a pas eu collision
			if(GestionCollision.collisionHeroesIndestructible(data,p)) {
				data.touched();
				data.setSoundEffect(Sound.SOUND.TouchIndestructible);
			} else {
				if (p.getPosition().x > HardCodedParameters.leftLimit)
					indestructibles.add(p);
			}			
		}
		data.setIndestructibles(indestructibles);
	}
	
	private void updateEnemy() {
		ArrayList<EnemyService> enemies = new ArrayList<EnemyService>();
		
		// met à jour le status des ennemis
		data.updateStatusEnemies();

		for (EnemyService p : data.getEnemies()) {
			
			// si l'ennemi n'est pas mort, on peut le garder
			if(!p.isDead()) {
				
				// bouge l'ennemi
				if (p.getAction() == MOVE.LEFT)
					Mouvement.moveLeftEnemy(p);
				if (p.getAction() == MOVE.RIGHT)
					Mouvement.moveRightEnemy(p);
				if (p.getAction() == MOVE.UP)
					Mouvement.moveUpEnemy(p);
				if (p.getAction() == MOVE.DOWN)
					Mouvement.moveDownEnemy(p);
	
				// s'il y a collision, le héro est touché et l'ennemi est mort, sinon on le garde
				// uniquement s'il ne sort pas de la limite du jeu
				if(GestionCollision.collisionHeroesEnemy(data,p)) {
					data.touched();
					data.addHitPoints(1);
					data.addScore(HardCodedParameters.enemyPoint);
					data.setSoundEffect(Sound.SOUND.HeroesGotHit);
				} else {
					if (p.getPosition().x > HardCodedParameters.leftLimit)
						enemies.add(p);
				}
			}
			// Lancement des bullets par les ennemies (si le cooldown le permet)
			data.addEnnemiesBullet(p);
			
		}
		data.setEnemies(enemies);
	}
	
	private void updateBullet() {
		ArrayList<BulletService> bullets = new ArrayList<BulletService>();
		
		// déplace les bullets
		for (BulletService b : data.getBullet()) {
			if (b.getAction() == MOVE.LEFT)
				Mouvement.moveLeftBullet(b);
			if (b.getAction() == MOVE.RIGHT)
				Mouvement.moveRightBullet(b);
			if (b.getAction() == MOVE.UP)
				Mouvement.moveUpBullet(b);
			if (b.getAction() == MOVE.DOWN)
				Mouvement.moveDownBullet(b);
			
			
			
			// --------------------------------------------
			// Collision bulletEnemy avec le héro
			// --------------------------------------------
			if(b.getType() == SIDE.Ennemy) {
				if(GestionCollision.collisionHeroesBullet(data, b)) {
					data.touched();
				} else {
					if (b.getPosition().x > HardCodedParameters.leftLimit &&
							b.getPosition().x < HardCodedParameters.rightLimit)
						bullets.add(b);
				}
			}
			
			// --------------------------------------------
			// Collision bulletHeroes avec phantoms/enemy
			// --------------------------------------------
			if(b.getType() == SIDE.Heroes) {
				PhantomService collisionPhantom = GestionCollision.collisionPhantomBullet(data, b);
				EnemyService collisionEnemy = GestionCollision.collisionHeroesBulletEnemy(data, b);
				boolean collisionIndestructible = GestionCollision.collisionIndestructibleBullet(data, b);
				
				if(collisionPhantom != null) {
					collisionPhantom.destroyPhantom();
					data.addHitPoints(1);
					data.addScore(HardCodedParameters.phantomPoint);
				} else if(collisionEnemy != null) {
					collisionEnemy.destroyEnemy();
					data.addHitPoints(1);
					data.addScore(HardCodedParameters.enemyPoint);
				} else if(collisionIndestructible){
					// supprime le missile (ne fait rien)
				} else {
					if (b.getPosition().x > HardCodedParameters.leftLimit && 
							b.getPosition().x < HardCodedParameters.rightLimit)
						bullets.add(b);
				}
			}
				
		}
		data.setBullet(bullets);
	}

	
	// -------------------------------------------------------------------------
	// Spawn
	// -------------------------------------------------------------------------

	/**
	 * spawn un phantom
	 */
	private void spawnPhantom() {
		int x = (int) (HardCodedParameters.defaultWidth * .9);
		int y = 0;
		boolean cont = true;
		while (cont) {
			y = (int) (gen.nextInt((int) (HardCodedParameters.defaultHeight * .6))
					+ HardCodedParameters.defaultHeight * .1);
			cont = false;
			for (PhantomService p : data.getPhantoms()) {
				if (p.getPosition().equals(new Position(x, y)))
					cont = true;
			}
		}
		data.addPhantom(new Position(x, y));
	}

	/**
	 * spawn un indestructible
	 */
	private void spawnIndestructible() {
		int x = (int) (HardCodedParameters.defaultWidth * .9);
		int y = 0;
		boolean cont = true;
		while (cont) {
			y = (int) (gen.nextInt((int) (HardCodedParameters.defaultHeight * .6))
					+ HardCodedParameters.defaultHeight * .1);
			cont = false;
			for (IndestructibleService p : data.getIndestructibles()) {
				if (p.getPosition().equals(new Position(x, y)))
					cont = true;
			}
		}
		data.addIndestructible(new Position(x, y));
	}
	
	/**
	 * spawn un ennemi
	 */
	private void spawnEnemy() {
		int x = (int) (HardCodedParameters.defaultWidth * .9);
		int y = 0;
		boolean cont = true;
		while (cont) {
			y = (int) (gen.nextInt((int) (HardCodedParameters.defaultHeight * .6))
					+ HardCodedParameters.defaultHeight * .1);
			cont = false;
			for (EnemyService p : data.getEnemies()) {
				if (p.getPosition().equals(new Position(x, y)))
					cont = true;
			}
		}
		data.addEnemy(new Position(x, y));
	}

}
