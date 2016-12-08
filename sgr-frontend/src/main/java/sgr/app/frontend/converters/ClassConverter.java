package sgr.app.frontend.converters;

import org.springframework.beans.factory.annotation.Autowired;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;

import javax.faces.component.UIComponent;
import java.util.Optional;

/**
 * Converter for class.
 *
 * @author leonzio
 */
public class ClassConverter extends AbstractConverter<ClassGroup>
{

	@Autowired
	private ClassGroupService classGroupService;

	@Override
	protected ClassGroup convertToObject(String value)
	{
		final int groupNumber = (int) value.charAt(0) - 48;
		final String groupName = String.valueOf(value.charAt(1));
		final Optional<ClassGroup> classGroup = classGroupService.find(ClassGroupQuery.all().withGroupName(
				groupName).withGroupNumber(groupNumber).build());
		return classGroup.isPresent() ? classGroup.get() : null;
	}

	@Override
	protected String convertToString(ClassGroup object, UIComponent component)
	{
		return object.getClassName();
	}

}
