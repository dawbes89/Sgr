package sgr.app.core.authentication;

import javax.servlet.http.HttpSession;

import sgr.app.api.authentication.AuthenticationService;

/**
 * Implementation of {@link AuthenticationService} to authenticate users.
 *
 * @author leonzio
 */
class AuthenticationServiceImpl implements AuthenticationService
{

   @Override
   public <T> boolean loginUser(T user)
   {
      HttpSession session = SessionHelper.getSession();
      session.setAttribute("user", user);
      return true;
   }

   @Override
   public void logoutUser()
   {
      SessionHelper.getSession().invalidate();
   }

   @SuppressWarnings("unchecked")
   @Override
   public <T> T getCurrentLoggedUser()
   {
      return (T) SessionHelper.getSession().getAttribute("user");
   }

}
