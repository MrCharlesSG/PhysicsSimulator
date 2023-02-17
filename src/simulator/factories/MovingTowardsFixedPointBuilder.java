package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	public MovingTowardsFixedPointBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) throws IllegalArgumentException{
		JSONArray c = data.getJSONArray("c");
		double g= data.getDouble("g");
		double cx= c.getDouble(0);
		double cy= c.getDouble(1);
		
		if(g>=0 && cx>=0 && cy>=0 ) {
			return new MovingTowardsFixedPoint(new Vector2D(cx, cy), g );
		}else {
			throw new IllegalArgumentException("Datos incorrectos en lectura de MovingTowardsFixedPoint");
		}
		
	}

}
