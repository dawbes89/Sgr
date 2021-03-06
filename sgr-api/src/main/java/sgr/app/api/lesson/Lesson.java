package sgr.app.api.lesson;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.teachingstaff.SchoolSubject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity for lessons.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "lesson",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"lesson_number", "date", "class_group_id"},
				name = "lesson_number_in_day_for_class_uk")})
public class Lesson implements Serializable
{

	public static final String PROPERTY_ID = "id";

	public static final String PROPERTY_STUDENT = "student";

	public static final String PROPERTY_SCHOOL_SUBJECT = "schoolSubject";

	public static final String PROPERTY_CLASS_GROUP = "classGroup";

	public static final String PROPERTY_DATE = "date";

	public static final String PROPERTY_LESSON_NUMBER = "lessonNumber";

	private static final long serialVersionUID = -5117496622690659878L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lesson_number", nullable = false, updatable = false, precision = 1)
	private int lessonNumber;

	@Column(name = "subject_number", nullable = false, updatable = false)
	private int subjectNumber;

	@Column(name = "date", nullable = false, updatable = false)
	private Date date;

	@Column(name = "lesson_subject", length = 100, nullable = false, updatable = false)
	private String lessonSubject;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "class_group_id", nullable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "lesson_class_group_id_fk"))
	private ClassGroup classGroup;

	@Enumerated(EnumType.STRING)
	@Column(name = "school_subject", length = 25, nullable = false, updatable = false)
	private SchoolSubject schoolSubject;

	@Column(name = "issuer_name", length = 100, nullable = false, updatable = false)
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

	public int getLessonNumber()
	{
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber)
	{
		this.lessonNumber = lessonNumber;
	}

	public int getSubjectNumber()
	{
		return subjectNumber;
	}

	public void setSubjectNumber(int subjectNumber)
	{
		this.subjectNumber = subjectNumber;
	}

}
