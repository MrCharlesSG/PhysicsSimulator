package simulator.factories;

import org.json.JSONObject;
import simulator.model.Body;

public class MovingBodyBuilder extends Builder<Body>{
	
	
	
	public MovingBodyBuilder(String typeTag, String desc) {
		super(typeTag, desc);
	}

	@Override
	protected Body createInstance(JSONObject data) {
		
		return null;
	}

}
