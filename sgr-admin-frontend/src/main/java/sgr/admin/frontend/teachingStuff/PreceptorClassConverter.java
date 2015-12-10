package sgr.admin.frontend.teachingStuff;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;

@FacesConverter(value = "classConverter", forClass = ClassGroup.class)
public class PreceptorClassConverter implements Converter
{

   @Autowired
   private ClassGroupService classGroupService;

   public PreceptorClassConverter()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value)
   {
      if (value == null)
         return null;
      ClassGroup classGroup = classGroupService.getClass(Integer.valueOf(value.charAt(0)) - 48,
            String.valueOf(value.charAt(1)));
      return classGroup;
   }

   @Override
   public String getAsString(FacesContext context, UIComponent component, Object value)
   {
      if (value == null)
         return null;
      ClassGroup classGroup = (ClassGroup) value;
      return classGroup.getClassName();
   }

}
