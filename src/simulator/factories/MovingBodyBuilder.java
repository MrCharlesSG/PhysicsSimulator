package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;

public class MovingBodyBuilder extends Builder<Body>{

	public MovingBodyBuilder(String typeTag, String desc) {
		super(typeTag, desc);
	}

	@Override
	protected Body createInstance(JSONObject data) throws IllegalArgumentException{
		try {
			String gid, id = data.getString("id");
			gid = data.getString("gid");
			JSONArray p = data.getJSONArray("p");
			double m= data.getDouble("m");

			if(p.length()==2) {
				double px, py;
				px= p.getDouble(0);
				py= p.getDouble(1);
				JSONArray v =data.getJSONArray("v");
				if(v.length()==2) {
					double vx= p.getDouble(0);
					double vy= p.getDouble(1);
					if(id!=null && gid != null && m>=0 && px>=0 && py>=0 && vx>=0 && vy>=0) {
						return new MovingBody(id, gid, new Vector2D(vx, vy), new Vector2D(px, py),m);
					}
				}
			}
		}catch(JSONException e){}
		throw new IllegalArgumentException("Error en leer valores de Moving Body"); 
	}
}
