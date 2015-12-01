package sgr.app.api.login;

import sgr.app.api.account.Account;

public interface LoginService
{
   public Account checkLogin(String userName, String password);
}
