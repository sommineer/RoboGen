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
	 * Category for advanced gun commands
	 */
	ActionCategory mCatAdvGun;
	
	/**
	 * Category for advanced turn commands
	 */
	ActionCategory mCatAdvTurn;
	
	/**
	 * Category for basic gun commands
	 */
	ActionCategory mCatBasicGun;
	
	/**
	 * Category for basic move commands
	 */
	ActionCategory mCatBasicMove;
	
	/**
	 * Category for basic turn commands
	 */
	ActionCategory mCatBasicTurn;
	
	/**
	 * Table model for when the robot is hit by a bullet
	 */
	ActionListTableModel mModelBullet;
	
	/**
	 * Table model for when the robot is running
	 */
	ActionListTableModel mModelRun;
	
	/**
	 * Table model for when the a robot is scanned
	 */
	ActionListTableModel mModelScan;

	/**
	 * Table model for when the robot hits a wall
	 */
	ActionListTableModel mModelWall;
	
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
		mCatBasicMove = new ActionCategory(new String[] {"ahead", "back", "doNothing"});
		mCatBasicTurn = new ActionCategory(new String[] {"turnLeft", "turnRight", "turnTo"});
		mCatBasicGun = new ActionCategory(new String[] {"fire", "turnGunTo", "turnGunLeft", "turnGunRight"});
		mCatAdvTurn = new ActionCategory(new String[] {"turnAheadRight", "turnAheadLeft", "turnBackRight", "turnBackLeft"});
	}
	
	/**
	 * Initializes the action table
	 */
	public void initRightTable() {
		
		// Create the table models
		mModelRun = new ActionListTableModel();
		mModelScan = new ActionListTableModel();
		mModelBullet = new ActionListTableModel();
		mModelWall = new ActionListTableModel();
		
		// Initialize the models
		for(int i=0; i < mModelRun.getRowCount(); i++){
			mModelRun.setValueAt("", i, 0);
			mModelRun.setValueAt("", i, 1);
		}
		
		for(int i=0; i < mModelScan.getRowCount(); i++){
			mModelScan.setValueAt("", i, 0);
			mModelScan.setValueAt("", i, 1);
		}
		
		for(int i=0; i < mModelBullet.getRowCount(); i++){
			mModelBullet.setValueAt("", i, 0);
			mModelBullet.setValueAt("", i, 1);
		}
		
		for(int i=0; i < mModelWall.getRowCount(); i++){
			mModelWall.setValueAt("", i, 0);
			mModelWall.setValueAt("", i, 1);
		}
	}

}
