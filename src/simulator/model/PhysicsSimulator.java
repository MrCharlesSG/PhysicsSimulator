package simulator.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private double dt;
	private ForceLaws fl;
	private double ta;
	private Map<String,BodiesGroup> map; //Si huebieramos usado un TreeMap apareceria ordenado por la clave
	private List<BodiesGroup> lista;
	private List<SimulatorObserver> listaobs;
	private Map<String, BodiesGroup> groupsRO;
	
	public PhysicsSimulator(ForceLaws fl, Double dt) throws IllegalArgumentException {
		if(fl == null) throw new IllegalArgumentException("Force laws cannot be null");
		else if(dt == null) throw new IllegalArgumentException("Delta-time must be positive");
		else {
			this.dt = dt;
			this.fl = fl;
			this.ta = 0.0;
			this.map = new HashMap<String,BodiesGroup>();
			this.lista = new LinkedList<BodiesGroup>();
			this.listaobs = new LinkedList<SimulatorObserver>();
			this.groupsRO = Collections.unmodifiableMap(map);
		}
	}
	
	public void advance() { 
		
		this.ta = this.ta + dt;
		for(String s: map.keySet()){
			map.get(s).advance(dt);
		}
		for(SimulatorObserver so:listaobs) {
			so.onAdvance(groupsRO, ta);
		}
		
	}
	
	public void addGroup(String id) {
		
		if(map.containsKey(id))throw new IllegalArgumentException("Cannot add a group twice");
		BodiesGroup bd=new BodiesGroup(id,fl);
		map.put(id, bd);
		lista.add(bd);
		for(SimulatorObserver so:listaobs) {
			so.onGroupAdded(groupsRO, bd);
		}
	}
	
	public void addBody(Body b) throws IllegalArgumentException{
		
		if(!map.containsKey(b.getgId())) throw new IllegalArgumentException("Group must exists");
		map.get(b.getgId()).addBody(b);
		for(SimulatorObserver so:listaobs) {
			so.onBodyAdded(groupsRO, b);
		}
	}
	

	
	public void setForceLaws(String id, ForceLaws fl) throws IllegalArgumentException {
		
		if(!map.containsKey(id))throw new IllegalArgumentException("Algun cuerpo ya tiene una fuerza asociada");
		map.get(id).setForceLaws(fl);
		for(SimulatorObserver so: listaobs) {
			so.onForceLawsChanged(map.get(id));
		}
		
	}
	
	public JSONObject getState() { 
		JSONObject i = new JSONObject();
		JSONArray j = new JSONArray();
		for(int n=0;n<map.values().size();n++) {
			j.put(lista.get(n).getState());
		}
		i.put("groups", j);	
		i.put("time", this.ta);
		return i;
	}
	
	public void reset() {
		this.ta=0.0;
		map.clear();
		for(SimulatorObserver so:listaobs) {
			so.onReset(groupsRO, ta, dt);
		}
	}
	
	public String toString() {
		return getState().toString();
	}
	
	public void setDeltaTime(double dt) throws IllegalArgumentException {
		if(dt<0) throw new IllegalArgumentException("Delta-time debe ser positivo");
		this.dt=dt;
		for(SimulatorObserver so:listaobs) {
			so.onDeltaTimeChanged(dt);
		}
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!listaobs.contains(o)) {
			listaobs.add(o);
			o.onRegister(groupsRO, ta, dt);
		}
	}
	
	public void removeObserver(SimulatorObserver o) {
		if(listaobs.contains(o)) {
			listaobs.remove(o);
		}
	}
	
	public double getTime() {
		return this.ta;
	}

	public String getGroupId(int i) {
		return this.lista.get(i).getId();
	}
}
