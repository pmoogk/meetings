package peter.meeting.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import peter.meeting.components.tables.BaseListTable;
import peter.meeting.components.tablesWithActions.ActionButton;
import peter.meeting.components.tablesWithActions.ActionButtonFactory;
import peter.meeting.data.Identifiable;

abstract public class BaseTablePanel<T extends Identifiable> extends JPanel implements UpdateableComponent {

	private static final long serialVersionUID = 1L;
	private BaseListTable<T> table;

	public BaseTablePanel(BaseListTable<T> table) {
		Box buttonContainer = Box.createHorizontalBox();
		List<ActionButton> buttons = getButtons();
		ActionButtonFactory.createButtons(buttons, table);
		this.table = table;

		// Add first button
		buttonContainer.add(buttons.get(0).getButton());

		for (int index = 1, size = buttons.size(); index < size; index++) {
			buttonContainer.add(Box.createHorizontalStrut(10));
			buttonContainer.add(buttons.get(index).getButton());
		}

		Box boxContainerOuter = Box.createVerticalBox();
		Box tableContainer = Box.createHorizontalBox();
		JPanel tablePanel = new JPanel();
		JScrollPane scrollableTable = new JScrollPane(table);
		int scrollBarSize = ((Integer) UIManager.get("ScrollBar.width")).intValue() + 1;

		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollableTable.setBorder(BorderFactory.createEmptyBorder());
		scrollableTable.setPreferredSize(new Dimension(table.getPreferredSize().width + scrollBarSize, 400));
		tablePanel.add(scrollableTable);

		boxContainerOuter.add(buttonContainer);
		boxContainerOuter.add(Box.createVerticalStrut(10));
		tableContainer.add(tablePanel);
		boxContainerOuter.add(tableContainer);

		setLayout(new BorderLayout());
		add(boxContainerOuter, BorderLayout.CENTER);
	}

	protected List<ActionButton> getButtons() {
		List<ActionButton> list = new ArrayList<>();

		list.add(ActionButtonFactory.addButton);
		list.add(ActionButtonFactory.deleteButton);
		return list;
	}

	@Override
	public void stopEditing() {
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
	}

	@Override
	public void refresh() {
		table.refreshTable();
	}
}
