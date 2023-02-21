package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.StationaryBody;

public class StationaryBodyBuilder extends Builder<Body>{

	public StationaryBodyBuilder() {
		super("st_body", "Cuerpo espacial que no se mueve");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createInstance(JSONObject data) {
		try {
		
		JSONArray p = data.getJSONArray("p");
		if(p.length()==2) {
			double m, px, py;
			String id, gid;
			id = data.getString("id");
			gid = data.getString("gid");
			m= data.getDouble("m");
			px= p.getDouble(0);
			py= p.getDouble(1);
			if(id!=null && gid != null && m>=0 && px>=0 && py>=0 ) {
				return new StationaryBody(id, gid, new Vector2D(px, py),m);
			}
		}
		
		}catch(JSONException e){}
		throw new IllegalArgumentException("Datos incorrectos en lectura de StationaryBody");
	}

}
