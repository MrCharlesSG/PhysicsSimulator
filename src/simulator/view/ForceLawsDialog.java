package simulator.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ForceLawsDialog extends JDialog implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> _lawsModel;
	private DefaultComboBoxModel<String> _groupsModel;
	private DefaultTableModel _dataTableModel;
	private Controller _ctrl;
	private List<JSONObject> _forceLawsInfo;
	
	private String[] _headers = { "Key", "Value", "Description" };
	// TODO en caso de ser necesario, añadir los atributos aquí
	private int _status;
	
	ForceLawsDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
		// TODO registrar this como observer;
		this._ctrl.addObserver(this);
	}
	
	private void initGUI() {
		setTitle("Force Laws Selection");
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// _forceLawsInfo se usará para establecer la información en la tabla
		_forceLawsInfo = _ctrl.getForceLawsInfo();
		
		// TODO crear un JTable que use _dataTableModel, y añadirla al panel
		_dataTableModel = new DefaultTableModel() {
				/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO hacer editable solo la columna 1
					if(column==1) 
						return true;
					else 
						return false;
				}
		};
		_dataTableModel.setColumnIdentifiers(_headers);
		mainPanel.add(new JTable(this._dataTableModel));
		
		_lawsModel = new DefaultComboBoxModel<>();
		
		// TODO añadir la descripción de todas las leyes de fuerza a _lawsModel
		for(JSONObject law: this._forceLawsInfo) {
			this._lawsModel.addElement(law.getString("desc"));
		}
		// TODO crear un combobox que use _lawsModel y añadirlo al panel
		mainPanel.add(new JComboBox<String>(this._lawsModel));
		
		_groupsModel = new DefaultComboBoxModel<>();
		// TODO crear un combobox que use _groupsModel y añadirlo al panel
		mainPanel.add(new JComboBox<String>(this._groupsModel));
		
		// TODO crear los botones OK y Cancel y añadirlos al panel
		//Boton OK
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JSONObject obj= new JSONObject();
				for(int i=0; i<_dataTableModel.getRowCount(); i++) {
					obj.append( _dataTableModel.getValueAt(i, 0).toString(),_dataTableModel.getValueAt(i, 2));
				}
				JSONObject obj2 = new JSONObject();
				obj2.append("data", obj);
				obj2.append("type", _forceLawsInfo.get(1).getString("type"));
				_ctrl.setForcesLaws((String) _groupsModel.getSelectedItem(), obj2);
				
				//COJER EXCEPCIONES
				
				_status=1;
				setVisible(false);
			} 
			
		});
		
		//Boton Cancel
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_status=0;
				setVisible(false);
			} 
			
		});
		mainPanel.add(okButton);
		mainPanel.add(cancelButton);
		
		setPreferredSize(new Dimension(700, 400));
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	public void updateDataTable(int i) {
		JSONObject info= this._forceLawsInfo.get(i);
		JSONObject data= info.getJSONObject("data");
		Iterator<String> it = data.keys();
		while(it.hasNext()) {
			it.next();
			this._dataTableModel.setValueAt(it.toString(), this._dataTableModel.getRowCount(), 0);
			this._dataTableModel.setValueAt(data.getString(it.toString()), this._dataTableModel.getRowCount(), 2);
		}
	}
	

	public int open() {
		if (_groupsModel.getSize() == 0)
			return _status;
		// TODO Establecer la posición de la ventana de diálogo de tal manera que se
		// abra en el centro de la ventana principal
		int posx=this.getOwner().getLocationOnScreen().x+this.getOwner().getSize().width/2-this.getSize().width/2;
		int posy=this.getOwner().getLocationOnScreen().y+this.getOwner().getSize().height/2-this.getSize().height/2;
		this.setLocation(posx,posy );
		
		pack();
		setVisible(true);
		return _status;
	}
	
	// TODO el resto de métodos van aquí…
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		this._groupsModel.removeAllElements();
	}
	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		for(String k:groups.keySet()) {
			this._groupsModel.addElement(groups.get(k).toString());
		}
	}
	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		//no va a ser asi
		this._groupsModel.addElement(g.toString());
		this.onRegister(groups,0,0);
	}
	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		
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
