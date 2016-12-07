package sgr.app.frontend.panels;

/**
 * This interface should be implemented by panel which provides editing data.
 *
 * @param <T>
 * 		edited data type
 * @author leonzio
 */
public interface EditablePanel<T>
{
	String HIDE_REMOVE_DIALOG_ACTION = "PF('removeDialog').hide();";
	String HIDE_ADD_DIALOG_ACTION = "PF('addDialog').hide();";
	String PROPERTY_REMOVE_FORM = "remove";
	String PROPERTY_ADD_FORM = "add";

	/**
	 * Creates entity.
	 */
	void create();

	/**
	 * Updates entity.
	 *
	 * @param object
	 * 		to update
	 */
	void update(T object);

	/**
	 * Removes entity.
	 *
	 * @param id
	 * 		of entity to remove
	 */
	void remove(Long id);

}
