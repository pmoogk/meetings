package peter.meeting.data.impl;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.DataType;
import peter.meeting.data.Person;
import peter.meeting.data.Role;
import peter.meeting.data.StateData;

public class PersonImpl extends NamedImpl implements Person, ToFromJson {
	private String roleId;
	private Role role = null;

	public PersonImpl(String id, String name, Role role) {
		super(id, name);
		this.role = null;
	}

	public PersonImpl() {
	}

	@Override
	public Role getRole() {
		if (role == null) {
			role = StateData.instance().factory().getIdentifiableById(DataType.Role, roleId);
		}

		return role;
	}

	public void setRole(Role role) {
		this.role = role;
		this.roleId = role == null ? null : role.getId();
	}

	@Override
	public void loadFrom(JSONObject json) {
		super.loadFrom(json);
		roleId = (String) json.get("roleId");
	}

	@Override
	public void saveTo(JSONObject json) {
		super.saveTo(json);
		json.put("roleId", roleId);
	}
}
