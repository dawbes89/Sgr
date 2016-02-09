package sgr.app.api.student;

import java.io.Serializable;
import java.util.Optional;
/**
 * @author dawbes89
 */
public class StudentQuery implements Serializable
{

   private static final long serialVersionUID = 8269837889818764008L;

   /**
    * For new instances use this.
    */
   public static final StudentQuery EMPTY = new StudentQuery();

   private Optional<Long> classGroupId = Optional.empty();

   public static StudentQuery withClassGroupId(Long classGroupId)
   {
      StudentQuery query = new StudentQuery();
      query.setClassGroupId(classGroupId);
      return query;
   }

   public Long getClassGroupId()
   {
      return classGroupId.get();
   }

   public void setClassGroupId(Long classGroupId)
   {
      this.classGroupId = Optional.ofNullable(classGroupId);
   }

   public boolean hasClassGroupId()
   {
      return classGroupId.isPresent();
   }

}
