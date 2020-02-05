package peter.meeting.common.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import peter.meeting.data.DataType;
import peter.meeting.data.Person;
import peter.meeting.data.Role;
import peter.meeting.data.StateData;

public class MeetingDetailsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public MeetingDetailsPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridBagLayout());

		add(leftPanel);
		leftPanel.setBorder(new EmptyBorder(0, 15, 0, 0));

		GridBagConstraints c = new GridBagConstraints();
		List<Person> regularPeople = getRegularPeople();
		String[] names = getNames(regularPeople);
		int secretaryIndex = getPersonIndex(regularPeople, true);
		int chairIndex = getPersonIndex(regularPeople, false);
		JLabel meetingTile = new JLabel("Meeting for January 1, 2020");
		JLabel whoPreparedLabel = new JLabel("Who prepared the minutes:");
		JComboBox<String> whoPreparedCombo = new JComboBox<>(names);
		JLabel whoChairedLabel = new JLabel("Who chaired the meeting:");
		JComboBox<String> whoChairedCombo = new JComboBox<>(names);

		if (secretaryIndex != -1) {
			whoPreparedCombo.setSelectedIndex(secretaryIndex);
		}

		if (chairIndex != -1) {
			whoChairedCombo.setSelectedIndex(chairIndex);
		}

		Dimension size = whoPreparedCombo.getPreferredSize();

		size.width = 250;
		whoPreparedCombo.setPreferredSize(size);
		whoChairedCombo.setPreferredSize(size);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 5, 0);
		leftPanel.add(meetingTile, c);

		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 20);
		leftPanel.add(whoPreparedLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 5, 0);
		leftPanel.add(whoPreparedCombo, c);

		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 20);
		leftPanel.add(whoChairedLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 5, 0);
		leftPanel.add(whoChairedCombo, c);

		JLabel timeLabel = new JLabel("Time called to order:");
		Box timePanel = Box.createHorizontalBox();

		JComboBox<String> hours = new JComboBox<String>(getTimeRange(1, 12));
		JComboBox<String> minutes = new JComboBox<String>(getTimeRange(0, 60));
		JComboBox<String> amPm = new JComboBox<>(new String[] { "", "am", "pm" });
		JButton setButton = new JButton("Set");

		size = hours.getPreferredSize();
		size.width = 10;
		hours.setPreferredSize(size);
		minutes.setPreferredSize(size);
		amPm.setPreferredSize(size);
		size.width = 250;
		timePanel.setPreferredSize(size);
		timePanel.add(hours);
		timePanel.add(Box.createHorizontalStrut(10));
		timePanel.add(minutes);
		timePanel.add(Box.createHorizontalStrut(10));
		timePanel.add(amPm);
		timePanel.add(Box.createHorizontalStrut(10));
		timePanel.add(setButton);

		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 20);
		leftPanel.add(timeLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0);
		leftPanel.add(timePanel, c);

		setButton.addActionListener(set -> {
			Date date = new Date();
			Calendar cal = Calendar.getInstance();

			cal.setTime(date);

			int hour = cal.get(Calendar.HOUR);

			hours.setSelectedIndex(hour == 0 ? 12 : hour);
			minutes.setSelectedIndex(cal.get(Calendar.MINUTE) + 1);

			int am_pm = cal.get(Calendar.AM_PM);

			amPm.setSelectedIndex(am_pm == Calendar.AM ? 1 : am_pm == Calendar.PM ? 2 : 0);
		});

		RegularRolesPanel regularPanel = new RegularRolesPanel();

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0);
		leftPanel.add(regularPanel, c);

	}

	private List<Person> getRegularPeople() {
		List<Person> people = StateData.instance().getRoot().getList(DataType.Person);
		List<Person> regularPeople = people.stream().filter(person -> {
			Role role = person.getRole();
			return role == null ? false : person.getRole().isRegular();
		}).collect(Collectors.toList());

		return regularPeople;
	}

	private String[] getNames(List<Person> people) {
		List<String> peopleNames = people.stream().map(person -> person.getName()).collect(Collectors.toList());

		return peopleNames.toArray(new String[0]);
	}

	private int getPersonIndex(List<Person> regularPeople, boolean findSecretary) {
		int resultIndex = -1;

		for (int index = 0, size = regularPeople.size(); index < size; index++) {
			Person person = regularPeople.get(index);

			if ((findSecretary && person.getRole().isSecretary()) || (!findSecretary && person.getRole().isChair())) {
				resultIndex = index;
				break;
			}
		}

		return resultIndex;
	}

	private String[] getTimeRange(int start, int size) {
		String[] result = new String[size + 1];

		result[0] = "";

		for (int index = 0; index < size; index++, start++) {
			result[index + 1] = start + "";
		}

		return result;
	}
}
