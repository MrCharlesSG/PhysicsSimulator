package simulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class GroupsTableModel extends AbstractTableModel implements SimulatorObserver {

	String[] _header = { "Id", "Force Laws", "Bodies" };
	List<BodiesGroup> _groups;
	Controller ctrl;//Se podrá eliminar en el futuro de seguro
	
	GroupsTableModel(Controller ctrl) {
		_groups = new ArrayList<>();
		// registrar this como observador
		this.ctrl= ctrl;
		this.ctrl.addObserver(this);
	}
	
	// TODO el resto de métodos van aquí …
	
	@Override
	public int getRowCount() {
		return this._groups.size();
	}

	@Override
	public int getColumnCount() {
		return this._header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this._groups.get(rowIndex).getState().get(this._header[columnIndex]);
	}

	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		//registro todos los bodiesgroups aqui
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
