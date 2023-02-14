package simulator.model;

import java.util.Map;

import org.json.JSONObject;

public class PhysicsSimulator {
	
	private Double dt;
	private ForceLaws fl;
	private String Id;
	private Map<String,BodiesGroup> map = new HashMap<String,BodiesGroup>; //Si huebieramos usado un TreeMap apareceria ordenado por la clave
	
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
		if(fl==null||dt==null) throw new IllegalArgumentException();
		else {
			this.dt = dt;
			this.fl = fl;
		}
	}
	
	public void advance(Double dt) { 
		
	}
	
	public void addBody(Body b) throws IllegalArgumentException{
		
	}
	
	public void setForceLaws(String id, ForceLaws f) throws IllegalArgumentException {
		
	}
	
	public JSONObject getState() { // No se hacerlo porque no se lo que es un map aun
		JSONObject i = new JSONObject();
		i.put("Id", this.Id);
		
		return i;
	}
	
}
