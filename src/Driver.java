import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Driver {
	
	AbstractListModel bMove;
	AbstractListModel bTurn;
	AbstractListModel bGun;
	AbstractListModel aTurn;
	AbstractListModel aGun;
	
	rightTableModel run;
	rightTableModel scan;
	rightTableModel bullet;
	rightTableModel wall;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		final Driver d = new Driver();
		d.init();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow(d);
					window.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void init() {
		initCommands();
		initRightTable();
	}
	
	public void initCommands() {
		bMove = new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"ahead", "back", "doNothing"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		};
		
		bTurn = new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"turnLeft", "turnRight", "turnTo"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		};
		
		bGun = new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"fire", "turnGunTo", "turnGunLeft", "turnGunRight"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		};
		
		aTurn = new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"turnAheadRight", "turnAheadLeft", "turnBackRight", "turnBackLeft"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		};
	}
	
	public void initRightTable() {
		run = new rightTableModel();
		scan = new rightTableModel();
		bullet = new rightTableModel();
		wall = new rightTableModel();
		
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
