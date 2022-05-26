/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: userInterface/Viewer.java 2015-03-11 buixuan.
 * ******************************************************/
package userInterface;

import tools.HardCodedParameters;
import specifications.binderService.RequireReadService;
import specifications.data.BulletService;
import specifications.data.EnemyService;
import specifications.data.IndestructibleService;
import specifications.data.PhantomService;
import specifications.viewer.ReadService;
import specifications.viewer.ViewerService;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import data.information.SIDE;


public class Viewer implements ViewerService, RequireReadService {
	private static final int spriteSlowDownRate = HardCodedParameters.spriteSlowDownRate;
	private static final double defaultMainWidth = HardCodedParameters.defaultWidth,
			defaultMainHeight = HardCodedParameters.defaultHeight;
	private ReadService data;
	private double xShrink, yShrink, shrink, xModifier, yModifier;
	
	private ImageView heroesAvatar;
	private Image heroesSpriteSheet;
	private ImageView explosionAvatar;
	
	private String bulletHeroesImage;
	private String bulletEnemyImage;
	private String explosionImage;
	
	private ArrayList<ImageView> phantomsAvatars = new ArrayList<>();
	private ArrayList<ImageView> enemiesAvatars = new ArrayList<>();
	private ArrayList<ImageView> indestructiblesAvatars = new ArrayList<>();
	private ArrayList<ImageView> fullLife = new ArrayList<>();
	private ArrayList<ImageView> emptyLife = new ArrayList<>();
	
	public Viewer() {
	}

	@Override
	public void bindReadService(ReadService service) {
		data = service;
	}

	@Override
	public void init() {
		xShrink = 1;
		yShrink = 1;
		xModifier = 0;
		yModifier = 0;		
		
		heroesSpriteSheet = new Image("file:src/images/spaceship.png");
		heroesAvatar = new ImageView(heroesSpriteSheet);
		explosionAvatar = new ImageView("file:src/images/explosion.png");
		
		for(int i = 0; i<HardCodedParameters.limitEnemy; i++)
			enemiesAvatars.add(new ImageView("file:src/images/enemy.png"));
		
		for(int i = 0; i<HardCodedParameters.limitPhantom; i++)
			phantomsAvatars.add(new ImageView("file:src/images/phantom.png"));
		
		for(int i = 0; i<HardCodedParameters.limitIndestructible; i++)
			indestructiblesAvatars.add(new ImageView("file:src/images/indestructible.png"));
		
		for(int i = 0; i<3; i++)
			fullLife.add(new ImageView("file:src/images/coeurPlein.png"));
		
		for(int i = 0; i<3; i++)
			emptyLife.add(new ImageView("file:src/images/coeurVide.png"));
		
		bulletHeroesImage = "file:src/images/bullet.png";
		bulletEnemyImage = "file:src/images/bulletEnemy.png";
		explosionImage = "file:src/images/explosion.png";
		
	}

	@Override
	public Parent getPanel() {
		shrink = Math.min(xShrink, yShrink);
		xModifier = .01 * shrink * defaultMainHeight;
		yModifier = .01 * shrink * defaultMainHeight;
		
		Rectangle map = new Rectangle(4.5 * xModifier + shrink * defaultMainWidth,
				-.2 * shrink * defaultMainHeight + shrink * defaultMainHeight);
		map.setStroke(Color.WHITESMOKE);
		map.setStrokeWidth(.002 * shrink * defaultMainHeight);
		map.setArcWidth(.03 * shrink * defaultMainHeight);
		map.setArcHeight(.03 * shrink * defaultMainHeight);
		map.setTranslateX(xModifier);
		map.setTranslateY(yModifier);
		
		
		Image backgroundImage = new Image("file:src/images/gameBackground.jpeg");
		map.setFill(new ImagePattern(backgroundImage));
		
		
		/*Text round = new Text(-0.1 * shrink * defaultMainHeight + .3 * shrink * defaultMainWidth,
				-0.1 * shrink * defaultMainWidth + shrink * defaultMainHeight, "Round 1");
		round.setFont(new Font("Impact", .05 * shrink * defaultMainHeight));
		round.setFill(Color.WHITE);*/

		Text score = new Text(-0.1 * shrink * defaultMainHeight + .3 * shrink * defaultMainWidth,
				-0.09 * shrink * defaultMainWidth + shrink * defaultMainHeight, "Score: " + data.getScore());
		score.setFont(new Font("Impact", .05 * shrink * defaultMainHeight));
		score.setFill(Color.WHITE);
		
		Text hit = new Text(-0.1* shrink * defaultMainHeight + .3 * shrink * defaultMainWidth,
				-0.04 * shrink * defaultMainWidth + shrink * defaultMainHeight, "Hit: " + data.getHitPoints());
		hit.setFont(new Font("Impact", .05 * shrink * defaultMainHeight));
		hit.setFill(Color.WHITE);
	
		
		// --------------------------
		// Affichage du héro et des scores
		// --------------------------
		this.heroesAvatar.setTranslateX(data.getHeroesPosition().x - HardCodedParameters.widthOffset);
		this.heroesAvatar.setTranslateY(data.getHeroesPosition().y - HardCodedParameters.heightOffset);
		this.heroesAvatar.setScaleX(0.07);
		this.heroesAvatar.setScaleY(0.08);
	
		
		Group panel = new Group();
		panel.getChildren().addAll(
				map, 
				//round, 
				score, 
				hit, 
				heroesAvatar
			);
		

		// --------------------------
		// Gestion des Phantoms
		// --------------------------
			
		ArrayList<PhantomService> phantoms = data.getPhantoms();
		PhantomService p;
		
		for (int i = 0; i < phantoms.size(); i++) {		
			ImageView phantomAvatar;		
			p = phantoms.get(i);
			if(p.isDestroyed()) phantomAvatar = new ImageView(explosionImage);
			else phantomAvatar = phantomsAvatars.get(i);
			
			phantomAvatar.setTranslateX(p.getPosition().x - HardCodedParameters.widthOffset);
			phantomAvatar.setTranslateY(p.getPosition().y - HardCodedParameters.heightOffset);
			phantomAvatar.setScaleX(0.07);
			phantomAvatar.setScaleY(0.07);		
			panel.getChildren().add(phantomAvatar);
		}
		

		// --------------------------
		// Gestion des Indestructibles
		// --------------------------
			
		ArrayList<IndestructibleService> indestructible = data.getIndestructibles();
		IndestructibleService indes;
		
		for (int i = 0; i < indestructible.size(); i++) {
			ImageView indestructibleAvatar = indestructiblesAvatars.get(i);		
			indes = indestructible.get(i);
			indestructibleAvatar.setTranslateX(indes.getPosition().x - HardCodedParameters.widthOffset);
			indestructibleAvatar.setTranslateY(indes.getPosition().y - HardCodedParameters.heightOffset);
			indestructibleAvatar.setScaleX(0.07);
			indestructibleAvatar.setScaleY(0.07);		
			panel.getChildren().add(indestructibleAvatar);
		}
		
		
		// --------------------------
		// Gestion des Enemies
		// --------------------------
		
		ArrayList<EnemyService> enemy = data.getEnemies();
		EnemyService e;
				
		for (int i = 0; i < enemy.size(); i++) {
			ImageView enemyAvatar;
			e = enemy.get(i);
			if(e.isDestroyed()) enemyAvatar = new ImageView(explosionImage);
			else enemyAvatar = enemiesAvatars.get(i);		
			
			enemyAvatar.setTranslateX(e.getPosition().x - HardCodedParameters.widthOffset);
			enemyAvatar.setTranslateY(e.getPosition().y - HardCodedParameters.heightOffset);
			enemyAvatar.setScaleX(0.07);
			enemyAvatar.setScaleY(0.07);
			panel.getChildren().add(enemyAvatar);

		}	
		
		
		// --------------------------
		// Gestion des Bullet 
		// --------------------------
		ArrayList<BulletService> bullets = data.getBullet();
		BulletService b;
		
		for (int j = 0; j < bullets.size(); j++) {	
			ImageView bulletAvatar;
			b = bullets.get(j);
			if(b.getType() == SIDE.Heroes)
				bulletAvatar = new ImageView(bulletHeroesImage);
			else
				bulletAvatar = new ImageView(bulletEnemyImage);
			bulletAvatar.setTranslateX(b.getPosition().x - HardCodedParameters.widthOffset);
			bulletAvatar.setTranslateY(b.getPosition().y - HardCodedParameters.heightOffset);
			bulletAvatar.setScaleX(0.06);
			bulletAvatar.setScaleY(0.06);
			panel.getChildren().add(bulletAvatar);
		}
		
		
		// --------------------------
		// Gestion des Vies
		// --------------------------
			
		ImageView life1,life2,life3;
		if(data.getLife() > 2) {
			life1 = fullLife.get(0);
			life2 = fullLife.get(1);
			life3 = fullLife.get(2);
		} else if(data.getLife() == 2) {
			life1 = fullLife.get(0);
			life2 = fullLife.get(1);
			life3 = emptyLife.get(0);
		} else if(data.getLife() == 1) {
			life1 = fullLife.get(0);
			life2 = emptyLife.get(0);
			life3 = emptyLife.get(1);
		} else{
			life1 = emptyLife.get(0);
			life2 = emptyLife.get(1);
			life3 = emptyLife.get(2);
		}
		
		life1.setScaleX(0.06);
		life1.setScaleY(0.06);
		life1.setX(300);
		life1.setY(250);
		
		life2.setScaleX(0.06);
		life2.setScaleY(0.06);
		life2.setX(340);
		life2.setY(250);
		
		life3.setScaleX(0.06);
		life3.setScaleY(0.06);
		life3.setX(380);
		life3.setY(250);
		
		panel.getChildren().addAll(life1,life2,life3);
		

		// --------------------------
		// Gestion des Hits
		// --------------------------
		
		explosionAvatar.setScaleX(0.06);
		explosionAvatar.setScaleY(0.06);
		explosionAvatar.setX(data.getHeroesPosition().x - HardCodedParameters.widthOffset + 20);
		explosionAvatar.setY(data.getHeroesPosition().y - HardCodedParameters.heightOffset);
		
		if(data.isTouched())
			panel.getChildren().add(explosionAvatar);
		

		// --------------------------
		// Game Over
		// --------------------------
		
		if(data.getLife() < 1) {
			Text game = new Text(-0.20* shrink * defaultMainHeight + .45 * shrink * defaultMainWidth,
					-0.5 * shrink * defaultMainWidth + shrink * defaultMainHeight, "G A M E");
			Text over = new Text(-0.20* shrink * defaultMainHeight + .47 * shrink * defaultMainWidth,
					-0.2 * shrink * defaultMainWidth + shrink * defaultMainHeight, "O V E R");
			game.setFont(new Font("Impact", .2 * shrink * defaultMainHeight));
			over.setFill(Color.WHITE);
			over.setFont(new Font("Impact", .2 * shrink * defaultMainHeight));
			game.setFill(Color.WHITE);
			panel.getChildren().clear();
			panel.getChildren().add(game);
			panel.getChildren().add(over);
		}
	
		return panel;
	}

	@Override
	public void setMainWindowWidth(double width) {
		xShrink = width / defaultMainWidth;
	}

	@Override
	public void setMainWindowHeight(double height) {
		yShrink = height / defaultMainHeight;
	}
	

}
