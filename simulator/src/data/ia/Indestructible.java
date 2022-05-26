package data.ia;

import data.information.MOVE;
import specifications.data.IndestructibleService;
import tools.Position;

public class Indestructible implements IndestructibleService {
	private Position position;
	private double indestructibleHeight = 0, indestructibleWidth = 0, indestructibleStep = 1;


	@Override
	public void initIndestructible(Position p, double w, double h, double s) {
		position = p;
		indestructibleHeight = h;
		indestructibleWidth = w;
		indestructibleStep = s;
		
	}
	
	@Override
	public Position getPosition() { return position; }
	
	@Override
	public double getStep() { return indestructibleStep; }

	@Override
	public double getIndestructibleHeight()  { return this.indestructibleHeight; }

	@Override
	public double getIndestructibleWidth()  { return this.indestructibleWidth; }

	@Override
	public MOVE getAction() { return MOVE.LEFT; }

	@Override
	public void setPosition(Position p) { position = p; }

}
