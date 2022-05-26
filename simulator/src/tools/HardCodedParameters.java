
package tools;

public class HardCodedParameters {
  //---HARD-CODED-PARAMETERS---//
	public static String defaultParamFileName = "in.parameters";
	
	 	
  	// ---------------------------------------------------------------
  	// Parametres des objets
  	// ---------------------------------------------------------------
	
	public static final int 
			defaultWidth = 800, defaultHeight = 600, 	
		// depart héros
			heroesStartX = 60, heroesStartY = 250,	
		// hitbox (hauteur,largeur) et pas du héros
            heroesWidth = 35, heroesHeight = 40, heroesStep = 10,
        // hitbox (hauteur,largeur) et pas du phantom
            phantomWidth = 30, phantomHeight = 30, phantomStep = 10, 
        // hitbox (hauteur,largeur) et pas de l'indestructible
            indestructibleWidth = 30, indestructibleHeight = 30, indestructibleStep = 10, 
        // hitbox (hauteur,largeur) et pas des bullet
            bulletWidth = 5, bulletHeight = 5, bulletStep = 20, 
        // hitbox (hauteur,largeur) et pas de l'ennemi
            enemyWidth = 35, enemyHeight = 40, enemyStep = 2;
	
	public static final long 
		// durée du status STATUS.DESTROY 
			destroyTimerInMilliSeconds = 500,
		// durée du status STATUS.TOUCHED
			touchedTimerInMilliSeconds = 500;

	
  	
  	// ---------------------------------------------------------------
  	// Difficulté du jeu 
	// ATTENTION: trop d'elements à afficher peut faire baisser le FPS
  	// ---------------------------------------------------------------	
	
	public static final int 
		// nombre maximal de ennemi, phantom, indestructible pouvant etre present en même temps
			limitEnemy = 2, limitPhantom = 4, limitIndestructible = 4,
		// nombre de hit necessaire pour pouvoir récuperer une vie
			gainLifeAfterXHit = 5;
	
	public static final long 
		// cadence de tir (durée minimale entre chaque tir) du joueur
			bulletTimerHeroesInMilliSeconds = 700,
	    // cadence de tir (durée minimale entre chaque tir) des ennemis	
			bulletTimerEnemyInMilliSeconds = 3000;
	
	public static final int 
		// nombre max de vies
			heroesMaxLife = 3;

	
  	
  	// ---------------------------------------------------------------
  	// Hit box pour la gestion des collisions
  	// ---------------------------------------------------------------
  	
  	public static final int widthOffset = 280;
  	public static final int heightOffset = 280;
  	
  	public static final int bulletWidthOffset = 155; // Bullet joueur
  	public static final int bulletHeightOffset = 265;
  	
  	public static final int bulletEnemyWidthOffset = 310; // Bullet enemy
  	public static final int bulletEnemyHeightOffset = 278;
  	
  	
  	// ---------------------------------------------------------------
  	// Points pour le score
  	// ---------------------------------------------------------------
  	public static final int phantomPoint = 1;
  	public static final int enemyPoint = 5;
	
	
	// ---------------------------------------------------------------
	// Engine
	// ---------------------------------------------------------------
	
	public static final int enginePaceMillis = 100,
                          	spriteSlowDownRate = 7;
	public static final double friction = 0.50;
	
	
	// ---------------------------------------------------------------
	// Display
	// ---------------------------------------------------------------
	
	public static final double resolutionShrinkFactor = 0.95,
           	userBarShrinkFactor = 0.25,
         	menuBarShrinkFactor = 0.5,
         	logBarShrinkFactor = 0.15,
         	logBarCharacterShrinkFactor = 0.1175,
         	logBarCharacterShrinkControlFactor = 0.01275,
         	menuBarCharacterShrinkFactor = 0.175;
	public static final int displayZoneXStep = 5,
                          	displayZoneYStep = 5,
                          	displayZoneXZoomStep = 5,
                          	displayZoneYZoomStep = 5;
	public static final double displayZoneAlphaZoomStep = 0.99;
  
	// ---------------------------------------------------------------
	// Limite de l'écran de jeu
	// ---------------------------------------------------------------
	
	public static final double leftLimit = 45;
	public static final double rightLimit = 790;
  	public static final double upLimit = 50;
  	public static final double downLimit = 465;
  	public static final double limitMargin = 0;
    

  	
  	
  	
  	
  	
  	
  	
  	
  	
  	//---MISCELLANOUS---//
  	public static final Object loadingLock = new Object();
  	public static final String greetingsZoneId = String.valueOf(0xED1C7E),
                             simulatorZoneId = String.valueOf(0x51E77E);
  
  	
  	public static <T> T instantiate(final String className, final Class<T> type){
  		try{
  			return type.cast(Class.forName(className).newInstance());
  		} catch(final InstantiationException e){
  			throw new IllegalStateException(e);
  		} catch(final IllegalAccessException e){
  			throw new IllegalStateException(e);
  		} catch(final ClassNotFoundException e){
  			throw new IllegalStateException(e);
  		}
  	}
  	
}
