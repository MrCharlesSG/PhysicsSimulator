package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.PhysicsSimulator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
		
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		
		JSONArray group = jsonInput.getJSONArray("groups");
		//a�adimos los grupos
		for(int i=0; i<group.length(); i++) {
			this.physicsSimulator.addGroup(group.getString(i));
		}

		if(jsonInput.has("laws")) {
			JSONArray laws = jsonInput.getJSONArray("laws");
			//a�adimos las fuerzas a los grupos
			ForceLaws fl;
			for(int i=0; i<laws.length(); i++) {
				fl= this.factoryFL.createInstance(laws.getJSONObject(i).getJSONObject("laws"));
				this.physicsSimulator.setForceLaws(laws.getJSONObject(i).getString("id"), fl);
			}
		}
		JSONArray bodies = jsonInput.getJSONArray("bodies");
		//a�adimos los bodys a los grupos
		Body b;
		for(int i=0; i<bodies.length(); i++) {
			b = this.factoryBody.createInstance(bodies.getJSONObject(i));
			this.physicsSimulator.addBody(b);
		}
		
	}
	
	public void run(int n, OutputStream out) {
		
		PrintStream p = new PrintStream(out);
		p.println("{");
		p.println("\"states\": [");
		
		if(n < 1) {
			p.println(physicsSimulator.toString());
		}
		else {
			for(int i=0;i<n;i++) {
				p.println(physicsSimulator.toString());
				physicsSimulator.advance();
			}
		}
		
		p.println("]");
		p.println("}");
	}
	
}
