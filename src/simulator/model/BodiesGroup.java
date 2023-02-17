package simulator.model;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BodiesGroup {
	
	private String Id;
	private ForceLaws Leyes;
	private List<Body> bodies;
	
	public BodiesGroup(String Id, ForceLaws fl) throws IllegalArgumentException {
		if(Id==null || Leyes == null) throw new IllegalArgumentException();
		else if(!(Id.trim().length()>0)) throw new IllegalArgumentException();
		else {
			this.Id = Id;
			this.Leyes = fl;
			this.bodies = new LinkedList<Body>();
		}
	}
	
	public String getId() {
		return this.Id;
	}
	
	public void setForceLaws(ForceLaws fl) throws IllegalArgumentException { //Cambia las leyes a fl
		if(fl==null) throw new IllegalArgumentException();
		else {
			this.Leyes = fl;
		}
	}
	
	public void addBody(Body bs) throws IllegalArgumentException{
		
		if(bs==null) throw new IllegalArgumentException();
		else {
			boolean yaExiste = false;
			for(Body i: this.bodies) {
				if(i==bs) {
					yaExiste = true;
					throw new IllegalArgumentException();
				}
			}
			if(!yaExiste) {
				this.bodies.add(bs);
			}
		}
	}
	
	public void advance(double dt) throws IllegalArgumentException{
		
		if(dt<0) throw new IllegalArgumentException();
		else {
			for(Body i: bodies) {
				i.resetForce();
				this.Leyes.apply(bodies);
				i.advance(dt);
			}
		}
		
	}
	
	public JSONObject getState() { // No se si esta bien porque dice que devuelve el i-esimo y le estoy pasando toda la lista
		
		JSONObject i = new JSONObject();
		i.put("id: ", this.Id);
		JSONArray j = new JSONArray();
		for(int n=0;n<bodies.size();n++) {
			j.put(bodies.get(n));
		}
		i.put("bodies: ", j);
		return i;
		
	}
	
	public String toString() {
		return getState().toString();
	}
	
}
