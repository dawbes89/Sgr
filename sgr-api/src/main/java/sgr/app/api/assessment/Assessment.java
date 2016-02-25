package sgr.app.api.assessment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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

import sgr.app.api.student.Student;
import sgr.app.api.teachingStuff.SchoolSubject;

/**
 * Entity for student assessments.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "assessment")
public class Assessment implements Serializable
{

   private static final long serialVersionUID = 1707760863896363695L;

   public static final String PROPERTY_STUDENT = "student";
   public static final String PROPERTY_SCHOOL_SUBJECT = "schoolSubject";
   public static final String PROPERTY_DATE = "date";

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date")
   private Date date;

   @Column(name = "assessment")
   private float assessment;

   @Enumerated(EnumType.STRING)
   @Column(name = "assessment_type")
   private AssessmentType assessmentType;

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "student_id", nullable = false, updatable = false,
         referencedColumnName = "id", foreignKey = @ForeignKey(name = "assessment_student_id_fk") )
   private Student student = new Student();

   @Enumerated(EnumType.STRING)
   @Column(name = "school_subject")
   private SchoolSubject schoolSubject;

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

   public float getAssessment()
   {
      return assessment;
   }

   public void setAssessment(float assessment)
   {
      this.assessment = assessment;
   }

   public AssessmentType getAssessmentType()
   {
      return assessmentType;
   }

   public void setAssessmentType(AssessmentType assessmentType)
   {
      this.assessmentType = assessmentType;
   }

   public Student getStudent()
   {
      return student;
   }

   public void setStudent(Student student)
   {
      this.student = student;
   }

   public SchoolSubject getSchoolSubject()
   {
      return schoolSubject;
   }

   public void setSchoolSubject(SchoolSubject schoolSubject)
   {
      this.schoolSubject = schoolSubject;
   }

}
