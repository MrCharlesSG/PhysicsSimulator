package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NoForce implements ForceLaws {
	
	protected Vector2D acel;
	protected Vector2D Fi;
	
	public NoForce(){ //¿Se deberia iniciar la aceleracion y fuerza a 0?
		this.acel = new Vector2D();
		this.Fi = new Vector2D();
	}

	@Override
	public void apply(List<Body> bs) {
		
	}
	
	public String toString() {
		return "";
	}
	
}
