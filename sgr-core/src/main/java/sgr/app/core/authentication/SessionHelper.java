package sgr.app.core.authentication;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Helper for session.
 *
 * @author leonzio
 */
class SessionHelper
{
   public static HttpSession getSession()
   {
      return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
   }

   public static HttpServletRequest getRequest()
   {
      return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
            .getRequest();
   }

}
