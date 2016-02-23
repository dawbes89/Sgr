package sgr.app.api.presence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sgr.app.api.lesson.Lesson;
import sgr.app.api.student.Student;

/**
 * @author dawbes
 */
@Entity
@Table(name = "presence")
public class Presence implements Serializable
{

   private static final long serialVersionUID = -1089351333165210891L;

   public static final String PROPERTY_LESSON = "lesson";
   public static final String PROPERTY_STUDENT = "student";
   public static final String PROPERTY_STATUS = "status";

   @Id
   @Column(name = "presence_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "lesson_id", foreignKey = @ForeignKey(name = "presence_lesson_id_fk"))
   private Lesson lesson;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "presence_student_id_fk"))
   private Student student;

   @Enumerated(EnumType.STRING)
   @Column(name = "status")
   private PresenceStatus status;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Lesson getLesson()
   {
      return lesson;
   }

   public void setLesson(Lesson lesson)
   {
      this.lesson = lesson;
   }

   public Student getStudent()
   {
      return student;
   }

   public void setStudent(Student student)
   {
      this.student = student;
   }

   public PresenceStatus getStatus()
   {
      return status;
   }

   public void setStatus(PresenceStatus status)
   {
      this.status = status;
   }

}
