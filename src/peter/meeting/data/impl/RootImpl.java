package peter.meeting.data.impl;

import java.util.ArrayList;
import java.util.List;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import peter.meeting.data.DataType;
import peter.meeting.data.Identifiable;
import peter.meeting.data.Person;
import peter.meeting.data.Role;
import peter.meeting.data.Root;
import peter.meeting.data.Section;

public class RootImpl implements Root, ToFromJson {

	private List<Role> roles;
	private List<Person> people;
	private List<Section> sections;
	private long nextId;

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Identifiable> List<T> getList(DataType dataType) {
		switch (dataType) {
		case Role:
			return (List<T>) roles;
		case Person:
			return (List<T>) people;
		case Section:
			return (List<T>) sections;
		default:
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadFrom(JSONObject json) {
		roles = new ArrayList<>();
		people = new ArrayList<>();
		sections = new ArrayList<>();
		JSONArray jsonRoles = (JSONArray) json.get("roles");
		JSONArray jsonPeople = (JSONArray) json.get("people");
		JSONArray jsonSections = (JSONArray) json.get("sections");

		Long next = (Long) json.get("nextId");

		nextId = next == null ? 1 : next;

		if (jsonRoles != null) {
			jsonRoles.forEach(item -> {
				JSONObject roleJson = (JSONObject) item;
				RoleImpl role = new RoleImpl();

				roles.add(role);
				role.loadFrom(roleJson);
			});
		}

		if (jsonPeople != null) {
			jsonPeople.forEach(item -> {
				JSONObject personJson = (JSONObject) item;
				PersonImpl person = new PersonImpl();

				people.add(person);
				person.loadFrom(personJson);
			});
		}

		if (jsonSections != null) {
			jsonSections.forEach(item -> {
				JSONObject sectionJson = (JSONObject) item;
				SectionImpl section = new SectionImpl();

				sections.add(section);
				section.loadFrom(sectionJson);
			});
		}
	}

	@Override
	public void saveTo(JSONObject json) {
		JSONArray rolesArray = new JSONArray();
		JSONArray peopleArray = new JSONArray();
		JSONArray sectionsArray = new JSONArray();

		json.put("roles", rolesArray);
		json.put("people", peopleArray);
		json.put("sections", sectionsArray);
		json.put("nextId", nextId);

		roles.forEach(role -> {
			RoleImpl roleImpl = (RoleImpl) role;
			JSONObject jsonObject = new JSONObject();

			rolesArray.add(jsonObject);
			roleImpl.saveTo(jsonObject);
		});

		people.forEach(person -> {
			PersonImpl personImpl = (PersonImpl) person;
			JSONObject jsonObject = new JSONObject();

			peopleArray.add(jsonObject);
			personImpl.saveTo(jsonObject);
		});
		
		sections.forEach(section -> {
			SectionImpl sectionImpl = (SectionImpl) section;
			JSONObject jsonObject = new JSONObject();

			sectionsArray.add(jsonObject);
			sectionImpl.saveTo(jsonObject);
		});
	}

	public String createID() {
		String nextString = "ID" + nextId;

		nextId++;
		return nextString;
	}
}
