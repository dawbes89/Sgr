package sgr.app.api.login;

import java.util.MissingResourceException;
import java.util.Optional;

public interface LoginService
{
   public <T> Optional<T> checkLogin(String userName, String password)
         throws MissingResourceException;
}
