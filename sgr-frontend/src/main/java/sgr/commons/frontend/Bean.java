package sgr.commons.frontend;

import javax.faces.context.FacesContext;

/**
 * @author leonzio
 */
public final class Bean
{
   @SuppressWarnings("unchecked")
   public static <T> T get(String formName, String beanName)
   {
      return (T) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent(formName + ":" + beanName);
   }
}
