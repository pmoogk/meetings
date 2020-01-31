package peter.meeting.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ibm.json.java.JSONObject;

import peter.meeting.data.impl.DataFactoryImpl;
import peter.meeting.data.impl.RootImpl;

public class StateData {
	private static StateData instance = null;
	private RootImpl root = new RootImpl();
	private DataFactoryImpl factory = new DataFactoryImpl(root);

	private StateData() {
	}

	public static StateData instance() {
		if (instance == null) {
			instance = new StateData();
		}

		return instance;
	}

	public Root getRoot() {
		return root;
	}

	public DataFactory factory() {
		return factory;
	}

	public void load() {
		Path filePath = Paths.get("meetings.json");

		if (Files.exists(filePath)) {
			try (BufferedReader br = Files.newBufferedReader(filePath)) {
				JSONObject json = JSONObject.parse(br);
				root.loadFrom(json);
			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
		} else {
			root.loadFrom(new JSONObject());
		}
	}

	public void save() {
		Path filePath = Paths.get("meetings.json");
		JSONObject outputJson = new JSONObject();

		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
			root.saveTo(outputJson);
			writer.write(outputJson.toString());
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}
}
