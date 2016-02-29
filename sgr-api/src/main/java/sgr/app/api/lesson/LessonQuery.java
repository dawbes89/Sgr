package sgr.app.api.lesson;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import sgr.app.api.QueryBuilder;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.teachingStuff.SchoolSubject;

/**
 * @author dawbes
 */
public class LessonQuery implements Serializable
{

   private static final long serialVersionUID = 645917670210127925L;

   /**
    * For new instances use this.
    */
   public static final LessonQuery EMPTY = new LessonQuery();

   private Optional<Long> studentId = Optional.empty();
   private Optional<SchoolSubject> schoolSubject = Optional.empty();
   private Optional<Long> classGroupId = Optional.empty();
   private Optional<Integer> lessonNumber = Optional.empty();
   private Optional<Date> date = Optional.empty();

   public static StudentQuery withClassGroupId(Long classGroupId)
   {
      StudentQuery query = new StudentQuery();
      query.setClassGroupId(classGroupId);
      return query;
   }

   public boolean hasStudentId()
   {
      return studentId.isPresent();
   }

   public Long getStudentId()
   {
      return studentId.get();
   }

   public void setStudentId(Long studentId)
   {
      this.studentId = Optional.of(studentId);
   }

   public boolean hasSchoolSubject()
   {
      return schoolSubject.isPresent();
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject.get();
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = Optional.of(schoolSubject);
   }

   public boolean hasClassGroupId()
   {
      return classGroupId.isPresent();
   }

   public Long getClassGroupId()
   {
      return classGroupId.get();
   }

   public void setClassGroupId(Long classGroupId)
   {
      this.classGroupId = Optional.of(classGroupId);
   }

   public boolean hasLessonNumber()
   {
      return lessonNumber.isPresent();
   }

   public Integer getLessonNumber()
   {
      return lessonNumber.get();
   }

   public void setLessonNumber(Integer lessonNumber)
   {
      this.lessonNumber = Optional.of(lessonNumber);
   }

   public boolean hasDate()
   {
      return date.isPresent();
   }

   public Date getDate()
   {
      return date.get();
   }

   public void setDate(Date date)
   {
      this.date = Optional.of(date);
   }

   public static Builder all()
   {
      return new Builder();
   }

   /**
    * @author dawbes
    */
   public static class Builder extends QueryBuilder<LessonQuery>
   {

      public Builder()
      {
         super(new LessonQuery());
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

      public Builder withClassGroupId(Long classGroupId)
      {
         query.setClassGroupId(classGroupId);
         return this;
      }

      public Builder withLessonNumber(Integer lessonNumber)
      {
         query.setLessonNumber(lessonNumber);
         return this;
      }

      public Builder withDate(Date date)
      {
         query.setDate(date);
         return this;
      }

   }
}
