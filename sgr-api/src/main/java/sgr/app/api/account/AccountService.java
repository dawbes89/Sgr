package sgr.app.api.account;

import java.util.List;

/**
 * @author dawbes
 */
public interface AccountService
{
   public List<Account> search(AccountQuery query);

   public boolean checkLogin(String userName, String password);

}
