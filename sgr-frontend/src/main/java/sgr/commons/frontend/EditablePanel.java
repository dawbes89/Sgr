package sgr.commons.frontend;

/**
 * This interface should be implemented by panel which provides editing data.
 *
 * @author leonzio
 * @param <T>
 *           edited data type
 */
public interface EditablePanel<T>
{
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
