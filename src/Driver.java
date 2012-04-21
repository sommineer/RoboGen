import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Main driver of the program
 * @author Erik Sommer
 *
 */
public class Driver {
	
	/**
	 * Category for basic move commands
	 */
	ActionCategory bMove;
	
	/**
	 * Category for basic turn commands
	 */
	ActionCategory bTurn;
	
	/**
	 * Category for basic gun commands
	 */
	ActionCategory bGun;
	
	/**
	 * Category for advanced turn commands
	 */
	ActionCategory aTurn;
	
	/**
	 * Category for advanced gun commands
	 */
	ActionCategory aGun;
	
	/**
	 * Table model for when the robot is running
	 */
	RightTableModel run;
	
	/**
	 * Table model for when the a robot is scanned
	 */
	RightTableModel scan;
	
	/**
	 * Table model for when the robot is hit by a bullet
	 */
	RightTableModel bullet;
	
	/**
	 * Table model for when the robot hits a wall
	 */
	RightTableModel wall;

	/**
	 * Main method of the program
	 * @param args	ignored
	 */
	public static void main(String[] args) {
		
		// Set the look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (ClassNotFoundException e) {
			System.err.println("Error setting look and feel");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err.println("Error setting look and feel");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("Error setting look and feel");
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println("Error setting look and feel");
			e.printStackTrace();
		}
		
		// Create the driver
		final Driver d = new Driver();
		d.init();
		
		// Start the drier
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(d);
					window.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initializes the driver
	 */
	public void init() {
		initCommands();
		initRightTable();
	}
	
	/**
	 * Initializes the list of commands
	 */
	public void initCommands() {
		
		// Initialize the commands
		bMove = new ActionCategory(new String[] {"ahead", "back", "doNothing"});
		bTurn = new ActionCategory(new String[] {"turnLeft", "turnRight", "turnTo"});
		bGun = new ActionCategory(new String[] {"fire", "turnGunTo", "turnGunLeft", "turnGunRight"});
		aTurn = new ActionCategory(new String[] {"turnAheadRight", "turnAheadLeft", "turnBackRight", "turnBackLeft"});
	}
	
	/**
	 * Initializes the action table
	 */
	public void initRightTable() {
		
		// Create the table models
		run = new RightTableModel();
		scan = new RightTableModel();
		bullet = new RightTableModel();
		wall = new RightTableModel();
		
		// Initialize the models
		for(int i=0; i < run.getRowCount(); i++){
			run.setValueAt("", i, 0);
			run.setValueAt("", i, 1);
		}
		
		for(int i=0; i < scan.getRowCount(); i++){
			scan.setValueAt("", i, 0);
			scan.setValueAt("", i, 1);
		}
		
		for(int i=0; i < bullet.getRowCount(); i++){
			bullet.setValueAt("", i, 0);
			bullet.setValueAt("", i, 1);
		}
		
		for(int i=0; i < wall.getRowCount(); i++){
			wall.setValueAt("", i, 0);
			wall.setValueAt("", i, 1);
		}
	}

}
