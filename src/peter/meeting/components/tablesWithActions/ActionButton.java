package peter.meeting.components.tablesWithActions;

import javax.swing.JButton;

import peter.meeting.components.tables.TableActions;

public class ActionButton {
	private String name;
	private boolean initialEnabled;
	private IsButtonEnabled isButtonEnabled;
	private ButtonExecution executeAction;
	private JButton button;

	public ActionButton(String name, boolean initialEnabled, IsButtonEnabled isButtonEnabled,
			ButtonExecution executeAction) {
		this.name = name;
		this.initialEnabled = initialEnabled;
		this.isButtonEnabled = isButtonEnabled;
		this.executeAction = executeAction;
	}

	public String getName() {
		return name;
	}

	public boolean initialEnabled() {
		return initialEnabled;
	}

	public boolean isEnabled(int selectedCount, boolean isTop, boolean isBottom) {
		return isButtonEnabled.isEnabled(selectedCount, isTop, isBottom);
	}

	public void executeAction(TableActions actions) {
		executeAction.execute(actions);
	}
	
	public JButton getButton() {
	  return button;	
	}
	
	public void setButton( JButton button) {
		this.button = button;
	}
}
