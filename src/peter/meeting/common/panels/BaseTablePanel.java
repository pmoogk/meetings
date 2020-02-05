package peter.meeting.common.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import peter.meeting.components.tables.BaseListTable;
import peter.meeting.components.tablesWithActions.ActionButton;
import peter.meeting.components.tablesWithActions.ActionButtonFactory;
import peter.meeting.components.tabs.UpdateableTabComponent;
import peter.meeting.data.Identifiable;

abstract public class BaseTablePanel<T extends Identifiable> extends JPanel implements UpdateableTabComponent {

	private static final long serialVersionUID = 1L;
	private BaseListTable<T> table;
	private int tableHeight = 400;

	public BaseTablePanel(BaseListTable<T> table) {
		this.table = table;
	}

	public BaseTablePanel(BaseListTable<T> table, int tableHeight) {
		this.table = table;
		this.tableHeight = tableHeight;
	}

	public void create() {
		Box buttonContainer = Box.createHorizontalBox();
		List<ActionButton> buttonActions = getButtonActions();
		List<JButton> buttons = ActionButtonFactory.createButtons(buttonActions, table);

		// Add first button
		if (!buttons.isEmpty()) {
			buttonContainer.add(Box.createHorizontalGlue());
			buttonContainer.add(buttons.get(0));
		}

		for (int index = 1, size = buttons.size(); index < size; index++) {
			buttonContainer.add(Box.createHorizontalStrut(10));
			buttonContainer.add(buttons.get(index));
		}

		buttonContainer.add(Box.createHorizontalGlue());

		JPanel tablePanel = new JPanel();
		JScrollPane scrollableTable = new JScrollPane(table);
		int scrollBarSize = ((Integer) UIManager.get("ScrollBar.width")).intValue() + 1;

		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollableTable.setBorder(BorderFactory.createLineBorder(Color.black));
		scrollableTable.setPreferredSize(new Dimension(table.getPreferredSize().width + scrollBarSize, tableHeight));
		tablePanel.add(scrollableTable);

		JPanel buttonContainer2 = new JPanel(new BorderLayout());
		JPanel tableContainer2 = new JPanel(new BorderLayout());

		buttonContainer2.add(buttonContainer, BorderLayout.NORTH);
		tableContainer2.add(tablePanel, BorderLayout.NORTH);

		Box containerOuter = Box.createVerticalBox();
		JPanel containerOuter2 = new JPanel(new BorderLayout());

		containerOuter.add(buttonContainer2);
		containerOuter.add(Box.createVerticalStrut(15));
		containerOuter.add(tableContainer2);
		containerOuter2.add(containerOuter, BorderLayout.NORTH);
		setLayout(new BorderLayout());
		add(containerOuter2, BorderLayout.CENTER);
	}

	protected List<ActionButton> getButtonActions() {
		List<ActionButton> list = new ArrayList<>();

		list.add(ActionButtonFactory.addButton);
		list.add(ActionButtonFactory.deleteButton);
		list.add(ActionButtonFactory.shiftUpButton);
		list.add(ActionButtonFactory.shiftDownButton);
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
