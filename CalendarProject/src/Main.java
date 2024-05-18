import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
//	static PrintWriter write;

	// put the arraylist here so that it can be passed through to other methods
	// without resetting
	
	//static ArrayList<Event> List;
	static ArrayList<Event> List;

	public static void main(String[] args) throws IOException {
		
//		File newFile = new File("Database.txt");
//		FileWriter file = new FileWriter(newFile);
//		write = new PrintWriter(file);

		// write.write(""); this is for auto added people for demo
		
		List = new ArrayList<Event>();


		/*
		 * Author of Code Majority below:
		 * https://gist.github.com/ahmednasserpro/6a0eaadc8e23f5dac0fc94bf29c8d31b
		 * Modified from original
		 */

		// creates a calendarMaker instance
		JFrame frame = new CalendarMaker();
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// whatever in this method gets executed after the main window is closed
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				// this is a test showing the use of having each event in an arraylist
//			    	System.out.println("\nThe Day for each event:");
//			    	for (Event list : List) {
//			    		System.out.println(list.getDay());
//			    	}

			}
		});

	}

}
