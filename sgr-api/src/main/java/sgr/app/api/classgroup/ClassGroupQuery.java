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
   private Optional<Long> teacherClassId = Optional.empty();

   public static ClassGroupQuery setAvailableForCurrentTeacher(Long classId)
   {
      ClassGroupQuery query = new ClassGroupQuery();
      query.setTeacherClassId(classId);
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

   public boolean hasTeacherClassId()
   {
      return teacherClassId.isPresent();
   }

   public Long getTeacherClassId()
   {
      return teacherClassId.get();
   }

   public void setTeacherClassId(Long teacherClassId)
   {
      this.teacherClassId = Optional.of(teacherClassId);
   }

}
