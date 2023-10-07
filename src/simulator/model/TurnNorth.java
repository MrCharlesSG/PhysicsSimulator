package simulator.model;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import simulator.control.Controller;
import simulator.misc.Vector2D;

public class TurnNorth implements SimulatorObserver {
	
	private Map<Body, Integer> contadores;
	private Map<Body, Vector2D> anteriores;
	
	public TurnNorth(Controller ctrl) {
		contadores= new HashMap<Body, Integer>();
		anteriores= new HashMap<Body, Vector2D>();
		ctrl.addObserver(this);
	}
	
	private boolean isNorth(Vector2D v) {
		return v.getY()>0;
	}
	
	public void parse( OutputStream out) {
		
		PrintStream p = new PrintStream(out);

		p.println("Turn North Counter");
		p.print(System.lineSeparator());
		for(Body b: contadores.keySet()) {
			p.print(b.getId()+":"+ b.getgId()+" => "+contadores.get(b)+System.lineSeparator());
		}
		
	}
		
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		for(String k: groups.keySet()) {
			for(Body b: groups.get(k).getUnmodificableBodyList()) {
				Vector2D ant=anteriores.get(b), ac=b.getVelocity();
				if(!isNorth(ant) && isNorth(ac)) {
					contadores.put(b, contadores.get(b)+1);
				}
				anteriores.put(b, b.getVelocity());
			}
		}
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		contadores.clear();
		anteriores.clear();
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		for(String k: groups.keySet()) {
			for(Body b: groups.get(k).getUnmodificableBodyList()) {
				onBodyAdded(groups, b);
			}
		}
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		contadores.put(b, 0);
		anteriores.put(b, b.getVelocity());
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBodyDeleted(Map<String, BodiesGroup> groups, Body b) {
		// TODO Auto-generated method stub

	}

	
	
}
