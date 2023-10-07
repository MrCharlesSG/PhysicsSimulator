package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingAroundBodyGreaterMass implements ForceLaws{

	protected double c;	
	protected double G;
	
	public MovingAroundBodyGreaterMass(double c, double G) {
		if(G<0 || c>=1 || c<0) {
			throw new IllegalArgumentException();
		}
		this.c=c;
		this.G=G;
	}
	
	@Override
	public void apply(List<Body> bs) {
		//encontrara la mayor posicion
		Body bm=null;
		for(Body b: bs) {
			if(bm==null|| b.getMass()>bm.getMass()) {
				bm=b;				
			}
		}
		//aplicar fuerza
		for(Body b: bs) {
			if(!b.getId().equals(bm.getId())) {
				double cociente=G*b.getMass()*bm.getMass()*(1-c);
				double resta=bm.getPosition().distanceTo(b.getPosition());
				double fm=cociente/(resta*resta);
				Vector2D perpendicular = b.getPosition();
			}
		}
	}
	
	public String toString() {
		return "Moving towards the greater Body, with rotation factor"+c+" & constant acceleration "+G;
	}
	
	
}
