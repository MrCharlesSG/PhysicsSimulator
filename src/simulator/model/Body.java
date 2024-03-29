package simulator.model;

import simulator.misc.Vector2D;
import org.json.JSONObject;

public abstract class Body {
	protected String Id;
	protected String gId;
	protected Vector2D velocidad;
	protected Vector2D fuerza;
	protected Vector2D posicion;
	protected double masa;
	
	public Body (String id, String gid, Vector2D pos, Vector2D vel, Double mass) throws IllegalArgumentException {
		if(id==null||gid==null||vel==null||pos==null||mass==null) throw new IllegalArgumentException("Algun argumento es nulo");
		else if(id.trim().length()==0||gid.trim().length()==0) throw new IllegalArgumentException("El Id o gId tienen caracteres en blanco");
		else if(mass<=0) throw new IllegalArgumentException("La masa no puede ser negativa");
		else {
			this.Id=id;
			this.gId=gid;
			this.velocidad=vel;
			this.fuerza=new Vector2D();
			this.posicion = pos;
			this.masa = mass;
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
	
	abstract void advance(double dt);
	
	public JSONObject getState() {
		JSONObject j= new JSONObject();
		j.put("id",this.getId());
		j.put("p",this.getPosition().asJSONArray());
		j.put("v",this.getVelocity().asJSONArray());
		j.put("f",this.getForce().asJSONArray());
		j.put("m",this.getMass());
		
		return j;
	}
	
	public String toString() {
		return this.getState().toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.Id.equals(((Body)obj).Id) && this.gId.equals(((Body)obj).getgId());
	}
	
}
