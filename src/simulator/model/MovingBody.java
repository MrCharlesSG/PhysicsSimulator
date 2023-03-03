package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{
	
	public MovingBody(String id, String gid, Vector2D pos, Vector2D vel, double mass) throws IllegalArgumentException {
		super(id, gid, pos, vel, mass);
	}

	@Override
	void advance(double dt) {
		if(this.getMass()>0) {
			Vector2D a = new Vector2D();
			a = this.getForce().scale(1/this.getMass());
			this.posicion = this.getPosition().plus(this.getVelocity().scale(dt).plus(a.scale(dt*dt*0.5)));
			this.velocidad = this.getVelocity().plus(a.scale(dt));
		}
		else {
			this.posicion = this.getPosition().plus(this.getVelocity().scale(dt));
		}
	}
}
