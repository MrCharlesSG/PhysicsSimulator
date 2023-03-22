package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
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
	// TODO añade más atributos aquí …
	private JButton _runButton;
	private JButton _selectorButton;
	private JButton _simulationButton;
	private JButton _viewerButton;
	private JSpinner _time;
	private JSpinner _steps;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		// TODO registrar this como observador
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		_toolaBar = new JToolBar();
		add(_toolaBar, BorderLayout.PAGE_START);
		// TODO crear los diferentes botones/atributos y añadirlos a _toolaBar.
		// Todos ellos han de tener su correspondiente tooltip. Puedes utilizar
		// _toolaBar.addSeparator() para añadir la línea de separación vertical
		// entre las componentes que lo necesiten
		// Quit Button
		_toolaBar.add(Box.createGlue()); // this aligns the button to the right
		_toolaBar.addSeparator();
		_quitButton = new JButton();
		_quitButton.setToolTipText("Quit");
		_quitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		_quitButton.addActionListener((e) -> Utils.quit(this));
		_toolaBar.add(_quitButton);
		
		//Esto no es asi, es con un JTextField, luego lo cambio
		_time=new JSpinner();
		_time.setToolTipText("Real time (seconds) corresponding to a step");
		
		// Steps JSpinner
		int stepsInitial = 10000;
		int stepsMin = 1;
		int stepsMax = 10000;
		int stepsDistance = 1;
		_steps=new JSpinner(new SpinnerNumberModel(stepsInitial,stepsMin,stepsMax,stepsDistance));
		_steps.setToolTipText("Simulation steps to run: 1-10000");
		_steps.setPreferredSize(new Dimension(40,20));
		
		
		_viewerButton = new JButton();
		_viewerButton.setToolTipText("Open viewer window");
		_viewerButton.setIcon(new ImageIcon("resources/icons/viewer.png"));
		_viewerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		_toolaBar.add(_viewerButton);
		
		_selectorButton = new JButton();
		_selectorButton.setToolTipText("Load an input file into the simulator");
		_selectorButton.setIcon(new ImageIcon("resources/icons/open.png"));
		_selectorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Es una prueba, no es asi, es por ver si accede bien al archivo
				if(e.getSource()==_selectorButton) {
					JFileChooser filechooser = new JFileChooser();
					filechooser.setDialogTitle("Open");
					filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	                filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	                FileNameExtensionFilter filter = new FileNameExtensionFilter("json");
	                filechooser.setFileFilter(filter);

	                int result = filechooser.showOpenDialog(_selectorButton);

	                if (result == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = filechooser.getSelectedFile();
	                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
	                }
				}
			}
			
		});
		_toolaBar.add(_selectorButton);
		
		_simulationButton = new JButton();
		_simulationButton.setToolTipText("Select force laws for groups");
		_simulationButton.setIcon(new ImageIcon("resources/icons/physics.png"));
		_simulationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			} 
			
		});
		_toolaBar.add(_simulationButton);
		
		_runButton = new JButton();
		_runButton.setToolTipText("Run the simulator");
		_runButton.setIcon(new ImageIcon("resources/icons/run.png"));
		_runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		_toolaBar.add(_runButton);
		
		// TODO crear el selector de ficheros
		_fc = new JFileChooser();
		
				
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}
	
}

