package simulator.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class InfoTable extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String _title;
	TableModel _tableModel;
	
	//constructor
	InfoTable(String title, TableModel tableModel) {
		_title = title;
		_tableModel = tableModel;
		initGUI();
	}
	private void initGUI() {
		// cambiar el layout del panel a BorderLayout()
		this.setLayout(new BorderLayout());
		
		// añadir un borde con título al JPanel, con el texto _title
		this.setBorder(BorderFactory.createTitledBorder(_title));
		
		// añadir un JTable (con barra de desplazamiento vertical) que use
		// _tableModel
		//crea una tabla con dicho modelo y se la pasa como argumento de tabla a un scroll panel
		this.add(new JScrollPane((new JTable(this._tableModel)), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		//horizontal scroll es cosecha propia
		
	}
}
