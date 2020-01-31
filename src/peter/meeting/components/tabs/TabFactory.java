package peter.meeting.components.tabs;

import javax.swing.JPanel;

import peter.meeting.common.Pair;

public class TabFactory {
	private static TabFactory instance = null;

	private TabFactory() {
	}

	public static TabFactory instance() {
		if (instance == null) {
			instance = new TabFactory();
		}

		return instance;
	}

	public Pair<String, JPanel> getPanel(TabType tabType) {
		Pair<String, JPanel> result = new Pair<>();

		switch (tabType) {
		case Meetings:
			break;
		case Issues:
			break;
		case Roles:
			result.setFirst("Roles");
			result.setSecond(new RolesTab());
			break;
		case People:
			result.setFirst("People");
			result.setSecond(new PeopleTab());
			break;
		case Sections:
			result.setFirst("Sections");
			result.setSecond(new SectionsTab());
			break;
		default:
			break;
		}

		return result;
	}
}
