package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double g;
	
	NewtonUniversalGravitation(){ //Por defecto g sera 6.67 * 10^-11
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
			if(i.masa > 0) {
				for(Body j : bs) {
					if(j.posicion.distanceTo(i.posicion) > 0 && j.masa > 0) {
						
						double fij = (this.g*(i.masa*j.masa))/(Math.pow(j.posicion.distanceTo(i.posicion),2));
						Vector2D dij = j.posicion.minus(i.posicion).direction();
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
