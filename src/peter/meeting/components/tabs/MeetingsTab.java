package peter.meeting.components.tabs;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import peter.meeting.common.panels.MeetingsPanel;

public class MeetingsTab extends JPanel implements UpdateableTabComponent{
	private static final long serialVersionUID = 1L;

	public MeetingsTab() {
		MeetingsPanel openMeetings = new MeetingsPanel(true);
		Box verticalBox = Box.createVerticalBox();
		JLabel meetingLabel = new JLabel("Open Meetings");
		JPanel labelPanel = new JPanel();
	
		labelPanel.add(meetingLabel);
		
		meetingLabel.setBorder( BorderFactory.createEmptyBorder(5, 0, 10, 0));
		meetingLabel.setFont( new Font(meetingLabel.getName(), Font.PLAIN, 20));
		verticalBox.add(labelPanel);
		verticalBox.add(openMeetings);

		setLayout(new BorderLayout());
		add(verticalBox, BorderLayout.CENTER);
	}

	@Override
	public void stopEditing() {
	}

	@Override
	public void refresh() {
	}
}
