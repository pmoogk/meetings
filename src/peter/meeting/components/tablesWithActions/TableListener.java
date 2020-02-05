package peter.meeting.components.tablesWithActions;

import java.util.List;

import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class TableListener implements TableModelListener {
	private List<ActionButton> buttonActions;
	private List<JButton> buttons;

	public TableListener(List<ActionButton> buttonActions, List<JButton> buttons) {
		this.buttonActions = buttonActions;
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

			for (int index = 0, size = buttonActions.size(); index < size; index++) {
				ActionButton action = buttonActions.get(index);
				JButton button = buttons.get(index);

				button.setEnabled(action.isEnabled(count, isTop, isBottom));
			}
		}
	}
}
