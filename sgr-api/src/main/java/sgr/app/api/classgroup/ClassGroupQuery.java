package sgr.app.api.classgroup;

import java.io.Serializable;
import java.util.Optional;

/**
 * Query for classes.
 *
 * @author leonzio
 */
public class ClassGroupQuery implements Serializable
{

   private static final long serialVersionUID = -2359187238841851888L;

   /**
    * For new instances use this.
    */
   public static final ClassGroupQuery EMPTY = new ClassGroupQuery();

   private boolean availableForTeachers;
   private Optional<Long> classId = Optional.empty();

   public static ClassGroupQuery setAvailableForCurrentTeacher(Long classId)
   {
      ClassGroupQuery query = new ClassGroupQuery();
      query.setClassId(classId);
      return query;
   }

   public static ClassGroupQuery setAvailableForTeachers(boolean availableForTeachers)
   {
      ClassGroupQuery query = new ClassGroupQuery();
      query.availableForTeachers = availableForTeachers;
      return query;
   }

   public boolean isAvailableForTeachers()
   {
      return availableForTeachers;
   }

   public boolean hasClassId()
   {
      return classId.isPresent();
   }

   public Long getClassId()
   {
      return classId.get();
   }

   public void setClassId(Long classId)
   {
      this.classId = Optional.of(classId);
   }

}
