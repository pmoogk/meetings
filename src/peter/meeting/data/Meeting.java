package peter.meeting.data;

import java.util.Date;
import java.util.List;

public interface Meeting extends Identifiable {
	public boolean isOpen();

	public void setIsOpen(boolean isOpen);

	public Date getScheduledMeetingDate();

	public void setScheduledMeetingData(Date date);

	public String getWhoChairedMeetingName();

	public String getWhoChairedMeetingRole();

	public void setWhoChairedMeeting(Person person);

	public String getWhoPreparedMeetingName();

	public String getWhoPreparedMeetingRole();

	public void setWhoPreparedMeeting(Person person);

	public Date getStartTime();

	public void setStartTime(Date date);

	public List<Person> getPeopleAttending();

	public void setPeopleAttending(List<Person> people);
}
