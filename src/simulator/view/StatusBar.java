package simulator.view;

import java.awt.Color;
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
		this._ctrl=ctrl;
		// TODO registrar this como observador
		initGUI();
		this._ctrl.addObserver(this);	
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		// TODO Crear una etiqueta de tiempo y añadirla al panel
		this.timeLabel= new JLabel("Time: ");
		this.add(this.timeLabel);
		//separador
		JSeparator s = new JSeparator(JSeparator.VERTICAL);
		s.setPreferredSize(new Dimension(10, 20));
		 this.add(s);
		// TODO Crear la etiqueta de número de grupos y añadirla al panel
		this.numGroupsLabel= new JLabel("Groups: ");
		this.add(this.numGroupsLabel);
		
		//separador 2
		JSeparator j = new JSeparator(JSeparator.VERTICAL);
		j.setPreferredSize(new Dimension(10, 20));
		this.add(j);
		
	}
	
	// TODO el resto de metodos van aqui...
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		this.timeLabel.setText("Time: "+ time);
	}
	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		this.timeLabel.setText("Time: "+time);
		this.numGroupsLabel.setText("Groups: " + groups.size());
	}
	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub
		this.numGroupsLabel.setText("Groups: " + groups.size());
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

