package sgr.commons.frontend;

/**
 * This interface should be implemnted by panel which provides editing data.
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
    * @param entity
    *           to update
    */
   void update(T entity);

   /**
    * Removes entity.
    *
    * @param entity
    *           to remove
    */
   void remove(T entity);

}
