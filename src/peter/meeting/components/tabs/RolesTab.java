package peter.meeting.components.tabs;

import peter.meeting.components.BaseTablePanel;
import peter.meeting.components.tables.RoleListTable;
import peter.meeting.data.Role;

public class RolesTab extends BaseTablePanel<Role> {
	private static final long serialVersionUID = 1L;

	public RolesTab() {
		super(new RoleListTable());
	}
}
