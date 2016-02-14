package sgr.app.core.authentication;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.admin.Admin;
import sgr.app.api.admin.AdminService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.authentication.SessionService;
import sgr.app.api.person.Person;
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
   private AdminService adminService;

   @Override
   // TODO zmieniæ typ zwracany na void, dodaæ odpowiednie exceptiony z
   // wiadomoœciami
   public boolean authenticateUser(String userName, String password,
         List<AccountType> supportedAccounts)
   {
      final Optional<Account> account = accountService.findAccountByLogin(userName);

      if (!account.isPresent())
      {
         return false;
      }
      if (!supportedAccounts.contains(account.get().getType()))
      {
         return false;
      }

      if (!PASSWORD_ENCODER.matches(password, account.get().getPassword()))
      {
         return false;
      }

      Optional<Object> user = accountService.findUserByAccount(account.get());
      if (!user.isPresent())
      {
         return false;
      }
      try
      {
         sessionService.setAttributeValue(USER_ATTRIBUTE, user.get());
         return true;
      }
      catch (IllegalStateException e)
      {
         return false;
      }
   }

   @Override
   // TODO zmieniæ typ zwracany na void, dodaæ exceptiony
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

   @Override
   public void createSuperAdmin()
   {
      Optional<Account> account = accountService.findAccountByLogin("root");
      if (account.isPresent())
      {
         return;
      }
      Account superAccount = new Account();
      superAccount.setPassword("kopytko");
      superAccount.setType(AccountType.ADMIN);
      superAccount.setUserName("root");

      Person superPerson = new Person();
      superPerson.setFirstName("root");
      superPerson.setBirthDate(new Date());
      superPerson.setLastName("root");

      Admin superAdmin = new Admin();
      superAdmin.setAccount(superAccount);
      superAdmin.setPerson(superPerson);
      adminService.create(superAdmin);
   }

   // TODO zabezpieczyæ metodê przed brakiem sesji
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

   @Required
   public void setAdminService(AdminService adminService)
   {
      this.adminService = adminService;
   }

}
