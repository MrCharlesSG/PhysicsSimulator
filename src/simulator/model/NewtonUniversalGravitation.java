package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected Double g = 6.67 * Math.pow(10, -11);
	protected Double fi;
	protected Vector2D Fi;
	
	NewtonUniversalGravitation(){ //Por defecto g sera 6.67 * 10^-11
		this.Fi = new Vector2D();
	}
	
	public NewtonUniversalGravitation(Double G) throws IllegalArgumentException{
		if(G < 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.g = G;
			this.Fi = new Vector2D();
		}
	}
	
	@Override
	public void apply(List<Body> bs) {
		
		for(Body i : bs) {
			if(i.masa == 0) { //Se pone la velocidad a 0, porque la aceleracion del cuerpo ya esta a 0
				i.velocidad = new Vector2D();
			}
			else {
				Vector2D acel = new Vector2D();
				acel = i.fuerza.scale(1/i.masa); //Se cambia la aceleracion, pero no es atributo de ningun cuerpo, entonces no entiendo por que importa cambiarlo
				this.Fi = acel.scale(i.masa);
				for(Body j:bs) {
					if(i.posicion.distanceTo(j.posicion) > 0) {
						Double Fij = this.g * ((i.masa*j.masa)/(j.posicion.distanceTo(i.posicion)*j.posicion.distanceTo(i.posicion)));
						this.fi = this.fi + Fij;
					}
					Vector2D dij = i.posicion.minus(j.posicion);
					this.Fi = this.Fi.plus(dij.scale(this.fi));
				}
			}
		}
	}
	
	public String toString() { //Saca por pantalla el vector Fi (Supongo,tampoco lo especifican)
		return this.Fi.toString();
	}
	
}
