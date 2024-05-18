
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

/**
 * @author Samuel Rocco
 *
 */
public class ListEventsWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<Event> eventList = Main.List;
	Font font = new Font("Ariel", Font.PLAIN, 20);

	JButton removeButton = new JButton("Remove");
	JButton editButton = new JButton("Edit");
	JPanel ListPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JLabel header = new JLabel("Events");
	JList<String> list;
	JScrollPane scrollPane;

	public ListEventsWindow() {
		remove(ListPanel);

		// font resizing
		removeButton.setFont(font);
		editButton.setFont(font);
		ListPanel.setFont(font);
		bottomPanel.setFont(font);
		header.setFont(font);

		DefaultListModel<String> name = new DefaultListModel<String>();

		ListPanel.setLayout(new BorderLayout());
		// String labels[] = {};
		// name = new DefaultListModel<String>();
		list = new JList<String>(name);
		list.setFont(font);
		scrollPane = new JScrollPane(list);
		scrollPane.setFont(font);

		// name.addElement("Hello");

		for (Event events : eventList) {
			String wholeEvent = events.getEvent() + " " + events.getTime() + " " + events.getMonth() + "/"
					+ events.getDay() + "/" + events.getYear();
			// String wholeEvent = events.getEvent();
			name.addElement(wholeEvent);
			// stringEvent.add(wholeEvent);
			// System.out.println(wholeEvent);
			list.setModel(name);
//			list.repaint();
//			list.revalidate();
//			
//			scrollPane.repaint();
//			scrollPane.revalidate();
		}

		// System.out.println(name);

		header.setHorizontalAlignment(JLabel.CENTER);

		scrollPane.setPreferredSize(new Dimension(400, 500));

		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		bottomPanel.add(editButton);
		bottomPanel.add(removeButton);

		add(ListPanel);
		add(header);
		add(scrollPane, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.PAGE_END);

		// for remove button
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					removeEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void removeEvent() throws IOException {

				int index = list.getSelectedIndex();
				if (index != -1) {

					eventList.remove(index);

					name.removeElementAt(index);

					list.setModel(name);

					CalendarMaker.refresh.doClick();

//					System.out.println(index);
				}

			}
		});

		// for edit button

		// edit button still doesnt work even though i thought it did
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					editEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void editEvent() throws IOException {

				int index = list.getSelectedIndex();
				if (index != -1) {

					Event theEvent = eventList.get(index);

					final String days[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
							"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
							"30", "31" };
					final String months[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

					final String hours[] = { "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1" };
					final String[] mins = new String[61];
					final String periods[] = { "PM", "AM" };

					// easy way to make an array from 1 to 60 for minutes
					for (int i = 0; i < mins.length; ++i) {
						if (i < 10) {
							String j = Integer.toString(i);
							mins[i] = "0".concat(j);
						} else {
							String j = Integer.toString(i);
							mins[i] = j;
						}
					}

					JFrame editFrame = new JFrame("Edit");
					editFrame.setSize(400, 500);
					editFrame.setLocationRelativeTo(null);
					editFrame.setLayout(new BorderLayout());

					JTextField event = new JTextField(theEvent.getEvent(), 20);
					JComboBox<?> month = new JComboBox<Object>(months);
					JComboBox<?> day = new JComboBox<Object>(days);
					JTextField year = new JTextField(theEvent.getYear(), 4);
					JComboBox<?> hour = new JComboBox<Object>(hours);
					JComboBox<?> min = new JComboBox<Object>(mins);
					JComboBox<?> period = new JComboBox<Object>(periods);

					JPanel panel = new JPanel() {
						private static final long serialVersionUID = 1L;

						@Override
						public Dimension getPreferredSize() {
							return new Dimension(350, 125);
						}
					};
					panel.setSize(600, 800); // this code might be unnecesary
					Font font2 = new Font("Ariel", Font.PLAIN, 16);
					panel.setFont(font2);
					day.setFont(font2);
					event.setFont(font2);
					month.setFont(font2);
					year.setFont(font2);
					hour.setFont(font2);
					min.setFont(font2);
					period.setFont(font2);

					// adds the label and placement of the event text block
					panel.add(new JLabel("Event:"));
					panel.add(event, BorderLayout.NORTH);
					// adds the labels and placements of the date options
					panel.add(new JLabel("Month:"));
					panel.add(month, BorderLayout.CENTER);
					panel.add(Box.createHorizontalStrut(20)); // a spacer
					panel.add(new JLabel("Day:"));
					panel.add(day, BorderLayout.CENTER);
					panel.add(Box.createHorizontalStrut(17)); // a spacer
					panel.add(new JLabel("Year:"));
					panel.add(year, BorderLayout.CENTER);
					panel.add(Box.createHorizontalStrut(17)); // a spacer
					// adds the labels and placement of time options
					panel.add(new JLabel("Time:"));
					panel.add(hour, BorderLayout.SOUTH);
					panel.add(new JLabel(":"));
					panel.add(min, BorderLayout.SOUTH);
					panel.add(new JLabel(" "));
					panel.add(period, BorderLayout.SOUTH);

					// shows the final window
					// result is if you click on OK or Cancel
					int result = JOptionPane.showConfirmDialog(editFrame, panel, "Edit Event",
							JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {

						remove(ListPanel);

						// I will probably replace this file writing stuff for a constructor for better
						// management
						// formats the time
						theEvent.setDay(day);
						theEvent.setEvent(event);
						theEvent.setMonth(month);
						theEvent.setYear(year);
						String time = hour.getSelectedItem() + ":" + min.getSelectedItem() + period.getSelectedItem();

						theEvent.setTime(time);
						
						//System.out.println(theEvent.getEvent());

						list.setModel(name);

						//ListPanel = new ListEventsWindow();

						add(ListPanel);

						// CalendarMaker.refresh.doClick();
						// constructor for creating each event

						new ListEventsWindow();
						
						CalendarMaker.refresh.doClick();
						
						// refreshes the list after a new event is added
					}

					// editFrame.setVisible(true);
				}

			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 600);
	}

}
