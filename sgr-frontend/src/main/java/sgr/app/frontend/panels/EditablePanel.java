package sgr.app.frontend.panels;

/**
 * This interface should be implemented by panel which provides editing data.
 *
 * @author leonzio
 * @param <T>
 *           edited data type
 */
public interface EditablePanel<T>
{
   public static final String PROPERTY_HIDE_REMOVE_DIALOG = "PF('removeDialog').hide();";
   public static final String PROPERTY_HIDE_ADD_DIALOG = "PF('addDialog').hide();";
   public static final String PROPERTY_REMOVE_FORM = "remove";
   public static final String PROPERTY_ADD_FORM = "add";
   /**
    * Creates entity.
    */
   void create();

   /**
    * Updates entity.
    *
    * @param object
    *           to update
    */
   void update(T object);

   /**
    * Removes entity.
    *
    * @param id
    *           of entity to remove
    */
   void remove(Long id);

}
