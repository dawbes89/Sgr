package sgr.app.api.assessment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import sgr.app.api.teachingStuff.SchoolSubject;

/**
 * @author dawbes
 */
@Entity
@Table(name = "assessment")
public class Assessment implements Serializable
{

   private static final long serialVersionUID = 1707760863896363695L;

   public static final String PROPERTY_STUDENT_ID = "studentId";
   public static final String PROPERTY_SCHOOLSUBJECT = "schoolSubject";

   @Id
   @Column(name = "assessment_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date")
   private Date date;

   @Column(name = "assessment")
   private float assessment;

   @Enumerated(EnumType.STRING)
   @Column(name = "assessment_type")
   private AssessmentType assessmentType;

   @Column(name = "student_id")
   private Long studentId;

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

   public Long getStudentId()
   {
      return studentId;
   }

   public void setStudentId(Long studentId)
   {
      this.studentId = studentId;
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
