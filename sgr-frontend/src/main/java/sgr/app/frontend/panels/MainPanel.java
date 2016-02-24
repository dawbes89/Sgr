package sgr.app.frontend.panels;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.account.AccountType;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.person.PersonName;
import sgr.app.api.teachingStuff.TeachingStuff;

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

   public boolean showMenuFor(AccountType type)
   {
      return authenticationService.checkUserAccountType(type);
   }

   public boolean currentUserIsPreceptor()
   {
      final TeachingStuff preceptor = authenticationService.getCurrentUser();
      return preceptor.getPreceptorClass() != null;
   }

   public String getUserName()
   {
      final Object currentUser = authenticationService.getCurrentUser();
      return ((PersonName) currentUser).getFullName();
   }
}
