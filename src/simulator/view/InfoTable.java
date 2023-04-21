package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
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
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), this._title, TitledBorder.LEFT,TitledBorder.TOP));
		
		// añadir un JTable (con barra de desplazamiento vertical) que use
		// _tableModel
		//crea una tabla con dicho modelo y se la pasa como argumento de tabla a un scroll panel
		JTable tabla = new JTable(_tableModel) {
			private static final long serialVersionUID = 1L;

			// we override prepareRenderer to resize columns to fit to content
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		this.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		
		
	}
}
