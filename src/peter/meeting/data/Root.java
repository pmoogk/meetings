package peter.meeting.data;

import java.util.List;
import java.util.Map;

public interface Root {
	public <T extends Identifiable> List<T> getList(DataType dataType);

	public <T extends Identifiable> Map<String, T> getMap(DataType dataType);
}
