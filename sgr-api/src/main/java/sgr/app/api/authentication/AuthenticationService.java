package sgr.app.api.authentication;

import java.util.List;

import sgr.app.api.account.AccountType;

/**
 * Service for authenticate users in application.
 *
 * @author leonzio
 */
public interface AuthenticationService
{

   String USER_ATTRIBUTE = "user";
   String LOGIN_PAGE = "loginPanel";
   String MAIN_PAGE = "index";

   boolean authenticateUser(String userName, String password, List<AccountType> a);

   boolean logoutUser();

   void createSuperAdmin();

   <T> T getCurrentLoggedUser();
}
