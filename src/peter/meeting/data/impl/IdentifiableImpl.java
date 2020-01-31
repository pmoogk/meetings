package peter.meeting.data.impl;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.Identifiable;

public class IdentifiableImpl implements Identifiable, ToFromJson {
	private String id;

	public IdentifiableImpl(String id) {
		this.id = id;
	}

	IdentifiableImpl() {
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void loadFrom(JSONObject json) {
		id = (String) json.get("id");
	}

	@Override
	public void saveTo(JSONObject json) {
		json.put("id", id);
	}
}
