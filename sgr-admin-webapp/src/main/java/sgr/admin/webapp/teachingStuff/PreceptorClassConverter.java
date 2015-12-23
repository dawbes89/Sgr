package sgr.admin.webapp.teachingStuff;

import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.commons.core.ObjectsHelper;
import sgr.commons.frontend.AbstractConverter;

@FacesConverter(value = "classConverter", forClass = ClassGroup.class)
public class PreceptorClassConverter extends AbstractConverter<ClassGroup>
{

   @Autowired
   private ClassGroupService classGroupService;

   @Override
   protected ClassGroup convertToObject(String value)
   {
      int groupNumber = Integer.valueOf(value.charAt(0)) - 48;
      String groupName = String.valueOf(value.charAt(1));
      ClassGroup classGroup = classGroupService.getClass(groupNumber, groupName);
      return classGroup;
   }

   @Override
   protected String convertToString(Object object)
   {
      ClassGroup classGroup = ObjectsHelper.uncheckedCast(object);
      return classGroup.getClassName();
   }

}
