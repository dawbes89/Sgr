package sgr.app.webapp;

import sgr.app.api.account.AccountType;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.frontend.panels.MainApplicationPanel;

/**
 * Main application panel.
 *
 * @author leonzio
 */
public class MainPanel extends MainApplicationPanel
{

   private static final long serialVersionUID = -7214848698811381282L;

   public boolean showMenuFor(AccountType type)
   {
      return authenticationService.checkUserAccountType(type);
   }

   public boolean currentUserIsPreceptor()
   {
      if (!authenticationService.checkUserAccountType(AccountType.TEACHER))
      {
         return false;
      }
      final TeachingStaff preceptor = authenticationService.getCurrentUser();
      return preceptor.getPreceptorClass() != null;
   }

}
