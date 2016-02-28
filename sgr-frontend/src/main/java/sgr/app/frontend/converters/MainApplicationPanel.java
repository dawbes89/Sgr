package sgr.app.frontend.converters;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.person.PersonName;

/**
 * Abstract base for <code>MainPanel</code>s used in application.
 *
 * @author leonzio
 */
public abstract class MainApplicationPanel implements Serializable
{

   private static final long serialVersionUID = -4946956951529094970L;

   @Autowired
   protected AuthenticationService authenticationService;

   protected MainApplicationPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

   public final String getUserName()
   {
      final Object currentUser = authenticationService.getCurrentUser();
      return ((PersonName) currentUser).getFullName();
   }

}
