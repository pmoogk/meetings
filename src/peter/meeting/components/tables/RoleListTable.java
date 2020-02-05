package peter.meeting.components.tables;

import java.util.List;

import javax.swing.table.TableColumnModel;

import peter.meeting.data.DataType;
import peter.meeting.data.Role;
import peter.meeting.data.StateData;

public class RoleListTable extends BaseListTable<Role> {

	private static final long serialVersionUID = 1L;

	public RoleListTable() {
		super(new String[] { "Select", "Role name", "Is Guest", "Is Regular", "Is Secretary", "Is Chair" },
				DataType.Role);
	}

	protected int[] getColumnWidth(TableColumnModel model) {
		return new int[] { 60, 300, 100, 100, 100, 100 };
	}

	protected Object[] getDefaultRow() {
		return new Object[] { false, "", false, false, false, false };
	}

	protected Object[] getNewData() {
		return new Object[] { "", false, false, false, false };
	}

	protected Object[] getRow(Role role) {
		return new Object[] { false, role.getName(), role.isGuest(), role.isRegular(), role.isSecretary(),
				role.isChair() };
	}

	protected void updateColumn(int rowIndex, int colIndex, Object newValue) {
		final List<Role> roles = StateData.instance().getRoot().getList(DataType.Role);

		if (colIndex == 1) {
			// Update name
			roles.get(rowIndex).setName((String) newValue);
		} else if (colIndex == 2) {
			roles.get(rowIndex).setIsGuest((Boolean) newValue);
		} else if (colIndex == 3) {
			roles.get(rowIndex).setIsRegular((Boolean) newValue);
		} else if (colIndex == 4) {
			roles.get(rowIndex).setIsSecretary((Boolean) newValue);
			setOthersToFalse(roles, rowIndex, colIndex);
		} else if (colIndex == 5) {
			roles.get(rowIndex).setIsChair((Boolean) newValue);
			setOthersToFalse(roles, rowIndex, colIndex);
		}
	}

	private void setOthersToFalse(List<Role> roles, int rowIndex, int colIndex) {
		setBooleanColumnFalse(rowIndex, colIndex);

		for (int index = 0, size = roles.size(); index < size; index++) {
			if (index != rowIndex) {
				if (colIndex == 4) {
					roles.get(index).setIsSecretary((Boolean) false);
				} else if (colIndex == 5) {
					roles.get(index).setIsChair((Boolean) false);
				}
			}
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
		case 3:
			return Boolean.class;
		case 4:
			return Boolean.class;
		case 5:
			return Boolean.class;
		}

		return null;
	}
}
