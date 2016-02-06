package sgr.app.frontend.converters;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;

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
      final int groupNumber = Integer.valueOf(value.charAt(0)) - 48;
      final String groupName = String.valueOf(value.charAt(1));
      final ClassGroup classGroup = classGroupService.getClass(groupNumber, groupName);
      return classGroup;
   }

   @Override
   protected String convertToString(Object object)
   {
      final ClassGroup classGroup = (ClassGroup) object;
      return classGroup.getClassName();
   }

}
