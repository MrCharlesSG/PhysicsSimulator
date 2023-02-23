package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	protected Vector2D ci;
	
	protected double g;
	
	public MovingTowardsFixedPoint(Vector2D ci, double g) {
		if(ci==null || g<=0) {
			throw new IllegalArgumentException();
		}
		this.g=g;
		this.ci=ci;
	}
	
	@Override
	public void apply(List<Body> bs) {
		
		for(Body x: bs) {
			
			Vector2D di = ci.minus(x.getPosition().direction());
			x.addForce(di.scale(g*x.masa));
			
		}
	}
	
	public String toString() {
		return "";
	}
	
	
	
	
}
