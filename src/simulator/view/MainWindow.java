package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		// TODO crear ControlPanel y añadirlo en PAGE_START de mainPanel
		JPanel controlPanel = new ControlPanel(_ctrl);
		mainPanel.add(controlPanel,BorderLayout.PAGE_START);
		
		// TODO crear StatusBar y añadirlo en PAGE_END de mainPanel
		JPanel statusBar = new StatusBar(_ctrl);
		mainPanel.add(statusBar,BorderLayout.PAGE_END);
		
		// Definición del panel de tablas (usa un BoxLayout vertical)
		JPanel contentPanel = new JPanel();
		
		
		// TODO crear la tabla de grupos y añadirla a contentPanel.
		//anadir tablas
		InfoTable groupsT=new InfoTable("Groups", new GroupsTableModel(_ctrl));
		InfoTable bodiesT=new InfoTable("Bodies", new BodiesTableModel(_ctrl));
		
		// Usa setPreferredSize(new Dimension(500, 250)) para fijar su tamaño
		Dimension dm = new Dimension (500,250);
		groupsT.setPreferredSize(dm);
		bodiesT.setPreferredSize(dm);
		
		contentPanel.add(groupsT);
		contentPanel.add(bodiesT);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//mainPanel.setLocation(EXIT_ON_CLOSE, ABORT);
		
		
		
		// TODO llama a Utils.quit(MainWindow.this) en el método windowClosing
		//addWindowListener();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Utils.quit(MainWindow.this);
			}
		});
	
	    	
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

