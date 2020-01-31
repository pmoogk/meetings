package peter.meeting.components.tabs;

import java.util.ArrayList;
import java.util.List;

import peter.meeting.components.BaseTablePanel;
import peter.meeting.components.tables.SectionsListTable;
import peter.meeting.components.tablesWithActions.ActionButton;
import peter.meeting.components.tablesWithActions.ActionButtonFactory;
import peter.meeting.data.Section;

public class SectionsTab extends BaseTablePanel<Section> {
	private static final long serialVersionUID = 1L;

	public SectionsTab() {
		super(new SectionsListTable());
	}

	protected List<ActionButton> getButtons() {
		List<ActionButton> list = new ArrayList<>();

		list.add(ActionButtonFactory.addButton);
		list.add(ActionButtonFactory.deleteButton);
		list.add(ActionButtonFactory.shiftUpButton);
		list.add(ActionButtonFactory.shiftDownButton);
		return list;
	}

}
