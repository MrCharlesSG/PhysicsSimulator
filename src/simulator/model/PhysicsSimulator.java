package simulator.model;

import java.util.Map;

import org.json.JSONObject;

public class PhysicsSimulator {
	
	private Double dt;
	private ForceLaws fl;
	private String Id;
	private Map<String,BodiesGroup> map; //No se de que va esto
	
	PhysicsSimulator(ForceLaws fl, Double dt) throws IllegalArgumentException {
		if(fl==null||dt==null) throw new IllegalArgumentException();
		else {
			this.dt = dt;
			this.fl = fl;
		}
	}
	
	public void advance(Double dt) { 
		
	}
	
	public void addBody(Body b) throws IllegalArgumentException{
		
	}
	
	public void setForceLaws(String id, ForceLaws f) throws IllegalArgumentException {
		
	}
	
	public JSONObject getState() { // No se hacerlo porque no se lo que es un map aun
		JSONObject i = new JSONObject();
		i.put("Id", this.Id);
		
		return i;
	}
	
}
