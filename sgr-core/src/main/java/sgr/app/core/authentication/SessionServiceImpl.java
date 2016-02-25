package sgr.app.core.authentication;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sgr.app.api.authentication.SessionService;

/**
 * @author leonzio
 */
class SessionServiceImpl implements SessionService
{

   @Override
   public HttpSession getSession()
   {
      return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
   }

   @Override
   public HttpServletRequest getRequest()
   {
      return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
            .getRequest();
   }

   @SuppressWarnings("unchecked")
   @Override
   public <T> T getAttributeValue(String attributeKey) throws NullPointerException
   {
      if (getSession() == null)
      {
         throw new NullPointerException("Session is null");
      }
      return (T) getSession().getAttribute(attributeKey);
   }

   @Override
   public <T> void setAttributeValue(String name, T value)
   {
      getSession().setAttribute(name, value);
   }

}
