package sgr.app.api.account;

import java.util.Optional;

/**
 * @author dawbes
 */
public interface AccountService
{

	Optional<Account> findAccountByLogin(String login);

	<T> Optional<T> findUserByAccount(Account account);

	Account createAccount(Account account);

	void updateAccountPassword(String login, String password);

}