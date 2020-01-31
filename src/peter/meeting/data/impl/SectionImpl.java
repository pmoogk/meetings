package peter.meeting.data.impl;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.Section;

public class SectionImpl extends NamedImpl implements Section, ToFromJson {
	public SectionImpl(String id, String name) {
		super(id, name);
	}

	public SectionImpl() {
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
