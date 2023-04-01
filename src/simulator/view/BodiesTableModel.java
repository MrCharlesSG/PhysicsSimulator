package simulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] _header = { "Id", "gId", "Mass", "Velocity", "Position", "Force" };
	List<Body> _bodies;
	Controller ctrl;//seguramente se puedan eliminar en el futuro
	
	BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		// TODO registrar this como observador
		this.ctrl=ctrl;
		this.ctrl.addObserver(this);
	}
	// TODO el resto de métodos van aquí…
	//Metodos AbstractTable
	@Override
	public int getRowCount() {
		return this._bodies.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return this._header[column];
	}

	@Override
	public int getColumnCount() {
		return this._header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this._bodies.get(rowIndex).getState().get(this._header[columnIndex]);
	}

	//Metodos Observer
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		/*for(String k:groups.keySet()) {
			groups.get(k).advance(time);
		}*/
		this.fireTableDataChanged();
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		this._bodies.clear();
		this.fireTableStructureChanged();
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		for(String k:groups.keySet()) {
			for(Body b:groups.get(k).getUnmodificableBodyList()) {
				this._bodies.add(b);
			}
		}
		this.fireTableStructureChanged();
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		for(Body b:g.getUnmodificableBodyList()) {
			this._bodies.add(b);
		}
		this.fireTableStructureChanged();
	}

	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		this._bodies.add(b);
		this.fireTableStructureChanged();
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
