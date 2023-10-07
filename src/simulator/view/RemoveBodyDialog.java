package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class RemoveBodyDialog extends JDialog implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> _bodiesModel;
	private Controller _ctrl;
	
	private List<Body> lista;
	
	
	private int  _selectedLawsIndex;
	
	RemoveBodyDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		lista= new ArrayList<>();
		initGUI();
		// TODO registrar this como observer;
		this._ctrl.addObserver(this);
	}
	
	private void initGUI() {
		setTitle("Remove Body Dialog");
		_bodiesModel = new DefaultComboBoxModel<>();
		for(Body b:lista) {
			this._bodiesModel.addElement(b.getId());;
		}
		
		JComboBox<String> cbbBodies = new JComboBox<String>(this._bodiesModel);
		JLabel cbbLabel= new JLabel ("Id of the Body to ELIMINATE: ");
		JButton cancelar = new JButton("Cancel");
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			} 
			
		});
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ctrl.deleteBody(lista.get(cbbBodies.getSelectedIndex()));
				setVisible(false);
			} 
			
		});
		
		JPanel main= new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		setContentPane(main);
		main.add(cbbLabel);
		main.add(cbbBodies);
		JPanel buttons= new JPanel();
		buttons.add(aceptar);
		buttons.add(cancelar);
		main.add(buttons, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(700, 400));
		setResizable(false);
		pack();
		this.setLocationRelativeTo(this.getParent());
		
	}
	
	public void open() {
		setVisible(true);
	}
	
	// TODO el resto de métodos van aquí…
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		this.lista.clear();
		this._bodiesModel.removeAllElements();
	}
	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		for(String k:groups.keySet()) {
			for(Body b: groups.get(k).getUnmodificableBodyList()) {
				this.lista.add(b);
				this._bodiesModel.addElement(b.getId());;
			}
		}
	}
	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		
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

	@Override
	public void onBodyDeleted(Map<String, BodiesGroup> groups, Body b) {
		// TODO Auto-generated method stub
		
	}
	
	

}
