package sgr.app.api.presence;

import sgr.app.api.QueryBuilder;
import sgr.app.api.teachingstaff.SchoolSubject;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * @author dawbes
 */
public class PresenceQuery implements Serializable
{

	public static final String PROPERTY_LESSON_ID = "lessonId";

	private static final long serialVersionUID = -5270400581989535765L;

	private Optional<Long> lessonId = Optional.empty();

	private Optional<Long> classGroupId = Optional.empty();

	private Optional<SchoolSubject> schoolSubject = Optional.empty();

	private Optional<PresenceStatus> status = Optional.empty();

	private Optional<String> studentFullName = Optional.empty();

	private Optional<Date> date = Optional.empty();

	private Optional<Long> studentId = Optional.empty();

	public static Builder all()
	{
		return new Builder();
	}

	public boolean hasLessonId()
	{
		return lessonId.isPresent();
	}

	public Long getLessonId()
	{
		return lessonId.get();
	}

	public void setLessonId(Long lessonId)
	{
		this.lessonId = Optional.of(lessonId);
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

	public boolean hasStatus()
	{
		return status.isPresent();
	}

	public PresenceStatus getStatus()
	{
		return status.get();
	}

	public void setStatus(PresenceStatus status)
	{
		this.status = Optional.of(status);
	}

	public boolean hasStudentFullName()
	{
		return studentFullName.isPresent();
	}

	public String getStudentFullName()
	{
		return studentFullName.get();
	}

	public void setStudentFullName(String studentFullName)
	{
		this.studentFullName = Optional.of(studentFullName);
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

	/**
	 * @author dawbes
	 */
	public static class Builder extends QueryBuilder<PresenceQuery>
	{

		public Builder()
		{
			super(new PresenceQuery());
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

		public Builder withStatus(PresenceStatus status)
		{
			query.setStatus(status);
			return this;
		}

		public Builder withStudentFullName(String studentFullName)
		{
			query.setStudentFullName(studentFullName);
			return this;
		}

		public Builder withDate(Date date)
		{
			query.setDate(date);
			return this;
		}

		public Builder withStudentId(Long id)
		{
			query.setStudentId(id);
			return this;
		}

	}

}
