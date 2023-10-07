package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsTwoFixedPoint implements ForceLaws{

	protected Vector2D c1;
	protected Vector2D c2;
	
	protected double g1;
	protected double g2;
	
	public MovingTowardsTwoFixedPoint(Vector2D c1, Vector2D c2, double g1, double g2) {
		if(c1==null || c2 == null || g1<0 || g2<=0) {
			throw new IllegalArgumentException();
		}
		this.g1=g1;
		this.g2=g2;
		this.c1=c1;
		this.c2=c2;
	}
	
	public MovingTowardsTwoFixedPoint() {
		this(new Vector2D(), new Vector2D(), 9.81, 9.81);
	}
	
	@Override
	public void apply(List<Body> bs) {
		Vector2D fuerza, d1, d2, gd1, gd2;
		for(Body b: bs) {
			
			d1=c1.minus(b.getPosition());
			d2=c2.minus(b.getPosition());
			gd1=d1.scale(g1);
			gd2= d2.scale(g2);
			fuerza=gd2.plus(gd1);
			fuerza = fuerza.scale(b.getMass());
			b.addForce(fuerza);			
		}
	}
	
	public String toString() {
		return "Moving towards "+c1+" && "+c2+" with constants accelerations "+g1 + " & "+ g2 ;
	}
	
	
}
