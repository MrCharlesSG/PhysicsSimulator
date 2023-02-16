package simulator.factories;

import org.json.JSONObject;

import simulator.model.Body;

public class MovingTowardsFixedPointBuilder extends Builder<Body>{
	
	public MovingTowardsFixedPointBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		
	}

	@Override
	protected Body createInstance(JSONObject data) {
		
		return null;
	}

}
