package sgr.app.core.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.authentication.SessionService;
import sgr.app.core.DaoSupport;

/**
 * Implementation of {@link AuthenticationService} to authenticate users.
 *
 * @author leonzio
 */
class AuthenticationServiceImpl extends DaoSupport implements AuthenticationService
{

   private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

   private AccountService accountService;
   private SessionService sessionService;

   @Override
   public boolean authenticateUser(String userName, String password)
   {
      final Optional<Account> account = accountService.findAccountByLogin(userName);

      if (!account.isPresent())
      {
         return false;
      }

      if (!PASSWORD_ENCODER.matches(password, account.get().getPassword()))
      {
         return false;
      }

      Object user = accountService.findUserByAccount(account.get());
      try
      {
         sessionService.setAttributeValue(USER_ATTRIBUTE, user);
         return true;
      }
      catch (IllegalStateException e)
      {
         return false;
      }
   }

   @Override
   public boolean logoutUser()
   {
      try
      {
         sessionService.getSession().invalidate();
         return true;
      }
      catch (IllegalStateException e)
      {
         return false;
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public <T> T getCurrentLoggedUser()
   {
      return (T) sessionService.getAttributeValue(USER_ATTRIBUTE);
   }

   @Required
   public void setAccountService(AccountService accountService)
   {
      this.accountService = accountService;
   }

   @Required
   public void setSessionService(SessionService sessionService)
   {
      this.sessionService = sessionService;
   }

}
