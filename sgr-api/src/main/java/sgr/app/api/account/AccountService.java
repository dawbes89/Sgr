package sgr.app.api.account;

import java.util.Optional;

/**
 * @author dawbes
 */
public interface AccountService
{

   public Optional<Account> findAccountByLogin(String login);

   public <T> Optional<T> findUserByAccount(Account account);

}
