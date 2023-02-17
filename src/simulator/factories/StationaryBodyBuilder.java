package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.StationaryBody;

public class StationaryBodyBuilder extends Builder<Body>{

	public StationaryBodyBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createInstance(JSONObject data) {
		String id = data.getString("id");
		String gid = data.getString("gid");
		JSONArray p = data.getJSONArray("p");
		double m= data.getDouble("m");
		double px= p.getDouble(0);
		double py= p.getDouble(1);
		
		if(id!=null && gid != null && m>=0 && px>=0 && py>=0 ) {
			return new StationaryBody(id, gid, new Vector2D(px, py),m);
		}else {
			throw new IllegalArgumentException();
		}
	}

}
