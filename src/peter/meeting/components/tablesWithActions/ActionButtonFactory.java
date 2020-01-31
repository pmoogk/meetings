package peter.meeting.components.tablesWithActions;

import java.util.List;

import javax.swing.JButton;

import peter.meeting.components.tables.TableActions;

public class ActionButtonFactory {
	static public ActionButton addButton = new ActionButton("Add", true, (selectedCount, isTop, isBottom) -> true,
			tableActions -> tableActions.addRow());

	static public ActionButton deleteButton = new ActionButton("Delete", false,
			(selectedCount, isTop, isBottom) -> selectedCount >= 1, tableActions -> tableActions.deleteRows());

	static public ActionButton shiftUpButton = new ActionButton("Shift Up", false,
			(selectedCount, isTop, isBottom) -> selectedCount == 1 && !isTop, tableActions -> tableActions.shiftUp());

	static public ActionButton shiftDownButton = new ActionButton("Shift Down", false,
			(selectedCount, isTop, isBottom) -> selectedCount == 1 && !isBottom,
			tableActions -> tableActions.shiftDown());

	static public ActionButton openDetailsButton = new ActionButton("Details", false,
			(selectedCount, isTop, isBottom) -> selectedCount == 1,
			tableActions -> tableActions.changeProperty("showdetails", "true"));

	static public ActionButton completeButton = new ActionButton("Complete", false,
			(selectedCount, isTop, isBottom) -> selectedCount == 1,
			tableActions -> tableActions.changeProperty("complete", "true"));

	static public ActionButton reopenButton = new ActionButton("Reopen", false,
			(selectedCount, isTop, isBottom) -> selectedCount == 1,
			tableActions -> tableActions.changeProperty("complete", "false"));

	static public ActionButton generateButton = new ActionButton("Generate", false,
			(selectedCount, isTop, isBottom) -> selectedCount == 1,
			tableActions -> tableActions.changeProperty("generate", "true"));

	static public void createButtons(List<ActionButton> buttonActions, TableActions tableActions) {
		buttonActions.forEach(buttonAction -> {
			JButton button = new JButton(buttonAction.getName());

			button.addActionListener(evt -> buttonAction.executeAction(tableActions));
			buttonAction.setButton(button);
			tableActions.addListener(new TableListener(buttonActions));
		});
	}
}
