package sgr.app.api.classgroup;

import java.io.Serializable;

/**
 * Query for classes.
 *
 * @author leonzio
 */
public class ClassGroupQuery implements Serializable
{

   private static final long serialVersionUID = -2359187238841851888L;

   public static final ClassGroupQuery EMPTY = new ClassGroupQuery();

   private boolean availableForTeacher;

   public static ClassGroupQuery setAvailableForTeacher(boolean availableForTeacher)
   {
      ClassGroupQuery query = new ClassGroupQuery();
      query.availableForTeacher = availableForTeacher;
      return query;
   }

   public boolean isAvailableForTeacher()
   {
      return availableForTeacher;
   }
}
