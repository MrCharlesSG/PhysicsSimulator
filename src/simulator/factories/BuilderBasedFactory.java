package simulator.factories;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	private Map<String,Builder<T>> _builders;
	private List<JSONObject> _buildersInfo;
	
	public BuilderBasedFactory() {
		// Create a HashMap for _builders, a LinkedList _buildersInfo
		// ...
		this._builders = new HashMap<String,Builder<T>>();
		this._buildersInfo = new LinkedList<JSONObject>();
	}
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this();
		// call addBuilder(b) for each builder b in builder
		// ...
		for(Builder<T> b : builders) {
			addBuilder(b);
		}
	}
	
	public void addBuilder(Builder<T> b) {
		// add and entry ‘‘ b.getTag() −> b’’ to _builders.
		// ...
		_builders.put(b.getTypeTag(), b);
		// add b.getInfo () to _buildersInfo
		// ...
		_buildersInfo.add(b.getInfo());
	}

	
	@Override
	public T createInstance(JSONObject info) {
		if (info == null) {
			throw new IllegalArgumentException("Invalid value for createInstance: null");
		}
		
		JSONObject aux = info.has("data") ? info.getJSONObject("data") : new JSONObject();
		
		Iterator<String> it = _builders.keySet().iterator();
		boolean encontrado=false;
		
		String ty= info.getString("type");
		while(it.hasNext() && !encontrado) {
			if(it.next().equals(ty)) {
				encontrado=true;
			}
		}
		if(encontrado) {
			T e=_builders.get(ty).createInstance(aux);
			if(e!=null) {
				return e;
			}
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
		return Collections.unmodifiableList(_buildersInfo);
	}
	
	
	
}
