package peter.meeting.common.panels;

import java.util.ArrayList;
import java.util.List;

import peter.meeting.components.tables.RegularRoleListTable;
import peter.meeting.components.tablesWithActions.ActionButton;
import peter.meeting.data.Role;

public class RegularRolesPanel extends BaseTablePanel<Role> {
	private static final long serialVersionUID = 1L;

	public RegularRolesPanel() {
		super(new RegularRoleListTable());
		create();
	}

	@Override
	protected List<ActionButton> getButtonActions() {
		return new ArrayList<>();
	}
}
