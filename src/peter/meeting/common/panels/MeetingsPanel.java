package peter.meeting.common.panels;

import java.util.ArrayList;
import java.util.List;

import peter.meeting.components.tables.MeetingListTable;
import peter.meeting.components.tablesWithActions.ActionButton;
import peter.meeting.components.tablesWithActions.ActionButtonFactory;
import peter.meeting.data.DataType;
import peter.meeting.data.Meeting;

public class MeetingsPanel extends BaseTablePanel<Meeting> {
	private static final long serialVersionUID = 1L;
	private boolean isOpen;

	public MeetingsPanel(boolean isOpen) {
		super(new MeetingListTable(isOpen, isOpen ? DataType.OpenMeeting : DataType.CompletedMeeting),
				isOpen ? 100 : 300);
		this.isOpen = isOpen;
		create();
	}

	@Override
	protected List<ActionButton> getButtonActions() {
		List<ActionButton> list = new ArrayList<>();

		if (isOpen) {
			list.add(ActionButtonFactory.addButton);
			list.add(ActionButtonFactory.deleteButton);
		}

		list.add(ActionButtonFactory.openDetailsButton);
		return list;
	}
}
