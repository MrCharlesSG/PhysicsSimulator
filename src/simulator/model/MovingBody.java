package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{
	
	public MovingBody(String id, String gid, Vector2D pos, Vector2D vel, double mass) throws IllegalArgumentException {
		super(id, gid, pos, vel, mass);
	}

	@Override
	void advance(Double dt) {
		if(this.masa!=0) {
			Vector2D a = new Vector2D();
			a = this.fuerza.scale(1/this.masa);
			this.posicion = this.posicion.plus(this.velocidad.scale(dt).plus(a.scale(dt*dt/2)));
			this.velocidad = this.velocidad.plus(a.scale(dt));
		}
		else {
			this.posicion= this.posicion.plus(this.velocidad.scale(dt));
		}
	}
}
