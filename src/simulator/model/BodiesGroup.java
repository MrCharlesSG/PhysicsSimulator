package simulator.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BodiesGroup {
	
	private String Id;
	private ForceLaws Leyes;
	private List<Body> bodies;
	private List<Body> bodiesRO;

	public BodiesGroup(String Id, ForceLaws fl) throws IllegalArgumentException {
		if(Id==null || fl == null) throw new IllegalArgumentException();
		else if(!(Id.trim().length()>0)) throw new IllegalArgumentException();
		else {
			this.Id = Id;
			this.Leyes = fl;
			this.bodies = new LinkedList<Body>();
			this.bodiesRO = Collections.unmodifiableList(bodies);
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
	
	public List<Body> getUnmodificableBodyList(){
		return this.bodiesRO;
	}
	
	public String getIDBodies(){
		String ret="";
		for(Body b:this.bodies) {
			//ret.concat(b.getId()).concat(" ");
			ret+=b.getId()+" ";
		}
		return ret;
	}
	
	public void addBody(Body bs) throws IllegalArgumentException{
	
		if(bs == null) throw new IllegalArgumentException("Valor del cuerpo nulo");
		else {
			if(this.bodies.contains(bs)) {
				throw new IllegalArgumentException("El cuerrpo ya existe");
			}else {
				this.bodies.add(bs);
			}
			
		}
	}
	
	public void advance(double dt) throws IllegalArgumentException{
		
		if(dt<=0) throw new IllegalArgumentException("Delta-time must be positive");
		for(Body b:bodies) {
			b.resetForce();
		}
		this.Leyes.apply(bodies);
		for(Body b:bodies) {	
			b.advance(dt);
		}
		
	}
	
	public JSONObject getState() {
		
		JSONObject i = new JSONObject();
		JSONArray j = new JSONArray();
		for(Body b:bodies) {
			j.put(b.getState());
		}
		i.put("bodies", j);
		i.put("id", this.getId());
		return i;
		
	}
	
	public String toString() {
		return getState().toString();
	}
	
	public String getForceLawsInfo() {
		return Leyes.toString();
		
	}
	
}
