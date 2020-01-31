package peter.meeting.components.tables;

import java.util.List;

import javax.swing.table.TableColumnModel;

import peter.meeting.data.DataType;
import peter.meeting.data.Role;
import peter.meeting.data.StateData;

public class RoleListTable extends BaseListTable<Role> {

	private static final long serialVersionUID = 1L;

	public RoleListTable() {
		super(new String[] { "Select", "Role name", "Is Guest" }, DataType.Role);
	}

	protected int[] getColumnWidth(TableColumnModel model) {
		return new int[] { 60, 400, 60 };
	}

	protected Object[] getDefaultRow() {
		return new Object[] { false, "", false };
	}

	protected Object[] getNewData() {
		return new Object[] { "", false };
	}

	protected Object[] getRow(Role role) {
		return new Object[] { false, role.getName(), role.isGuest() };
	}

	protected void updateColumn(int rowIndex, int colIndex, Object newValue) {
		final List<Role> roles = StateData.instance().getRoot().getList(DataType.Role);

		if (colIndex == 1) {
			// Update name
			roles.get(rowIndex).setName((String) newValue);
		} else if (colIndex == 2) {
			roles.get(rowIndex).setIsGuest((Boolean) newValue);
		}

	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return Boolean.class;
		case 1:
			return String.class;
		case 2:
			return Boolean.class;
		}

		return null;
	}
}
