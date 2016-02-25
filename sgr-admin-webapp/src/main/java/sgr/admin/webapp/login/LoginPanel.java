package sgr.admin.webapp.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import sgr.app.api.account.AccountType;
import sgr.app.frontend.panels.AbstractLoginPanel;

/**
 * Panel used for login into application.
 *
 * @author dawbes89
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

   @Override
   protected List<AccountType> supportedAccountTypes()
   {
      final List<AccountType> list = new ArrayList<>();
      list.add(AccountType.ADMIN);
      return list;
   }
}
