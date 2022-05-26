package specifications.data;

import data.information.MOVE;
import data.information.SIDE;
import tools.Position;

public interface BulletService {	
	
	/**
	 * Initialise une nouvelle bullet provenant de l'ennemi
	 * @param position position
	 * @param h hauteur
	 * @param w largeur
	 * @param s pas
	 */
	public void initBulletEnnemies(Position position, double h, double w, double s);
	
	/**
	 * Initialise une nouvelle bullet provenant du héros
	 * @param position position
	 * @param h hauteur
	 * @param w largeur
	 * @param s pas
	 */
	public void initBulletHeroes(Position position, double h, double w, double s);
	
	
	
	public MOVE getAction();
	
	public SIDE getType();
	public double getHeight();
	public double getWidth();
	public Position getPosition();
	public double getStep();
	
	public void setHeight(double h);
	public void setWidth(double w);
	public void setPosition(Position p);
	public void setStep(double s);

}
