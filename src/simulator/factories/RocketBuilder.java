package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;
import simulator.model.Rocket;

public class RocketBuilder extends Builder<Body>{

	public RocketBuilder() {
		super("rocket", "Literamente un Cohete");
	}
//(String id, String gid, Vector2D pos, Vector2D vel, double mass, double c, double l)
	@Override
	protected Body createInstance(JSONObject data) throws IllegalArgumentException{
		try {
			String id;
			id = data.getString("id");
			double m= data.getDouble("mass");
			Vector2D vel= getVector(data, "vel");
			Vector2D pos= getVector(data, "pos");
			double comb = data.getDouble("comb");
			double loss= data.getDouble("loss");
			
			return new Rocket(id, "R", pos, vel,m, comb, loss);
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
	
	private Vector2D getVector(JSONObject data, String id ) {
		JSONArray ja = data.getJSONArray(id);
		Vector2D aux= null;
		if(ja.length()==2) {
			double px, py;
			px= ja.getDouble(0);
			py= ja.getDouble(1);
			aux = new Vector2D(px,py);
		}
		return aux;
	}
}
