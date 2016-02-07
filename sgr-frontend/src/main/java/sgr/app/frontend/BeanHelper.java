package sgr.app.frontend;

import javax.faces.context.FacesContext;

/**
 * BeanHelper used to get menagmentBean and gets components from xhtml file into java file for perform
 * operations on it.
 *
 * @author leonzio
 * @author dawbes89
 */
public final class BeanHelper
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

   /**
    * Gets management bean.
    *
    * @param panelName
    *           alias bean name
    * @param clazz
    *           bean class
    * @return management bean
    */
   public static <T> T findBean(String panelName, Class<T> clazz)
   {
      FacesContext context = FacesContext.getCurrentInstance();
      return context.getApplication().evaluateExpressionGet(context, panelName, clazz);
   }
}
