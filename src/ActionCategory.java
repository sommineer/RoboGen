import javax.swing.AbstractListModel;


/**
 * Holds the data for a category of an action
 * @author Erik Sommer
 *
 */
public class ActionCategory extends AbstractListModel<String>{

	/**
	 * Serial identifier
	 */
	private static final long serialVersionUID = -2894139214303559696L;

	/**
	 * Category actions
	 */
	private String[] mActions;

	/**
	 * Constructor
	 * @param actions	array of actions
	 */
	public ActionCategory(String[] actions){

		// Parameter validation
		if(actions == null){
			throw new IllegalArgumentException("values is null");
		}

		// Store the action
		mActions = actions;
	}

	/**
	 * Gets the action at the specified index
	 */
	@Override
	public String getElementAt(int index) {
		return mActions[index];
	}


	/**
	 * Gets the number of actions
	 */
	@Override
	public int getSize() {
		return mActions.length;
	}
}
