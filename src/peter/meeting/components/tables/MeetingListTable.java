package peter.meeting.components.tables;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.github.lgooddatepicker.tableeditors.DateTableEditor;

import peter.meeting.data.DataType;
import peter.meeting.data.Meeting;

public class MeetingListTable extends BaseListTable<Meeting> {
	private static final long serialVersionUID = 1L;
	private String status;

	public MeetingListTable(boolean isOpen, DataType dataType) {
		super(new String[] { "Select", "Date", "Status" }, dataType);

		TableColumn dateColumn = getColumnModel().getColumn(1);

		setDefaultEditor(LocalDateTime.class, new DateTableEditor());
		setDefaultRenderer(LocalDateTime.class, new DateTableEditor());

		dateColumn.setCellEditor(getDefaultEditor(LocalDateTime.class));
		dateColumn.setCellRenderer(getDefaultRenderer(LocalDateTime.class));
		this.status = isOpen ? "Open" : "Completed";
	}

	protected int[] getColumnWidth(TableColumnModel model) {
		return new int[] { 60, 200, 200 };
	}

	protected Object[] getDefaultRow() {
		return new Object[] { false, LocalDate.now(), this.status };
	}

	protected Object[] getNewData() {
		return new Object[] { LocalDate.now(), this.status };
	}

	protected Object[] getRow(Meeting meeting) {
		return new Object[] { meeting.getScheduledMeetingDate(), this.status };
	}

	protected void updateColumn(int rowIndex, int colIndex, Object newValue) {
		// TODO make sure that you get the ID of the meeting. You can't use the rowIndex
		// to set the meeting.
		// List<Section> sections =
		// StateData.instance().getRoot().getList(DataType.Section);

		if (colIndex == 1) {
			// Update name
			// sections.get(rowIndex).setName((String) newValue);
		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return Boolean.class;
		case 1:
			return LocalDate.class;
		case 2:
			return String.class;
		}

		return null;
	}
}
