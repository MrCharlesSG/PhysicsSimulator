package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder() {
		super("nlug", "Ley universal de gravitacion de Newton");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		try {
			double g= data.getDouble("G");
			if(g>=0 ) {
				return new NewtonUniversalGravitation(g);
			}
		}catch(JSONException e){}
		throw new IllegalArgumentException("Datos incorrectos en lectura de NewtonUniversalGravitation");
	}

}
