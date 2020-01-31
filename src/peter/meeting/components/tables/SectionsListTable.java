package peter.meeting.components.tables;

import java.util.List;

import javax.swing.table.TableColumnModel;

import peter.meeting.data.DataType;
import peter.meeting.data.Section;
import peter.meeting.data.StateData;

public class SectionsListTable extends BaseListTable<Section> {
	private static final long serialVersionUID = 1L;

	public SectionsListTable() {
		super(new String[] { "Select", "Section name" }, DataType.Section);
	}

	protected int[] getColumnWidth(TableColumnModel model) {
		return new int[] { 60, 300 };
	}

	protected Object[] getDefaultRow() {
		return new Object[] { false, "" };
	}

	protected Object[] getNewData() {
		return new Object[] { "" };
	}

	protected Object[] getRow(Section section) {
		return new Object[] { false, section.getName() };
	}

	protected void updateColumn(int rowIndex, int colIndex, Object newValue) {
		List<Section> sections = StateData.instance().getRoot().getList(DataType.Section);

		if (colIndex == 1) {
			// Update name
			sections.get(rowIndex).setName((String) newValue);
		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return Boolean.class;
		case 1:
			return String.class;
		}

		return null;
	}
}
