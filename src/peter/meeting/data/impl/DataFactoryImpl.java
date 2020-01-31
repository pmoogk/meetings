package peter.meeting.data.impl;

import java.util.List;
import java.util.Optional;

import peter.meeting.data.DataFactory;
import peter.meeting.data.DataType;
import peter.meeting.data.Identifiable;
import peter.meeting.data.Role;

public class DataFactoryImpl implements DataFactory {

	private RootImpl root;

	public DataFactoryImpl(RootImpl root) {
		this.root = root;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Identifiable> T getIdentifiableById(DataType dataType, String id) {
		Identifiable result = null;
		List<Identifiable> list = root.getList(dataType);

		if (id != null) {
			Optional<Identifiable> found = list.stream().filter(identifier -> identifier.getId().equals(id))
					.findFirst();
			result = found.isPresent() ? found.get() : null;
		}

		return (T) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Identifiable> T create(DataType dataType, Object... parameters) {
		Identifiable result = null;

		switch (dataType) {
		case Role:
			result = new RoleImpl(root.createID(), (String) parameters[0], (Boolean) parameters[1]);
			break;
		case Person:
			result = new PersonImpl(root.createID(), (String) parameters[0], (Role) parameters[1]);
			break;
		case Section:
			result = new SectionImpl(root.createID(), (String) parameters[0]);
			break;
		default:
			break;
		}

		return (T) result;
	}
}
