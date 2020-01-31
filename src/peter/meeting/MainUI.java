package peter.meeting;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import peter.meeting.common.Pair;
import peter.meeting.components.ButtonTabComponent;
import peter.meeting.components.UpdateableComponent;
import peter.meeting.components.tabs.TabFactory;
import peter.meeting.components.tabs.TabType;
import peter.meeting.data.StateData;

public class MainUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private StateData stateData = StateData.instance();
	private JTabbedPane rootTabPane = null;

	public MainUI() {
		addMenus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Meeting minutes application");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				save();
				e.getWindow().dispose();
			}
		});
	}

	private void save() {
		if (rootTabPane != null) {
			// Need to close the editor in the last tabbed pane,
			UpdateableComponent component = (UpdateableComponent) rootTabPane.getSelectedComponent();

			if (component != null) {
				component.stopEditing();
			}
		}
		stateData.save();
	}

	private void addMenus() {
		JMenuBar menuBar = new JMenuBar();
		// TODO add required menus
		JMenu menu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenu listsMenu = new JMenu("Lists");

		JMenuItem showMeetingsItem = new JMenuItem("Meetings");
		JMenuItem rolesItem = new JMenuItem("Roles");
		JMenuItem peopleItem = new JMenuItem("People");
		JMenuItem sectionItem = new JMenuItem("Section");

		showMeetingsItem.addActionListener(e -> {
			addTab(TabType.Meetings);
		});

		rolesItem.addActionListener(e -> {
			addTab(TabType.Roles);
		});

		peopleItem.addActionListener(e -> {
			addTab(TabType.People);
		});

		sectionItem.addActionListener(e -> {
			addTab(TabType.Sections);
		});

		saveMenuItem.addActionListener(e -> {
			save();
		});

		openMenuItem.addActionListener(e -> {
			// stateData.open(this);
		});

		menu.add(newMenuItem);
		menu.add(openMenuItem);
		menu.add(saveMenuItem);
		menuBar.add(menu);
		listsMenu.add(rolesItem);
		listsMenu.add(peopleItem);
		listsMenu.add(sectionItem);
		menuBar.add(listsMenu);
		this.setJMenuBar(menuBar);
	}

	private void addTab(TabType tabType) {
		if (rootTabPane == null) {
			rootTabPane = new JTabbedPane();
			rootTabPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			getContentPane().add(rootTabPane, BorderLayout.CENTER);
		}

		int tabIndex = findTab(rootTabPane, tabType);

		if (tabIndex == -1) {
			int tabCount = rootTabPane.getTabCount();
			Pair<String, JPanel> tabInfo = TabFactory.instance().getPanel(tabType);

			rootTabPane.addTab(tabInfo.getFirst(), tabInfo.getSecond());
			rootTabPane.setTabComponentAt(tabCount, new ButtonTabComponent(rootTabPane, tabType));
			rootTabPane.setSelectedIndex(tabCount);
		} else {
			UpdateableComponent component = (UpdateableComponent) rootTabPane.getComponentAt(tabIndex);

			component.refresh();
			rootTabPane.setSelectedIndex(tabIndex);
		}

		rootTabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JTabbedPane tabPane = (JTabbedPane) e.getSource();
				int currIndex = tabPane.getSelectedIndex();

				int oldIdx = 0;
				Object old = tabPane.getClientProperty("OLD_INDEX");

				if (old instanceof Integer) {
					oldIdx = (Integer) old;
				}

				if (old != null && oldIdx < tabPane.getTabCount()) {
					((UpdateableComponent) tabPane.getComponentAt(oldIdx)).stopEditing();
				}

				if (currIndex != -1) {
					tabPane.putClientProperty("OLD_INDEX", currIndex);
				}
			}
		});
	}

	private int findTab(JTabbedPane tabbedPane, TabType tabType) {
		int result = -1;

		for (int index = 0, size = tabbedPane.getTabCount(); index < size; index++) {
			ButtonTabComponent tab = (ButtonTabComponent) tabbedPane.getTabComponentAt(index);

			if (tabType == tab.getTabType()) {
				result = index;
				break;
			}
		}

		return result;
	}
}
