package peter.meeting.components.tables;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import peter.meeting.data.DataType;
import peter.meeting.data.Identifiable;
import peter.meeting.data.StateData;

abstract public class BaseListTable<T extends Identifiable> extends JTable implements TableActions {

	private static final long serialVersionUID = 1L;

	protected DefaultTableModel tModel;
	private DataType dataType;
	private boolean changeListenerEnabled = true;
	private List<T> dataList;

	public BaseListTable(String[] headers, DataType dataType) {
		tModel = new DefaultTableModel(headers, 0);
		dataList = StateData.instance().getRoot().getList(dataType);
		this.dataType = dataType;
		setModel(tModel);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setRowHeight(25);
		addRows(tModel);

		tModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {

				if (!changeListenerEnabled) {
					return;
				}

				int rowIndex = e.getFirstRow();
				int colIndex = e.getColumn();

				switch (e.getType()) {
				case TableModelEvent.UPDATE:
					updateColumn(rowIndex, colIndex, tModel.getValueAt(rowIndex, colIndex));
					break;
				case TableModelEvent.INSERT:
					insertNewRow();
					break;
				case TableModelEvent.DELETE:
					deleteRow(rowIndex);
					break;
				}
			}
		});

		TableColumnModel colModel = getColumnModel();
		int[] widths = getColumnWidth(colModel);

		for (int index = 0, size = widths.length; index < size; index++) {
			TableColumn col = colModel.getColumn(index);

			col.setPreferredWidth(widths[index]);
		}
	}

	public void refreshTable() {
		changeListenerEnabled = false;
		for (int index = 0, size = tModel.getRowCount(); index < size; index++) {
			tModel.removeRow(index);
		}
		addRows(tModel);
		changeListenerEnabled = true;
	}

	abstract protected int[] getColumnWidth(TableColumnModel model);

	abstract protected Object[] getDefaultRow();

	abstract protected Object[] getNewData();

	abstract protected Object[] getRow(T rowData);

	abstract protected void updateColumn(int rowIndex, int colIndex, Object newValue);

	private void addRows(DefaultTableModel model) {
		dataList.forEach(row -> model.addRow(getRow(row)));
	}

	/**
	 * Deletes from the state data model.
	 */
	@SuppressWarnings("unchecked")
	private void insertNewRow() {
		dataList.add((T) StateData.instance().factory().create(dataType, getNewData()));
	}

	/**
	 * Deletes from the state data model at the given index.
	 * 
	 * @param rowIndex
	 */
	private void deleteRow(int rowIndex) {
		dataList.remove(rowIndex);
	}

	@SuppressWarnings("unchecked")
	public void shiftUp() {
		int rowIndex = findSelectedRow();
		Vector<Object> currentRow = (Vector<Object>) tModel.getDataVector().elementAt(rowIndex);

		changeListenerEnabled = false;
		tModel.removeRow(rowIndex);
		tModel.insertRow(rowIndex - 1, currentRow);

		T itemToShift = dataList.remove(rowIndex);

		dataList.add(rowIndex - 1, itemToShift);
		tModel.fireTableCellUpdated(rowIndex, 0);
		changeListenerEnabled = true;
	}

	@SuppressWarnings("unchecked")
	public void shiftDown() {
		int rowIndex = findSelectedRow();
		Vector<Object> currentRow = (Vector<Object>) tModel.getDataVector().elementAt(rowIndex);

		changeListenerEnabled = false;
		tModel.removeRow(rowIndex);
		tModel.insertRow(rowIndex + 1, currentRow);

		T itemToShift = dataList.remove(rowIndex);

		dataList.add(rowIndex + 1, itemToShift);
		tModel.fireTableCellUpdated(rowIndex, 0);
		changeListenerEnabled = true;
	}

	private int findSelectedRow() {
		int result = -1;

		for (int index = 0, size = tModel.getRowCount(); index < size; index++) {
			if ((Boolean) tModel.getValueAt(index, 0) == true) {
				result = index;
				break;
			}
		}

		return result;
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component comp = super.prepareRenderer(renderer, row, column);
		Color alternateColor = new Color(200, 201, 210);
		Color whiteColor = Color.WHITE;
		Color bg = (row % 2 == 0 ? alternateColor : whiteColor);

		comp.setBackground(bg);
		return comp;
	}

	public void addRow() {
		tModel.addRow(getDefaultRow());
	}

	private int getFirstSelected() {
		int result = -1;

		for (int index = 0, rows = tModel.getRowCount(); index < rows; index++) {
			if ((boolean) tModel.getValueAt(index, 0)) {
				result = index;
				break;
			}
		}

		return result;
	}

	public void deleteRows() {
		int firstSelected = getFirstSelected();

		while (firstSelected != -1) {
			tModel.removeRow(firstSelected);
			firstSelected = getFirstSelected();
		}
	}

	public DefaultTableModel getModel() {
		return tModel;
	}

	public void addListener(TableModelListener listener) {
		tModel.addTableModelListener(listener);
	}

	public void changeProperty(String propertyName, String propertyValue) {
		// Do nothing
	}
}
