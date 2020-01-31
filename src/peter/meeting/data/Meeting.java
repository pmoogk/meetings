package peter.meeting.data;

import java.util.Date;
import java.util.List;

public interface Meeting extends Identifiable {
	public Date getScheduledMeetingDate();

	public Person getWhoCalledMeetingToOrder();

	public Date getStartTime();

	public List<Person> getPeopleAttending();

	public MeetingCategory getMeetingCategory();

	public MeetingDetails getMeetingDetails();
}
