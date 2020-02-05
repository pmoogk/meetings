package peter.meeting.data;

public interface Person extends Identifiable, Named {
	public Role getRole();

	public void setRole(Role role);
}
