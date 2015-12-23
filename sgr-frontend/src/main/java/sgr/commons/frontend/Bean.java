package sgr.commons.frontend;

import javax.faces.context.FacesContext;

import sgr.commons.core.ObjectsHelper;

/**
 * @author leonzio
 */
public final class Bean
{
   public static <T> T get(String formName, String beanName)
   {
      return ObjectsHelper.uncheckedCast(FacesContext.getCurrentInstance().getViewRoot()
            .findComponent(formName + ":" + beanName));
   }
}
