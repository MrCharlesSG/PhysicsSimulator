package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	public MovingTowardsFixedPointBuilder(String typeTag, String desc) {
		super("mtfp","Fuerza que atrae a un punto concreto" );
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) throws IllegalArgumentException{
		
		JSONArray c = data.getJSONArray("c");
		
		if(c.length()==2) {
			double g, cx, cy; 
			g= data.getDouble("g");
			cx= c.getDouble(0);
			cy= c.getDouble(1);
			
			if(g>=0 && cx>=0 && cy>=0 ) {
				return new MovingTowardsFixedPoint(new Vector2D(cx, cy), g );
			}
		}
		throw new IllegalArgumentException("Datos incorrectos en lectura de MovingTowardsFixedPoint");
	}

}
