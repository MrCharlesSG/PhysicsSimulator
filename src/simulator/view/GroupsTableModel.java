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
	Controller ctrl;//Se podrá eliminar en el futuro de seguro
	
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
		return this._groups.get(rowIndex).getState().get(this._header[columnIndex]);
	}
	
	//Metodos Observer
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		for (BodiesGroup bg : _groups) {
			//si no es así comprobar para todas los groups que estan en el mapa
			bg.advance(time);
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
		for (BodiesGroup bg : _groups) {
			if(b.getgId().equals(bg.getId())) {
				bg.addBody(b);
			}
		}
		this.fireTableStructureChanged();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		int i=0, pos=0;
		for (BodiesGroup bg : _groups) {
			if(g.getId().equals(bg.getId())) {
				pos=i;
			}
			i++;
		}
		this._groups.set(pos, g);
	}

}
