import java.util.ArrayList;
import java.util.TreeMap;

import com.sun.source.tree.BinaryTree;

public class Group {
	String name;
	String discrpton;
	int[] users = new int[10];
	ArrayList<Event> events = new ArrayList<Event>();
	int[] admin = new int[4];
	int owner = users[0];
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the discrpton
	 */
	public String getDiscrpton() {
		return discrpton;
	}
	/**
	 * @param discrpton the discrpton to set
	 */
	public void setDiscrpton(String discrpton) {
		this.discrpton = discrpton;
	}
	/**
	 * @return the users
	 */
	public int[] getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(int[] users) {
		this.users = users;
	}
	/**
	 * @return the events
	 */
	public ArrayList<Event> getEvents() {
		return events;
	}
	/**
	 * @param events the events to set
	 */
	public void addEvents(Event events) {
		this.events.add(events);
	}
	/**
	 * @return the admin
	 */
	public int[] getAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(int[] admin) {
		this.admin = admin;
	}
	/**
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
}