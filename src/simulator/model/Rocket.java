package simulator.model;

import simulator.misc.Vector2D;

public class Rocket extends Body{
	
	protected double c, l;
	
	public Rocket(String id, String gid, Vector2D pos, Vector2D vel, double mass, double c, double l) throws IllegalArgumentException {
		super(id, gid, pos, vel, mass);
		this.c=c;
		this.l=l;
	}

	@Override
	void advance(double dt) {
		if(c>=l) {
			this.posicion= this.posicion.plus(velocidad.scale(dt));
			c-=l;
		}
	}
}
