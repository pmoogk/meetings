package peter.meeting.data;

import java.util.List;

public interface Root {
	public <T extends Identifiable> List<T> getList(DataType dataType);
}
