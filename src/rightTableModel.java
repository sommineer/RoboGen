import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class rightTableModel implements TableModel {
	private static final long serialVersionUID = 1L;
	private static final int MAX_CMDS = 100;
	private String[] columnNames = {"Method", "Parameters"};
	public String[][] data = new String[MAX_CMDS][2];
	private int index = 0;


	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public String getValueAt(int row, int col) {
		return data[row][col];
	}

	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}


	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = (String) value;

	}

	public void addValueAt(String value, String param){
		data[index][0] = value;
		data[index][1] = param;
		System.out.println(param);

		if(index == 99){
			JOptionPane.showMessageDialog(null, "You found the Easter Egg!!!!!!!!!!!!!!!!!!!!!!!!!!!","Easter Eggs", 1);
		}
		else{
			index++;
		}

	}
	
	public boolean removeValueAt(int index){
		
		boolean success = false;
		
		if((index >= 0) && (index <= this.index) && (data[index][0].length() > 0)){
			
			for(int i = index; i < (this.index - 1); i++){
				data[i][0] = data[i + 1][0];
				data[i][1] = data[i + 1][1];
				System.out.println("\tMoved index " + (i + 1) + " values (" + data[i+1][0] + ", " + data[i + 1][1] + ") to " + i);
			}
			
			data[this.index - 1][0] = "";
			data[this.index - 1][1] = "";
			
			this.index--;
			
			success = true;
			
		}
		
		return success;
	}
}
