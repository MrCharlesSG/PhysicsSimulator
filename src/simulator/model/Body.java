package simulator.model;

import org.json.JSONObject;
import simulator.misc.Vector2D;

public abstract class Body {
	protected String Id;
	protected String gId;
	protected Vector2D velocidad;
	protected Vector2D fuerza;
	protected Vector2D posicion;
	protected Double masa;
	
	//jijijija
	
	Body (String id, String gid, Vector2D vel, Vector2D pos, Double mass) throws IllegalArgumentException {
		if(id==null||gid==null||vel==null||pos==null||mass==null) throw new IllegalArgumentException("Algun argumento es nulo");
		else if(id.trim().length()>0||gid.trim().length()>0) throw new IllegalArgumentException("Ni idea");
		else if(mass<0) throw new IllegalArgumentException("La masa no puede ser negativa");
		else {
			this.Id=id;
			this.gId=gid;
			this.velocidad=vel;
			this.fuerza = new Vector2D(0,0);
			this.posicion=pos;
			this.masa=mass;
		}
	}
	
	public String getId() {
		return this.Id;
	}
	
	public String getgId() {
		return this.gId;
	}
	
	public Vector2D getVelocity() {
		return this.velocidad;
	}
	
	public Vector2D getForce() {
		return this.fuerza;
	}
	
	public Vector2D getPosition() {
		return this.posicion;
	}
	
	public double getMass() {
		return this.masa;
	}
	
	void addForce(Vector2D f) {
		this.fuerza = this.fuerza.plus(f);
	}
	
	void resetForce() {
		this.fuerza = new Vector2D(0,0);
	}
	
	abstract void advance(Double dt);
	
	public JSONObject getState() {
		JSONObject j= new JSONObject();
		j.put("Id",this.Id);
		j.put("m",this.masa);
		j.put("p",this.posicion.asJSONArray());
		j.put("v",this.velocidad.asJSONArray());
		j.put("f", this.fuerza.asJSONArray());
		return j;
	}
	
}
