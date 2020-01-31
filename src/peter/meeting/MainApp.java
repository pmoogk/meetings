package peter.meeting;

import javax.swing.SwingUtilities;

import peter.meeting.data.StateData;

public class MainApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				StateData.instance().load();
				new MainUI();
			}
		});
	}

}
