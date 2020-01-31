package peter.meeting.data;

public interface DataFactory {
	public <T extends Identifiable> T getIdentifiableById(DataType dataType, String id );

	public <T extends Identifiable> T create(DataType dataType, Object... parameters);
}
