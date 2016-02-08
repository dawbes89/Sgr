package sgr.app.api.authentication;

import java.util.Optional;

/**
 * Service for authenticate users in application.
 *
 * @author leonzio
 */
public interface AuthenticationService
{

   String USER_ATTRIBUTE = "user";
   String LOGIN_PAGE = "loginPanel.xhtml";
   String MAIN_PAGE = "index.xhtml";

   <T> boolean loginUser(T user);

   boolean logoutUser();

   <T> Optional<T> getCurrentLoggedUser();
}
