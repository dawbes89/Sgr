package sgr.app.core.login;

import java.util.MissingResourceException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.login.LoginService;
import sgr.app.core.DaoSupport;

class LoginServiceImpl extends DaoSupport implements LoginService
{
   private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
   private AccountService accountService;

   @Override
   public <T> Optional<T> checkLogin(String userName, String password)
         throws MissingResourceException
   {

      Optional<Account> account = accountService.findAccountByLogin(userName);
      if (account.isPresent())
      {
         if (PASSWORD_ENCODER.matches(password, account.get().getPassword()))
         {
            return accountService.findUserByAccount(account.get());
         }
      }

      return Optional.empty();
   }

   @Required
   public void setAccountService(AccountService accountService)
   {
      this.accountService = accountService;
   }

}
