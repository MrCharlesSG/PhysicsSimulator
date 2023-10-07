package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;
import simulator.model.MovingTowardsTwoFixedPoint;

public class MovingTowardsTwoFixedPointBuilder extends Builder<ForceLaws> {

	public MovingTowardsTwoFixedPointBuilder() {
		super("mt2fp","Constructor de una fuerza que atrae a dos puntos concreto" );
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		double g1 = 9.81, g2 = 9.81;
		
		Vector2D c1 = new Vector2D(), c2 = new Vector2D();
		
		if(data.has("c1") && !data.get("c1").equals("") ) {
			c1=this.getVector2D(data.getJSONArray("c1"));
		}
		
		if(data.has("c2") && !data.get("c2").equals("") ) {
			c2=this.getVector2D(data.getJSONArray("c2"));
		}
		
		if( data.has("g1")&&!data.get("g1").equals("")) {
			g1 = data.getDouble("g1");
		}
		
		if( data.has("g2")&&!data.get("g2").equals("")) {
			g2 = data.getDouble("g2");
		}
		
		return new MovingTowardsTwoFixedPoint(c1, c2, g1, g2);
	}
	
	public JSONObject getInfo() {
		JSONObject jo=new JSONObject();
		
		jo.put("type",getTypeTag());
		jo.put("desc",getDesc());
		JSONArray jar= new JSONArray();
		jar.put((new JSONObject()).put("c1", "the first point towards bodies move (e.g., [100.0,50.0])"));
		jar.put((new JSONObject()).put("c2", "the second point towards bodies move (e.g., [100.0,50.0])"));
		jar.put((new JSONObject()).put("g1", "the length of the first acceleration vector (a number)"));
		jar.put((new JSONObject()).put("g2", "the length of the second acceleration vector (a number)"));
		jo.put("data", jar);
		return jo;
	}
	
	private Vector2D getVector2D(JSONArray cj) {
		double cx,cy;
		cx= cj.getDouble(0);
		cy= cj.getDouble(1);
		return new Vector2D(cx, cy);
	}
}
