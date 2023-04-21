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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] _header = { "Id", "Force Laws", "Bodies" };
	List<BodiesGroup> _groups;
	Controller ctrl;
	
	GroupsTableModel(Controller ctrl) {
		_groups = new ArrayList<>();
		// registrar this como observador
		this.ctrl= ctrl;
		this.ctrl.addObserver(this);
	}
	
	// TODO el resto de métodos van aquí …
	//Metodos AbstractTable
	@Override
	public int getRowCount() {
		return this._groups.size();
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
		//return this._groups.get(rowIndex).getState().get(this._header[columnIndex].toLowerCase());
		switch(columnIndex) {
		case 0: return this._groups.get(rowIndex).getId();
		case 1:return this._groups.get(rowIndex).getForceLawsInfo();
		case 2: return this._groups.get(rowIndex).getIDBodies();
		}
		return columnIndex;
	}
	
	//Metodos Observer
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		this._groups.clear();
		for(String k: groups.keySet()) {
			this._groups.add(groups.get(k));
		}
		
		this.fireTableStructureChanged();
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		this._groups.clear();
		this.fireTableStructureChanged();
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		//registro todos los bodiesgroups aqui
		for(String k:groups.keySet()) {
			this._groups.add(groups.get(k));
		}
		this.fireTableStructureChanged();
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		this._groups.add(g);
		this.fireTableStructureChanged();
	}

	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		this.fireTableStructureChanged();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
				
		this.fireTableDataChanged();
	}

	
}
