package sgr.app.api.assessment;

import java.io.Serializable;
import java.util.Optional;

import sgr.app.api.QueryBuilder;
import sgr.app.api.teachingStuff.SchoolSubject;

/**
 * @author dawbes
 */
public class AssessmentQuery implements Serializable
{

   private static final long serialVersionUID = -2253192413793115596L;

   /**
    * For new instances use this.
    */
   public static final AssessmentQuery EMPTY = new AssessmentQuery();

   private Optional<Long> studentId = Optional.empty();
   private Optional<SchoolSubject> schoolSubject = Optional.empty();

   public Long getStudentId()
   {
      return studentId.get();
   }

   public void setStudentId(Long studentId)
   {
      this.studentId = Optional.of(studentId);
   }

   public boolean hasStudentId()
   {
      return studentId.isPresent();
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
   public static class Builder extends QueryBuilder<AssessmentQuery>
   {

      public Builder()
      {
         super(new AssessmentQuery());
      }

      public Builder withStudentId(Long studentId)
      {
         query.setStudentId(studentId);
         return this;
      }

      public Builder withSchoolSubject(SchoolSubject schoolSubject)
      {
         query.setSchoolSubject(schoolSubject);
         return this;
      }

   }

}
