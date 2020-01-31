package peter.meeting.data.impl;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.Role;

public class RoleImpl extends NamedImpl implements Role, ToFromJson {
	private boolean isGuest;

	public RoleImpl(String id, String name, boolean isGuest) {
		super(id, name);
		this.isGuest = isGuest;
	}

	RoleImpl() {
	}

	@Override
	public boolean isGuest() {
		return isGuest;
	}

	@Override
	public void setIsGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	@Override
	public void loadFrom(JSONObject json) {
		super.loadFrom(json);
		isGuest = (Boolean)json.get("isGuest");
	}

	@Override
	public void saveTo(JSONObject json) {
		super.saveTo(json);
		json.put("isGuest", isGuest);
	}
}
