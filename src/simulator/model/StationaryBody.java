package simulator.model;

import simulator.misc.Vector2D;

public class StationaryBody extends Body{

	StationaryBody(String id, String gid, Vector2D vel, Vector2D pos, Double mass) throws IllegalArgumentException {
		super(id, gid, vel, pos, mass);
	}

	@Override
	void advance(Double dt) {
	}

}
