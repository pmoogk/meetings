package peter.meeting.components.tablesWithActions;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class TableListener implements TableModelListener {
	private List<ActionButton> buttons;

	public TableListener(List<ActionButton> buttons) {
		this.buttons = buttons;
	}

	@Override
	public void tableChanged(TableModelEvent evt) {
		if ((evt.getType() == TableModelEvent.UPDATE && evt.getColumn() == 0)
				|| evt.getType() == TableModelEvent.DELETE) {
			DefaultTableModel model = (DefaultTableModel) evt.getSource();
			int selectedCount = 0;
			int lastIndex = -1;

			for (int index = 0, rows = model.getRowCount(); index < rows; index++) {
				if ((boolean) model.getValueAt(index, 0)) {
					selectedCount++;
					lastIndex = index;
				}
			}

			final boolean isTop = lastIndex == 0;
			final boolean isBottom = lastIndex == model.getRowCount() - 1;
			final int count = selectedCount;

			buttons.forEach(button -> button.getButton().setEnabled(button.isEnabled(count, isTop, isBottom)));
		}
	}
}
