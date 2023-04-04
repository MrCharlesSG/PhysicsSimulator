package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

class ControlPanel extends JPanel implements SimulatorObserver {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private JToolBar _toolaBar;
	private JFileChooser _fc;
	private boolean _stopped = true; // utilizado en los botones de run/stop
	private JButton _quitButton;
	private JButton _runButton;
	private JButton _selectorButton;
	private JButton _physicsButton;
	private JButton _viewerButton;
	private JButton _stopButton;
	private ViewerWindow viewerWindow;
	private JTextField deltaTimeBox;
	private JSpinner stepsSelector;
	private ForceLawsDialog forceLawsDialog;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		// TODO registrar this como observador
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		_toolaBar = new JToolBar();
		//Fijar la toolbar en la pantalla
		this._toolaBar.setFloatable(false);
		add(_toolaBar, BorderLayout.PAGE_START);
		// TODO crear los diferentes botones/atributos y a�adirlos a _toolaBar.
		// Todos ellos han de tener su correspondiente tooltip. Puedes utilizar
		// _toolaBar.addSeparator() para a�adir la l�nea de separaci�n vertical
		// entre las componentes que lo necesiten
		// Quit Button
		_toolaBar.add(Box.createGlue()); // this aligns the button to the right
		_toolaBar.addSeparator();
		
		// TODO crear el selector de ficheros
		_fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
		_fc.setFileFilter(filter);
		this._fc.setCurrentDirectory(new File("C:\\hlocal\\TP 2\\PhysicsSimulator\\PhysicsSimulator\\resources\\examples\\input"));
		/*
		 * CREAR BOTONES
		 */
		//1 er boton (carpeta)
		//se abre el file system
		_selectorButton = new JButton();
		_selectorButton.setToolTipText("Load an input file into the simulator");
		_selectorButton.setIcon(new ImageIcon("resources/icons/open.png"));
		_selectorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int res=_fc.showOpenDialog(Utils.getWindow(_fc));
				if(JFileChooser.APPROVE_OPTION==res) {
					_ctrl.reset();
					try {
						_ctrl.loadData(new FileInputStream(_fc.getSelectedFile()));
					} catch (FileNotFoundException e1) {
						Utils.showErrorMsg("Archivo incorrecto o no es un json");
					}
				}else if(JFileChooser.CANCEL_OPTION==res) {
					
				}else {
					
				}
			}
			
		});
		
		//2do boton
		//se abre la otra ventana
		_physicsButton = new JButton();
		_physicsButton.setToolTipText("Select force laws for groups");
		_physicsButton.setIcon(new ImageIcon("resources/icons/physics.png"));
		_physicsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//if(forceLawsDialog==null) {
					forceLawsDialog=new ForceLawsDialog(new Frame(), _ctrl );
				//}
				//forceLawsDialog.open();
			} 
			
		});
		//3er boton
		//se abre el simulador visual
		_viewerButton = new JButton();
		_viewerButton.setToolTipText("Open viewer window");
		_viewerButton.setIcon(new ImageIcon("resources/icons/viewer.png"));
		
		_viewerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewerWindow = new ViewerWindow();
			}
		});
		//4to boton
		// boton de start
		_runButton = new JButton();
		_runButton.setToolTipText("Run the simulator");
		_runButton.setIcon(new ImageIcon("resources/icons/run.png"));
		_runButton.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped=false;
				setStateButtons(false);
				try {
					_ctrl.setDeltaTime(Double.parseDouble(deltaTimeBox.getText()));
					run_sim(((Number) stepsSelector.getValue()).intValue());
				}catch(NumberFormatException e1) {
					Utils.showErrorMsg("No es un numero");
					setStateButtons(true);
				}
				
				
			}
		});
		
		//5to boton
		// boton de stop
		_stopButton = new JButton();
		_stopButton.setToolTipText("Stop the simulator");
		_stopButton.setIcon(new ImageIcon("resources/icons/stop.png"));
		_stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped=true;
			}
			
		});
		
		//6to Boton
		//boton de quit
		_quitButton = new JButton();
		_quitButton.setToolTipText("Quit");
		_quitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		_quitButton.addActionListener((e) -> Utils.quit(this));
		
		//dimension para todas las cajitas
		Dimension dm = new Dimension(80, 40);
		
		//CREO lA CAJA DE STEPS
		this.stepsSelector = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		
		stepsSelector.setMaximumSize(dm);
		stepsSelector.setMinimumSize(dm);
		stepsSelector.setPreferredSize(dm);
		
		//CREO lA CAJA DE TIEMPO
		this.deltaTimeBox= new JTextField();
		this.deltaTimeBox.setMaximumSize(dm);
		this.deltaTimeBox.setMinimumSize(dm);
		this.deltaTimeBox.setPreferredSize(dm);
		
		/*
		 * ANADO ELEMENTOS A LA BARRA DE TOOLS
		 */
		_toolaBar.add(_quitButton);
		_toolaBar.add(_selectorButton);
		_toolaBar.add(_physicsButton);
		_toolaBar.add(_viewerButton);
		_toolaBar.add(_runButton);
		_toolaBar.add(_stopButton);
		_toolaBar.add(deltaTimeBox);
		_toolaBar.add(stepsSelector);
	}
	
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
		this.deltaTimeBox.setText(Double.toString(dt));
	}
	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}
	
	private void setStateButtons(boolean state) {
		_selectorButton.setEnabled(state);
		_physicsButton.setEnabled(state);
		_viewerButton.setEnabled(state);
	}
	
	// TODO el resto de m�todos van aqu�
	
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				//este run estab a 1
				_ctrl.run(1);
			} catch (Exception e) {
				// llamar a Utils.showErrorMsg con el mensaje de error que
				// corresponda
				Utils.showErrorMsg(e.getMessage());
				//  activar todos los botones
				_stopped = true;
				setStateButtons(true);
				return;
			}
			SwingUtilities.invokeLater(() -> run_sim(n - 1));
		} else {
			//activar todos los botones
			setStateButtons(true);	
			_stopped = true;
		}
	}

	
}

