package peter.meeting.data.impl;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.Role;

public class RoleImpl extends NamedImpl implements Role, ToFromJson {
	private boolean isGuest;
	private boolean isSecretary;
	private boolean isChair;
	private boolean isRegular;

	public RoleImpl(String id, String name, boolean isGuest, boolean isRegular, boolean isSecretary, boolean isChair) {
		super(id, name);
		this.isGuest = isGuest;
		this.isRegular = isRegular;
		this.isSecretary = isSecretary;
		this.isChair = isChair;
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
	public boolean isSecretary() {
		return isSecretary;
	}

	@Override
	public void setIsSecretary(boolean value) {
		this.isSecretary = value;
	}

	@Override
	public boolean isChair() {
		return isChair;
	}

	@Override
	public void setIsChair(boolean value) {
		this.isChair = value;
	}

	@Override
	public boolean isRegular() {
		return isRegular;
	}

	@Override
	public void setIsRegular(boolean value) {
		this.isRegular = value;
	}

	@Override
	public void loadFrom(JSONObject json) {
		super.loadFrom(json);

		Object value = json.get("isGuest");

		isGuest = value == null ? false : (Boolean) value;
		value = json.get("isSecretary");
		isSecretary = value == null ? false : (Boolean) value;
		value = json.get("isChair");
		isChair = value == null ? false : (Boolean) value;
		value = json.get("isRegular");
		isRegular = value == null ? false : (Boolean) value;
	}

	@Override
	public void saveTo(JSONObject json) {
		super.saveTo(json);
		json.put("isGuest", isGuest);
		json.put("isSecretary", isSecretary);
		json.put("isChair", isChair);
		json.put("isRegular", isRegular);
	}
}
