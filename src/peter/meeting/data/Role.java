package peter.meeting.data;

public interface Role extends Named {
	public boolean isGuest();

	public void setIsGuest(boolean isGuest);

	public boolean isSecretary();

	public void setIsSecretary(boolean value);

	public boolean isChair();

	public void setIsChair(boolean value);

	public boolean isRegular();

	public void setIsRegular(boolean value);
}
