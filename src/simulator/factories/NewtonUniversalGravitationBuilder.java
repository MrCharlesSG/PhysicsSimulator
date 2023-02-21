package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder(String typeTag, String desc) {
		super("nlug", "Ley universal de gravitacion de Newton");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		double g= data.getDouble("G");

		if(g>=0 ) {
			return new NewtonUniversalGravitation(g);
		}else {
			throw new IllegalArgumentException("Datos incorrectos en lectura de NewtonUniversalGravitation");
		}
	}

}
