package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
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
	private int  _selectedLawsIndex;
	
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
		
		mainPanel.add(new JScrollPane(new JTable(this._dataTableModel)));
		
		//Crear el combomox de leyes
		_lawsModel = new DefaultComboBoxModel<>();		
		
		for(JSONObject law: this._forceLawsInfo) {
			this._lawsModel.addElement(law.getString("desc"));
		}
		
		JComboBox<String> cbbLawsMod = new JComboBox<String>(this._lawsModel);
		JLabel lawsLabel= new JLabel ("Laws: ");
		cbbLawsMod.setPreferredSize(new Dimension(200, 20));
		//agregar funcionalidad a combobox leyes
		cbbLawsMod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//elimino todos los elemntos
				_dataTableModel.setRowCount(0);
				String info= (String)cbbLawsMod.getSelectedItem();
				JSONArray data= new JSONArray();
				//que law es y guardo en data
				for(JSONObject law: _forceLawsInfo) {
					if(law.getString("desc").toString().equals(info)) {
						data=law.getJSONArray("data");
					}
				}
				
				//cargo data a _dataTableModel
				for(int i=0; i<data.length(); i++) {
					JSONObject js=((JSONObject)data.get(i));
					for(String st:js.keySet()) {
						Object[] aux={st,"",js.get(st) };
						_dataTableModel.addRow(aux );
					}
				}
				
				//Guardo selectedLawsIndex
				_selectedLawsIndex=cbbLawsMod.getSelectedIndex();
				
			}
		});
		
		//crear el combobox de grupos
		_groupsModel = new DefaultComboBoxModel<>();
		JComboBox<String> cbbGroupsMod = new JComboBox<String>(this._groupsModel);
		JLabel groupsLabel=new JLabel("Group: ");
		
		cbbGroupsMod.setPreferredSize(new Dimension (50, 20));
		//mainPanel.add(new JComboBox<String>(this._groupsModel));
		
		// TODO crear los botones OK y Cancel y añadirlos al panel
		//Boton OK
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//el pdf dice que hay que hacer un JSONObject que guarde la informacion de la tabla
					
					JSONObject jsonObject = new JSONObject();
					
			        //Guardo 
			        JSONObject okJson = new JSONObject();
			        //typo de fuerza
			        okJson.put("type", _forceLawsInfo.get(_selectedLawsIndex).getString("type"));
			        
			      //guardo datos de la tabla en JSONArray
			        String var;
			        for (int i = 0; i < _dataTableModel.getRowCount(); i++) {
			        	var=(String) _dataTableModel.getValueAt(i, 0);
			        	if(var=="c") {
			        		JSONArray ar= new JSONArray();
			        		String[] aux=((String)_dataTableModel.getValueAt(i,1)).trim().split(",");
			        		if(aux.length==2) {
			        			String aux2= aux[0].substring(1, aux[0].length());
			        			ar.put(aux2);
			        			aux2=aux[1].substring(0,aux[1].length()-1);
			        			ar.put(aux2);
			        			jsonObject.put(var, ar);
			        		}else {
			        			throw new IllegalArgumentException();
			        		}
			        	}else {
			        		jsonObject.put(var,_dataTableModel.getValueAt(i,1) );
			        	}
			        }
			      //datos json
			        okJson.put("data", jsonObject);
			        //set forcelaws del grupo correspondiente
			        String st=(String)_groupsModel.getSelectedItem();
			        _ctrl.setForcesLaws(st, okJson);
			        _status=1;
					setVisible(false);
				
				} catch(JSONException | IllegalArgumentException e2) {
					Utils.showErrorMsg("En Universal tiene que ser un double"+ System.lineSeparator()+"En MovingTowardsFixedPoint"+System.lineSeparator()+"	c:double, double"+System.lineSeparator()+"	g:double");
				}
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
		
		JPanel panelCbb = new JPanel();
		panelCbb.setLayout(new FlowLayout());
		//Añade lwas cbb
		panelCbb .add(lawsLabel);
		panelCbb.add(cbbLawsMod);
		panelCbb.add(Box.createHorizontalStrut(10));
		//añada groups cbb
		panelCbb.add(groupsLabel);
		panelCbb.add(cbbGroupsMod);
		
		//añade bto
		JPanel panelB = new JPanel();
		panelB.setLayout(new FlowLayout());
		panelB.add(okButton);
		panelB.add(cancelButton);
		
		//JPanel principal de los cbb y boton
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.add(panelCbb, BorderLayout.NORTH);
		panelAux.add(panelB, BorderLayout.SOUTH);
		
		this.add(panelAux);
		setPreferredSize(new Dimension(700, 400));
		if (getParent() != null)
			setLocation(//
					getParent().getLocation().x + getParent().getWidth() / 2 - getWidth() / 2, //
					getParent().getLocation().y + getParent().getHeight() / 2 - getHeight() / 2);
		pack();
		setResizable(false);
		setVisible(false);
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
		if (_groupsModel.getSize() == 0) {
			Utils.showErrorMsg("Tienes que cargar un archivo");
			return _status;
			
		}
		// TODO Establecer la posición de la ventana de diálogo de tal manera que se
		// abra en el centro de la ventana principal
		/*
		int posx=this.getOwner().getLocationOnScreen().x+this.getOwner().getSize().width/2-this.getSize().width/2;
		int posy=this.getOwner().getLocationOnScreen().y+this.getOwner().getSize().height/2-this.getSize().height/2;
		this.setLocation(posx,posy );
		*/
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
			this._groupsModel.addElement(groups.get(k).getId());
		}
	}
	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		//no va a ser asi
		this._groupsModel.addElement(g.getId());
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
