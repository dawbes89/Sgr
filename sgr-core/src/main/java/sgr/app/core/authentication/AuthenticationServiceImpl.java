package sgr.app.core.authentication;

import java.util.Optional;

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
   public boolean logoutUser()
   {
      try
      {
         SessionHelper.getSession().invalidate();
         return true;
      }
      catch (IllegalStateException e)
      {
         return false;
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public <T> Optional<T> getCurrentLoggedUser()
   {
      final Object user = SessionHelper.getSession().getAttribute("user");
      return Optional.ofNullable((T) user);
   }

}
