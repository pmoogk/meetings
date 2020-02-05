package peter.meeting.components.tabs;

import peter.meeting.common.panels.BaseTablePanel;
import peter.meeting.components.tables.PeopleListTable;
import peter.meeting.data.Person;

public class PeopleTab extends BaseTablePanel<Person> {
	private static final long serialVersionUID = 1L;

	public PeopleTab() {
		super(new PeopleListTable());
		create();
	}
}
