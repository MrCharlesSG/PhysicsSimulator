package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected Double g;
	
	NewtonUniversalGravitation(Double G) throws IllegalArgumentException{
		if(G < 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.g=G;
		}
	}
	
	@Override
	public void apply(List<Body> bs) {
		// TODO Auto-generated method stub
		for(Body i : bs) {
			for(Body j:bs) {
				bs.
			}
		}
	}
	
}
