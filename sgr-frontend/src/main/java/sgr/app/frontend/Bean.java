package sgr.app.frontend;

import javax.faces.context.FacesContext;

/**
 * Bean used to gets components from xhtml file into java file for perform
 * operations on it.
 *
 * @author leonzio
 */
public final class Bean
{
   /**
    * Gets component.
    *
    * @param formId
    *           alias form name from which will be getting compoment
    * @param componentId
    *           which we will wants to get
    * @return component
    */
   @SuppressWarnings("unchecked")
   public static <T> T getComponent(String formId, String componentId)
   {
      return (T) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent(formId + ":" + componentId);
   }
}
