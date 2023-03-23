package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	public NoForceBuilder() {
		super("nf", "Constructor de no fuerza");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		if(data.isEmpty()) {
			return new NoForce();
		}else {
			throw new IllegalArgumentException("Argumentos de mas en NoForce");
		}
		
	}
	
	public JSONObject getInfo() {
		JSONObject jo=new JSONObject();
		
		jo.put("type",getTypeTag());
		jo.put("desc",getDesc());
		JSONArray jar= new JSONArray();
		jo.put("data", jar);
		return jo;
	}
	
}
