/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Data.java 2015-03-11 buixuan.
 * ******************************************************/
package data;

import tools.HardCodedParameters;
import tools.Position;
import tools.Sound;
import specifications.data.BulletService;
import specifications.data.DataService;
import specifications.data.EnemyService;
import specifications.data.HeroesService;
import specifications.data.IndestructibleService;
import specifications.data.PhantomService;
import data.ia.Bullet;
import data.ia.Enemy;
import data.ia.Indestructible;
import data.ia.Phantom;
import data.player.Heroes;

import java.util.ArrayList;

public class Data implements DataService {
	
	private HeroesService hero;
	
	private int stepNumber, score, hitPoints;
	private ArrayList<PhantomService> phantoms;
	private ArrayList<IndestructibleService> indestructibles;
	private ArrayList<BulletService> bullets;
	private ArrayList<EnemyService> enemies;
	

	private Sound.SOUND sound;

	public Data() {
	}

	@Override
	public void init() {
		
		stepNumber = 0;
		score = 0;
		hitPoints = 0;
		
		hero = new Heroes();
		hero.initHeroes(
				new Position(HardCodedParameters.heroesStartX, HardCodedParameters.heroesStartY),
				HardCodedParameters.heroesHeight,
				HardCodedParameters.heroesWidth,
				HardCodedParameters.heroesStep,
				HardCodedParameters.heroesMaxLife,
				HardCodedParameters.touchedTimerInMilliSeconds,
				HardCodedParameters.bulletTimerHeroesInMilliSeconds
			);
		
		phantoms = new ArrayList<PhantomService>();
		indestructibles = new ArrayList<IndestructibleService>();
		bullets = new ArrayList<BulletService>();
		enemies = new ArrayList<EnemyService>();
		
		sound = Sound.SOUND.None;
	}
	
	
	// -------------------------------------------------------------------------
	// Heroes
	// -------------------------------------------------------------------------

	@Override
	public Position getHeroesPosition() { return hero.getPosition(); }

	@Override
	public double getHeroesWidth() { return hero.getWidth(); }

	@Override
	public double getHeroesHeight() { return hero.getHeight(); }

	@Override
	public int getLife() { return hero.getLife(); }
	
	@Override
	public void setHeroesPosition(Position p) { hero.setPosition(p); }
	
	@Override
	public boolean isTouched() { return hero.isTouched(); }

	@Override
	public boolean isAlive() { return hero.isAlive(); }

	@Override
	public boolean isDead() { return hero.isDead(); }
	
	@Override
	public void touched() { hero.touched(); }
	
	@Override
	public void updateStatusHero() { hero.updateStatus(); }
	
	
	// -------------------------------------------------------------------------
	// Phantoms
	// -------------------------------------------------------------------------	

	@Override
	public ArrayList<PhantomService> getPhantoms() { return phantoms; }

	@Override
	public void addPhantom(Position p) { 
		if(phantoms.size() >= HardCodedParameters.limitPhantom) return;
		
		PhantomService phantom = new Phantom();
		phantom.initPhantom(
				p, 
				HardCodedParameters.phantomWidth, 
				HardCodedParameters.phantomHeight,
				HardCodedParameters.phantomStep,
				HardCodedParameters.destroyTimerInMilliSeconds
			);
		phantoms.add(phantom); 
	}

	@Override
	public void setPhantoms(ArrayList<PhantomService> phantoms) { this.phantoms = phantoms; }
	
	@Override
	public void updateStatusPhantoms() { 
		for(PhantomService p: phantoms)
			p.updatePhantom();
	}
	
	@Override
	public void updateStatusEnemies() { 
		for(EnemyService e: enemies)
			e.updateStatus();
	}
	
	// -------------------------------------------------------------------------
	// Indestructibles
	// -------------------------------------------------------------------------	
	
	@Override
	public ArrayList<IndestructibleService> getIndestructibles() { return indestructibles; }

	@Override
	public void addIndestructible(Position p) { 
		if(indestructibles.size() >= HardCodedParameters.limitIndestructible) return;
		
		IndestructibleService ind = new Indestructible();
		ind.initIndestructible(
				p, 
				HardCodedParameters.indestructibleWidth, 
				HardCodedParameters.indestructibleHeight,
				HardCodedParameters.indestructibleStep
			);
		indestructibles.add(ind);
	}

	@Override
	public void setIndestructibles(ArrayList<IndestructibleService> indestructibles) { 
		this.indestructibles = indestructibles;
	}
	
	
	// -------------------------------------------------------------------------
	// Bullet
	// -------------------------------------------------------------------------
	
	@Override
	public ArrayList<BulletService> getBullet() { return bullets; }

	@Override
	public void setBullet(ArrayList<BulletService> bullets) { this.bullets = bullets; }


	@Override
	public void addEnnemiesBullet(EnemyService enemy) { 
		if(enemy.canShoot()) {
			BulletService bullet = new Bullet();
			bullet.initBulletEnnemies(
					enemy.getPosition(), 
					HardCodedParameters.bulletHeight, 
					HardCodedParameters.bulletWidth, 
					HardCodedParameters.bulletStep
				);
			bullets.add(bullet);
			enemy.resetTimerBullet();
		}
	}

	@Override
	public void addHeroesBullet() {
		if(hero.canShoot()) {
			BulletService bullet = new Bullet();
			bullet.initBulletHeroes(
					hero.getPosition(), 
					HardCodedParameters.bulletHeight, 
					HardCodedParameters.bulletWidth, 
					HardCodedParameters.bulletStep
				);
			bullets.add(bullet);
			hero.resetTimerBullet();
		}
	}
	
	
	// -------------------------------------------------------------------------
	// Enemies
	// -------------------------------------------------------------------------	
	
	@Override
	public void addEnemy(Position p) {
		if(enemies.size() >= HardCodedParameters.limitEnemy) return;
		EnemyService e = new Enemy();
		e.initEnemy(
				p, 
				HardCodedParameters.enemyHeight,
				HardCodedParameters.enemyWidth, 
				HardCodedParameters.enemyStep,
				//HardCodedParameters.enemiesMaxLife,
				HardCodedParameters.touchedTimerInMilliSeconds,
				HardCodedParameters.bulletTimerEnemyInMilliSeconds
			);
		enemies.add(e);
	}
	
	@Override
	public ArrayList<EnemyService> getEnemies() {
		return this.enemies;
	}
	
	@Override
	public void setEnemies(ArrayList<EnemyService> enemies) {
		this.enemies = enemies;
	}
		
	
	// -------------------------------------------------------------------------
	// Other elements
	// -------------------------------------------------------------------------	
	
	@Override
	public int getStepNumber() { return stepNumber; }

	@Override
	public void setStepNumber(int n) { stepNumber = n; }
	
	@Override
	public int getScore() { return score; }
	
	@Override
	public void addScore(int score) { this.score += score; }

	@Override
	public Sound.SOUND getSoundEffect() { return sound; }

	@Override
	public void setSoundEffect(Sound.SOUND s) { sound = s; }

	@Override
	public int getHitPoints() { return this.hitPoints; }
	
	@Override
	public void addHitPoints(int hit) {
		this.hitPoints += hit;
	}
	
	@Override
	public void updateLife() {
		if(hitPoints == HardCodedParameters.gainLifeAfterXHit) {
			if(hero.maxLife()) score += 5;
			else hero.gainLife();
			hitPoints = 0;
		}
	}
	

}

