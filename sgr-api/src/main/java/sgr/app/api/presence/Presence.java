package sgr.app.api.presence;

import sgr.app.api.lesson.Lesson;
import sgr.app.api.student.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity for student presences.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "presence")
public class Presence implements Serializable
{

	public static final String PROPERTY_ID = "id";

	public static final String PROPERTY_LESSON = "lesson";

	public static final String PROPERTY_STUDENT = "student";

	public static final String PROPERTY_STATUS = "status";

	public static final String PROPERTY_DATE = "date";

	private static final long serialVersionUID = -1089351333165210891L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lesson_id", nullable = false, updatable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "presence_lesson_id_fk"))
	private Lesson lesson;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", nullable = false, updatable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "presence_student_id_fk"))
	private Student student;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 25, nullable = false, updatable = true)
	private PresenceStatus status;

	@Column(name = "date", nullable = false, updatable = false)
	private Date date;

	public static Presence createAbsent(Student student)
	{
		Presence presence = new Presence();
		presence.setStudent(student);
		presence.setStatus(PresenceStatus.ABSENT);
		return presence;
	}

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

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

}
