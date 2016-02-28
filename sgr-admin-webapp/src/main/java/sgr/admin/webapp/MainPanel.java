package sgr.admin.webapp;

import sgr.app.api.admin.Admin;
import sgr.app.frontend.converters.MainApplicationPanel;

/**
 * Main application panel.
 *
 * @author leonzio
 */
public class MainPanel extends MainApplicationPanel
{

   private static final long serialVersionUID = -7214848698811381282L;

   public boolean isSuperuser()
   {
      final Admin currentUser = authenticationService.getCurrentUser();
      return currentUser.isSuperuser();
   }

}
