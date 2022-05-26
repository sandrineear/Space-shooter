package data.ia;

import data.information.MOVE;
import data.information.SIDE;
import specifications.data.BulletService;
import tools.Position;

public class Bullet implements BulletService{

	private Position position;
	private double height;
	private double width;
	private double step;
	private SIDE type;
	
	@Override
	public void initBulletEnnemies(Position p, double h, double w, double s) {
		position = p;
		height = h;
		width = w;
		step = s;
		type = SIDE.Ennemy;		
	}	
	
	@Override
	public void initBulletHeroes(Position p, double h, double w, double s) {
		position = p;
		height = h;
		width = w;
		step = s;
		type = SIDE.Heroes;		
	}

	@Override
	public SIDE getType() { return type; }

	@Override
	public double getHeight() { return height; }

	@Override
	public double getWidth() { return width; }

	@Override
	public Position getPosition() { return position; }

	@Override
	public double getStep() { return step; }

	
	
	@Override
	public void setHeight(double h) { height = h; }

	@Override
	public void setWidth(double w) { width = w; }	

	@Override
	public void setPosition(Position p) { position = p; }

	@Override
	public void setStep(double s) { step = s; }
		

	@Override
	public MOVE getAction() { 
		if(type == SIDE.Ennemy) return MOVE.LEFT;
		if(type == SIDE.Heroes) return MOVE.RIGHT;
		else return MOVE.RIGHT;
	}

}
