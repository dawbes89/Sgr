package sgr.admin.webapp.teachingStuff;

import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.frontend.AbstractConverter;

@FacesConverter(value = "classConverter")
public class PreceptorClassConverter extends AbstractConverter<ClassGroup>
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
