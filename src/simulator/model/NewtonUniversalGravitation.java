package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected Double g;
	protected Double fi;
	protected Vector2D acel;
	protected Vector2D Fi;
	
	NewtonUniversalGravitation(Double G) throws IllegalArgumentException{
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
			if(i.masa == 0) { //Se ponen la velocidad y la aceleracion del cuerpo a 0
				i.velocidad = new Vector2D(0,0);
				acel = new Vector2D();
			}
			else {
				acel = i.fuerza.scale(1/i.masa); //Se cambia la aceleracion, pero no es atributo de ningun cuerpo, entonces no entiendo por que importa cambiarlo
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
