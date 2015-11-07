package sgr.commons.frontend;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Injector for {@link Bean} annotation.
 *
 * @author leonzio
 */
public class BeanInjector
{

   /**
    * Injects panel with {@link ComponentGetter} for setting components to
    * fields.
    *
    * @param object
    *           panel on which is performed injection
    */
   public static void proccessInjection(Object object)
   {
      Class<? extends Object> clazz = object.getClass();
      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields)
      {
         if (field.isAnnotationPresent(Bean.class))
         {
            Bean annotation = field.getDeclaredAnnotation(Bean.class);
            final String componentName = annotation.form() + ":" + annotation.name();
            UIComponent component = FacesContext.getCurrentInstance().getViewRoot()
                  .findComponent(componentName);
            if (component == null)
            {
               continue;
            }
            try
            {
               field.setAccessible(true);
               field.set(object, component);
               field.setAccessible(false);
            }
            catch (IllegalArgumentException | IllegalAccessException e)
            {
               e.printStackTrace();
            }
         }
      }
   }

}
