package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	public NoForceBuilder() {
		super("nf", "No se ejerce ninguna fuerza");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		if(data.getString("").equals("")) {
			return new NoForce();
		}else {
			throw new IllegalArgumentException("Argumentos de mas en NoForce");
		}
		
	}

}
