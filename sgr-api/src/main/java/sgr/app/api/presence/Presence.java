package sgr.app.api.presence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sgr.app.api.lesson.Lesson;

/**
 * @author dawbes
 */
@Entity
@Table(name = "presence")
public class Presence
{
   @Id
   @Column(name = "presence_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "presence")
   private boolean presence;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "lesson_id",  foreignKey = @ForeignKey(name = "presence_lesson_id_fk") )
   private Lesson lesson;

   @Column(name = "student_id")
   private Long studentId;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public boolean isPresence()
   {
      return presence;
   }

   public void setPresence(boolean presence)
   {
      this.presence = presence;
   }

   public Long getStudentId()
   {
      return studentId;
   }

   public void setStudentId(Long studentId)
   {
      this.studentId = studentId;
   }

   public Lesson getLesson()
   {
      return lesson;
   }

   public void setLesson(Lesson lesson)
   {
      this.lesson = lesson;
   }



}
