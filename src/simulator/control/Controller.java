package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.PhysicsSimulator;

import org.json.JSONArray;
import org.json.JSONException;

public class Controller {
	//lee los datos de un InputStream dado
	// ejecuta simulador y muestra los estados en un OutputStream dado
	//recibe factory y physicsSimulator
	
	private PhysicsSimulator physicsSimulator;
	private Factory<Body> factoryBody;
	private Factory<ForceLaws> factoryFL;
	
	public Controller(PhysicsSimulator ps, Factory<Body> fb, Factory<ForceLaws> ffl ) {
		this.physicsSimulator=ps;
		this.factoryBody=fb;
		this.factoryFL=ffl;		
	}
	
	public void loadData(InputStream in) {
		try {
			
		}catch(JSONException e){}
		throw new IllegalArgumentException();
	}
	
	public void run(int n, OutputStream out) {
		
	}
	
}
