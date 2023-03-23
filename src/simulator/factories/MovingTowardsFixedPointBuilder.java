package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	public MovingTowardsFixedPointBuilder() {
		super("mtfp","Constructor de una fuerza que atrae a un punto concreto" );
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		//Valor por defecto de g
		double g = 9.81;
		//Valor por defecto de c
		Vector2D c = new Vector2D();
		
		if(data.has("c") ) {
			JSONArray cj = data.getJSONArray("c");
			double cx,cy;
			cx= cj.getDouble(0);
			cy= cj.getDouble(1);
			c = new Vector2D(cx, cy);
		}
		if( data.has("g")) {
			g = data.getDouble("g");
		}
		return new MovingTowardsFixedPoint(c, g);
	}
	
	public JSONObject getInfo() {
		JSONObject jo=new JSONObject();
		
		jo.put("type",getTypeTag());
		jo.put("desc",getDesc());
		JSONArray jar= new JSONArray();
		jar.put((new JSONObject()).put("c", "the point towards which bodies move (e.g., [100.0,50.0])"));
		jar.put((new JSONObject()).put("g", "the length of the acceleration vector (a number)"));
		jo.put("data", jar);
		return jo;
	}
}
