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
		if(Id==null || fl == null) throw new IllegalArgumentException();
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
	
	public void setForceLaws(ForceLaws fl) throws IllegalArgumentException { 
		if(fl==null) throw new IllegalArgumentException();
		else {
			this.Leyes = fl;
		}
	}
	
	public void addBody(Body bs) throws IllegalArgumentException{
		
		if(bs == null) throw new IllegalArgumentException("Valor del cuerpo nulo");
		else {
			boolean yaExiste = false;
			//cambiar el for por un contains y sobreescribimos el equals de body para que solo compare los ID de los cuerpos
			for(Body i: this.bodies) {
				if(i.getId()==bs.getId()) {
					yaExiste = true;
					throw new IllegalArgumentException("El cuerpo ya existe");
				}
			}
			if(!yaExiste) {
				this.bodies.add(bs);
			}
		}
	}
	
	public void advance(double dt) throws IllegalArgumentException{
		
		if(dt<=0) throw new IllegalArgumentException("Delta-time must be positive");
		for(int i=0;i<bodies.size();i++) {
			bodies.get(i).resetForce();
		}
		this.Leyes.apply(bodies);
		for(int i=0;i<bodies.size();i++) {	
			bodies.get(i).advance(dt);
		}
		
	}
	
	public JSONObject getState() {
		
		JSONObject i = new JSONObject();
		JSONArray j = new JSONArray();
		for(int n=0;n<bodies.size();n++) {
			j.put(bodies.get(n).getState());
		}
		i.put("bodies", j);
		i.put("id", this.getId());
		return i;
		
	}
	
	public String toString() {
		return getState().toString();
	}
	
}
