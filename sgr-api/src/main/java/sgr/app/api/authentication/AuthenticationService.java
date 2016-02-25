package sgr.app.api.authentication;

import java.util.List;

import sgr.app.api.account.AccountType;
import sgr.app.api.exceptions.AuthenticationException;

/**
 * Service for authenticate users in application.
 *
 * @author leonzio
 */
public interface AuthenticationService
{

   String USER_ATTRIBUTE = "user";
   String ACCOUNT_TYPE = "accountType";
   String LOGIN_PAGE = "loginPanel";
   String MAIN_PAGE = "index";

   void authenticateUser(String userName, String password, List<AccountType> supportedAccounts)
         throws AuthenticationException;

   void logoutUser();

   <T> T getCurrentUser() throws NullPointerException;

   boolean checkUserAccountType(AccountType type);

   void createSuperAdmin();

}
