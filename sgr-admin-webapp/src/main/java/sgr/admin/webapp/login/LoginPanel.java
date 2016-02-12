package sgr.admin.webapp.login;

import org.springframework.stereotype.Controller;

import sgr.app.frontend.panels.AbstractLoginPanel;

/**
 * @author dawbes
 */
@Controller
public class LoginPanel extends AbstractLoginPanel
{
   private static final long serialVersionUID = -7242960918445825945L;

   @Override
   public void init()
   {
      super.init();
      authenticationService.createSuperAdmin();
   }

}
