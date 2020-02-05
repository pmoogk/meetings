package peter.meeting.components.tabs;

import peter.meeting.common.panels.BaseTablePanel;
import peter.meeting.components.tables.SectionsListTable;
import peter.meeting.data.Section;

public class SectionsTab extends BaseTablePanel<Section> {
	private static final long serialVersionUID = 1L;

	public SectionsTab() {
		super(new SectionsListTable());
		create();
	}
}
