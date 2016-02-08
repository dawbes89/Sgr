package sgr.app.api.authentication;

/**
 * Service for authenticate users in application.
 *
 * @author leonzio
 */
public interface AuthenticationService
{
   <T> boolean loginUser(T user);

   void logoutUser();

   <T> T getCurrentLoggedUser();
}
