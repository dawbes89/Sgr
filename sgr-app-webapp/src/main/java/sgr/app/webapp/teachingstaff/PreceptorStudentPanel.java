package sgr.app.webapp.teachingstaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sgr.app.api.assessment.Assessment;
import sgr.app.api.assessment.AssessmentQuery;
import sgr.app.api.assessment.AssessmentService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.api.teachingstaff.SchoolSubject;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.frontend.panels.AbstractPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dawbes89
 */
@Controller
public class PreceptorStudentPanel extends AbstractPanel<Student>
{

	private static final long serialVersionUID = 8358711395309154466L;

	@Autowired
	private StudentService studentService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private AssessmentService assessmentService;

	private TeachingStaff currentLoggedTeacher;

	private ClassGroup classGroup;

	private SchoolSubject schoolSubject;

	@Override
	public void init()
	{
		entity = new Student();
		entities = new ArrayList<>();
	}

	@Override
	public void onLoad()
	{
		currentLoggedTeacher = authenticationService.getCurrentUser();
		classGroup = currentLoggedTeacher.getPreceptorClass();
		searchStudents();
	}

	public List<Comment> getComments()
	{
		return commentService.findByStudentId(entity.getId());
	}

	public void searchStudents()
	{
		if (classGroup == null || classGroup.getId() == null)
		{
			entities = new ArrayList<>();
		} else
		{
			final StudentQuery query = StudentQuery.withClassGroupId(classGroup.getId());
			entities = studentService.search(query);
		}
	}

	public List<Assessment> getAssessments()
	{
		if (currentLoggedTeacher == null || entity.getId() == null)
		{
			return new ArrayList<>();
		}
		return assessmentService.search(createQuery());
	}

	public String getAverageAssessments()
	{
		double average = 0;
		if (currentLoggedTeacher != null && entity != null)
		{
			average = assessmentService.getAverageAssesment(createQuery());
		}
		return String.format("%1$,.2f", average);
	}

	private AssessmentQuery createQuery()
	{
		final AssessmentQuery query = new AssessmentQuery();
		if (schoolSubject != null)
		{
			query.setSchoolSubject(schoolSubject);
		}
		if (entity != null && entity.getId() != null)
		{
			query.setStudentId(entity.getId());
		}
		return query;
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

}
