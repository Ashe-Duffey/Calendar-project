import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * 
 * @author dylan an Event class to hold information of an Event
 */
public class Event {
	private JTextField Event;
	private JComboBox<?> Month;
	private JComboBox<?> Day;
	private JTextField Year;
	private String time;

	/**
	 * Constructor
	 * 
	 * @param event
	 * @param month
	 * @param day
	 * @param year2
	 * @param time
	 */
	public Event(JTextField event, JComboBox<?> month, JComboBox<?> day, JTextField year2, String time) {
		Event = event;
		Month = month;
		Day = day;
		Year = year2;
		this.time = time;
	}

	/**
	 * @return the name
	 */
	public String getEvent() {
		return Event.getText();
	}

	/**
	 * @param name the name to set
	 */
	public void setEvent(JTextField event) {
		Event = event;
	}

	/**
	 * @return the month
	 */
	public Object getMonth() {
		return Month.getSelectedItem();
	}

	/**
	 * @param object the month to set
	 */
	public void setMonth(JComboBox<?> month) {
		Month = month;
	}

	/**
	 * @return the day
	 */
	public Object getDay() {
		return Day.getSelectedItem();
	}

	/**
	 * @param object the day to set
	 */
	public void setDay(JComboBox<?> day) {
		Day = day;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return Year.getText();
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(JTextField year) {
		Year = year;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Checks if an object is the same as this object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(Day, other.Day) && Objects.equals(Event, other.Event)
				&& Objects.equals(Month, other.Month) && Objects.equals(Year, other.Year)
				&& Objects.equals(time, other.time);
	}

}