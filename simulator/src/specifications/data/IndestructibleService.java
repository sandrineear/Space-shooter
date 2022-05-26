/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/PhantomService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications.data;

import data.information.MOVE;
import tools.Position;

public interface IndestructibleService {

	/**
	 * Initialisation des parametres de indestructibles
	 * @param p position
	 * @param w largeur
	 * @param h hauteur
	 * @param s pas
	 */
	public void initIndestructible(Position p, double w, double h, double s);
	
	public Position getPosition();
	public double getStep();
	public double getIndestructibleHeight();
	public double getIndestructibleWidth();
	public MOVE getAction();

	public void setPosition(Position p);
}
