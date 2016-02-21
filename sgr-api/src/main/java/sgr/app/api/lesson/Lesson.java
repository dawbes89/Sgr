package sgr.app.api.lesson;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.teachingStuff.SchoolSubject;

/**
 * @author dawbes
 */
@Entity
@Table(name = "lesson")
public class Lesson implements Serializable
{

   private static final long serialVersionUID = -5117496622690659878L;

   public static final String PROPERTY_ID = "id";
   public static final String PROPERTY_STUDENT_ID = "studentId";
   public static final String PROPERTY_SCHOOL_SUBJECT = "schoolSubject";
   public static final String PROPERTY_CLASS_GROUP = "classGroup";

   @Id
   @Column(name = "lesson_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date")
   private Date date;

   @Column(name = "lesson_subject")
   private String lessonSubject;

   @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
         CascadeType.REFRESH }, fetch = FetchType.EAGER)
   @JoinColumn(name = "class_group_id", nullable = false)
   private ClassGroup classGroup;

   @Enumerated(EnumType.STRING)
   @Column(name = "school_subject")
   private SchoolSubject schoolSubject;

   @Column(name = "issuer_name")
   private String issuerName;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public String getLessonSubject()
   {
      return lessonSubject;
   }

   public void setLessonSubject(String lessonSubject)
   {
      this.lessonSubject = lessonSubject;
   }

   public ClassGroup getClassGroup()
   {
      return classGroup;
   }

   public void setClassGroup(ClassGroup classGroup)
   {
      this.classGroup = classGroup;
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = schoolSubject;
   }

   public String getIssuerName()
   {
      return issuerName;
   }

   public void setIssuerName(String issuerName)
   {
      this.issuerName = issuerName;
   }

}
