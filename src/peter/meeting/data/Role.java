package peter.meeting.data;

public interface Role extends Named {
	public boolean isGuest();
	
	public void setIsGuest( boolean isGuest);
}
