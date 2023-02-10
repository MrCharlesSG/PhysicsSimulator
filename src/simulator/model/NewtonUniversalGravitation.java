package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected Double g;
	protected Double fi;
	protected Vector2D acel;
	
	NewtonUniversalGravitation(Double G) throws IllegalArgumentException{
		if(G < 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.g = G;
		}
	}
	
	@Override
	public void apply(List<Body> bs) {
		
		for(Body i : bs) {
			if(i.masa == 0) {
				i.velocidad = new Vector2D(0,0);
			}
			else {
				for(Body j:bs) {
					if(i.posicion.distanceTo(j.posicion) > 0) {
						Double Fij = this.g * ((i.masa*j.masa)/(j.posicion.distanceTo(i.posicion)*j.posicion.distanceTo(i.posicion)));
						this.fi = this.fi + Fij;
					}
					Vector2D dij = i.posicion.minus(j.posicion);
					i.fuerza = i.fuerza.plus(dij.scale(this.fi));
				}
			}
		}
	}
	
}
