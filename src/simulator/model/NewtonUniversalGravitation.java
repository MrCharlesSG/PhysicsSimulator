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
			if(i.masa == 0) { //Se pone la velocidad a 0
				i.velocidad = new Vector2D();
			}
			else {
				for(Body j : bs) {
					if(i.posicion.distanceTo(j.posicion) > 0) {
						
						double Fij = (this.g*(i.masa*j.masa))/(Math.pow(j.posicion.distanceTo(i.posicion),2));
						
						Vector2D dij = i.posicion.minus(j.posicion).direction();
						
						Vector2D acel = dij.scale(Fij).scale(1/j.masa);
						
						i.addForce(acel.scale(j.masa));
					}
				}
			}
		}
	}
	
	public String toString() { 
		return "";
	}
	
}
