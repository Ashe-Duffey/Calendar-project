
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.IOException;

/**
 * @author Samuel Rocco
 *
 */
public class AddWindow extends JFrame {

	private static final long serialVersionUID = 1L; // this code doesnt really matter

//	public AddWindow() {
//
//	}
//	    JFrame addFrame = new JFrame();   
//	    String date=JOptionPane.showInputDialog(addFrame,"Enter Date"); 
//	    
////	    JOptionPane.showMessageDialog(null, date);
//	    
//	    if (date == null) {
//	    	this.getContentPane().setVisible(false);
//	    	this.dispose();
//	    }
//	    
//	    System.out.println(date);

	public void addEvent() throws IOException {

		// creates a new window, and size and if it can be resized
		Font font = new Font("Ariel", Font.PLAIN, 20);
		JFrame frame2 = new JFrame();
		frame2.pack();
		frame2.setResizable(true);
		frame2.setSize(400, 500);
		// sets the formatting style, formats with respect to borders
		frame2.setLayout(new BorderLayout());

		// timeFormats for the dropdown options when adding new event
		final String days[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
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

		// creates objects for each option that will soon be added
		JTextField event = new JTextField("Event", 20);
		JComboBox<?> month = new JComboBox<Object>(months);
		JComboBox<?> day = new JComboBox<Object>(days);
		JTextField year = new JTextField("2022", 4);
		JComboBox<?> hour = new JComboBox<Object>(hours);
		JComboBox<?> min = new JComboBox<Object>(mins);
		JComboBox<?> period = new JComboBox<Object>(periods);

		// creates the panel inside of the frame with preferred size
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(350, 125);
			}
		};
		panel.setSize(600, 800); // this code might be unnecesary but im not
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
		// result is if you clinck on OK or Cancel
		int result = JOptionPane.showConfirmDialog(frame2, panel, "Please Enter Event", JOptionPane.OK_CANCEL_OPTION);

		// checks if the option clicked was OK
		if (result == JOptionPane.OK_OPTION) {

			// I will probably replace this file writing stuff for a constructor for better
			// management
			// formats the time
			String time = hour.getSelectedItem() + ":" + min.getSelectedItem() + period.getSelectedItem();

			// constructor for creating each event
			Event returnEvent = new Event(event, month, day, year, time);
			// adding each event
			Main.List.add(returnEvent);
			// new ListEventsWindow();

		}

	}

}
