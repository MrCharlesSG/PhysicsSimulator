package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	public MovingTowardsFixedPointBuilder() {
		super("mtfp","Constructor de una fuerza que atrae a un punto concreto" );
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		//Valor por defecto de g
		double g = 9.81;
		//Valor por defecto de c
		Vector2D c = new Vector2D();
		
		if(data.isNull("c")&&data.isNull("g")) {
			return new MovingTowardsFixedPoint();
		}
		else if(data.isNull("c")&&!data.isNull("g")) {
			return new MovingTowardsFixedPoint(c, data.getDouble("g"));
		}
		else if(!data.isNull("c")&&data.isNull("g")) {
			JSONArray cj = data.getJSONArray("c");
			double cx,cy;
			cx= cj.getDouble(0);
			cy= cj.getDouble(1);
			return new MovingTowardsFixedPoint(new Vector2D(cx,cy), g);
		}
		else {
			JSONArray cj = data.getJSONArray("c");
			double cx,cy;
			g=data.getDouble("g");
			cx= cj.getDouble(0);
			cy= cj.getDouble(1);
			return new MovingTowardsFixedPoint(new Vector2D(cx, cy), g);
		}
	}

}
