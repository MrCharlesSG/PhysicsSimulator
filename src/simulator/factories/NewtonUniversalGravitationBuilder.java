package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder() {
		super("nlug", "Constructor de la Ley Universal de Gravitacion de Newton");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		if(data.isNull("G")) {
			return new NewtonUniversalGravitation();
		}
		else {
			double g = data.getDouble("G");
			return new NewtonUniversalGravitation(g);
		}
	}

}
