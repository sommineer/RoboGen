import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Model that holds a list of actions
 * @author Erik Sommer
 *
 */
public class ActionListTableModel implements TableModel {

	/**
	 * Maximum number of commands
	 */
	private static final int MAX_CMDS = 100;

	/**
	 * Names of the columns
	 */
	private String[] columnNames = {"Method", "Parameters"};

	/**
	 * Array of commands
	 */
	public String[][] data = new String[MAX_CMDS][2];

	/**
	 * Current mIndex
	 */
	private int mIndex = 0;


	/**
	 * Listeners that should be notified when the table model updates
	 */
	private List<TableModelListener> mModelListeners;

	/**
	 * Constructor
	 */
	public ActionListTableModel(){
		// Create the list of listeners
		mModelListeners = new LinkedList<TableModelListener>();
	}

	/**
	 * Adds a new listener for when the table model values are changed.
	 * @param l	listener to add
	 * @throws IllegalArgumentException	if {@code l} is {@code null}
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {

		// Argument validation
		if(l == null){
			throw new IllegalArgumentException("l is null");
		}

		mModelListeners.add(l);
	}

	/**
	 * Adds a value to the end of the list
	 * @param value	value to add
	 * @param param	parameter value to add
	 */
	public void addValueAt(String value, String param){

		// Create a local reference
		int index = mIndex;

		// Set the value
		setValueAt(value, index, 0);
		setValueAt(param, index, 1);

		// Check for the easter egg!
		if(index == 99){
			JOptionPane.showMessageDialog(null, "You found the Easter Egg!!!!!!!!!!!!!!!!!!!!!!!!!!!","Easter Eggs", 1);
		}else{
			index++;
		}

		// Update the parameter variable
		mIndex = index;

	}

	/**
	 * Gets the most specific superclass for the objects in a column
	 * @param col	column mIndex to get the class for
	 * @throws IllegalArgumentException	if {@code col} is not between 0 and 
	 * 									{@code #getColumnCount()}
	 */
	@Override
	public Class<? extends Object> getColumnClass(int col) {

		int columnCount = getColumnCount();

		// Argument validation
		if((col < 0) || (col >= columnCount)){
			throw new IllegalArgumentException("col is not between 0 and " + columnCount);
		}

		return getValueAt(0, col).getClass();
	}

	/**
	 * Gets the number of columns
	 * @return number of columns
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}


	/**
	 * Gets the name of a column
	 * @param col	mIndex of column name to get
	 * @throws IllegalArgumentException	if {@code col} is not between 0 and
	 * 									{@link #getColumnCount()}
	 */
	@Override
	public String getColumnName(int col) {

		int columnCount = getColumnCount();

		// Argument validation
		if((col < 0) || col >= columnCount){
			throw new IllegalArgumentException("col is not between 0 and " + columnCount);
		}

		return columnNames[col];
	}

	/**
	 * Gets the number of rows
	 * @return number of rows
	 */
	@Override
	public int getRowCount() {
		return data.length;
	}

	/**
	 * Gets the value at the given position
	 * @param row	mIndex of the row to get the value of
	 * @param col	mIndex of the column to get the value of
	 * @throws IllegalArgumentException	if {@code row} is not between 0 and
	 * 									{@link #getRowCount()} or {@code col}
	 * 									is not between 0 and 
	 * 									{@code #getColumnCount()}
	 */
	@Override
	public String getValueAt(int row, int col) {

		int rowCount = getRowCount();
		int columnCount = getColumnCount();

		// Argument validation
		if((row < 0) || (row >= rowCount)){
			throw new IllegalArgumentException("row is not between 0 and " + rowCount);
		}else if((col < 0) || (col >= columnCount)){
			throw new IllegalArgumentException("col is not between 0 and " + columnCount);
		}

		return data[row][col];
	}

	/**
	 * Gets whether a cell is editable
	 * @param row	row mIndex of the cell
	 * @param colIndex	column mIndex of the cell
	 * @throws IllegalArgumentException	if {@code row} is not between 0 and
	 * 									{@link #getRowCount()} or {@code col}
	 * 									is not between 0 and 
	 * 									{@code #getColumnCount()}
	 */
	@Override
	public boolean isCellEditable(int row, int col) {

		int rowCount = getRowCount();
		int columnCount = getColumnCount();

		// Argument validation
		if((row < 0) || (row >= rowCount)){
			throw new IllegalArgumentException("row is not between 0 and " + rowCount);
		}else if((col < 0) || (col >= columnCount)){
			throw new IllegalArgumentException("col is not between 0 and " + columnCount);
		}

		// Always return false as none of the cells should be editable
		return false;
	}

	/**
	 * Removes a listener for when the table model values are changed.
	 * @param l	listener to remove
	 * @throws IllegalArgumentException	if {@code l} is {@code null}
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {

		// Argument validation
		if(l == null){
			throw new IllegalArgumentException("l is null");
		}


		mModelListeners.remove(l);
	}

	/**
	 * Removes a row from the table
	 * @param index	index of the row to remove
	 * @return	{@code true} if the row was removed, 
	 * 			{@code false} if the remove failed
	 * @throws IllegalArgumentException	if {@code index} is not between 0 and
	 * 									{@link #getRowCount()}
	 */
	public boolean removeValueAt(int index){

		boolean success = false;
		int currentIndex = mIndex;
		int rowCount = getRowCount();

		// Parameter validation
		if((index < 0) || (index > rowCount)){
			throw new IllegalArgumentException("index is not between 0 and " + rowCount);
		}

		// If there is data
		if(data[index][0].length() > 0){

			// Loop through and move up the rows
			for(int i = index; i < (currentIndex - 1); i++){
				data[i][0] = data[i + 1][0];
				data[i][1] = data[i + 1][1];
			}

			// Blank out the row
			data[currentIndex - 1][0] = "";
			data[currentIndex - 1][1] = "";

			currentIndex--;

			success = true;
		}

		// Update the variable
		mIndex = currentIndex;

		return success;
	}

	/**
	 * Sets the given value at the given cell
	 * @param value	{@code String} to set the value to
	 * @param row	row mIndex to set the value at
	 * @param col	column mIndex to set the value at
	 * @throws IllegalArgumentException	if {@code row} is not between 0 and
	 * 									{@link #getRowCount()} or {@code col}
	 * 									is not between 0 and 
	 * 									{@code #getColumnCount()} or if 
	 * 									{@code value} is not an instance of 
	 * 									{@code String}
	 */
	@Override
	public void setValueAt(Object value, int row, int col) {

		int rowCount = getRowCount();
		int columnCount = getColumnCount();

		// Argument validation
		if((row < 0) || (row >= rowCount)){
			throw new IllegalArgumentException("row is not between 0 and " + rowCount);
		}else if((col < 0) || (col >= columnCount)){
			throw new IllegalArgumentException("col is not between 0 and " + columnCount);
		}else if(!(value instanceof String)){
			throw new IllegalArgumentException("value is not an instance of String");
		}

		// Set the value
		data[row][col] = (String) value;

	}
}
