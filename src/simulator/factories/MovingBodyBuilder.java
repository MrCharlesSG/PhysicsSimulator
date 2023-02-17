package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;

public class MovingBodyBuilder extends Builder<Body>{

	public MovingBodyBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createInstance(JSONObject data) throws IllegalArgumentException{
		String id = data.getString("id");
		String gid = data.getString("gid");
		JSONArray p = data.getJSONArray("p");
		JSONArray v =data.getJSONArray("v");
		double m= data.getDouble("m");
		double px= p.getDouble(0);
		double py= p.getDouble(1);
		double vx= p.getDouble(0);
		double vy= p.getDouble(1);
		
<<<<<<< HEAD
		if(!data.has("id")||!data.has("gid")||!data.has("p")||!data.has("v")||!data.has("m")) 
			throw new IllegalArgumentException("No existe un tag para el Body");
		
		String id=data.getString("id");
		String gid=data.getString("gid");
		double m=data.getDouble("m");
		
		JSONArray ja = new JSONArray("v");
		double[] array = new double[2];
		
		for(int i=0;i<2;i++) {
			array[i]=ja.getDouble(i);
		}
		
		Vector2D vel= new Vector2D(array[0],array[1]);
		
		ja = new JSONArray("p");
		
		for(int i=0;i<2;i++) {
			array[i]= ja.getDouble(i);
		}
		
		Vector2D pos= new Vector2D(array[0],array[1]);
		
		
		return new MovingBody(id,gid,vel,pos,m);
=======
		if(id!=null && gid != null && m>=0 && px>=0 && py>=0 && vx>=0 && vy>=0) {
			return new MovingBody(id, gid, new Vector2D(vx, vy), new Vector2D(px, py),m);
		}else {
			throw new IllegalArgumentException();
		}
>>>>>>> 74cda24d58a724c2d5526f57ea91808d005c0dc3
	}

}
