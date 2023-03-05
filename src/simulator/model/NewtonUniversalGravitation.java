package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double g;
	
	public NewtonUniversalGravitation(){ //Por defecto g sera 6.67 * 10^-11
		this.g = 6.67 * (Math.pow(10, -11));
	}
	
	public NewtonUniversalGravitation(double G) throws IllegalArgumentException{
		if(G <= 0) {
			throw new IllegalArgumentException();
		}	
		else {
			this.g = G;
		}
	}
	
	@Override
	public void apply(List<Body> bs) {
		
		for(Body i : bs) {
			if(i.getMass() > 0) {
				for(Body j : bs) {
					double distance=j.getPosition().distanceTo(i.getPosition());
					if(distance > 0 && j.getMass() > 0) {
						Vector2D dij = j.getPosition().minus(i.getPosition()).direction();
						i.addForce(dij.scale((this.g*(i.getMass()*j.getMass()))/(Math.pow(distance,2))));
					}
				}
			}
		}
	}
	
	public String toString() { 
		return "";
	}
	
}
