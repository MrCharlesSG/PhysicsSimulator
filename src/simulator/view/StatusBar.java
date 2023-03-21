package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

class StatusBar extends JPanel implements SimulatorObserver {
	/**
	 * Muestra la info general del simulador (el meollo)
	 */
	private static final long serialVersionUID = 1L;
	Controller _ctrl;
	JLabel timeLabel;
	JLabel numGroupsLabel;
	// TODO Añadir los atributos necesarios, si hace falta …
	StatusBar(Controller ctrl) {
		initGUI();
		this._ctrl=ctrl;
		// TODO registrar this como observador
		this._ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		// TODO Crear una etiqueta de tiempo y añadirla al panel
		this.timeLabel= new JLabel("Time: "+ this._ctrl.getTime());
		this.add(this.timeLabel);
		
		// TODO Crear la etiqueta de número de grupos y añadirla al panel
		this.numGroupsLabel= new JLabel("Time: " + this._ctrl.getNumberOfGroups());
		this.add(this.numGroupsLabel);
		
		// TODO Utilizar el siguiente código para añadir un separador vertical	
		JSeparator s = new JSeparator(JSeparator.VERTICAL);
		s.setPreferredSize(new Dimension(10, 20));
		 this.add(s);
	}
	
	// TODO el resto de métodos van aquí…
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}
	}

