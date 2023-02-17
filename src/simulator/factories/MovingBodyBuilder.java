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
		JSONArray ja =data.getJSONArray("v");
		double m= data.getDouble("m");
		double[] array = new double[2];
		
		for(int i=0;i<2;i++) {
			array[i]=ja.getDouble(i);
		}
		
		Vector2D vel= new Vector2D(array[0],array[1]);
		
		ja = data.getJSONArray("p");
		
		for(int i=0;i<2;i++) {
			array[i]= ja.getDouble(i);
		}
		
		Vector2D pos= new Vector2D(array[0],array[1]);

		if(id!=null && gid != null && m>=0 && pos.getX()>=0 && pos.getY()>=0 && vel.getX()>=0 && vel.getY()>=0) {
			return new MovingBody(id, gid, vel, pos,m);
		}else {
			throw new IllegalArgumentException();
		}
	}

}
