package sgr.app.api.assessment;

import sgr.app.api.student.Student;
import sgr.app.api.teachingstaff.SchoolSubject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity for student assessments.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "assessment")
public class Assessment implements Serializable
{

	public static final String PROPERTY_STUDENT = "student";

	public static final String PROPERTY_SCHOOL_SUBJECT = "schoolSubject";

	public static final String PROPERTY_DATE = "date";

	private static final long serialVersionUID = 1707760863896363695L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date", nullable = false, updatable = false)
	private Date date;

	@Column(name = "assessment", nullable = false, updatable = false)
	private float assessment;

	@Enumerated(EnumType.STRING)
	@Column(name = "assessment_type", length = 25, nullable = false, updatable = false)
	private AssessmentType assessmentType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", nullable = false, updatable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "assessment_student_id_fk"))
	private Student student = new Student();

	@Enumerated(EnumType.STRING)
	@Column(name = "school_subject", nullable = false, updatable = false)
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
