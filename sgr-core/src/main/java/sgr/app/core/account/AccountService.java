package sgr.app.core.account;

import java.util.Optional;

/**
 * @author dawbes
 */
public interface AccountService
{

	Account create(AccountCreateRequest request);

	Optional<Account> find(String login);

	<T> Optional<T> findUserByAccount(Account account);

	boolean updateAccountPassword(AccountUpdatePasswordRequest request);

}