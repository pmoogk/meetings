package peter.meeting.data.impl;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.Named;

public class NamedImpl extends IdentifiableImpl implements Named, ToFromJson {
	private String name;

	public NamedImpl(String id, String name) {
		super(id);
		this.name = name;
	}

	public NamedImpl() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void loadFrom(JSONObject json) {
		super.loadFrom(json);
		name = (String) json.get("name");
	}

	@Override
	public void saveTo(JSONObject json) {
		super.saveTo(json);
		json.put("name", name);
	}
}
