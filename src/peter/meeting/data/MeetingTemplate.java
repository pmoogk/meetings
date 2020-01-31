package peter.meeting.data;

import java.util.List;

public interface MeetingTemplate extends Named {
	public List<Person> getRegularMeetingPeople();

	public List<MeetingCategory> getCategories();
}
