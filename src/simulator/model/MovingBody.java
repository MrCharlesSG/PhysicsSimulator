package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{
	
	protected Vector2D a;
	
	MovingBody(String id, String gid, Vector2D vel, Vector2D pos, Double mass) throws IllegalArgumentException {
		super(id, gid, vel, pos, mass);
	}

	@Override
	void advance(Double dt) {
		if(this.masa!=0) {
			a = a.scale(1/this.masa);
			this.posicion = this.posicion.plus(velocidad.scale(dt).plus(a.scale(dt*dt/2)));
			this.velocidad = this.velocidad.plus(a.scale(dt));
		}
	}
	
}
