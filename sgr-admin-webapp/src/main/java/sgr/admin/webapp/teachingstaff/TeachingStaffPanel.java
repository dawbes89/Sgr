package sgr.admin.webapp.teachingstaff;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.api.teachingstaff.TeachingStaffService;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.helpers.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

import java.util.List;

/**
 * Panel used for handling teachers.
 *
 * @author dawbes89
 */
@Controller
public class TeachingStaffPanel extends AbstractPanel<TeachingStaff> implements EditablePanel<TeachingStaff>
{

	private static final long serialVersionUID = 2553933126154263063L;

	@Autowired
	private TeachingStaffService teachingStaffService;

	@Autowired
	private ClassGroupService classGroupService;

	private List<ClassGroup> availableClasses;

	@Override
	public void init()
	{
		entity = new TeachingStaff();
		entities = teachingStaffService.search();
		availableClasses = classGroupService.search(ClassGroupQuery.all().withAvailableForTeachers(true).build());
	}

	@Override
	public void onLoad()
	{
		init();
	}

	@Override
	public void create()
	{
		teachingStaffService.create(entity);
		entity = new TeachingStaff();
		onLoad();
	}

	@Override
	public void update(TeachingStaff object)
	{
		teachingStaffService.update(entity);
		onLoad();
	}

	@Override
	public void remove(Long id)
	{
		teachingStaffService.remove(id);
		requestContextExecute(HIDE_REMOVE_DIALOG_ACTION);
		onLoad();
	}

	@Override
	public void setEntity(TeachingStaff entity)
	{
		super.setEntity(entity);

		ClassGroupQuery query = ClassGroupQuery.all().withAvailableForTeachers(true).build();
		final ClassGroup preceptorClass = entity.getPreceptorClass();
		if (preceptorClass != null)
		{
			query.setClassId(preceptorClass.getId());
		}
		availableClasses = classGroupService.search(query);
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
