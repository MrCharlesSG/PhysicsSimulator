package simulator.factories;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	private Map<String,Builder<T>> _builders;
	private List<JSONObject> _buildersInfo;
	
	public BuilderBasedFactory() {
		// Create a HashMap for _builders, a LinkedList _buildersInfo
		// ...
	}
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this();
		// call addBuilder(b) for each builder b in builder
		// ...
	}
	
	public void addBuilder(Builder<T> b) {
		// add and entry ‘‘ b.getTag() −> b’’ to _builders.
		// ...
		// add b.getInfo () to _buildersInfo
		// ...
	}

	
	@Override
	public T createInstance(JSONObject info) {
		if (info == null) {
			throw new IllegalArgumentException("Invalid value for createInstance: null");
			}
		
		Iterator<String> it = _builders.keySet().iterator();
		boolean encontrado=false;
		while(it.hasNext() && !encontrado) {
			if(it.next().equals(info.getString("type"))) {
				encontrado=true;
			}
		}
		if(encontrado) {

		}
			// Search for a builder with a tag equals to info . getString ("type"), call its
			// createInstance method and return the result if it is not null . The value you
			// pass to createInstance is :
			//
			// info . has("data") ? info . getJSONObject("data") : new getJSONObject()
			// If no builder is found or the result is null ...
			throw new IllegalArgumentException("Invalid value for createInstance: " + info.toString());
	}

	@Override
	public List<JSONObject> getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
