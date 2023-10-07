package simulator.view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class TotalForceDialog extends JDialog implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel _dataTableModel;
	private Map<Body, Vector2D> _fuerzas;
	private String[] _headers = {"Body", "Total Forces"};
	private Controller _ctrl;
	private int _status;
	TotalForceDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		this._fuerzas= new HashMap<Body, Vector2D>();
		_dataTableModel = new DefaultTableModel() ;
		initGUI();
		// TODO registrar this como observer;
		this._ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setTitle("Total Force per Body");
		JPanel mainPanel = new JPanel();
		JPanel buttomPanel= new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		_dataTableModel.setColumnIdentifiers(_headers);
		mainPanel.add(new JScrollPane(new JTable(this._dataTableModel)));
		
		
		
		JButton okButtom= new JButton("OK");
		okButtom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_status=0;
				setVisible(false);
			} 
			
		});
		buttomPanel.add(okButtom);
		mainPanel.add(buttomPanel);
		
		
		setPreferredSize(new Dimension(700, 400));
		setResizable(false);
		pack();
		this.setLocationRelativeTo(this.getParent());
		setVisible(false);
	}

	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		
		for(String k: groups.keySet()) {
				List<Body> list = groups.get(k).getUnmodificableBodyList();
				for(Body b: list) {
					if(_fuerzas.containsKey(b)) {
						Vector2D a=_fuerzas.get(b).plus(b.getForce());
						this._fuerzas.put(b, a);
					}
				}
		}
		
		
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		this._fuerzas.clear();
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		for(String k: groups.keySet()) {
			List<Body> list = groups.get(k).getUnmodificableBodyList();
			for(Body b: list) {
				this._fuerzas.put(b,new Vector2D() );
			}
		}
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		
	}

	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		this._fuerzas.put(b, new Vector2D());
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		
	}

	public int open() {
		this.dateTableUpdate();
		setVisible(true);
		return _status;
	}
	
	private void dateTableUpdate() {
		//DAtos a tabla
		this._dataTableModel.setRowCount(0);
		for(Body b: this._fuerzas.keySet()) {
			Object[] aux= {b.getId(), this._fuerzas.get(b)};
			_dataTableModel.addRow(aux);
		}
		this._dataTableModel.fireTableDataChanged();
	}

	@Override
	public void onBodyDeleted(Map<String, BodiesGroup> groups, Body b) {
		// TODO Auto-generated method stub
		
	}
}
