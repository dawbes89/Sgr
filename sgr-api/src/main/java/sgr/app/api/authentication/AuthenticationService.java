package sgr.app.api.authentication;

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

   boolean authenticateUser(String userName, String password);

   boolean logoutUser();

   <T> T getCurrentLoggedUser();
}
