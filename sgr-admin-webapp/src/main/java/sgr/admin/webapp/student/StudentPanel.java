package sgr.admin.webapp.student;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.exceptions.RemoveException;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.helpers.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

import java.util.List;

/**
 * Panel used for handling students.
 *
 * @author leonzio
 */
@Controller
public class StudentPanel extends AbstractPanel<Student> implements EditablePanel<Student>
{

	private static final long serialVersionUID = 2553933126154263063L;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ClassGroupService classGroupService;

	private List<ClassGroup> availableClasses;

	@Override
	public void init()
	{
		entity = new Student();
		entities = studentService.search(StudentQuery.EMPTY);
		availableClasses = classGroupService.search(ClassGroupQuery.EMPTY);
	}

	@Override
	public void onLoad()
	{
		init();
	}

	@Override
	public void create()
	{
		studentService.create(entity);
		entity = new Student();
		onLoad();
	}

	@Override
	public void update(Student object)
	{
		studentService.update(object);
		onLoad();
	}

	@Override
	public void remove(Long id)
	{
		try
		{
			studentService.remove(id);
			requestContextExecute(HIDE_REMOVE_DIALOG_ACTION);
			onLoad();
		} catch (RemoveException e)
		{
			showValidationMessage(PROPERTY_REMOVE_FORM, e.getMessage(), e.getSeverity());
		}
	}

	public void generatePassword()
	{
		final InputText passwordField = BeanHelper.getComponent(PROPERTY_ADD_FORM, "password");
		final String password = RandomPasswordGenerator.generate();
		passwordField.setSubmittedValue(password);
	}

	public List<ClassGroup> getAvailableClasses()
	{
		return availableClasses;
	}
}
