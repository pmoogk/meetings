package peter.meeting.components.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import peter.meeting.data.DataType;
import peter.meeting.data.Person;
import peter.meeting.data.Role;
import peter.meeting.data.StateData;

public class PeopleListTable extends BaseListTable<Person> {

	private static final long serialVersionUID = 1L;
	private List<ComboRole> comboRoles = new ArrayList<>();
	private ComboRole noRole = new ComboRole(null);

	public PeopleListTable() {
		super(new String[] { "Select", "Person name", "Role" }, DataType.Person);
		TableColumn roleColumn = getColumnModel().getColumn(2);
		JComboBox<ComboRole> comboBox = new JComboBox<>();
		List<Role> roles = StateData.instance().getRoot().getList(DataType.Role);
		RoleComboBoxModel comboModel = new RoleComboBoxModel();

		roles.forEach(role -> comboRoles.add(new ComboRole(role)));
		comboRoles.add(noRole);
		roleColumn.setCellEditor(new DefaultCellEditor(comboBox));
		comboBox.setModel(comboModel);
	}

	protected int[] getColumnWidth(TableColumnModel model) {
		return new int[] { 60, 200, 200 };
	}

	protected Object[] getDefaultRow() {
		return new Object[] { false, "", noRole };
	}

	protected Object[] getNewData() {
		return new Object[] { "", null };
	}

	protected Object[] getRow(Person person) {
		return new Object[] { false, person.getName(), new ComboRole(person.getRole()) };
	}

	protected void updateColumn(int rowIndex, int colIndex, Object newValue) {
		List<Person> people = StateData.instance().getRoot().getList(DataType.Person);

		if (colIndex == 1) {
			// Update name
			people.get(rowIndex).setName((String) newValue);
		} else if (colIndex == 2) {
			ComboRole role = (ComboRole) newValue;

			people.get(rowIndex).setRole(role.role == null ? null : role.role);
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
			return ComboRole.class;
		}

		return null;
	}

	private class ComboRole {
		private Role role;

		public ComboRole(Role role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return role == null ? "No role" : role.getName();
		}

		@Override
		public boolean equals(Object obj) {
			String thisId = role == null ? null : role.getId();

			if (!(obj instanceof ComboRole)) {
				return false;
			}

			ComboRole objComboRole = ((ComboRole) obj);
			String objId = objComboRole.role == null ? null : objComboRole.role.getId();

			return objId == thisId || (objId != null && objId.equals(thisId));
		}

		@Override
		public int hashCode() {
			return role == null || role.getId() == null ? 0 : role.getId().hashCode();
		}
	}

	private class RoleComboBoxModel extends AbstractListModel<ComboRole> implements ComboBoxModel<ComboRole> {
		private static final long serialVersionUID = 1L;
		ComboRole selection = null;

		public ComboRole getElementAt(int index) {
			return comboRoles.get(index);
		}

		public int getSize() {
			return comboRoles.size();
		}

		public void setSelectedItem(Object anItem) {
			selection = (ComboRole) anItem;
		}

		public ComboRole getSelectedItem() {
			return selection;
		}
	}
}
