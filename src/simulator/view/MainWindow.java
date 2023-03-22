package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowListener;

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
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		// TODO crear la tabla de grupos y añadirla a contentPanel.
		// Usa setPreferredSize(new Dimension(500, 250)) para fijar su tamaño
		JPanel grupos=new JPanel();
		grupos.setPreferredSize(new Dimension(500, 250));
		mainPanel.add(grupos,BorderLayout.NORTH);
		// TODO crear la tabla de cuerpos y añadirla a contentPanel.
		// Usa setPreferredSize(new Dimension(500, 250)) para fijar su tamaño
		JPanel cuerpos=new JPanel();
		cuerpos.setPreferredSize(new  Dimension(500,250));
		mainPanel.add(cuerpos,BorderLayout.SOUTH);
		// TODO llama a Utils.quit(MainWindow.this) en el método windowClosing
		//addWindowListener();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setVisible(true);
	}
}

