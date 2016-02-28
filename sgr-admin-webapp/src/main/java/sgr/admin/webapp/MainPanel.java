package sgr.admin.webapp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.admin.Admin;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.person.PersonName;

/**
 * Main application panel.
 *
 * @author leonzio
 *
 */
public class MainPanel implements Serializable
{

   private static final long serialVersionUID = -7214848698811381282L;

   @Autowired
   private AuthenticationService authenticationService;

   public MainPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

   public boolean isSuperuser()
   {
      final Admin currentUser = authenticationService.getCurrentUser();
      return currentUser.isSuperuser();
   }

   public String getUserName()
   {
      final Object currentUser = authenticationService.getCurrentUser();
      return ((PersonName) currentUser).getFullName();
   }

}
