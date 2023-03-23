package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;

public class MovingBodyBuilder extends Builder<Body>{

	public MovingBodyBuilder() {
		super("mv_body", "Constructor del moving body");
	}

	@Override
	protected Body createInstance(JSONObject data) throws IllegalArgumentException{
		try {
			String gid, id;
			id = data.getString("id");
			gid = data.getString("gid");
			JSONArray ja = data.getJSONArray("p");
			double m= data.getDouble("m");

			if(ja.length()==2) {
				double px, py;
				px= ja.getDouble(0);
				py= ja.getDouble(1);
				Vector2D pos = new Vector2D(px,py);
				ja =data.getJSONArray("v");
				if(ja.length()==2) {
					double vx= ja.getDouble(0);
					double vy= ja.getDouble(1);
					Vector2D vel = new Vector2D(vx,vy);
					return new MovingBody(id, gid, pos, vel,m);
				}
			}
		}catch(JSONException e) {}
			throw new IllegalArgumentException("Error en leer valores de Moving Body"); 
	}
	
	
	public JSONObject getInfo() {
		JSONObject jo=new JSONObject();
		
		jo.put("type",getTypeTag());
		jo.put("desc",getDesc());
		jo.put("data", toString());
		
		return jo;
	}
}
