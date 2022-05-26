/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/PhantomService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications.data;

import data.information.MOVE;
import tools.Position;

public interface PhantomService {
	
	/**
	 * Initialise les paramètre du phantom
	 * @param p position initiale
	 * @param w largeur
	 * @param h hauteur
	 * @param s pas
	 * @param t durée durant laquelle il peut rester en mode STATUS.DESTROY
	 */
	public void initPhantom(Position p, double w, double h, double s, long t);
	
	public Position getPosition();
	public double getStep();
	public double getPhantomHeight();
	public double getPhantomWidth();
	public MOVE getAction();
	public void destroyPhantom();
	public void setPosition(Position p);
	public boolean isDestroyed();
	public boolean isDead();
	public boolean isAlive();
	
	/**
	 * met à jour le status du phantom
	 */
	public void updatePhantom();
}
