package sgr.app.core.authentication;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;

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
import sgr.app.api.exceptions.AuthenticationException;
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
   public void authenticateUser(String userName, String password,
         List<AccountType> supportedAccounts) throws AuthenticationException
   {
      final Optional<Account> foundAccount = accountService.findAccountByLogin(userName);

      if (!foundAccount.isPresent())
      {
         throw new AuthenticationException("authenticationException_invalidUserNameOrPassword",
               FacesMessage.SEVERITY_ERROR);
      }

      final Account account = foundAccount.get();

      if (!supportedAccounts.contains(account.getType()))
      {
         throw new AuthenticationException("authenticationException_lackOfPermission",
               FacesMessage.SEVERITY_ERROR);
      }

      if (!PASSWORD_ENCODER.matches(password, account.getPassword()))
      {
         throw new AuthenticationException("authenticationException_invalidUserNameOrPassword",
               FacesMessage.SEVERITY_ERROR);
      }

      final Date now = new Date();
      final Date validTo = account.getValidTo();
      if (validTo != null && now.after(validTo))
      {
         throw new AuthenticationException("authenticationException_accountIsBlocked",
               FacesMessage.SEVERITY_ERROR);
      }

      Optional<Object> user = accountService.findUserByAccount(account);
      if (!user.isPresent())
      {
         throw new AuthenticationException("authenticationException_invalidUserNameOrPassword",
               FacesMessage.SEVERITY_ERROR);
      }
      try
      {
         sessionService.setAttributeValue(USER_ATTRIBUTE, user.get());
         sessionService.setAttributeValue(ACCOUNT_TYPE, account.getType());
      }
      catch (IllegalStateException e)
      {}
   }

   @Override
   public void logoutUser()
   {
      try
      {
         sessionService.getSession().invalidate();
      }
      catch (IllegalStateException e)
      {}
   }

   // TODO zabezpieczyæ metodê przed brakiem sesji
   @SuppressWarnings("unchecked")
   @Override
   public <T> T getCurrentUser() throws NullPointerException
   {
      return (T) sessionService.getAttributeValue(USER_ATTRIBUTE);
   }

   @Override
   public boolean checkUserAccountType(AccountType type)
   {
      final AccountType accountType = sessionService.getAttributeValue(ACCOUNT_TYPE);
      return type.equals(accountType);
   }

   @Override
   public void createSuperAdmin()
   {
      final Optional<Account> account = accountService.findAccountByLogin("root");
      if (account.isPresent())
      {
         return;
      }

      final Account superAccount = new Account();
      superAccount.setPassword("kopytko");
      superAccount.setUserName("root");

      final Person superPerson = new Person();
      superPerson.setFirstName("root");
      superPerson.setLastName("root");
      superPerson.setBirthDate(new Date());

      final Admin superAdmin = new Admin();
      superAdmin.setAccount(superAccount);
      superAdmin.setPerson(superPerson);
      superAdmin.setSuperuser(true);
      adminService.create(superAdmin);
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
