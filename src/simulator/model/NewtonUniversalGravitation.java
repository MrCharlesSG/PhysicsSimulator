package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double g;
	
	public NewtonUniversalGravitation(){ //Por defecto g sera 6.67 * 10^-11
		this.g = 6.67 * Math.pow(10, -11);
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
					if(j.getPosition().distanceTo(i.getPosition()) > 0 && j.getMass() > 0) {
						
						double fij = (this.g*(i.getMass()*j.getMass()))/(Math.pow(j.getPosition().distanceTo(i.getPosition()),2));
						Vector2D dij = j.getPosition().minus(i.getPosition()).direction();
						Vector2D Fij = dij.scale(fij);
						i.addForce(Fij);

					}
				}
			}
		}
	}
	
	public String toString() { 
		return "";
	}
	
}
