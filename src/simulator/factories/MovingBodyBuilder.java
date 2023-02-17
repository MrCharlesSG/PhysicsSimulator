package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;
<<<<<<< Updated upstream

public class MovingBodyBuilder extends Builder<Body>{
	
=======
import simulator.model.MovingTowardsFixedPoint;

public class MovingBodyBuilder extends Builder<Body>{

>>>>>>> Stashed changes
	public MovingBodyBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createInstance(JSONObject data) throws IllegalArgumentException{
		String id = data.getString("id");
		String gid = data.getString("gid");
		JSONArray p = data.getJSONArray("p");
		JSONArray v =data.getJSONArray("v");
		double m= data.getDouble("m");
		double px= p.getDouble(0);
		double py= p.getDouble(1);
		double vx= p.getDouble(0);
		double vy= p.getDouble(1);
		
<<<<<<< Updated upstream
		if(!data.has("id")||!data.has("gid")||!data.has("p")||!data.has("v")||!data.has("m")) 
			throw new IllegalArgumentException("No existe un tag para el Body");
		
		String id=data.getString("id");
		String gid=data.getString("gid");
		double m=data.getDouble("m");
		Vector2D vel = new Vector2D(data.getDouble("vel"),data.getDouble("vel")));
		
		return new Body(id,gid, , ,m);
=======
		if(id!=null && gid != null && m>=0 && px>=0 && py>=0 && vx>=0 && vy>=0) {
			return new MovingBody(id, gid, new Vector2D(vx, vy), new Vector2D(px, py),m);
		}else {
			throw new IllegalArgumentException();
		}
>>>>>>> Stashed changes
	}

}
