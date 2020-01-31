package peter.meeting.components.tables;

import javax.swing.event.TableModelListener;

public interface TableActions {
	public void addRow();

	public void deleteRows();

	public void shiftUp();

	public void shiftDown();

	public void addListener(TableModelListener listener);

	public void changeProperty(String propertyName, String propertyValue);
}
