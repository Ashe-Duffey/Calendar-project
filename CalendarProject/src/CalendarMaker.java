/*
 * 	Author of Code Majority:
 * 	https://gist.github.com/ahmednasserpro/6a0eaadc8e23f5dac0fc94bf29c8d31b
 * 	Modified from original
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CalendarMaker extends JFrame {

	private static final long serialVersionUID = 1L;
	static Calendar cal1 = new GregorianCalendar();
	static Calendar cal2 = new GregorianCalendar(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), 1);

	Font font = new Font("Ariel", Font.PLAIN, 20);
	private CalendarPanel dc = new CalendarPanel(cal2);
	private JPanel bottomPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel listPanel = new ListEventsWindow();
	private JButton group = new JButton("Group Something");
	private JButton previous = new JButton("Previous");
	private JButton next = new JButton("Next");
	private JButton add = new JButton("Add Event");
	public static JButton refresh = new JButton("Refresh");
//	private JButton listArrow = new JButton(">");

	public CalendarMaker() {

		// Font
		dc.setFont(font);
		bottomPanel.setFont(font);
		topPanel.setFont(font);
		listPanel.setFont(font);
		group.setFont(font);
		previous.setFont(font);
		next.setFont(font);
		add.setFont(font);
		refresh.setFont(font);

		setTitle("Calendar");

		topPanel.add(group, BorderLayout.WEST);
		topPanel.add(Box.createHorizontalStrut(1100)); // a spacer
		topPanel.add(add, BorderLayout.EAST);
//		topPanel.add(listArrow);
		bottomPanel.add(previous);
		bottomPanel.add(next);
		// bottomPanel.add(refresh);

		// adjusting the BorderLayout direction changes the layout
		add(topPanel, BorderLayout.PAGE_START);
		add(dc, BorderLayout.LINE_START);
		add(listPanel, BorderLayout.LINE_END);
		add(bottomPanel, BorderLayout.SOUTH);

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(dc);
				remove(topPanel);
				remove(listPanel);
				remove(bottomPanel);
				remove(dc);
				dc = dc.moveToNextMonth();
				add(topPanel, BorderLayout.NORTH);
				add(dc, BorderLayout.LINE_START);
				add(listPanel, BorderLayout.LINE_END);
				add(bottomPanel, BorderLayout.SOUTH);
				repaint();
				revalidate();
			}
		});

		previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(dc);
				remove(topPanel);
				remove(listPanel);
				remove(bottomPanel);
				dc = dc.moveToPreviousMonth();
				add(topPanel, BorderLayout.NORTH);
				add(dc, BorderLayout.LINE_START);
				add(listPanel, BorderLayout.LINE_END);
				add(bottomPanel, BorderLayout.SOUTH);
				repaint();
				revalidate();
			}
		});

		/*
		 * 
		 * add in for add event button
		 */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createAddWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void createAddWindow() throws IOException {

				setLayout(new BorderLayout());

				JFrame aw = new AddWindow();
				aw.pack();
				aw.setResizable(true);
				((AddWindow) aw).addEvent();
				aw.setLocationRelativeTo(null);

				refresh.doClick();

//				remove(dc);
//				remove(topPanel);
//				remove(listPanel);
//				remove(bottomPanel);
//				listPanel = new ListEventsWindow();
//				add(topPanel, BorderLayout.NORTH);
//				add(dc, BorderLayout.LINE_START);
//				add(listPanel, BorderLayout.LINE_END);
//				add(bottomPanel, BorderLayout.SOUTH);
//				repaint();
//				revalidate();

			}
		});

		// for group button
		group.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createGroup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void createGroup() throws IOException {

				setLayout(new BorderLayout());

				JFrame aw = new AddWindow();
				((AddWindow) aw).addEvent();
				aw.setLocationRelativeTo(null);

			}
		});

		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next.doClick();
				previous.doClick();

				remove(dc);
				remove(topPanel);
				remove(listPanel);
				remove(bottomPanel);
				listPanel = new ListEventsWindow();
				add(topPanel, BorderLayout.NORTH);
				add(dc, BorderLayout.LINE_START);
				add(listPanel, BorderLayout.LINE_END);
				add(bottomPanel, BorderLayout.SOUTH);
				repaint();
				revalidate();
			}
		});

	}

}

class CalendarPanel extends JPanel {

	Font font = new Font("Ariel", Font.PLAIN, 20);
	private static final long serialVersionUID = 1L;
	public Calendar calendar;
	private int currentYear;
	private int currentMonth;
	private int numOfDaysInMonth;
	private int starDay;
	private int previousMonth;

	public CalendarPanel(Calendar calendar) {

		this.calendar = calendar;

		setLayout(new BorderLayout());

		currentYear = calendar.get(Calendar.YEAR);
		currentMonth = calendar.get(Calendar.MONTH);
		numOfDaysInMonth = getMaximum(currentMonth);
		starDay = calendar.get(Calendar.DAY_OF_WEEK);
		previousMonth = getPreviousMonth(currentMonth);

		JLabel calendarHeader = new JLabel((currentMonth + 1) + "/" + currentYear);
		calendarHeader.setFont(font);
		calendarHeader.setHorizontalAlignment(JLabel.CENTER);

		JPanel panel = new JPanel(new GridLayout(0, 7, 0, 0));
		panel.setFont(font);
		Border border = new LineBorder(Color.BLACK, 1);

		JLabel l1 = new JLabel("Sunday");
		l1.setFont(font);
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setBorder(border);

		JLabel l2 = new JLabel("Monday");
		l2.setFont(font);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setBorder(border);

		JLabel l3 = new JLabel("Tuesday");
		l3.setFont(font);
		l3.setHorizontalAlignment(JLabel.CENTER);
		l3.setBorder(border);

		JLabel l4 = new JLabel("Wednesday");
		l4.setFont(font);
		l4.setHorizontalAlignment(JLabel.CENTER);
		l4.setBorder(border);

		JLabel l5 = new JLabel("Thursday");
		l5.setFont(font);
		l5.setHorizontalAlignment(JLabel.CENTER);
		l5.setBorder(border);

		JLabel l6 = new JLabel("Friday");
		l6.setFont(font);
		l6.setHorizontalAlignment(JLabel.CENTER);
		l6.setBorder(border);

		JLabel l7 = new JLabel("Saturday");
		l7.setFont(font);
		l7.setHorizontalAlignment(JLabel.CENTER);
		l7.setBorder(border);

		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(l5);
		panel.add(l6);
		panel.add(l7);

		int i;

		int numOfDaysInPreviousMonth = getMaximum(previousMonth);
		int previousMonthDayToDisplay = numOfDaysInPreviousMonth - starDay + 2;

		for (i = 1; i < starDay; i++) {
			// JLabel label = new JLabel(previousMonthDayToDisplay + "");

			String myString = "<html>" + previousMonthDayToDisplay + "<br>&emsp;<br>&emsp;</html>";
			JLabel label = new JLabel(myString);
			label.setFont(font);
			label.setBorder(border);
			label.setHorizontalAlignment(JLabel.LEFT);
			label.setForeground(Color.lightGray);
			panel.add(label);
			previousMonthDayToDisplay++;
		}

		for (int j = 1; j <= numOfDaysInMonth; j++, i++) {
			// JLabel label = new JLabel(j + "");
			String test = "Test";
			String myString = "<html>" + j + "<br>&emsp;<br>&emsp;</html>";
			ArrayList<Event> list = Main.List;
			ArrayList<Integer> day = new ArrayList<>();
			ArrayList<Integer> month = new ArrayList<>();
			ArrayList<Integer> year = new ArrayList<>();
			ArrayList<String> event = new ArrayList<>();
			
			for (Event e : list) {
				String days = e.getDay().toString();
				String months = e.getMonth().toString();
				String years = e.getYear();
				String events = e.getEvent();

				day.add(Integer.parseInt(days));
				month.add(Integer.parseInt(months));
				year.add(Integer.parseInt(years));
				event.add(events);

				// System.out.println(days);
			}

			if (month.contains(currentMonth + 1) && year.contains(currentYear) && day.contains(j)) {

				int size = day.size();
				Object[] e = event.toArray();

				for (int z = 0; z < size; z++) {
					if (year.get(z) == currentYear && month.get(z) == currentMonth + 1 && day.get(z) == j) {

//				System.out.println(dayIndex);
//				System.out.println(month.get(dayIndex));
//				System.out.println(currentMonth);

						myString = "<html>" + j + "<br>&emsp;<br><font color='red'>" + e[z].toString() + "</html>";

					}
				}
			} else {
				test = "&emsp;"; // 4 spaces for some reason in HTML
				myString = "<html>" + j + "<br>&emsp;<br><font color='red'>" + test + "</html>";

			}

			JLabel label = new JLabel(myString);
			label.setFont(font);
			label.setBorder(border);
			label.setHorizontalAlignment(JLabel.LEFT);
			panel.add(label);
		}

		for (int j = 1; i <= 42; j++, i++) {
			// JLabel label = new JLabel(j + "");

			String myString = "<html>" + j + "<br>&emsp;<br>&emsp;</html>";
			JLabel label = new JLabel(myString);
			label.setFont(font);
			label.setBorder(border);
			label.setHorizontalAlignment(JLabel.LEFT);
			label.setForeground(Color.LIGHT_GRAY);
			panel.add(label);
		}
		add(calendarHeader, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
	}

//	private int findIndex(ArrayList<Integer> day, ArrayList<Integer> month, ArrayList<Integer> year, int j) {
//		if (day.indexOf(j) > 1 && month.indexOf(j) > 1) {
//			System.out.println(day.indexOf(j));
//		}
//		
//		return 1;
//	}

	private int getPreviousMonth(int month) {
		if (month == 0) {
			return 11;
		}

		return month - 1;
	}

	private int getMaximum(int month) {
		int maximum = 0;
		switch (month) {
		case 0:
			maximum = 31;
			break;
		case 1:
			if (isLeap())
				maximum = 29;
			else
				maximum = 28;
			break;
		case 2:
			maximum = 31;
			break;
		case 3:
			maximum = 30;
			break;
		case 4:
			maximum = 31;
			break;
		case 5:
			maximum = 30;
			break;
		case 6:
			maximum = 31;
			break;
		case 7:
			maximum = 31;
			break;
		case 8:
			maximum = 30;
			break;
		case 9:
			maximum = 31;
			break;
		case 10:
			maximum = 30;
			break;
		case 11:
			maximum = 31;
			break;
		}

		return maximum;
	}

	private boolean isLeap() {
		return (currentYear % 4 == 0 && currentYear % 100 != 0) || currentYear % 400 == 0;
	}

	public CalendarPanel moveToPreviousMonth() {
		return new CalendarPanel(new GregorianCalendar(currentYear, currentMonth - 1, 1));
	}

	public CalendarPanel moveToNextMonth() {
		return new CalendarPanel(new GregorianCalendar(currentYear, currentMonth + 1, 1));
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000, 600);
	}

}