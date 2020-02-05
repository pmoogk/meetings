package peter.meeting.data.impl;

import java.util.Date;
import java.util.List;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.Meeting;
import peter.meeting.data.Person;
import peter.meeting.data.Role;

public class MeetingImpl extends IdentifiableImpl implements Meeting, ToFromJson {
	private boolean isOpen;
	private Date scheduledMeeting;
	private Date startTime;
	private String whoChairedMeetingName;
	private String whoChairedMeetingRole;
	private String whoPreparedMeetingName;
	private String whoPreparedMeetingRole;

	public MeetingImpl(String id) {
		super(id);
	}

	public MeetingImpl() {
	}

	@Override
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	@Override
	public Date getScheduledMeetingDate() {
		return scheduledMeeting;
	}

	@Override
	public void setScheduledMeetingData(Date date) {
		this.scheduledMeeting = date;
	}

	@Override
	public String getWhoChairedMeetingName() {
		return whoChairedMeetingName;
	}

	@Override
	public String getWhoChairedMeetingRole() {
		return whoChairedMeetingRole;
	}

	@Override
	public void setWhoChairedMeeting(Person person) {
		Role role = person.getRole();

		whoChairedMeetingName = person.getName();
		whoChairedMeetingRole = role == null ? "" : role.getName();
	}

	@Override
	public String getWhoPreparedMeetingName() {
		return whoPreparedMeetingName;
	}

	@Override
	public String getWhoPreparedMeetingRole() {
		return whoPreparedMeetingRole;
	}

	@Override
	public void setWhoPreparedMeeting(Person person) {
		Role role = person.getRole();

		whoPreparedMeetingName = person.getName();
		whoPreparedMeetingRole = role == null ? "" : role.getName();
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(Date date) {
		startTime = date;
	}

	@Override
	public List<Person> getPeopleAttending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPeopleAttending(List<Person> people) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadFrom(JSONObject json) {
		super.loadFrom(json);
	}

	@Override
	public void saveTo(JSONObject json) {
		super.saveTo(json);
	}
}
