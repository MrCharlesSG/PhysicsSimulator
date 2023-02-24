package simulator.model;

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
	private List<String> lista;
	
	/*
	 * Para saber si existe en un mapa:
	 * map.containsKey("String"); // Devuelve booleano indicando si esta o no
	 * map.get("String")
	 * 
	 * Como recorrer los mapas:
	 * 
	 * Por clave: for(String s: map.keySet(){
	 * 		System.out.println(s); //Muestra el elemento
	 * 		System.out.println(map.get(s); //Accede al valor
	 * }
	 * 
	 * Por valor: for(Integer a: map.keySet(){}
	 * 
	 * 
	 * Como recorrer usando map.size()
	 * 
	 * List<Integer> misValores = new LinkedList<Integer>(map.values());
	 * for(int i=0;i<map.size();i++){
	 * 		misValores.get(i);
	 * }
	 * 
	 * Clase anidada que nos ayudara a comparar
	 * public static class Micomparador implements Comparator<Person>{
	 * 
	 * 		@Override
	 * 		public int compare(Person o1, Person o2){
	 * 				return o1.Id-o2.Id;
	 * 		}
	 * 
	 * }
	 * 
	 */
	
	PhysicsSimulator(ForceLaws fl, Double dt) throws IllegalArgumentException {
		if(fl == null) throw new IllegalArgumentException("Force laws cannot be null");
		else if(dt == null) throw new IllegalArgumentException("Delta-time must be positive");
		else {
			this.dt = dt;
			this.fl = fl;
			this.ta = 0.0;
			this.map = new HashMap<String,BodiesGroup>();
			this.lista = new LinkedList<String>();
		}
	}
	
	public void advance() { 
		for(BodiesGroup s: map.values()){
			s.advance(dt);
		}
		this.ta = this.ta + dt;
	}
	
	public void addGroup(String id) {
		
		if(map.containsKey(id))throw new IllegalArgumentException("Cannot add a group twice");
		map.put(id, null);
		lista.add(id);
	}
	
	public void addBody(Body b) throws IllegalArgumentException{
		
		if(!map.containsKey(b.gId)) throw new IllegalArgumentException("Group must exists");
		map.put(b.gId, new BodiesGroup(b.Id, this.fl));
		
	}
	
	public void setForceLaws(String id, ForceLaws fl) throws IllegalArgumentException {
		
		if(map.containsKey(id))throw new IllegalArgumentException("");
		map.get(id).setForceLaws(fl);
	}
	
	public JSONObject getState() { 
		JSONObject i = new JSONObject();
		i.put("time:", this.ta);
		
		JSONArray j = new JSONArray();
		for(int n=0;n<map.size();n++) {
			j.put(lista.get(n));
		}
		i.put("groups", j);

		return i;
	}
	
	public String toString() {
		return getState().toString();
	}
	
}
