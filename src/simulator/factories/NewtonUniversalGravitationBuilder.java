package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder() {
		super("nlug", "Constructor de la Ley Universal de Gravitacion de Newton");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		double g=6.67E-11;
		
		if(!data.isNull("G")) {
			g = data.getDouble("G");
		}
		
		return new NewtonUniversalGravitation(g);
		
	}
	
	public JSONObject getInfo() {
		JSONObject jo=new JSONObject();
		
		jo.put("type",getTypeTag());
		jo.put("desc",getDesc());
		JSONArray jar= new JSONArray();
		jar.put((new JSONObject()).put("G", "the gravitational constant (a number)"));
		jo.put("data", jar);
		return jo;
	}

}
