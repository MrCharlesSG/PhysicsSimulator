package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;

public class MovingBodyBuilder extends Builder<Body>{
	
	public MovingBodyBuilder(String typeTag, String desc) {
		super(typeTag, desc);
	}

	@Override
	protected Body createInstance(JSONObject data) {
		
		if(!data.has("id")||!data.has("gid")||!data.has("p")||!data.has("v")||!data.has("m")) 
			throw new IllegalArgumentException("No existe un tag para el Body");
		
		String id=data.getString("id");
		String gid=data.getString("gid");
		double m=data.getDouble("m");
		Vector2D vel = new Vector2D(data.getDouble("vel"),data.getDouble("vel")));
		
		return new Body(id,gid, , ,m);
	}

}
