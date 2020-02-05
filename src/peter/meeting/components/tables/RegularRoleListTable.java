package peter.meeting.components.tables;

import java.util.List;

import javax.swing.table.TableColumnModel;

import peter.meeting.data.DataType;
import peter.meeting.data.Person;
import peter.meeting.data.Role;
import peter.meeting.data.StateData;

public class RegularRoleListTable extends BaseListTable<Role> {

	private static final long serialVersionUID = 1L;

	public RegularRoleListTable() {
		super(new String[] { "Role", "Name", "Present" }, DataType.RegularRole);
	}

	protected int[] getColumnWidth(TableColumnModel model) {
		return new int[] { 300, 300, 60 };
	}

	protected Object[] getDefaultRow() {
		return new Object[] { "", "", false };
	}

	protected Object[] getNewData() {
		return new Object[] { "", "", false };
	}

	protected Object[] getRow(Role role) {
		return new Object[] { role.getName(), getPersonNameForRole(role), false };
	}

	private String getPersonNameForRole(Role role) {
		List<Person> people = StateData.instance().getRoot().getList(DataType.Person);

		Person person = people.stream().filter(per -> {
			Role personRole = per.getRole();

			return personRole.getId().equals(role.getId());
		}).findFirst().orElse(null);

		return person == null ? "" : person.getName();
	}

	protected boolean isColumnEditable(int colIndex) {
		return colIndex == 2;
	}

	protected void updateColumn(int rowIndex, int colIndex, Object newValue) {
		final List<Role> roles = StateData.instance().getRoot().getList(DataType.Role);

		if (colIndex == 2) {
			// Set person present
			// roles.get(rowIndex).setName((String) newValue);
		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Boolean.class;
		}

		return null;
	}
}
