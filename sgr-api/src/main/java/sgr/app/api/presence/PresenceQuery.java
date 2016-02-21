package sgr.app.api.presence;

import java.io.Serializable;
import java.util.Optional;

import sgr.app.api.QueryBuilder;
import sgr.app.api.teachingStuff.SchoolSubject;
/**
 * @author dawbes
 */
public class PresenceQuery implements Serializable
{

   private static final long serialVersionUID = -5270400581989535765L;

   public static final String PROPERTY_LESSON_ID = "lessonId";

   private Optional<Long> lessonId = Optional.empty();
   private Optional<Long> classGroupId = Optional.empty();
   private Optional<SchoolSubject> schoolSubject = Optional.empty();

   public Long getLessonId()
   {
      return lessonId.get();
   }

   public void setLessonId(Long lessonId)
   {
      this.lessonId = Optional.of(lessonId);
   }

   public boolean hasLessonId()
   {
      return lessonId.isPresent();
   }

   public Long getClassGroupId()
   {
      return classGroupId.get();
   }

   public void setClassGroupId(Long classGroupId)
   {
      this.classGroupId = Optional.of(classGroupId);
   }

   public boolean hasClassGroupId()
   {
      return classGroupId.isPresent();
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject.get();
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = Optional.of(schoolSubject);
   }

   public boolean hasSchoolSubject()
   {
      return schoolSubject.isPresent();
   }

   public static Builder all()
   {
      return new Builder();
   }

   /**
    * @author dawbes
    */
   public static class Builder extends QueryBuilder<PresenceQuery>
   {

      public Builder()
      {
         super(new PresenceQuery());
      }

      public Builder withSchoolSubject(SchoolSubject schoolSubject)
      {
         query.setSchoolSubject(schoolSubject);
         return this;
      }

      public Builder withClassGroupId(Long classGroupId)
      {
         query.setClassGroupId(classGroupId);
         return this;
      }

      @Override
      public PresenceQuery build()
      {
         return query;
      }

   }
}
