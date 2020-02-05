package peter.meeting.components.tabs;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import peter.meeting.common.panels.MeetingDetailsPanel;

public class MeetingDetailsTab extends JPanel implements UpdateableTabComponent {
	private static final long serialVersionUID = 1L;

	public MeetingDetailsTab() {
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new MeetingDetailsPanel());
	}

	@Override
	public void stopEditing() {
	}

	@Override
	public void refresh() {
	}
}
