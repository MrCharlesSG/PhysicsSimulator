package simulator.model;

import simulator.misc.Vector2D;

public class StationaryBody extends Body{
	
	protected Vector2D a;
	
	public StationaryBody(String id, String gid, Vector2D pos, Double mass) throws IllegalArgumentException {
		super(id, gid, new Vector2D(), pos, mass);
	}

	@Override
	void advance(Double dt) {
	}
	
}
